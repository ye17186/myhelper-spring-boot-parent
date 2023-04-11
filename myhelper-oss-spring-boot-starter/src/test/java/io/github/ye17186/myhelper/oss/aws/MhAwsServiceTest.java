package io.github.ye17186.myhelper.oss.aws;

import io.github.ye17186.myhelper.core.utils.IdUtils;
import io.github.ye17186.myhelper.core.utils.JsonUtils;
import io.github.ye17186.myhelper.oss.MhAwsService;
import io.github.ye17186.myhelper.oss.aotuconfigure.MhOssAutoConfiguration;
import io.github.ye17186.myhelper.core.oss.result.OssPutResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author yemeng20
 * @date 2023-04-11
 */
@Slf4j
@SpringBootTest(classes = {MhOssAutoConfiguration.class})
@TestPropertySource("classpath:application-test.properties")
public class MhAwsServiceTest {

    @Autowired
    MhAwsService service;

    @Test
    void putObj() throws IOException {

        Path path = Paths.get(".\\wx_head.jpg");
        String bucket = "fhm-public";
        String key = IdUtils.uuid() + "_wx_head.jpg";
        OssPutResult result = service.putObj("fhm-public", key, Files.newInputStream(path));
        System.out.println(JsonUtils.obj2Json(result));
        System.out.println(JsonUtils.obj2Json(service.getUrl(bucket, key)));
        System.out.println(JsonUtils.obj2Json(service.getUrl(bucket, key, 10 * 60)));
    }

    @Test
    void getUrl() throws IOException {

        Path path = Paths.get(".\\wx_head.jpg");
        String bucket = "fhm-public";
        String key = IdUtils.uuid() + "_wx_head.jpg";
        OssPutResult result = service.putObj("fhm-public", key, Files.newInputStream(path));
        System.out.println(JsonUtils.obj2Json(result));
    }

    @Test
    void getUrl2() throws IOException {

        Path path = Paths.get(".\\wx_head.jpg");
        String bucket = "fhm-public";
        String key = IdUtils.uuid() + "_wx_head.jpg";
        service.putObj("fhm-public", key, Files.newInputStream(path));
        System.out.println(JsonUtils.obj2Json(service.getUrl(bucket, key, 10 * 60)));
    }
}