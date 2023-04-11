package io.github.ye17186.myhelper.core.oss.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ye17186
 * @since 2023-02-20
 */
@Setter
@Getter
public class OssPutResult extends OssResult {
    private static final long serialVersionUID = 7846836842030552563L;

    public OssPutResult(String bucket, String objKey) {
        super(bucket, objKey);
    }
}
