package io.github.ye17186.myhelper.core.web.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author ye17186
 * @since 2022-10-01
 */
@Setter
@Getter
public class PageResponse<T> implements Serializable {

    private static final long serialVersionUID = -8960466194898414340L;

    /**
     * 页码
     */
    private long pageNo;

    /**
     * 每页大小
     */
    private long pageSize;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 总数据条数
     */
    private long totalCount;

    /**
     * 数据集合
     */
    private List<T> items;
}
