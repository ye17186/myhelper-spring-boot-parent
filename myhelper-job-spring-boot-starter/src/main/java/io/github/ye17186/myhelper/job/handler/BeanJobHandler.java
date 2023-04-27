package io.github.ye17186.myhelper.job.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import io.github.ye17186.myhelper.core.job.MhJob;
import io.github.ye17186.myhelper.core.job.JobContext;

/**
 * @author ye17186
 * @date 2023-04-17
 */
public class BeanJobHandler extends IJobHandler {

    private final MhJob jobBean;

    public BeanJobHandler(MhJob jobBean) {

        this.jobBean = jobBean;
    }

    @Override
    public final void execute() throws Exception {

        JobContext context = buildContext();
        jobBean.execute(context);
    }

    private JobContext buildContext() {

        JobContext context = new JobContext();
        context.setJobId(XxlJobHelper.getJobId());
        context.setJobParam(XxlJobHelper.getJobParam());
        context.setShardIndex(XxlJobHelper.getShardIndex());
        context.setShardTotal(XxlJobHelper.getShardTotal());
        return context;
    }
}
