package io.github.ye17186.myhelper.web.advice.service;

import io.github.ye17186.myhelper.web.advice.model.SysLogModel;
import io.github.ye17186.myhelper.web.annotation.SysLogPoint;

/**
 * @author ye17186
 * @date 2024/5/29
 */
public interface SysLogService {

    void handle(SysLogPoint point, SysLogModel model);
}
