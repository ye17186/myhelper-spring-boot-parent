package io.github.ye17186.myhelper.imail.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class Imail implements Serializable {

    @NotNull
    private ImailClassifyEnum classify;

    /**
     * 站内信标题
     */
    @NotEmpty
    private String title;

    /**
     * 站内信描述
     */
    private String description;

    /**
     * 站内信内容
     */
    private String content;

    /**
     * 详情链接
     */
    private String link;
}
