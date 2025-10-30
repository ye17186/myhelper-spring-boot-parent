package io.github.ye17186.myhelper.web.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ye17186
 * @since 2023-03-16
 */
@Setter
@Getter
public class MhWebApiAdviceProperties {

    /**
     * 启用Controller API增强
     */
    private boolean enabled = true;

    /**
     * 切点表达式
     */
    private String expression = "execution(* com.ye186.myhelper..controller..*Controller.*(..))";

    private boolean logEnabled = true;
}
