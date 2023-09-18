package io.github.ye17186.myhelper.core.excel;

import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ye17186
 * @date 2023/9/14
 */
public abstract class MhExcelListener<T> implements ReadListener<T> {

    /**
     * 任务ID
     */
    protected final String taskId;

    /**
     * 处理成功的行数
     */
    protected int successCount = 0;

    /**
     * 处理失败的行数
     */
    protected int failedCount = 0;

    protected final List<T> failedRows = Lists.newArrayList();

    public MhExcelListener(String taskId) {
        this.taskId = taskId;
    }
}
