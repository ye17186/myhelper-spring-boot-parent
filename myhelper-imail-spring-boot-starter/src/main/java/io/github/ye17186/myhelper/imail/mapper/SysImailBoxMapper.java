package io.github.ye17186.myhelper.imail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.ye17186.myhelper.imail.entity.SysImailBoxEntity;
import io.github.ye17186.myhelper.imail.model.ImailBasicResponse;
import io.github.ye17186.myhelper.imail.model.ImailCountResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SysImailBoxMapper extends BaseMapper<SysImailBoxEntity> {
    void insertBatch(List<SysImailBoxEntity> list);

    List<ImailCountResponse.Item> selectCount4UnRead(@Param("loginKey") String loginKey, @Param("beginTime") LocalDateTime beginTime);

    IPage<ImailBasicResponse> selectImailPage(@Param("page") IPage<ImailBasicResponse> page,
                                              @Param("loginKey") String loginKey,
                                              @Param("classify") String classify,
                                              @Param("readStatus") String readStatus);
}
