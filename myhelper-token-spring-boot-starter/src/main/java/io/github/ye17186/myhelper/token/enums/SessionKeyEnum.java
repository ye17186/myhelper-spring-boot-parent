package io.github.ye17186.myhelper.token.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ye17186
 */
@Getter
@AllArgsConstructor
public enum SessionKeyEnum {

    USER_INFO("USER_INFO", "用户信息"),
    ;

    private final String code;
    private final String desc;
}
