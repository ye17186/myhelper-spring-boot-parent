package com.ye186.thirdparty.myhelper.web.utils;

import com.ye186.thirdparty.myhelper.core.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ye17186
 * @date 2022-10-13
 */
public class RequestUtils {

    public static String getClientIp(HttpServletRequest request) {

        if (request == null) {
            return "0.0.0.0";
        }

        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");

        String UNKNOWN_IP = "unknown";
        if (StringUtils.isNotEmpty(XFor) && !UNKNOWN_IP.equalsIgnoreCase(XFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            } else {
                return XFor;
            }
        }

        XFor = Xip;
        if (StringUtils.isNotEmpty(XFor) && !UNKNOWN_IP.equalsIgnoreCase(XFor)) {
            return XFor;
        }

        if (StringUtils.isBlank(XFor) || UNKNOWN_IP.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || UNKNOWN_IP.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || UNKNOWN_IP.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || UNKNOWN_IP.equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || UNKNOWN_IP.equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}
