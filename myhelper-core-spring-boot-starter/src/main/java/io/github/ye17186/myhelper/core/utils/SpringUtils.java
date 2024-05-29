package io.github.ye17186.myhelper.core.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;

/**
 * @author ye17186
 * @since 2022-09-22
 */
@Slf4j
public class SpringUtils {

    @Getter
    private static ApplicationContext context = null;

    private SpringUtils() {
    }

    public static void initContext(ApplicationContext ctx) {

        context = ctx;
    }

    public static <T> T getBean(Class<T> cls) {

        return Optional.ofNullable(context).map(item -> item.getBean(cls)).orElse(null);
    }

    public static Object getBean(String name) {

        return Optional.ofNullable(context).map(item -> item.getBean(name)).orElse(null);
    }

    public static <T> T getBean(String name, Class<T> cls) {

        return Optional.ofNullable(context).map(item -> item.getBean(name, cls)).orElse(null);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> cls) {

        return Optional.ofNullable(context).map(item -> item.getBeansWithAnnotation(cls)).orElse(null);
    }

    public static Environment getEnv() {

        return Optional.ofNullable(context).map(ApplicationContext::getEnvironment).orElse(null);
    }

    public static String getProperty(String key) {

        return Optional.ofNullable(getEnv()).map(item -> item.getProperty(key)).orElse(null);
    }

    public static <T> T getProperty(String key, Class<T> targetType) {

        return Optional.ofNullable(getEnv()).map(item -> item.getProperty(key, targetType)).orElse(null);
    }
}
