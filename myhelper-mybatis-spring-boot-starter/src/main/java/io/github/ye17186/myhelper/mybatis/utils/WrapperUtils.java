package io.github.ye17186.myhelper.mybatis.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.ye17186.myhelper.core.web.context.user.MhUserContext;
import io.github.ye17186.myhelper.mybatis.entity.BaseEntity;

/**
 * @author ye17186
 * @since 2022-09-29
 */
public class WrapperUtils {

    public static <T extends BaseEntity> LambdaUpdateWrapper<T> update() {

        return Wrappers.lambdaUpdate();
    }

    public static <T> LambdaQueryWrapper<T> query() {

        return Wrappers.lambdaQuery();
    }

    public static <T> LambdaQueryWrapper<T> queryOne() {

        LambdaQueryWrapper<T> wrapper = Wrappers.lambdaQuery();
        wrapper.last("limit 1");
        return wrapper;
    }

    public static void preInsert(BaseEntity entity) {

        entity.setCreated(MhUserContext.mhLoginKey());
        entity.setModified(entity.getCreated());
    }

    public static void preUpdate(BaseEntity entity) {

        entity.setModified(MhUserContext.mhLoginKey());
    }

    public static <T extends BaseEntity> void preUpdate(LambdaUpdateWrapper<T> wrapper) {

        wrapper.set(BaseEntity::getModified, MhUserContext.mhLoginKey());
    }
}
