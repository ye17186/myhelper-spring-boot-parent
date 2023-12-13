package io.github.ye17186.myhelper.core.utils;

import com.google.common.collect.Lists;
import io.github.ye17186.myhelper.core.web.env.AppEnvEnum;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 枚举工具类
 *
 * @author ye17186ß
 * @date 2023/11/27
 */
public class EnumUtils {

    public static <E extends Enum<E>> E getBy(Class<E> enumClass, Predicate<? super E> predicate) {

        return Arrays.stream(enumClass.getEnumConstants()).filter(predicate).findFirst().orElse(null);
    }

    public static <E extends Enum<E>> List<String> getNames(Class<E> clazz) {

        final Enum<?>[] enums = clazz.getEnumConstants();
        if (enums == null) {
            return null;
        }
        final List<String> list = Lists.newArrayListWithExpectedSize(enums.length);
        for (Enum<?> e : enums) {
            list.add(e.name());
        }
        return list;
    }

    public static <E extends Enum<E>> List<Object> getFieldValues(Class<E> clazz, String fieldName) {

        final Enum<?>[] enums = clazz.getEnumConstants();
        if (enums == null) {
            return null;
        }
        final List<Object> list = Lists.newArrayListWithExpectedSize(enums.length);
        for (Enum<?> e : enums) {
            list.add(ReflectUtils.getFieldValue(e, fieldName));
        }
        return list;
    }

    public static void main(String[] args) {

        AppEnvEnum a = getBy(AppEnvEnum.class, one -> one.getCode().equals("dev"));
        System.out.println(a.toString());
        System.out.println(getFieldValues(AppEnvEnum.class, "desc"));
        System.out.println(ReflectUtils.getFieldValue(a, "desc"));
    }
}
