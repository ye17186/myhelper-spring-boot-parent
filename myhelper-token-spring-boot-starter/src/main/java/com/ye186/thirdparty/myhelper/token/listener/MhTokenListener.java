package com.ye186.thirdparty.myhelper.token.listener;

import cn.dev33.satoken.listener.SaTokenListenerForSimple;
import cn.dev33.satoken.stp.SaLoginModel;
import com.ye186.thirdparty.myhelper.token.utils.LoginTypeUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author ye17186
 * @date 2023-03-03
 */
public abstract class MhTokenListener extends SaTokenListenerForSimple {

    public abstract void login(String loginType, String loginId, String tokenValue);

    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {

        Pair<String, String> pair = LoginTypeUtils.decode(String.valueOf(loginId));
        login(pair.getLeft(), pair.getRight(), tokenValue);
    }
}
