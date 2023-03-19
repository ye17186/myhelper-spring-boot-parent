package com.ye186.thirdparty.myhelper.core.web.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ye17186
 * @date 2022-12-27
 */
@Setter
@Getter
public class OptionResponse<T> implements Serializable {

    private static final long serialVersionUID = 2329883144918781321L;

    private String title;

    private T value;
}
