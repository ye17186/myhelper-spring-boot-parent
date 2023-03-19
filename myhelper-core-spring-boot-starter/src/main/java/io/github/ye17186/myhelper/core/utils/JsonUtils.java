package io.github.ye17186.myhelper.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ye17186.myhelper.core.jackson.ObjectMappers;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ye17186
 * @since 2022-09-22
 */
@Slf4j
public class JsonUtils {
    private JsonUtils() {
    }

    private static ObjectMapper om;

    /**
     * 对象 => json字符串
     *
     * @param obj 源对象
     * @return
     */
    public static <T> String obj2Json(T obj) {

        String json = null;
        if (obj != null) {
            try {
                json = objectMapper().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.warn(e.getMessage(), e);
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return json;
    }

    /**
     * json字符串 => 对象
     *
     * @param json  源json串
     * @param clazz 对象类
     * @param <T>   泛型
     * @return
     */
    public static <T> T json2Obj(String json, Class<T> clazz) {

        return json2Obj(json, clazz, null, null);
    }

    /**
     * json字符串 => 对象
     *
     * @param json 源json串
     * @param type 对象类型
     * @param <T>  泛型
     * @return
     */
    public static <T> T json2Obj(String json, TypeReference<T> type) {

        return json2Obj(json, null, type, null);
    }

    /**
     * json字符串 => List
     *
     * @param json 源json串
     * @param <T>  元素泛型
     * @return
     */
    public static <T> List<T> json2List(String json, Class<T> itemClz) {

        return json2Obj(json, null, null, buildCollectionType(List.class, itemClz));
    }

    /**
     * json字符串 => Map
     *
     * @param json 源json串
     * @param <V>  元素的value泛型
     * @return
     */
    public static <V> Map<String, V> json2Map(String json, Class<V> valueClz) {

        // json字符串的key，只能是String类型
        return json2Obj(json, null, null, buildCollectionType(Map.class, String.class, valueClz));
    }

    /**
     * json => 对象处理方法
     * <br>
     * 参数clazz和type必须一个为null，另一个不为null
     * <br>
     * 此方法不对外暴露，访问权限为private
     *
     * @param json  源json串
     * @param clazz 对象类
     * @param type  对象类型
     * @param <T>   泛型
     * @return
     */
    private static <T> T json2Obj(String json, Class<T> clazz, TypeReference<T> type, JavaType javaType) {

        T obj = null;
        if (!StringUtils.isEmpty(json)) {
            try {
                if (clazz != null) {
                    obj = objectMapper().readValue(json, clazz);
                } else if (type != null) {
                    obj = objectMapper().readValue(json, type);
                } else {
                    obj = objectMapper().readValue(json, javaType);
                }
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return obj;
    }

    /**
     * 构建集合泛型
     *
     * @param collectionClz 集合类型（List、Map）
     * @param elementClz    集合中元素类型
     * @return
     */
    private static JavaType buildCollectionType(Class<?> collectionClz, Class<?>... elementClz) {

        return objectMapper().getTypeFactory().constructParametricType(collectionClz, elementClz);
    }

    private static ObjectMapper objectMapper() {

        if (om == null) {
            om = SpringUtils.getBean(ObjectMapper.class);
            if (om == null) {
                om = ObjectMappers.builder().build();
            }
        }
        return om;
    }
}
