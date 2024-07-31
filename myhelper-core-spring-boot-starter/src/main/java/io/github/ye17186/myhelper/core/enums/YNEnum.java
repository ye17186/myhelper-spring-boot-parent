package io.github.ye17186.myhelper.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ye17186
 * @date 2024/5/31
 */
@Getter
@AllArgsConstructor
public enum YNEnum {

    Y("Y", "是"),
    N("N", "否"),
    ;

    private final String code;
    private final String desc;
}
