package com.ye186.thirdparty.myhelper.knife4j.converter;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.Schema;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 枚举类属性插件
 *
 * @author ye17186
 * @date 2022-11-05
 */
@Slf4j
public class EnumSchemaConverter implements ModelConverter {

    private static final String FIELD_CODE = "code";
    private static final String FIELD_DESC = "desc";
    private static final String CONCAT_STR = " - ";

    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {

        if (chain.hasNext()) {
            Schema resolvedSchema = chain.next().resolve(type, context, chain);
            Class<?> enumClz;
            if (type.getType() instanceof Class) {
                enumClz = (Class<?>) type.getType();
                if (enumClz.isEnum()) {
                    List<String> enumsValues = getEnumValues(enumClz);
                    resolvedSchema.setEnum(enumsValues);
                }
            }

            return resolvedSchema;
        } else {
            return null;
        }
    }


    /**
     * 获取枚举值
     *
     * @param clz 枚举类
     */
    private List<String> getEnumValues(Class<?> clz) {

        List<String> values = new ArrayList<String>();
        for (Object obj : clz.getEnumConstants()) {
            try {
                Field codeField = clz.getDeclaredField(FIELD_CODE);
                Field descField = clz.getDeclaredField(FIELD_DESC);
                codeField.setAccessible(true);
                descField.setAccessible(true);
                Object codeValue = codeField.get(obj);
                Object descValue = descField.get(obj);
                values.add(codeValue + CONCAT_STR + descValue);
            } catch (Exception e) {
                log.info("[knife4j]枚举Schema解析异常", e);
            }
        }
        return values;
    }
}
