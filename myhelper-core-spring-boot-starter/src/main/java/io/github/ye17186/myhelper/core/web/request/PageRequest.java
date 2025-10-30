package io.github.ye17186.myhelper.core.web.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author ye17186
 * @since 2022-10-01
 */
@Valid
@Setter
@Getter
public class PageRequest extends BaseRequest {

    private static final long serialVersionUID = 36456355499882795L;

    @Min(1)
    private long pageNo = 1;

    @Min(1)
    private long pageSize = 10;
}
