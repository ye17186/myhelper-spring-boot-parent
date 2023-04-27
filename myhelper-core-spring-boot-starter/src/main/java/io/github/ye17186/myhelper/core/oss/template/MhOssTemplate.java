package io.github.ye17186.myhelper.core.oss.template;

import io.github.ye17186.myhelper.core.oss.result.OssDownloadResult;
import io.github.ye17186.myhelper.core.oss.result.OssPutResult;
import io.github.ye17186.myhelper.core.oss.result.OssUrlResult;

import java.io.InputStream;

/**
 * @author ye17186
 * @date 2023-04-11
 */
public interface MhOssTemplate {

    Object getClient();

    /**
     * 存入对象
     *
     * @param bucket 桶
     * @param objKey key
     * @param stream 文件流
     */
    OssPutResult putObj(String bucket, String objKey, InputStream stream);

    /**
     * 存入对象
     *
     * @param bucket 桶
     * @param objKey key
     * @param stream 文件流
     * @param contentType type
     */
    OssPutResult putObj(String bucket, String objKey, InputStream stream, String contentType);

    /**
     * 获取永久URL
     * 注意：此方法必须提前将bucket设置为public
     *
     * @param bucket 桶
     * @param objKey 对象key
     */
    OssUrlResult getUrl(String bucket, String objKey);

    /**
     * 获取签名URL
     *
     * @param bucket 桶
     * @param objKey 文件key
     * @param expire 有效时间，单位秒，minio最大支持7天
     */
    OssUrlResult getUrl(String bucket, String objKey, int expire);

    /**
     * 通过URL下载文件
     *
     * @param url 文件URL
     */
    OssDownloadResult download(String url);
}
