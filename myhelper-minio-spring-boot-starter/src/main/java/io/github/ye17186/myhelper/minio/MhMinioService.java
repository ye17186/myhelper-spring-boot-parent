package io.github.ye17186.myhelper.minio;

import io.github.ye17186.myhelper.core.oss.result.OssGetResult;
import io.github.ye17186.myhelper.core.oss.result.OssPutResult;
import io.github.ye17186.myhelper.core.oss.result.OssUrlResult;
import io.github.ye17186.myhelper.core.oss.template.MhOssTemplate;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Minio对象存储服务
 *
 * @author ye17186
 * @since 2023-02-15
 */
@Slf4j
public class MhMinioService implements MhOssTemplate {

    private final MinioClient client;
    private final MinioClient proxyClient;

    public MhMinioService(MinioClient client, @Nullable MinioClient proxyClient) {

        this.client = client;
        this.proxyClient = Optional.ofNullable(proxyClient).orElse(client);
    }

    @Override
    public MinioClient getClient() {

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
            log.info("[MyHelper][Minio] 上传文件到Minio开始。bucket：{}，objKey:{}", bucket, objKey);
            PutObjectArgs args = PutObjectArgs.builder().bucket(bucket).object(objKey)
                    .stream(stream, stream.available(), -1)
                    .contentType(contentType).build();
            client.putObject(args);
        } catch (Exception e) {
            log.info("[MyHelper][Minio] 上传文件异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[MyHelper][Minio] 上传文件结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }

    @Override
    public OssGetResult getObj(String bucket, String objKey) {

        OssGetResult result = new OssGetResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            GetObjectArgs args = GetObjectArgs.builder().bucket(bucket).object(objKey).build();
            GetObjectResponse response = client.getObject(args);
            result.setInputStream(response);
        } catch (Exception e) {
            log.info("[MyHelper][Minio] 获取文件流异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[MyHelper][Minio] 获取文件流结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }

    @Override
    public OssUrlResult getUrl(String bucket, String objKey) {

        return doGetUrl(bucket, objKey, -1, false);
    }

    @Override
    public OssUrlResult getProxyUrl(String bucket, String objKey) {

        return doGetUrl(bucket, objKey, -1, true);
    }

    @Override
    public OssUrlResult getUrl(String bucket, String objKey, int expire) {

        return doGetUrl(bucket, objKey, expire, false);
    }

    @Override
    public OssUrlResult getProxyUrl(String bucket, String objKey, int expire) {

        return doGetUrl(bucket, objKey, expire, true);
    }

    private OssUrlResult doGetUrl(String bucket, String objKey, int expire, boolean isProxy) {

        OssUrlResult result = new OssUrlResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            // 注意：minio最大值支持7天
            GetPresignedObjectUrlArgs.Builder builder = GetPresignedObjectUrlArgs.builder().method(Method.GET)
                    .bucket(bucket)
                    .object(objKey);
            if (expire > 0) {
                builder.expiry(expire);
            }
            GetPresignedObjectUrlArgs args = builder.build();

            MinioClient targetClient = getTargetClient(isProxy);
            String url = targetClient.getPresignedObjectUrl(args);

            result.setUrl(url);
            result.setExpiredAt(LocalDateTime.now().plusSeconds(expire));
        } catch (Exception e) {
            log.info("[MyHelper][Minio] 获取文件URL异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[MyHelper][Minio] 获取文件URL结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }

    @NonNull
    private MinioClient getTargetClient(boolean isProxy) {

        return isProxy ? proxyClient : client;
    }
}
