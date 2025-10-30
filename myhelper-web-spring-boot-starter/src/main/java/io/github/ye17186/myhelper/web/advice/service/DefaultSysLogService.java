package io.github.ye17186.myhelper.web.advice.service;

import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.web.advice.model.SysLogModel;
import io.github.ye17186.myhelper.web.annotation.SysLogPoint;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ye17186
 * @date 2024/5/29
 */
@Slf4j
public class DefaultSysLogService implements SysLogService {

    @Override
    public void handle(SysLogPoint point, SysLogModel model) {

        // 默认情况直接打印系统日志即可
        log.info("【MyHelper】SYS LOG ==> {}", JsonUtils.obj2Json(model));
    }
}
