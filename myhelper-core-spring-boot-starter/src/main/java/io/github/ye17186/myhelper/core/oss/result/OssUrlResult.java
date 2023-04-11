package io.github.ye17186.myhelper.core.oss.result;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author ye17186
 * @since 2023-02-20
 */
@Setter
@Getter
public class OssUrlResult extends OssResult {

    private static final long serialVersionUID = -44388960384244269L;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 有效期
     */
    private LocalDateTime expiredAt;

    public OssUrlResult(String bucket, String objKey) {
        super(bucket, objKey);
    }
}
