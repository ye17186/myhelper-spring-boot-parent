package com.ye186.thirdparty.myhelper.web.interceptor;

import com.google.common.base.Charsets;
import com.ye186.thirdparty.myhelper.core.utils.JsonUtils;
import com.ye186.thirdparty.myhelper.core.web.response.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ye17186
 * @date 2023-03-09
 */
@Slf4j
public abstract class MhInterceptor implements HandlerInterceptor {

    protected <T> void writeResp(HttpServletRequest request, HttpServletResponse response, ApiResp<T> resp) throws IOException {

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setCharacterEncoding(Charsets.UTF_8.name());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JsonUtils.obj2Json(resp));
    }
}
