package io.github.ye17186.myhelper.core.web.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


/**
 * @author ye17186
 * @since 2022-10-12
 */
@Setter
@Getter
public class IdsRequest extends BaseRequest {

    private static final long serialVersionUID = -1L;

    /**
     * 业务ID
     */
    @NotNull
    private Long id;
}
