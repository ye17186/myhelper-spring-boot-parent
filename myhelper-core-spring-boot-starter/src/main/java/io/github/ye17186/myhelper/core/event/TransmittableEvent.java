package io.github.ye17186.myhelper.core.event;

import com.google.common.collect.Maps;
import io.github.ye17186.myhelper.core.web.context.user.MhContextUser;
import io.github.ye17186.myhelper.core.web.context.user.MhUserContext;

import java.util.Map;

/**
 * @author ye17186
 * @since 2023-01-30
 */
public class TransmittableEvent {

    private static final String KEY_CONTEXT_USER = "ContextUser";

    private final Map<String, Object> transmittableMap = Maps.newHashMap();

    public TransmittableEvent() {

        transmittableMap.put(KEY_CONTEXT_USER, MhUserContext.get());
    }

    public MhContextUser getUser() {
        return (MhContextUser) transmittableMap.get(KEY_CONTEXT_USER);
    }
}
