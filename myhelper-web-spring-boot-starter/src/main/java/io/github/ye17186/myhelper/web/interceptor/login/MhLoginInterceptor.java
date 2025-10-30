package io.github.ye17186.myhelper.web.interceptor.login;

import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.core.web.context.RequestContext;
import io.github.ye17186.myhelper.core.web.context.user.MhContextUser;
import io.github.ye17186.myhelper.core.web.context.user.MhUserContext;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import io.github.ye17186.myhelper.core.web.response.ApiResp;
import io.github.ye17186.myhelper.token.MhTokenService;
import io.github.ye17186.myhelper.token.model.LoginKey;
import io.github.ye17186.myhelper.web.interceptor.MhInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ye17186
 * @since 2023-03-16
 */
@Slf4j
public class MhLoginInterceptor extends MhInterceptor {

    private final Class<? extends MhContextUser> userType;

    private final MhTokenService mhTokenService;

    public MhLoginInterceptor(MhTokenService mhTokenService, Class<? extends MhContextUser> userType) {

        this.mhTokenService = mhTokenService;
        this.userType = userType;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        try {
            return doHandler(request, response);
        } catch (Exception e) {
            log.info("登录拦截器执行异常", e);
        }
        return false;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {

        MhUserContext.clear();
    }

    private boolean doHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }


        boolean isLogin = mhTokenService.isLogin();
        log.trace("[MyHelper - Interceptor] 登录拦截器校验。当前用户登录信息是否有效: {}", isLogin);

        if (isLogin) {
            LoginKey key = mhTokenService.getLoginKey();
            if (key != null) {
                try {
                    String userJson = mhTokenService.getUserJson();
                    if (StringUtils.isNotEmpty(userJson)) {
                        MhUserContext.set(JsonUtils.json2Obj(userJson, userType));
                    } else {
                        isLogin = false;
                        mhTokenService.logout();
                    }
                } catch (Exception e) {
                    isLogin = false;
                }
            } else {
                isLogin = false;
            }
        }

        if (!isLogin) {
            log.info("[业务异常] traceId = {}, code = {}, msg = {}, uri = {}",
                    RequestContext.requestId(),
                    ErrorCode.NO_LOGIN.getCode(),
                    ErrorCode.NO_LOGIN.getMsg(),
                    request.getRequestURI());
            ApiResp<String> resp = ApiResp.fail(ErrorCode.NO_LOGIN);
            writeResp(request, response, resp);
        }

        return isLogin;
    }
}
