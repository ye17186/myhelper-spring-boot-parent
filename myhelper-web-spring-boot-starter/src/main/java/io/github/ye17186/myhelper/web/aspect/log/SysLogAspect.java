package io.github.ye17186.myhelper.web.aspect.log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import io.github.ye17186.myhelper.core.utils.CollectionUtils;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.core.web.context.RequestContext;
import io.github.ye17186.myhelper.web.annotation.SysLogPoint;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 系统日志处理切面
 *
 * @author ye17186
 * @since 2022-09-30
 */
@Slf4j
@Aspect
public class SysLogAspect {

    @Around(value = "@annotation(point)")
    public Object around(ProceedingJoinPoint pPoint, SysLogPoint point) throws Throwable {

        LocalDateTime start = LocalDateTime.now();

        Model logModel = null;
        try {
            Object result = pPoint.proceed();
            logModel = buildLog(pPoint, point, start, result, null);
            return result;
        } catch (Throwable ex) {
            logModel = buildLog(pPoint, point, start, null, ex);
            throw ex;
        } finally {
            if (logModel != null) {
                logModel.setDuration(Duration.between(start, LocalDateTime.now()).toMillis());
            }
            saveLog(logModel);
        }
    }

    private void saveLog(Model sysLog) {

        // 本例中直接打印日志，生产环境中可采用异步的方式，保存到DB等媒介中
        log.info("【=== My-Helper ===】SysLog: {}", JsonUtils.obj2Json(sysLog));
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
    private Model buildLog(JoinPoint jp, SysLogPoint annotation, LocalDateTime start, Object result, Throwable ex) {

        Model sysLog = new Model();
        sysLog.setRequestId(RequestContext.requestId());
        sysLog.setAction(annotation.action());
        sysLog.setTarget(annotation.target().name());
        sysLog.setClassName(jp.getTarget().getClass().getName());
        sysLog.setMethod(jp.getSignature().getName());
        sysLog.setStatus(ex == null ? "SUCCESS" : "FAIL");
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
    private Object handleInput(Object[] args, List<String> sensitiveParams, boolean ignore) {

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
    private Object handleOutput(Object result, boolean ignore) {

        return (ignore || result == null) ? null : result;
    }


    /**
     * 处理异常信息
     *
     * @param ex 异常对象
     * @return 处理后的异常信息
     */
    private String handleException(Throwable ex) {
        return ex == null ? null : ex.toString();
    }

    /**
     * 处理敏感参数
     *
     * @param node      jackson节点
     * @param sensitive 敏感参数名列表
     */
    private void handleSensitiveParams(JsonNode node, List<String> sensitive) {

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


    @Setter
    @Getter
    private static class Model implements Serializable {

        private static final long serialVersionUID = -4984030010415677569L;

        /**
         * 请求唯一标识
         */
        private String requestId;

        /**
         * 操作名
         */
        private String action;

        /**
         * 目标
         */
        private String target;

        /**
         * 类名
         */
        private String className;

        /**
         * 方法名
         */
        private String method;

        /**
         * 入参
         */
        private Object input;

        /**
         * 返回结果
         */
        private Object output;

        /**
         * 返回类型（SUCCESS=成功；FAIL=失败）
         */
        private String status;

        /**
         * 异常信息
         */
        private String exMsg;

        /**
         * 进入时间
         */
        private LocalDateTime startTime;

        /**
         * 耗时，单位毫秒
         */
        private Long duration;
    }
}
