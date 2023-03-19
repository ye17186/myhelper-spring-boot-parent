package com.ye186.thirdparty.myhelper.web.context;

import com.ye186.thirdparty.myhelper.core.web.context.user.MhContextUser;
import org.springframework.lang.NonNull;

/**
 * 用户信息缓存服务
 *
 * @author ye17186
 * @date 2023-03-16
 */
public interface MhUserCacheService {

    /**
     * 获取并缓存上下文用户信息
     *
     * @param loginId 登录唯一标识
     */
    MhContextUser getAndCache(@NonNull String loginType, @NonNull String loginId);

    /**
     * 移除登录用户上下文
     *
     * @param loginId 登录唯一标识
     */
    void remove(@NonNull String loginType, @NonNull String loginId);
}
