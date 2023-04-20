package io.github.ye17186.myhelper.token.permission;

import cn.dev33.satoken.stp.StpInterface;
import io.github.ye17186.myhelper.token.model.LoginKey;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author ye17186
 * @since 2023-03-03
 */
public abstract class MhPermissionService implements StpInterface {

    /**
     * 获取当前登录用户的权限码集合
     *
     * @param key 登录唯一标识
     */
    public abstract List<String> getPermissions(@NonNull LoginKey key);

    /**
     * 获取当前登录用户的角色码集合
     *
     * @param key 登录唯一标识
     */
    public abstract List<String> getRoles(@NonNull LoginKey key);

    /**
     * 获取权限码集合
     *
     * @param loginId   登录标识ID
     * @param loginType 登录类型
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        // 没有使用sa-token默认的loginType
        return getPermissions(LoginKey.decode(String.valueOf(loginId)));
    }

    /**
     * 获取角色码集合
     *
     * @param loginId   登录标识ID
     * @param loginType 登录类型
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {

        // 没有使用sa-token默认的loginType
        return getRoles(LoginKey.decode(String.valueOf(loginId)));
    }
}
