package io.github.ye17186.myhelper.web.filter;

import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.core.web.context.RequestInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ye17186
 * @date 2024/5/29
 */
@Slf4j
public class DefaultRequestLogService implements RequestLogService {

    @Override
    public void handle(RequestInfo info) {

        log.info("【MyHelper】HTTP LOG ==> {}", JsonUtils.obj2Json(info));
    }
}
