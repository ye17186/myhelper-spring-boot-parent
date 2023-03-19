package io.github.ye17186.myhelper.token.utils;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.fun.SaFunction;
import cn.dev33.satoken.listener.SaTokenEventCenter;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @author ye17186
 * @since 2023-03-16
 */
public class MhStpUtils {

    private static Map<String, StpLogic> stpLogicMap = Maps.newHashMap();


    public static void login(String loginId, String loginType) {

        SaManager.getStpLogic(loginType).login(loginId);
    }
}
