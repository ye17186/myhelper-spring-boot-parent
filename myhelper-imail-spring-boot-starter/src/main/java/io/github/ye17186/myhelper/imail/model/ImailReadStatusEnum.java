package io.github.ye17186.myhelper.imail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImailReadStatusEnum {

    Y("Y", "已读"),
    N("N", "未读"),
    ;
    private final String code;
    private final String desc;
}
