package io.github.ye17186.myhelper.minio.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ye17186
 * @since 2023-02-20
 */
@Setter
@Getter
public class MinioResult implements Serializable {

    private static final long serialVersionUID = -6690631970849112223L;

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 桶
     */
    private String bucket;

    /**
     * 对象Key
     */
    private String objKey;

    /**
     * 耗时
     */
    private long duration;

    public MinioResult(String bucket, String objKey) {
        this.bucket = bucket;
        this.objKey = objKey;
    }
}
