package com.ye186.thirdparty.myhelper.core.event;

import com.ye186.thirdparty.myhelper.core.autoconfigure.properties.MyHelperCoreProperties;
import com.ye186.thirdparty.myhelper.core.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;

/**
 * 事件发布器
 *
 * @author ye17186
 * @date 2023-01-09
 */
@Slf4j
public class EventPublisher implements ApplicationEventPublisher {

    private final ApplicationContext context;
    private final MyHelperCoreProperties properties;
    private final boolean delayEventEnable;

    public EventPublisher(ApplicationContext context, MyHelperCoreProperties properties) {
        this.context = context;
        this.properties = properties;
        this.delayEventEnable = properties.getDelayEvent().getEnabled();
    }

    /**
     * 发布实时事件
     *
     * @param event 事件对象
     */
    @Override
    public void publishEvent(@NonNull Object event) {

        log.info("【事件发布】{}", JsonUtils.obj2Json(event));
        context.publishEvent(event);
    }

    /**
     * 发布延时事件
     *
     * @param event 时间对象
     * @param delay 延时指定时间后才会被监听
     */
    public void publishEvent(@NonNull Object event, long delay) {

        if (this.delayEventEnable) {
            DelayedEvent delayEvent = DelayedEvent.create(event, delay);
            boolean suc = DelayedEventQueueHolder.QUEUE.offer(delayEvent);
            log.info("【事件发布】延时事件放入队列。结果：{}，延时：{}ms，事件：{}", suc, delay, JsonUtils.obj2Json(event));
        } else {
            log.warn("【事件发布】延时事件未启用，直接发布实时事件。");
            publishEvent(event);
        }
    }
}
