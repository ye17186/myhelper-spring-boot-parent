package io.github.ye17186.myhelper.web.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yemeng20
 * @since 2023-03-19
 */
@Setter
@Getter
public class MhWebAsyncProperties {

    /**
     * 线程名前缀
     */
    private String threadNamePrefix = "mh-task-pool-";

    /**
     * 核心线程数
     */
    private int corePoolSize = 10;

    /**
     * 最大线程数
     */
    private int maxPoolSize = 50;
}
