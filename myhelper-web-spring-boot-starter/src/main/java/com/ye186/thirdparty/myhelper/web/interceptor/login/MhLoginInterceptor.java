package com.ye186.thirdparty.myhelper.web.interceptor.login;

import com.ye186.thirdparty.myhelper.core.utils.SpringUtils;
import com.ye186.thirdparty.myhelper.core.web.context.user.MhContextUser;
import com.ye186.thirdparty.myhelper.core.web.context.user.MhUserContext;
import com.ye186.thirdparty.myhelper.core.web.error.ErrorCode;
import com.ye186.thirdparty.myhelper.core.web.response.ApiResp;
import com.ye186.thirdparty.myhelper.token.MhTokenService;
import com.ye186.thirdparty.myhelper.web.context.MhUserCacheService;
import com.ye186.thirdparty.myhelper.web.interceptor.MhInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ye17186
 * @date 2023-03-16
 */
@Slf4j
public class MhLoginInterceptor extends MhInterceptor {

    private MhUserCacheService cacheService = null;
    private final String userRef;
    /**
     * 登录类型（账号体系）
     */
    private final String loginType;

    private final MhTokenService mhTokenService;

    public MhLoginInterceptor(MhTokenService mhTokenService, String loginType, String userRef) {

        this.mhTokenService = mhTokenService;
        this.loginType = loginType;
        this.userRef = userRef;
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

        if (isLogin) {
            Pair<String, String> pair = mhTokenService.getLoginPair();
            if (loginType.equals(pair.getLeft())) {
                MhContextUser user = getCacheService().getAndCache(pair.getLeft(), pair.getRight());
                MhUserContext.set(user);
            } else {
                isLogin = false;
            }
        }


        if (!isLogin) {
            ApiResp<String> resp = ApiResp.fail(ErrorCode.NO_LOGIN);
            writeResp(request, response, resp);
        }

        return isLogin;
    }

    private MhUserCacheService getCacheService() {

        if (cacheService == null) {
            cacheService = SpringUtils.getBean(userRef, MhUserCacheService.class);
        }
        return cacheService;
    }
}
