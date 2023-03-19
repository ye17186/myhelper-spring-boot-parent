package io.github.ye17186.myhelper.token.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ye17186
 * @since 2023-03-03
 */
@Setter
@Getter
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = -1786884984058494360L;

    /**
     * 登录类型（账号体系）
     */
    private String loginType;

    /**
     * token名
     */
    private String tokenName;

    /**
     * token值
     */
    private String tokenValue;

    /**
     * 过期时间
     */
    private long expiredAt;
}
