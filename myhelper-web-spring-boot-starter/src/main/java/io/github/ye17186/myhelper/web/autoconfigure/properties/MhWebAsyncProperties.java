package io.github.ye17186.myhelper.web.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ye17186
 * @since 2023-03-19
 */
@Setter
@Getter
public class MhWebAsyncProperties {

    /**
     * 线程名前缀
     */
    private String namePrefix = "mh-task-pool-";

    /**
     * 核心线程数
     */
    private int coreSize = 10;

    /**
     * 最大线程数
     */
    private int maxSize = 50;
}
