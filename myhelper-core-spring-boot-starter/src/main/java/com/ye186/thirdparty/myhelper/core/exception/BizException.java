package com.ye186.thirdparty.myhelper.core.exception;

import com.ye186.thirdparty.myhelper.core.web.error.IErrorEnum;
import lombok.Getter;

/**
 * 业务异常
 *
 * @author ye17186
 * @date 2022-10-12
 */
@Getter
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -6372421245660734961L;

    private final int code;
    private final String msg;

    public BizException(int code, String msg) {

        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(int code, String msg, Throwable e) {

        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public BizException(IErrorEnum error) {

        super(error.getMsg());
        this.code = error.getCode();
        this.msg = error.getMsg();
    }

    public BizException(IErrorEnum error, Throwable e) {

        super(error.getMsg(), e);
        this.code = error.getCode();
        this.msg = error.getMsg();
    }
}
