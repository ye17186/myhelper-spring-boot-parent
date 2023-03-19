package com.ye186.thirdparty.myhelper.minio.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ye17186
 * @date 2023-02-20
 */
@Setter
@Getter
public class MinioPutResult extends MinioResult {
    private static final long serialVersionUID = 7846836842030552563L;

    public MinioPutResult(String bucket, String objKey) {
        super(bucket, objKey);
    }
}
