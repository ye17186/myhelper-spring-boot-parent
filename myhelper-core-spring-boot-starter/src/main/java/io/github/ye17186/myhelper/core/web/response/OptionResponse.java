package io.github.ye17186.myhelper.core.web.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 选项响应（用于支持前端的下拉选择组件）
 *
 * @author ye17186
 * @since 2022-12-27
 */
@Setter
@Getter
public class OptionResponse<T> implements Serializable {

    private static final long serialVersionUID = 2329883144918781321L;

    /**
     * 指
     */
    private T value;

    /**
     * 标题
     */
    private String title;

    /**
     * 扩展属性1
     */
    private String extAttr1;

    /**
     * 扩展属性2
     */
    private String extAttr2;

    /**
     * 扩展属性3
     */
    private String extAttr3;

    /**
     * 子项
     */
    private List<OptionResponse<T>> children;
}
