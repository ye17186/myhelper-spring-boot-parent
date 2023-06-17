package io.github.ye17186.myhelper.imail.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.ye17186.myhelper.core.utils.CollectionUtils;
import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.core.web.context.user.MhUserContext;
import io.github.ye17186.myhelper.core.web.response.PageResponse;
import io.github.ye17186.myhelper.imail.entity.SysImailBoxEntity;
import io.github.ye17186.myhelper.imail.entity.SysImailEntity;
import io.github.ye17186.myhelper.imail.entity.SysImailGroupEntity;
import io.github.ye17186.myhelper.imail.mapper.SysImailBoxMapper;
import io.github.ye17186.myhelper.imail.mapper.SysImailGroupMapper;
import io.github.ye17186.myhelper.imail.mapper.SysImailMapper;
import io.github.ye17186.myhelper.imail.model.*;
import io.github.ye17186.myhelper.mybatis.utils.PageUtils;
import io.github.ye17186.myhelper.mybatis.utils.WrapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MhSysImailService {

    @Autowired
    SysImailMapper mapper;

    @Autowired
    SysImailGroupMapper groupMapper;

    @Autowired
    SysImailBoxMapper boxMapper;

    /**
     * 发送站内信
     *
     * @param imail 站内信
     * @param group 发送对象
     */
    @Transactional
    public void send(Imail imail, SendGroup group) {

        // 插入站内信主体
        Long imailId = insertImail(imail);
        // 插入收件组
        insertToGroup(imailId, group);
        if (GroupType.SPECIFIC.equals(group.getGroupType()) && CollectionUtils.isNotEmpty(group.getGroupKeys())) {
            // 发送时指定用户时，直接插入收件箱
            insertImailBox(imailId, group.getGroupKeys());
        }
    }

    private void insertImailBox(Long imailId, List<String> groupKeys) {

        List<SysImailBoxEntity> list = groupKeys.stream().map(item -> {
            SysImailBoxEntity entity = new SysImailBoxEntity();
            entity.setImailId(imailId);
            entity.setLoginKey(item);
            entity.setReadStatus(ImailReadStatusEnum.N.getCode());
            WrapperUtils.preInsert(entity);
            return entity;
        }).collect(Collectors.toList());

        boxMapper.insertBatch(list);

    }

    private void insertToGroup(Long imailId, SendGroup sendGroup) {

        SysImailGroupEntity entity = new SysImailGroupEntity();
        entity.setImailId(imailId);
        entity.setGroupType(sendGroup.getGroupType().getCode());
        if (!GroupType.SPECIFIC.equals(sendGroup.getGroupType())) {
            entity.setGroupKey(StringUtils.join(sendGroup.getGroupKeys(), ","));
        }
        WrapperUtils.preInsert(entity);
        groupMapper.insert(entity);
    }

    private Long insertImail(Imail imail) {

        SysImailEntity entity = new SysImailEntity();
        entity.setClassify(imail.getClassify().getCode());
        entity.setTitle(imail.getTitle());
        entity.setDescription(imail.getDescription());
        entity.setContent(imail.getContent());
        entity.setLink(imail.getLink());
        WrapperUtils.preInsert(entity);
        mapper.insert(entity);
        return entity.getId();
    }

    /**
     * 获取用户未读数（通知、消息）
     *
     * @param loginKey 用户唯一标识
     */
    public ImailCountResponse countUnRead(String loginKey) {

        LocalDate beginDate = LocalDate.now().minusMonths(3); // 3个月前的站内信，不再关注
        List<ImailCountResponse.Item> unreadItems = boxMapper.selectCount4UnRead(loginKey, LocalDateTime.of(beginDate, LocalTime.MIN));
        Integer total = unreadItems.stream().mapToInt(ImailCountResponse.Item::getCount).sum();
        ImailCountResponse response = new ImailCountResponse();
        response.setItems(unreadItems);
        response.setTotalCount(total);
        return response;
    }

    /**
     * 阅读站内信
     *
     * @param loginKey 用户唯一标识
     * @param imailId  站内信ID
     */
    @Transactional
    public void read(String loginKey, Long imailId) {

        LambdaQueryWrapper<SysImailBoxEntity> queryWrapper = WrapperUtils.query();
        queryWrapper.eq(SysImailBoxEntity::getImailId, imailId)
                .eq(SysImailBoxEntity::getLoginKey, loginKey);
        boolean exists = boxMapper.exists(queryWrapper);
        if (exists) {
            LambdaUpdateWrapper<SysImailBoxEntity> wrapper = WrapperUtils.update();
            wrapper.set(SysImailBoxEntity::getReadStatus, ImailReadStatusEnum.Y.getCode())
                    .set(SysImailBoxEntity::getReadTime, LocalDateTime.now())
                    .eq(SysImailBoxEntity::getImailId, imailId)
                    .eq(SysImailBoxEntity::getLoginKey, loginKey);
            WrapperUtils.preUpdate(wrapper);
            boxMapper.update(null, wrapper);

        } else {
            SysImailBoxEntity entity = new SysImailBoxEntity();
            entity.setImailId(imailId);
            entity.setLoginKey(loginKey);
            entity.setReadStatus(ImailReadStatusEnum.Y.getCode());
            entity.setReadTime(LocalDateTime.now());
            WrapperUtils.preInsert(entity);
            boxMapper.insert(entity);
        }
    }

    @Transactional
    public void delete(String loginKey, Long imailId) {

        LambdaQueryWrapper<SysImailBoxEntity> queryWrapper = WrapperUtils.query();
        queryWrapper.eq(SysImailBoxEntity::getImailId, imailId)
                .eq(SysImailBoxEntity::getLoginKey, loginKey);
        boolean exists = boxMapper.exists(queryWrapper);
        if (exists) {
            LambdaUpdateWrapper<SysImailBoxEntity> wrapper = WrapperUtils.update();
            wrapper.set(SysImailBoxEntity::getReadStatus, ImailReadStatusEnum.Y.getCode())
                    .set(SysImailBoxEntity::getReadTime, LocalDateTime.now())
                    .set(SysImailBoxEntity::getBoxDeleted, Boolean.TRUE)
                    .set(SysImailBoxEntity::getBoxDeletedTime, LocalDateTime.now())
                    .eq(SysImailBoxEntity::getImailId, imailId)
                    .eq(SysImailBoxEntity::getLoginKey, loginKey);
            WrapperUtils.preUpdate(wrapper);
            boxMapper.update(null, wrapper);

        } else {
            SysImailBoxEntity entity = new SysImailBoxEntity();
            entity.setImailId(imailId);
            entity.setLoginKey(loginKey);
            entity.setReadStatus(ImailReadStatusEnum.Y.getCode());
            entity.setReadTime(LocalDateTime.now());
            entity.setBoxDeleted(Boolean.TRUE);
            entity.setBoxDeletedTime(LocalDateTime.now());
            WrapperUtils.preInsert(entity);
            boxMapper.insert(entity);
        }
    }

    /**
     * 站内信详情
     *
     * @param loginKey 用户唯一标识
     * @param imailId  站内信ID
     */
    @Nullable
    public ImailDetailResponse detail(String loginKey, Long imailId) {

        LambdaQueryWrapper<SysImailBoxEntity> queryWrapper = WrapperUtils.query();
        queryWrapper.eq(SysImailBoxEntity::getImailId, imailId)
                .eq(SysImailBoxEntity::getLoginKey, loginKey);
        boolean exists = boxMapper.exists(queryWrapper);

        ImailDetailResponse response = null;
        if (exists) {
            SysImailEntity entity = mapper.selectById(imailId);
            if (entity != null) {
                response = new ImailDetailResponse();
                response.setImailId(entity.getId());
                response.setTitle(entity.getTitle());
                response.setDescription(entity.getDescription());
                response.setContent(entity.getContent());
                response.setLink(entity.getLink());
            }
        }
        return response;
    }

    public PageResponse<ImailBasicResponse> queryPage(String loginKey, String classify, ImailReadStatusEnum readStatus, int pageNo, int pageSize) {

        IPage<ImailBasicResponse> page = new Page<>(pageNo, pageSize);
        IPage<ImailBasicResponse> result = boxMapper.selectImailPage(page, loginKey, classify, readStatus == null ? null : readStatus.getCode());
        return PageUtils.toPage(result);
    }
}
