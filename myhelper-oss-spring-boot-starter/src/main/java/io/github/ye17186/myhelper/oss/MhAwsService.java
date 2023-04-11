package io.github.ye17186.myhelper.oss;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.core.oss.result.OssPutResult;
import io.github.ye17186.myhelper.core.oss.result.OssUrlResult;
import io.github.ye17186.myhelper.core.oss.template.MhOssTemplate;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Minio对象存储服务
 *
 * @author ye1718620
 * @since 2023-02-15
 */
@Slf4j
public class MhAwsService implements MhOssTemplate {

    private final AmazonS3 client;

    public MhAwsService(AmazonS3 client) {
        this.client = client;
    }

    @Override
    public AmazonS3 getClient() {

        return this.client;
    }

    @Override
    public OssPutResult putObj(String bucket, String objKey, InputStream stream) {

        OssPutResult result = new OssPutResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            log.info("[My-Helper][OSS] 上传文件到Minio开始。bucket：{}，objKey:{}", bucket, objKey);

            PutObjectRequest request = new PutObjectRequest(bucket, objKey, stream, null);
            client.putObject(request);
        } catch (Exception e) {
            log.info("[My-Helper][OSS] 上传文件异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[My-Helper][OSS] 上传文件结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }

    @Override
    public OssUrlResult getUrl(String bucket, String objKey) {

        OssUrlResult result = new OssUrlResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            URL url = client.getUrl(bucket, objKey);
            result.setUrl(url.toString());
        } catch (Exception e) {
            log.info("[My-Helper][OSS] 获取文件URL异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[My-Helper][OSS] 获取文件URL结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }

    @Override
    public OssUrlResult getUrl(String bucket, String objKey, int expire) {

        OssUrlResult result = new OssUrlResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, objKey);
            URL url =
                    client.generatePresignedUrl(request.withExpiration(new Date((System.currentTimeMillis() + expire * 1000L))));
            result.setUrl(url.toString());
            result.setExpiredAt(LocalDateTime.now().plusSeconds(expire));
        } catch (Exception e) {
            log.info("[My-Helper][OSS] 获取文件URL异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[My-Helper][OSS] 获取文件URL结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }
}
