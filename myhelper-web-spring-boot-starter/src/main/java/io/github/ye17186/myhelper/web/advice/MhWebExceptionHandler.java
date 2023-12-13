package io.github.ye17186.myhelper.web.advice;

import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import io.github.ye17186.myhelper.core.web.response.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * Controller增强、异常处理
 *
 * @author ye17186
 * @since 2022-09-30
 */
@Slf4j
@RestControllerAdvice
public class MhWebExceptionHandler extends MhSaExceptionHandler {

    @ExceptionHandler(BizException.class)
    protected ApiResp<String> handleLogicException(HttpServletRequest request, BizException ex) {

        log.info("[业务异常] uri = {}, code = {}, msg = {}", request.getRequestURI(), ex.getCode(), ex.getMsg(), ex);

        return ApiResp.fail(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResp<String> handleArgsException(HttpServletRequest request, MethodArgumentNotValidException ex) {


        String errMsg = collectErrorMsg(ex);
        log.info("[业务异常] uri = {}, msg = {}", request.getRequestURI(), errMsg);
        return ApiResp.fail(ErrorCode.PARAM_EX.getCode(), errMsg);
    }

    @ExceptionHandler(Exception.class)
    protected ApiResp<String> handleException(HttpServletRequest request, Exception ex) {

        log.error("[系统异常] uri = {}", request.getRequestURI(), ex);
        return ApiResp.fail("系统异常");
    }

    private String collectErrorMsg(MethodArgumentNotValidException ex) {

        return ex.getBindingResult().getAllErrors().stream().map(error -> {
            if (error instanceof FieldError) {
                return String.format(FIELD_ERROR_FORMAT, ((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                return error.getDefaultMessage();
            }

        }).collect(Collectors.joining(", "));
    }

    private static final String FIELD_ERROR_FORMAT = "[%s]%s";
}
