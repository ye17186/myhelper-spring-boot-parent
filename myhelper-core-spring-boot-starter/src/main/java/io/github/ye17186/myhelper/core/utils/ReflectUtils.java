package io.github.ye17186.myhelper.core.utils;

import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 反射工具类
 *
 * @author ye17186
 * @date 2023/11/27
 */
public class ReflectUtils extends ReflectionUtils {

    /**
     * 获取对象指定属性的值
     *
     * @param target    目标对象
     * @param fieldName 属性名
     * @return 属性值
     */
    @Nullable
    public static Object getFieldValue(Object target, String fieldName) {

        Object value = null;

        if (target != null && StringUtils.isNotEmpty(fieldName)) {
            Field field = getField(target.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    value = field.get(target);
                } catch (Exception e) {
                    throw new BizException(ErrorCode.SYSTEM_ERROR, e);
                }
            }
        }
        return value;
    }

    @Nullable
    public static Field getField(Class<?> clazz, String fieldName) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(item -> item.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }
}
