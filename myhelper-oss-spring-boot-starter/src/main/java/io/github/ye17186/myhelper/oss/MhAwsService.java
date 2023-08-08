package io.github.ye17186.myhelper.oss;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import io.github.ye17186.myhelper.core.oss.result.OssGetResult;
import io.github.ye17186.myhelper.core.oss.result.OssPutResult;
import io.github.ye17186.myhelper.core.oss.result.OssUrlResult;
import io.github.ye17186.myhelper.core.oss.template.MhOssTemplate;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * AWS对象存储服务
 *
 * @author ye17186
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

        return putObj(bucket, objKey, stream, MediaType.APPLICATION_OCTET_STREAM_VALUE);
    }

    @Override
    public OssPutResult putObj(String bucket, String objKey, InputStream stream, String contentType) {

        OssPutResult result = new OssPutResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            log.info("[My-Helper][OSS] 上传文件到OSS开始。bucket：{}，objKey:{}", bucket, objKey);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(stream.available());
            metadata.setContentType(contentType);
            PutObjectRequest request = new PutObjectRequest(bucket, objKey, stream, metadata);
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
    public OssGetResult getObj(String bucket, String objKey) {

        OssGetResult result = new OssGetResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            GetObjectRequest request = new GetObjectRequest(bucket, objKey);
            S3Object response = client.getObject(request);
            result.setInputStream(response.getObjectContent());
        } catch (Exception e) {
            log.info("[My-Helper][OSS] 获取文件流异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[My-Helper][OSS] 获取文件流结束。结果：{}", JsonUtils.obj2Json(result));
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
