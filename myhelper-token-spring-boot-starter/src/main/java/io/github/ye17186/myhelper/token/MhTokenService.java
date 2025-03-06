package io.github.ye17186.myhelper.token;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.token.enums.SessionKeyEnum;
import io.github.ye17186.myhelper.token.model.LoginKey;
import io.github.ye17186.myhelper.token.model.TokenInfo;

import java.util.List;

/**
 * @author ye17186
 * @since 2023-03-03
 */
public class MhTokenService {

    /**
     * 用户登录
     *
     * @param key 登录唯一标识
     */
    public TokenInfo login(LoginKey key) {

        StpUtil.login(key.format());
        return toToken(StpUtil.getTokenInfo(), key);
    }

    public TokenInfo login(LoginKey key, Object userInfo) {

        StpUtil.login(key.format());
        StpUtil.getSession().set(SessionKeyEnum.USER_INFO.getCode(), JsonUtils.obj2Json(userInfo));
        return toToken(StpUtil.getTokenInfo(), key);
    }

    /**
     * 用户登出
     */
    public void logout() {

        StpUtil.logout();
    }

    /**
     * 指定用户登出
     */
    public void logout(LoginKey key) {

        StpUtil.logout(key.format());
    }

    /**
     * 获取当前登录用户的登录标识
     *
     * @return 登录标识
     */
    public LoginKey getLoginKey() {

        return LoginKey.decode(StpUtil.getLoginIdAsString());
    }

    public String getUserJson() {

        return  (String) StpUtil.getSession().get(SessionKeyEnum.USER_INFO.getCode());
    }

    /**
     * 当前状态是否已登录
     */
    public boolean isLogin() {

        return StpUtil.isLogin();
    }

    /**
     * 获取当前用户的权限码集合
     *
     * @return 权限码集合
     */
    public List<String> permissions() {

        return StpUtil.getPermissionList();
    }

    /**
     * 获取当前用户的角色码集合
     */
    public List<String> roles() {

        return StpUtil.getRoleList();
    }

    /**
     * 判断当前用户是否拥有指定权限
     *
     * @param permission 权限码
     */
    public boolean hasPermission(String permission) {

        return StpUtil.hasPermission(permission);
    }

    /**
     * 判断当前用户是否拥有指定角色
     *
     * @param role 角色码
     */
    public boolean hasRole(String role) {

        return StpUtil.hasRole(role);
    }

    private TokenInfo toToken(SaTokenInfo info, LoginKey key) {

        TokenInfo token = new TokenInfo();
        token.setLoginType(key.getLoginType());
        token.setTokenName(info.getTokenName());
        token.setTokenValue(info.getTokenValue());
        token.setExpiredAt(System.currentTimeMillis() + info.getTokenTimeout() * 1000);
        return token;
    }
}
