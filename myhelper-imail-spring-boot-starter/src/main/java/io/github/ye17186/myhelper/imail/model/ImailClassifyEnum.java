package io.github.ye17186.myhelper.imail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImailClassifyEnum {

    NOTICE("NOTICE", "通知"),
    MSG("MSG", "消息"),
    ;
    private final String code;
    private final String desc;
}
