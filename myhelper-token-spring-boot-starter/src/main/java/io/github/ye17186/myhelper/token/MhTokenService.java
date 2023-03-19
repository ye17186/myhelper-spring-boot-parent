package io.github.ye17186.myhelper.token;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.github.ye17186.myhelper.token.model.TokenInfo;
import io.github.ye17186.myhelper.token.utils.LoginTypeUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @author ye17186
 * @since 2023-03-03
 */
public class MhTokenService {

    /**
     * 用户登录
     *
     * @param loginId 登录唯一标识
     */
    public TokenInfo login(String loginId) {

        StpUtil.login(LoginTypeUtils.encode(LoginTypeUtils.DEFAULT_TYPE, loginId));
        return toToken(StpUtil.getTokenInfo());
    }

    /**
     * 用户登录
     *
     * @param loginId 登录唯一标识
     */
    public TokenInfo login(String loginType, String loginId) {

        StpUtil.login(LoginTypeUtils.encode(loginType, loginId));
        return toToken(StpUtil.getTokenInfo());
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
    public void logout(String loginId) {

        StpUtil.logout(LoginTypeUtils.encode(LoginTypeUtils.DEFAULT_TYPE, loginId));
    }

    /**
     * 指定用户登出
     */
    public void logout(String loginType, String loginId) {

        StpUtil.logout(LoginTypeUtils.encode(loginType, loginId));
    }

    /**
     * 获取当前登录用户的登录标识
     *
     * @return 登录标识
     */
    public Pair<String, String> getLoginPair() {

        return LoginTypeUtils.decode(StpUtil.getLoginIdAsString());
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
     *
     * @return
     */
    public List<String> roles() {

        return StpUtil.getRoleList();
    }

    public boolean hasPermission(String permission) {

        return StpUtil.hasPermission(permission);
    }

    public boolean hasRole(String role) {

        return StpUtil.hasRole(role);
    }

    private TokenInfo toToken(SaTokenInfo info) {

        TokenInfo token = new TokenInfo();
        token.setLoginType(info.getLoginType());
        token.setTokenName(info.getTokenName());
        token.setTokenValue(info.getTokenValue());
        token.setExpiredAt(System.currentTimeMillis() + info.getTokenTimeout() * 1000);
        return token;
    }
}
