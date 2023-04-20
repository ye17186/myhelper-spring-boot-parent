package io.github.ye17186.myhelper.job.executor;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import io.github.ye17186.myhelper.core.job.MhJob;
import io.github.ye17186.myhelper.job.handler.BeanJobHandler;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author yemeng20
 * @date 2023-04-17
 */
public class MhJobSpringExecutor extends XxlJobSpringExecutor {

    @Override
    public void afterSingletonsInstantiated() {
        super.afterSingletonsInstantiated();
    }

    private void initJobHandlerBeanRepository(ApplicationContext applicationContext) {

        if (applicationContext != null) {
            // init job handler from bean
            Map<String, MhJob> beanHandlers = applicationContext.getBeansOfType(MhJob.class, false, true);
            beanHandlers.forEach((name, v) -> registJobHandler(name, new BeanJobHandler(v)));
        }

        super.afterSingletonsInstantiated();
    }
}
