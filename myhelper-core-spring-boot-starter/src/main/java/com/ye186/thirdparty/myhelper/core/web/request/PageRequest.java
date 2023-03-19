package com.ye186.thirdparty.myhelper.core.web.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author ye17186
 * @date 2022-10-01
 */
@Setter
@Getter
public class PageRequest extends BaseRequest {

    private static final long serialVersionUID = 36456355499882795L;

    @NotNull
    @Min(1)
    private long pageNo = 1;

    @NotNull
    @Min(1)
    private long pageSize = 10;
}
