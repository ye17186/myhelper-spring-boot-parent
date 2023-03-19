package io.github.ye17186.myhelper.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author ye17186
 * @since 2022-09-22
 */
@Slf4j
public class SpringUtils {

    private static ApplicationContext context = null;

    private SpringUtils() {
    }

    public static void initContext(ApplicationContext ctx) {

        context = ctx;
    }

    public static <T> T getBean(Class<T> cls) {

        return context == null ? null : context.getBean(cls);
    }

    public static Object getBean(String name) {

        return context.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> cls) {

        return context.getBean(name, cls);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> cls) {

        return context.getBeansWithAnnotation(cls);
    }

    @Nullable
    public static ApplicationContext getContext() {
        return context;
    }

    @Nullable
    public static Environment getEnv() {
        return context == null ? null : context.getEnvironment();
    }
}
