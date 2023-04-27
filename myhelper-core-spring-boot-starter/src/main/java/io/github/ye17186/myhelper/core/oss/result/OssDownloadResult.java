package io.github.ye17186.myhelper.core.oss.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * @author ye17186
 * @since 2023-02-20
 */
@Setter
@Getter
public class OssDownloadResult extends OssResult {

    private static final long serialVersionUID = -44388960384244269L;

    public OssDownloadResult(String bucket, String objKey) {

        super(bucket, objKey);
    }

    /**
     * 文件URL
     */
    private String url;

    @JsonIgnore
    private InputStream inputStream;
}
