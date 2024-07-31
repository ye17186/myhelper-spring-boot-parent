package io.github.ye17186.myhelper.core.job;

/**
 * MyHelper分布式调度任务接口
 *
 * @author ye17186
 * @date 2023-04-17
 */
public interface MhJob {

    void execute(JobContext context);
}
