package io.github.ye17186.myhelper.core.utils;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * @author ye17186
 * @since 2022-10-15
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    /**
     * 集合不为空
     *
     * @param collection 集合
     * @return
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {

        return !isEmpty(collection);
    }

    /**
     * map不为空
     *
     * @param map 集合
     * @return
     */
    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {

        return !isEmpty(map);
    }
}
