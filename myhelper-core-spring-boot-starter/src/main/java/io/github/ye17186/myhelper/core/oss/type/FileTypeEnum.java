package io.github.ye17186.myhelper.core.oss.type;

import io.github.ye17186.myhelper.core.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

/**
 * 为统一规范，方便管理，不通类型的文件，在上传时会存到不同的目录下
 *
 * @author ye17186
 * @date 2023-04-22
 */
@AllArgsConstructor
@Getter
public enum FileTypeEnum {

    PUBLIC_FILE("public", "公有文件", OssAcl.PUBLIC, "public/"),
    PUBLIC_TMP_FILE("public_tmp", "公有临时文件", OssAcl.PUBLIC, "public-tmp/"),
    PRIVATE_FILE("private", "私有文件", OssAcl.PRIVATE, "private/"),
    PRIVATE_TMP_FILE("private_tmp", "私有临时文件", OssAcl.PRIVATE, "private-tmp/"),
    ;

    private final String code;
    private final String desc;
    private final OssAcl acl;
    private final String dir;

    @Nullable
    public static FileTypeEnum getByCode(String code) {

        if (StringUtils.isNotEmpty(code)) {
            for (FileTypeEnum value : values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
        }

        return null;
    }
}
