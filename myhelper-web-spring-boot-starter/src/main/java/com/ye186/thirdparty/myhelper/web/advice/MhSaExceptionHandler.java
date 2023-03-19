package com.ye186.thirdparty.myhelper.web.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.ye186.thirdparty.myhelper.core.web.error.ErrorCode;
import com.ye186.thirdparty.myhelper.core.web.response.ApiResp;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ye17186
 * @date 2023-03-03
 */
public class MhSaExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public ApiResp<String> handlerNotLoginException(NotLoginException ignore) {

        return ApiResp.fail(ErrorCode.NO_LOGIN);
    }

    @ExceptionHandler(NotRoleException.class)
    public ApiResp<String> notRole(NotRoleException ignore) {

        return ApiResp.fail(ErrorCode.NO_AUTH);
    }

    @ExceptionHandler(NotPermissionException.class)
    public ApiResp<String> notPermission(NotPermissionException ignore) {

        return ApiResp.fail(ErrorCode.NO_AUTH);
    }
}