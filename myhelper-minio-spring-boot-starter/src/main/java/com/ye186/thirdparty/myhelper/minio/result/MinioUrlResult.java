package com.ye186.thirdparty.myhelper.minio.result;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author ye17186
 * @date 2023-02-20
 */
@Setter
@Getter
public class MinioUrlResult extends MinioResult {

    private static final long serialVersionUID = -44388960384244269L;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 有效期
     */
    private LocalDateTime expiredAt;

    public MinioUrlResult(String bucket, String objKey) {
        super(bucket, objKey);
    }
}
