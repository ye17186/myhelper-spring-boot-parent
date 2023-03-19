package io.github.ye17186.myhelper.core.web.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ye17186
 * @since 2022-10-12
 */
@Getter
@AllArgsConstructor
public enum ErrorCode implements IErrorEnum {

    SYSTEM_ERROR(999, "系统异常"),
    BIZ_EX(998, "业务异常"),
    NO_AUTH(997, "权限不足"),
    NO_LOGIN(996, "未登录"),
    OSS_EX(995, "OSS异常"),
    ;

    private final int code;
    private final String msg;
}
