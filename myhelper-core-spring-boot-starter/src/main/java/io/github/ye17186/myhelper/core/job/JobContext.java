package io.github.ye17186.myhelper.core.job;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ye17186
 * @date 2023-04-17
 */
@Setter
@Getter
public class JobContext implements Serializable {

    /**
     * 调度任务ID
     */
    private long jobId;

    /**
     * 任务参数
     */
    private String jobParam;

    /**
     * 分片index
     */
    private int shardIndex = 0;

    /**
     * 总分片
     */
    private int shardTotal = 1;
}
