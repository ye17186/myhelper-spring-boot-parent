package io.github.ye17186.myhelper.web.aspect.log;

import io.github.ye17186.myhelper.web.advice.model.SysLogModel;
import io.github.ye17186.myhelper.web.advice.service.SysLogService;
import io.github.ye17186.myhelper.web.advice.utils.SysLogUtils;
import io.github.ye17186.myhelper.web.annotation.SysLogPoint;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebApiAdviceProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 系统日志处理切面
 *
 * @author ye17186
 * @since 2022-09-30
 */
@Slf4j
@Aspect
public class SysLogAspect {

    private final MhWebApiAdviceProperties properties;
    private final SysLogService logService;

    public SysLogAspect(MhWebApiAdviceProperties properties, SysLogService logService) {

        this.properties = properties;
        this.logService = logService;
    }

    @Around(value = "@annotation(point)")
    public Object around(ProceedingJoinPoint pPoint, SysLogPoint point) throws Throwable {

        if (LogTarget.CONTROLLER.equals(point.target()) || !properties.isLogEnabled()) {

            // Controller日志通过MhApiAdviceHandler来处理 或者日志功能未启用
            return pPoint.proceed();
        }

        LocalDateTime start = LocalDateTime.now();

        SysLogModel logModel = null;
        try {
            Object result = pPoint.proceed();
            logModel = SysLogUtils.buildLog(pPoint, point, start, result, null);
            return result;
        } catch (Throwable ex) {
            logModel = SysLogUtils.buildLog(pPoint, point, start, null, ex);
            throw ex;
        } finally {
            if (logModel != null) {
                logModel.setDuration(Duration.between(start, LocalDateTime.now()).toMillis());
                if (logService != null) {
                    logService.handle(point, logModel);
                }
            }
        }
    }
}
