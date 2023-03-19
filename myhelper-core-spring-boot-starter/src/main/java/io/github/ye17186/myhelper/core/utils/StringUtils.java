package io.github.ye17186.myhelper.core.utils;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ye17186
 * @since 2022-11-05
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static List<String> split2List(String str) {

        return isEmpty(str) ? Lists.newArrayList() : Lists.newArrayList(split(str, ","));
    }
}
