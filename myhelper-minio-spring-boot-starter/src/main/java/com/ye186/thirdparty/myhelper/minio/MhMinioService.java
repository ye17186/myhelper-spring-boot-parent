package com.ye186.thirdparty.myhelper.minio;

import com.ye186.thirdparty.myhelper.core.utils.JsonUtils;
import com.ye186.thirdparty.myhelper.minio.result.MinioPutResult;
import com.ye186.thirdparty.myhelper.minio.result.MinioUrlResult;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * Minio对象存储服务
 *
 * @author ye1718620
 * @date 2023-02-15
 */
@Slf4j
public class MhMinioService {

    private final MinioClient client;

    public MhMinioService(MinioClient client) {
        this.client = client;
    }

    /**
     * 存入对象
     *
     * @param bucket 桶
     * @param objKey key
     * @param stream 文件流
     */
    public MinioPutResult putObj(String bucket, String objKey, InputStream stream) {

        MinioPutResult result = new MinioPutResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            log.info("[My-Helper][Minio] 上传文件到Minio开始。bucket：{}，objKey:{}", bucket, objKey);
            PutObjectArgs args = PutObjectArgs.builder().bucket(bucket).object(objKey)
                    .stream(stream, stream.available(), -1)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE).build();
            client.putObject(args);
        } catch (Exception e) {
            log.info("[My-Helper][Minio] 上传文件异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[My-Helper][Minio] 上传文件结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }

    /**
     * 获取永久URL
     * 注意：此方法必须提前将bucket设置为public
     *
     * @param bucket 桶
     * @param objKey 对象key
     */
    public MinioUrlResult getUrl(String bucket, String objKey) {

        MinioUrlResult result = new MinioUrlResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            // 注意：minio最大值支持7天，如果需要获取永久链接，必须将bucket设置为public
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder().method(Method.GET)
                    .bucket(bucket).object(objKey).build();
            String url = client.getPresignedObjectUrl(args);
            result.setUrl(url.substring(0, url.indexOf("?")));
        } catch (Exception e) {
            log.info("[My-Helper][Minio] 获取文件URL异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[My-Helper][Minio] 获取文件URL结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }

    /**
     * 获取签名URL
     *
     * @param bucket 桶
     * @param objKey 文件key
     * @param expire 有效时间，单位秒，minio最大支持7天
     */
    public MinioUrlResult getUrl(String bucket, String objKey, int expire) {

        MinioUrlResult result = new MinioUrlResult(bucket, objKey);
        long start = System.currentTimeMillis();
        try {
            // 注意：minio最大值支持7天
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder().method(Method.GET)
                    .bucket(bucket).object(objKey).expiry(expire).build();
            String url = client.getPresignedObjectUrl(args);
            result.setUrl(url);
            result.setExpiredAt(LocalDateTime.now().plusSeconds(expire));
        } catch (Exception e) {
            log.info("[My-Helper][Minio] 获取文件URL异常。", e);
            result.setSuccess(false);
        } finally {
            result.setDuration(System.currentTimeMillis() - start);
            log.info("[My-Helper][Minio] 获取文件URL结束。结果：{}", JsonUtils.obj2Json(result));
        }
        return result;
    }
}
