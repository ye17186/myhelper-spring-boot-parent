package io.github.ye17186.myhelper.web.context;

import io.github.ye17186.myhelper.core.web.context.user.MhContextUser;
import io.github.ye17186.myhelper.token.model.LoginKey;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

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
    MhContextUser getAndCache(@NotNull LoginKey key);

    /**
     * 移除登录用户上下文
     *
     * @param key 登录唯一标识
     */
    void remove(@NotNull LoginKey key);
}
