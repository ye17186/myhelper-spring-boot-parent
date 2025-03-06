package io.github.ye17186.myhelper.core.web.context.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author yemeng20
 */
@Setter
@Getter
public class UniContextUser extends DefaultContextUser {

    /**
     * 三方联合登录时的额外参数，用于查询第三方系统中的用户信息
     */
    private Map<String, Object> uniData;

    /**
     * 三方联合登录时，三方系统中的用户信息
     */
    private Object uniUser;
}
