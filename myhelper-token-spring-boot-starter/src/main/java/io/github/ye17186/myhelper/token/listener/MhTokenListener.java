package io.github.ye17186.myhelper.token.listener;

import cn.dev33.satoken.listener.SaTokenListenerForSimple;
import cn.dev33.satoken.stp.SaLoginModel;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.token.model.LoginKey;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ye17186
 * @since 2023-03-03
 */
@Slf4j
public abstract class MhTokenListener extends SaTokenListenerForSimple {

    /**
     * 用户登录事件
     *
     * @param key        用户唯一标识
     * @param tokenValue token值
     */
    public void login(LoginKey key, String tokenValue) {
    }

    /**
     * 用户登出事件
     *
     * @param key        用户唯一标识
     * @param tokenValue token值
     */
    public void logout(LoginKey key, String tokenValue) {
    }

    @Override
    public final void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {

        LoginKey key = LoginKey.decode(String.valueOf(loginId));
        log.info("[My-Helper][token] 用户登录。{}", JsonUtils.obj2Json(key));
        try {
            login(key, tokenValue);
        } catch (Exception e) {
            log.warn("[My-Helper][token] 用户登录。处理异常。", e);
        }
    }

    @Override
    public final void doLogout(String loginType, Object loginId, String tokenValue) {

        LoginKey key = LoginKey.decode(String.valueOf(loginId));
        log.info("[My-Helper][token] 用户登出。{}", JsonUtils.obj2Json(key));
        try {
            logout(key, tokenValue);
        } catch (Exception e) {
            log.warn("[My-Helper][token] 用户登出。处理异常。", e);
        }
    }
}
