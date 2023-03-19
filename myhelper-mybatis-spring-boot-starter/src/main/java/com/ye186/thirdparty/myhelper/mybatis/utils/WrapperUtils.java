package com.ye186.thirdparty.myhelper.mybatis.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ye186.thirdparty.myhelper.mybatis.entity.BaseEntity;

/**
 * @author ye17186
 * @date 2022-09-29
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
}
