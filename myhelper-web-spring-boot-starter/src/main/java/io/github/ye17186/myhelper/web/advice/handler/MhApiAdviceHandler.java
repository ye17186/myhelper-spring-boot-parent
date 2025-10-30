package io.github.ye17186.myhelper.web.advice.handler;

import io.github.ye17186.myhelper.web.advice.model.SysLogModel;
import io.github.ye17186.myhelper.web.advice.service.SysLogService;
import io.github.ye17186.myhelper.web.advice.utils.SysLogUtils;
import io.github.ye17186.myhelper.web.annotation.SysLogPoint;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebApiAdviceProperties;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author ye17186
 * @date 2024/5/29
 */
@Slf4j
public class MhApiAdviceHandler implements MethodInterceptor {

    private final MhWebApiAdviceProperties properties;
    private final SysLogService logService;

    public MhApiAdviceHandler(MhWebApiAdviceProperties properties, SysLogService logService) {

        this.properties = properties;
        this.logService = logService;
    }

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {

        LocalDateTime start = LocalDateTime.now();


        SysLogPoint point = getLogPoint(invocation);
        SysLogModel logModel = null;
        try {
            log.trace("[MyHelper - API] API增强。 API业务逻辑处理开始.");
            Object apiResponse = invocation.proceed();
            if (properties.isLogEnabled() && point != null) {
                logModel = SysLogUtils.buildLog(invocation, start, apiResponse, null);
            }
            return apiResponse;
        } catch (Throwable e) {
            if (properties.isLogEnabled() && point != null) {
                logModel = SysLogUtils.buildLog(invocation, start, null, e);
            }
            log.trace("[MyHelper - API] API增强。 API业务逻辑处理异常. 异常信息: {}", e.getMessage(), e);
            throw e;
        } finally {
            if (logModel != null) {
                logModel.setDuration(Duration.between(start, LocalDateTime.now()).toMillis());
                if (logService != null) {
                    logService.handle(invocation.getMethod().getAnnotation(SysLogPoint.class), logModel);
                }
            }
            log.trace("[MyHelper - API] API增强。 API业务逻辑处理完成.");
        }
    }

    private SysLogPoint getLogPoint(MethodInvocation invocation) {

        return invocation.getMethod().getAnnotation(SysLogPoint.class);
    }
}
