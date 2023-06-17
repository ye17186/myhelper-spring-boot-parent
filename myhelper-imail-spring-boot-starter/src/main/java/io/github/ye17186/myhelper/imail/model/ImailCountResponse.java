package io.github.ye17186.myhelper.imail.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class ImailCountResponse implements Serializable {

    private Integer totalCount;

    private List<Item> items;

    @Setter
    @Getter
    public static class Item implements Serializable {

        /**
         * 站内信分类
         */
        private String classify;

        /**
         * 数量
         */
        private Integer count;
    }
}
