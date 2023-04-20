package io.github.ye17186.myhelper.token.listener;

import cn.dev33.satoken.listener.SaTokenListenerForSimple;
import cn.dev33.satoken.stp.SaLoginModel;
import io.github.ye17186.myhelper.token.model.LoginKey;

/**
 * @author ye17186
 * @since 2023-03-03
 */
public abstract class MhTokenListener extends SaTokenListenerForSimple {

    public abstract void login(String loginType, String loginId, String tokenValue);

    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {

        LoginKey key = LoginKey.decode(String.valueOf(loginId));
        login(key.getLoginType(), key.getLoginId(), tokenValue);
    }
}
