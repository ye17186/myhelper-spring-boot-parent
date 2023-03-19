package com.ye186.thirdparty.myhelper.core.utils;

import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MapAccessor;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * Spel工具类
 *
 * @author ye17186
 * @date 2022-06-30
 */
public class SpelUtils {

    private SpelUtils() {
    }

    private static final Map<String, Expression> cache = Maps.newConcurrentMap();
    private static final SpelExpressionParser parser = new SpelExpressionParser();
    private static final TemplateParserContext parseContext = new TemplateParserContext();
    private final static ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

    /**
     * 根据el，设置根对象中的变量值
     *
     * @param el      el表达式
     * @param rootObj 根对象
     * @param value   待设置的值
     */
    public static void setValue(String el, @Nullable Object rootObj, @Nullable Object value) {

        Expression expression = getExpression(el, Boolean.FALSE);
        setValue(expression, buildContext(rootObj), value);
    }

    /**
     * 获取EL模板的值
     *
     * @param el      EL模板
     * @param rootObj 根对象
     */
    public static String getTemplateValue(String el, Object rootObj) {

        Expression expression = getExpression(el, Boolean.TRUE);
        EvaluationContext context = buildContext(rootObj);
        return getValue(expression, context, String.class);
    }

    /**
     * 获取EL表达式的值
     *
     * @param el      el表达式
     * @param rootObj 根对象
     */
    public static Object getValue(String el, Object rootObj) {

        Expression expression = getExpression(el, Boolean.FALSE);
        EvaluationContext context;
        if (rootObj instanceof EvaluationContext) {
            context = (EvaluationContext) rootObj;
        } else {
            context = buildContext(rootObj);
        }
        return getValue(expression, context);
    }

    /**
     * 获取EL表达式的值
     *
     * @param el      el表达式
     * @param rootObj 根对象
     * @param clz     值类型
     */
    public static <T> T getValue(String el, Object rootObj, Class<T> clz) {

        Expression expression = getExpression(el, Boolean.FALSE);
        EvaluationContext context;
        if (rootObj instanceof EvaluationContext) {
            context = (EvaluationContext) rootObj;
        } else {
            context = buildContext(rootObj);
        }

        return getValue(expression, context, clz);
    }

    /**
     * 根据EL表达式，获取切面对象的值
     *
     * @param el        el表达式
     * @param joinPoint 切面连接点
     */
    public static Object getValue(String el, @NonNull JoinPoint joinPoint) {

        EvaluationContext context = buildContext(joinPoint);
        return getValue(el, context);
    }

    /**
     * 根据EL表达式，获取切面对象的值
     *
     * @param el        el表达式
     * @param joinPoint 切面连接点
     * @param clz       值类型
     */
    public static <T> T getValue(String el, @NonNull JoinPoint joinPoint, Class<T> clz) {

        EvaluationContext context = buildContext(joinPoint);
        return getValue(el, context, clz);
    }

    ////////////////////////////////////////////////////////////////////////

    private static void setValue(Expression expression, EvaluationContext context, @Nullable Object value) {

        expression.setValue(context, value);
    }

    private static Object getValue(Expression expression, EvaluationContext context) {

        return expression.getValue(context);
    }

    private static <T> T getValue(Expression expression, EvaluationContext context, Class<T> clz) {

        return expression.getValue(context, clz);
    }

    private static EvaluationContext buildContext(@Nullable Object rootObject) {

        StandardEvaluationContext context = new StandardEvaluationContext(rootObject);
        context.addPropertyAccessor(new MapAccessor());
        return context;
    }

    private static EvaluationContext buildContext(@NonNull JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        MethodBasedEvaluationContext context = new MethodBasedEvaluationContext(null, signature.getMethod(),
                joinPoint.getArgs(), discoverer);
        context.addPropertyAccessor(new MapAccessor());
        return context;
    }

    private static Expression getExpression(String el, boolean isTemplate) {

        String key = cacheKey(el, isTemplate);
        ParserContext parserContext = isTemplate ? parseContext : null;
        Expression expression = cache.get(key);
        if (expression != null) {
            return expression;
        }
        return cache.computeIfAbsent(key, v -> parser.parseExpression(el, parserContext));
    }

    /**
     * 本地Expression缓存的key
     *
     * @param el         el表达式
     * @param isTemplate 是否为模板
     */
    private static String cacheKey(String el, boolean isTemplate) {

        return isTemplate + el;
    }
}
