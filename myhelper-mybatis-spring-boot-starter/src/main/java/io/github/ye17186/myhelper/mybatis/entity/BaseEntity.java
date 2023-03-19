package io.github.ye17186.myhelper.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库表Entity基类
 *
 * @author ye17186
 * @since 2022-10-12
 */
@Setter
@Getter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7273233799127037048L;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 是否已删除
     */
    private Boolean deleted;

    /**
     * 创建人
     */
    private String created;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改人
     */
    private String modified;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;
}
