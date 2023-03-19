package com.ye186.thirdparty.myhelper.token.style;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ye17186
 * @date 2023-03-03
 */
@Getter
@AllArgsConstructor
public enum TokenStyle {

    UUID("uuid"),
    SIMPLE_UUID("simple-uuid"),
    RANDOM32("random-32"),
    RANDOM64("random-64"),
    RANDOM128("random-128"),
    TIK("tik");

    private String code;
}
