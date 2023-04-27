package io.github.ye17186.myhelper.core.oss.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ye17186
 * @date 2023-04-22
 */
@AllArgsConstructor
@Getter
public enum OssAcl {

    PUBLIC("public"),
    PRIVATE("private"),
    ;

    private final String acl;
}
