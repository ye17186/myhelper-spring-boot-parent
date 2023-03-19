package com.ye186.thirdparty.myhelper.core.web.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.ye186.thirdparty.myhelper.core.utils.IdUtils;

import java.util.Optional;

/**
 * @author ye17186
 * @date 2022-10-12
 */
public class RequestContext {

    private static final TransmittableThreadLocal<RequestInfo> HOLDER = new TransmittableThreadLocal<>();

    public static void set(RequestInfo value) {

        HOLDER.set(value);
    }

    public static RequestInfo get() {

        return HOLDER.get();
    }

    public static void remove() {

        HOLDER.remove();
    }

    public static String requestId() {

        return Optional.ofNullable(HOLDER.get()).map(RequestInfo::getRequestId).orElse(IdUtils.uuid());
    }
}
