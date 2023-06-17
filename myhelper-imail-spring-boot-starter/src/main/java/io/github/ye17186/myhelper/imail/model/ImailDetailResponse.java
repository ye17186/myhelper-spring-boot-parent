package io.github.ye17186.myhelper.imail.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ImailDetailResponse implements Serializable {

    private Long imailId;
    private String title;
    private String description;
    private String content;
    private String link;
}
