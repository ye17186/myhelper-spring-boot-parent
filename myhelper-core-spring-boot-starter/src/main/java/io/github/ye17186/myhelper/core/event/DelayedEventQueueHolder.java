package io.github.ye17186.myhelper.core.event;

import io.github.ye17186.myhelper.core.utils.Asserts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.DelayQueue;

/**
 * @author ye17186
 * @since 2023-01-13
 */
@Slf4j
public class DelayedEventQueueHolder implements ApplicationRunner {

    protected static final DelayQueue<DelayedEvent> QUEUE = new DelayQueue<>();

    private final EventPublisher publisher;
    private final ThreadPoolTaskExecutor executor;

    public DelayedEventQueueHolder(EventPublisher publisher, ThreadPoolTaskExecutor executor) {
        this.publisher = publisher;
        this.executor = executor;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Asserts.notNull(executor, "异步任务执行器异常");

        executor.submit(() -> {
            log.info("【DelayedEventQueueHolder】启动延时事件存储队列，开始监听延时事件。");
            while (true) {
                try {
                    DelayedEvent event = QUEUE.take();
                    publisher.publishEvent(event.getSource());
                } catch (InterruptedException e) {
                    log.info("【DelayedEventQueueHolder】监听线程被打断，清空延时队列。");
                    QUEUE.clear();
                    break;
                }
            }
        });
    }
}
