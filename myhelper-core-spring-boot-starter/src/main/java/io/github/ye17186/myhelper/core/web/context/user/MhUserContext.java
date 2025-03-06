package io.github.ye17186.myhelper.core.web.context.user;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

/**
 * 用户上下文
 *
 * @author ye17186
 * @since 2022-09-25
 */
@Setter
@Getter
public abstract class MhUserContext implements Serializable {

    private static final  TransmittableThreadLocal<MhContextUser> HOLDER = new TransmittableThreadLocal<>();

    /**
     * 设置当前登录用户的信息
     *
     * @param value 用户信息
     */
    public static void set(MhContextUser value) {

        HOLDER.set(value);
    }

    public static MhContextUser get() {

        return HOLDER.get();
    }

    public static void clear() {

        HOLDER.remove();
    }

    public static String mhToken() {

        return Optional.ofNullable(get()).map(MhContextUser::getMhToken).orElse("");
    }

    public static String mhLoginKey() {

        return Optional.ofNullable(get()).map(MhContextUser::getLoginKey).orElse("");
    }
}
