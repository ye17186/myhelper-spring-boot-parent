package io.github.ye17186.myhelper.core.web.context.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ye17186
 * @since 2022-12-05
 */
@Setter
@Getter
public abstract class MhContextUser implements Serializable {

    private static final long serialVersionUID = -851315499261810885L;

    /**
     * 登录类型（账号体系）
     */
    private String loginType;

    /**
     * 用户Token
     */
    private String mhToken;

    /**
     * Token过期时间
     */
    private long mhExpiredAt;
}
