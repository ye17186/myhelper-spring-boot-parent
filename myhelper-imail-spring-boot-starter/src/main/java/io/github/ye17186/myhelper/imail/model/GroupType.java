package io.github.ye17186.myhelper.imail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupType {

    ALL("ALL", "全量用户"),
//    GROUP("GROUP", "用户组"),
    SPECIFIC("SPECIFIC", "特定用户"),
    ;
    private final String code;
    private final String desc;
}
