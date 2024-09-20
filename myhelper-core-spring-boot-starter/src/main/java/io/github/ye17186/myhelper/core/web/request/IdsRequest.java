package io.github.ye17186.myhelper.core.web.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * @author ye17186
 * @since 2022-10-12
 */
@Setter
@Getter
public class IdsRequest extends BaseRequest {

    private static final long serialVersionUID = -1L;

    /**
     * 业务ID集合
     */
    @NotNull
    @Size(min = 1)
    private List<Long> ids;
}
