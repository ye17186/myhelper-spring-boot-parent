package io.github.ye17186.myhelper.web.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import io.github.ye17186.myhelper.core.web.response.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ye17186
 * @since 2023-03-03
 */
@Slf4j
public class MhSaExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public ApiResp<String> handlerNotLoginException(HttpServletRequest request, NotLoginException ex) {

        log.info("[业务异常] uri = {}", request.getRequestURI(), ex);
        return ApiResp.fail(ErrorCode.NO_LOGIN);
    }

    @ExceptionHandler(NotRoleException.class)
    public ApiResp<String> notRole(HttpServletRequest request, NotRoleException ex) {

        log.info("[业务异常] uri = {}", request.getRequestURI(), ex);
        return ApiResp.fail(ErrorCode.NO_AUTH);
    }

    @ExceptionHandler(NotPermissionException.class)
    public ApiResp<String> notPermission(HttpServletRequest request, NotPermissionException ex) {

        log.info("[业务异常] uri = {}", request.getRequestURI(), ex);
        return ApiResp.fail(ErrorCode.NO_AUTH);
    }
}
