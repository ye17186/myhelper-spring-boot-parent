package io.github.ye17186.myhelper.web.context;

import io.github.ye17186.myhelper.core.web.context.user.MhContextUser;
import io.github.ye17186.myhelper.token.model.LoginKey;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 用户信息缓存服务
 *
 * @author ye17186
 * @since 2023-03-16
 */
public interface MhUserCacheService {

    /**
     * 获取并缓存上下文用户信息
     *
     * @param key 登录唯一标识
     */
    @NonNull
    MhContextUser getAndCache(@NotNull LoginKey key, Map<String, Object> params);

    /**
     * 获取缓存中的上下文用户信息
     *
     * @param key 登录唯一标识
     */
    @Nullable
    MhContextUser getCacheOnly(@NotNull LoginKey key);

    /**
     * 移除登录用户上下文
     *
     * @param key 登录唯一标识
     */
    void remove(@NotNull LoginKey key);
}
