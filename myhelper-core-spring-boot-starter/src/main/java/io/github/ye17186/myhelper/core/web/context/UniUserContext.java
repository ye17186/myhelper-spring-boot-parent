package io.github.ye17186.myhelper.core.web.context;

import io.github.ye17186.myhelper.core.web.context.user.UniContextUser;

import java.util.Map;

/**
 * @author ye17186
 */
public class UniUserContext extends DefaultUserContext {

    public static Object uniUser() {

        return getUser().getUniUser();
    }

    public static Map<String, Object> uniData() {

        return getUser().getUniData();
    }

    public static UniContextUser getUser() {

        return (UniContextUser) get();
    }
}
