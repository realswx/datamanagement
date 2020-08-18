package com.dm.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    private static final Logger log = LoggerFactory.getLogger(StringUtils.class);
    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

//    /**
//     * 根据ip获取详细地址
//     */
//    public static String getCityInfo(String ip) {
//        if (ipLocal) {
//            return getLocalCityInfo(ip);
//        } else {
//            return getHttpCityInfo(ip);
//        }
//    }
//
//    /**
//     * 根据ip获取详细地址
//     */
//    public static String getHttpCityInfo(String ip) {
//        String api = String.format(ElAdminConstant.Url.IP_URL, ip);
//        JSONObject object = JSONUtil.parseObj(HttpUtil.get(api));
//        return object.get("addr", String.class);
//    }
//
//
//    /**
//     * 根据ip获取详细地址
//     */
//    public static String getLocalCityInfo(String ip) {
//        try {
//            DataBlock dataBlock = new DbSearcher(config, file.getPath())
//                    .binarySearch(ip);
//            String region = dataBlock.getRegion();
//            String address = region.replace("0|", "");
//            char symbol = '|';
//            if (address.charAt(address.length() - 1) == symbol) {
//                address = address.substring(0, address.length() - 1);
//            }
//            return address.equals(ElAdminConstant.REGION) ? "内网IP" : address;
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return "";
//    }

    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }

}
