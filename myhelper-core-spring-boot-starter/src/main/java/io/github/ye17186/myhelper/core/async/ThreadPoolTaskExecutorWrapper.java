package io.github.ye17186.myhelper.core.async;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.spi.TtlEnhanced;
import com.alibaba.ttl.spi.TtlWrapper;
import com.alibaba.ttl.threadpool.agent.TtlAgent;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * @author ye17186
 * @since 2022-12-13
 * @deprecated 使用TtlExecutors.getTtlExecutor()代替
 */
@Deprecated
public class ThreadPoolTaskExecutorWrapper extends ThreadPoolTaskExecutor implements TtlWrapper<Executor>, TtlEnhanced {

    private static final long serialVersionUID = 105770736693032615L;

    public static ThreadPoolTaskExecutor wrap(@Nullable ThreadPoolTaskExecutor executor) {

        return !TtlAgent.isTtlAgentLoaded() && executor != null && !(executor instanceof TtlEnhanced) ? new ThreadPoolTaskExecutorWrapper(executor, true) : executor;
    }

    private final ThreadPoolTaskExecutor executor;
    protected final boolean idempotent;

    public ThreadPoolTaskExecutorWrapper(ThreadPoolTaskExecutor executor, boolean idempotent) {
        this.executor = executor;
        this.idempotent = idempotent;
    }

    @Override
    @NonNull
    public Executor unwrap() {

        return this.executor;
    }

    @Override
    public void execute(@NonNull Runnable task) {

        executor.execute(TtlRunnable.get(task));
    }

    @Override
    @Deprecated
    public void execute(@NonNull Runnable task, long startTimeout) {

        executor.execute(TtlRunnable.get(task), startTimeout);
    }

    @Override
    @NonNull
    public Future<?> submit(@NonNull Runnable task) {

        return executor.submit(TtlRunnable.get(task));
    }

    @Override
    @NonNull
    public <T> Future<T> submit(@NonNull Callable<T> task) {

        return executor.submit(TtlCallable.get(task));
    }

    @Override
    @NonNull
    public ListenableFuture<?> submitListenable(@NonNull Runnable task) {

        return executor.submitListenable(TtlRunnable.get(task));
    }

    @Override
    @NonNull
    public <T> ListenableFuture<T> submitListenable(@NonNull Callable<T> task) {

        return executor.submitListenable(TtlCallable.get(task));
    }
}
