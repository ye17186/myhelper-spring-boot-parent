package io.github.ye17186.myhelper.job.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author ye17186
 * @date 2023-04-17
 */
@Setter
@Getter
public class MhJobProperties {

    /**
     * 调度中心地址
     */
    @NotEmpty
    private String serverUrl;

    /**
     * 调度中心访问凭证
     */
    private String accessToken;

    /**
     * 客户端应用名
     */
    @NotEmpty
    private String appName;

    /**
     * 日志目录
     */
    private String logPath = "/export/logs/mh-job/";

    /**
     * 日志保留天数
     */
    private int logRetentionDays = 30;

}
