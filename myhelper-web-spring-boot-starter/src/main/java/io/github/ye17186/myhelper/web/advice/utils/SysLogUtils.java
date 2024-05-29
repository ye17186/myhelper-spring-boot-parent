package io.github.ye17186.myhelper.web.advice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import io.github.ye17186.myhelper.core.utils.CollectionUtils;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.core.web.context.RequestContext;
import io.github.ye17186.myhelper.web.advice.model.SysLogModel;
import io.github.ye17186.myhelper.web.annotation.SysLogPoint;
import io.github.ye17186.myhelper.web.aspect.log.LogTarget;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author ye17186
 * @date 2024/5/29
 */
public class SysLogUtils {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    /**
     * 构建日志对象
     *
     * @param invocation API方法
     * @param result     处理结果对象
     * @param ex         处理异常对象
     * @return 日志日志对象
     */
    @NonNull
    public static SysLogModel buildLog(MethodInvocation invocation, LocalDateTime start, Object result, Throwable ex) {

        SysLogPoint point = invocation.getMethod().getAnnotation(SysLogPoint.class);

        SysLogModel sysLog = new SysLogModel();
        sysLog.setRequestId(RequestContext.requestId());
        sysLog.setAction(point.action());
        sysLog.setTarget(LogTarget.CONTROLLER.name());
        sysLog.setClassName(invocation.getThis().getClass().getName());
        sysLog.setMethod(invocation.getMethod().getName());
        sysLog.setStatus(ex == null ? SUCCESS : FAIL);
        sysLog.setInput(handleInput(invocation.getArguments(), Arrays.asList(point.sensitiveParams()), point.ignoreInput()));
        sysLog.setOutput(handleOutput(result, point.ignoreOutput()));
        sysLog.setExMsg(handleException(ex));
        sysLog.setStartTime(start);
        return sysLog;
    }

    /**
     * 构建日志对象
     *
     * @param jp         连接点
     * @param annotation 注解
     * @param result     处理结果对象
     * @param ex         处理异常对象
     * @return 日志日志对象
     */
    @NonNull
    public static SysLogModel buildLog(JoinPoint jp, SysLogPoint annotation, LocalDateTime start, Object result, Throwable ex) {

        SysLogModel sysLog = new SysLogModel();
        sysLog.setRequestId(RequestContext.requestId());
        sysLog.setAction(annotation.action());
        sysLog.setTarget(annotation.target().name());
        sysLog.setClassName(jp.getTarget().getClass().getName());
        sysLog.setMethod(jp.getSignature().getName());
        sysLog.setStatus(ex == null ? SUCCESS : FAIL);
        sysLog.setInput(handleInput(jp.getArgs(), Arrays.asList(annotation.sensitiveParams()), annotation.ignoreInput()));
        sysLog.setOutput(handleOutput(result, annotation.ignoreOutput()));
        sysLog.setExMsg(handleException(ex));
        sysLog.setStartTime(start);
        return sysLog;
    }

    /**
     * 处理输入参数
     *
     * @param args            入参
     * @param sensitiveParams 敏感参数关键字
     * @return 特殊处理都的入参
     */
    private static Object handleInput(Object[] args, List<String> sensitiveParams, boolean ignore) {

        if (ignore) {
            return null;
        }
        JsonNode source = JsonUtils.json2Obj(JsonUtils.obj2Json(args), JsonNode.class);
        if (CollectionUtils.isNotEmpty(sensitiveParams)) {
            handleSensitiveParams(source, sensitiveParams);
        }

        return source;
    }

    /**
     * 处理输出结果
     *
     * @param result 源输出结果
     * @param ignore 是否忽略结果
     * @return 处理后的输出结果
     */
    private static Object handleOutput(Object result, boolean ignore) {

        return (ignore || result == null) ? null : result;
    }

    /**
     * 处理异常信息
     *
     * @param ex 异常对象
     * @return 处理后的异常信息
     */
    private static String handleException(Throwable ex) {
        return ex == null ? null : ex.toString();
    }


    /**
     * 处理敏感参数
     *
     * @param node      jackson节点
     * @param sensitive 敏感参数名列表
     */
    private static void handleSensitiveParams(JsonNode node, List<String> sensitive) {

        if (node.isObject() || node.isArray()) {
            if (node.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
                while (iterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = iterator.next();
                    if (sensitive.contains(entry.getKey())) {
                        entry.setValue(TextNode.valueOf("*****"));
                    }
                }
            }
            node.forEach(item -> handleSensitiveParams(item, sensitive));
        }
    }
}
