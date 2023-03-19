package com.ye186.thirdparty.myhelper.core.web.response;

import com.ye186.thirdparty.myhelper.core.web.error.IErrorEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ye17186
 * @date 2022-10-12
 */
@Setter
@Getter
public class ApiResp<T> implements Serializable {

    private static final long serialVersionUID = -4622975208445843473L;

    private int code;

    private String msg;

    private T data;

    private static final int SUCCESS = 0;
    private static final int FAIL = 999;

    public static <T> ApiResp<T> suc() {

        return suc(null);
    }

    public static <T> ApiResp<T> suc(T data) {

        ApiResp<T> resp = new ApiResp<>();
        resp.setCode(SUCCESS);
        resp.setData(data);
        return resp;
    }

    public static <T> ApiResp<T> fail(IErrorEnum error) {

        return fail(error.getCode(), error.getMsg());
    }

    public static <T> ApiResp<T> fail(String msg) {

        ApiResp<T> resp = new ApiResp<>();
        resp.setCode(FAIL);
        resp.setMsg(msg);
        return resp;
    }

    public static <T> ApiResp<T> fail(int code, String msg) {

        ApiResp<T> resp = new ApiResp<>();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }
}
