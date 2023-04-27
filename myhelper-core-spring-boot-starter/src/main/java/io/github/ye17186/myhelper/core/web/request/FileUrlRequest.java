package io.github.ye17186.myhelper.core.web.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author ye17186
 * @date 2023-04-21
 */
@Setter
@Getter
public class FileUrlRequest extends BaseRequest {

    @NotEmpty
    private String url;
}
