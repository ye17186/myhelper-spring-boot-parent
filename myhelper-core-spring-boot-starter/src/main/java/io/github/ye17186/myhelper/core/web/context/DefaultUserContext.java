package io.github.ye17186.myhelper.core.web.context;

import io.github.ye17186.myhelper.core.web.context.user.DefaultContextUser;
import io.github.ye17186.myhelper.core.web.context.user.MhUserContext;

/**
 * @author ye17186
 */
public class DefaultUserContext extends MhUserContext {

    private static final long serialVersionUID = -3954172646544985218L;

    public static Long userId() {

        return getUser().getUserId();
    }

    public static Long department() {

        return getUser().getDepartmentId();
    }

    public static String realName() {

        return getUser().getRealName();
    }

    /**
     * 获取当前登录用户
     */
    public static DefaultContextUser getUser() {

        return (DefaultContextUser) get();
    }
}
