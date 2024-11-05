package org.slf4j;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;
import org.slf4j.spi.MDCAdapter;

import java.util.Collections;
import java.util.Map;

/**
 * @author ye17186
 */
public class TtlLogbackMDCAdapter implements MDCAdapter {
    private static final TtlLogbackMDCAdapter ttlAdapter;

    static {
        ttlAdapter = new TtlLogbackMDCAdapter();
        MDC.mdcAdapter = ttlAdapter;
    }

    public static MDCAdapter getInstance() {
        return ttlAdapter;
    }


    final ThreadLocal<Map<String, String>> copyOnThreadLocal = new TransmittableThreadLocal<>();

    @Override
    public void put(String key, String value) {

        Map<String, String> map = copyOnThreadLocal.get();
        map = map == null ? Maps.newHashMap() : Maps.newHashMap(map);
        map.put(key, value);
        copyOnThreadLocal.set(map);
    }

    @Override
    public void remove(String key) {

        final Map<String, String> map = copyOnThreadLocal.get();
        if (map != null) {
            final Map<String, String> copy = Maps.newHashMap(map);
            copy.remove(key);
            copyOnThreadLocal.set(Collections.unmodifiableMap(copy));
        }
    }

    @Override
    public void clear() {

        copyOnThreadLocal.remove();
    }

    @Override
    public String get(String key) {

        final Map<String, String> map = copyOnThreadLocal.get();
        return map == null ? null : map.get(key);
    }

    @Override
    public Map<String, String> getCopyOfContextMap() {

        Map<String, String> hashMap = this.copyOnThreadLocal.get();
        return hashMap == null ? null : Maps.newHashMap(hashMap);
    }

    @Override
    public void setContextMap(Map<String, String> contextMap) {

        Map<String, String> newMap = Maps.newHashMap(contextMap);
        this.copyOnThreadLocal.set(newMap);
    }
}
