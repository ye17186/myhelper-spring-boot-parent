package io.github.ye17186.myhelper.imail.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class SendGroup implements Serializable {

    /**
     * 发送组类型
     *
     * @see GroupType
     */
    private GroupType groupType;

    /**
     * 发送组唯一标识
     */
    private List<String> groupKeys;
}
