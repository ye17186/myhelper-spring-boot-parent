package io.github.ye17186.myhelper.core.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author ye17186
 * @since 2023-01-09
 */
@Setter
@Getter
public class DelayedEvent implements Delayed {

    /**
     * 事件发送的时间
     */
    private long timestamp;

    /**
     * 延时消费的时间，单位毫秒
     */
    private long delay;

    /**
     * 源事件对象
     */
    private Object source;

    /**
     * 创建一个延时事件
     *
     * @param source 源事件
     * @param delay  延时消费时间，单位毫秒
     * @return
     */
    public static DelayedEvent create(Object source, long delay) {

        DelayedEvent event = new DelayedEvent();
        event.setSource(source);
        event.setDelay(delay);
        event.setTimestamp(System.currentTimeMillis());
        return event;
    }

    @Override
    public long getDelay(@NonNull TimeUnit unit) {

        long current = System.currentTimeMillis();
        long sourceDuration = timestamp + delay - current;
        return unit.convert(sourceDuration, unit);
    }

    @Override
    public int compareTo(@NonNull Delayed o) {

        long delta = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return (int) delta;
    }
}
