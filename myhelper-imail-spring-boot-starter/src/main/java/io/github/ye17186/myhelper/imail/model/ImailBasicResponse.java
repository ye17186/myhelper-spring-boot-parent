package io.github.ye17186.myhelper.imail.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class ImailBasicResponse implements Serializable {

    private Long imailId;
    private String classify;
    private String title;
    private String description;
    private String link;
    private LocalDateTime sendTime;
    private String readStatus;
    private LocalDateTime readTime;
}
