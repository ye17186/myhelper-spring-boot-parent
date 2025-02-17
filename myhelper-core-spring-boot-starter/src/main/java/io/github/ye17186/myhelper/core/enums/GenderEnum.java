package io.github.ye17186.myhelper.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举：性别
 *
 * @author yemeng20
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    M("M", "男"),
    F("F", "女"),
    X("X", "未知"),
    ;

    private final String code;
    private final String desc;
}
