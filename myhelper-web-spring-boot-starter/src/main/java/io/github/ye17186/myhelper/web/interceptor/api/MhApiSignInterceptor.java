package io.github.ye17186.myhelper.web.interceptor.api;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.sign.SaSignUtil;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import io.github.ye17186.myhelper.core.web.response.ApiResp;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebApiAuthInterceptorProperties;
import io.github.ye17186.myhelper.web.interceptor.MhInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ye17186
 */
@Slf4j
public class MhApiSignInterceptor extends MhInterceptor {

    private final MhWebApiAuthInterceptorProperties properties;

    public MhApiSignInterceptor(MhWebApiAuthInterceptorProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        // todo 签名待开发
        try {
            SaSignUtil.checkRequest(SaHolder.getRequest());
            return true;
        } catch (Exception e) {
            ApiResp<String> resp = ApiResp.fail(ErrorCode.API_FAIL);
            writeResp(request, response, resp);
            return false;
        }
    }
}
