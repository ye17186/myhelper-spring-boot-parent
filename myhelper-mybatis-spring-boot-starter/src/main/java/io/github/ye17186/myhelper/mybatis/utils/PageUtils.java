package io.github.ye17186.myhelper.mybatis.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.github.ye17186.myhelper.core.web.request.PageRequest;
import io.github.ye17186.myhelper.core.web.response.PageResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ye17186
 * @since 2022-10-01
 */
public class PageUtils {

    private PageUtils() {
    }

    public static <T> IPage<T> initPage(PageRequest request) {

        return new Page<>(request.getPageNo(), request.getPageSize());
    }

    public static <T> PageResponse<T> toPage(PageRequest request, List<T> records) {

        long total = records.size();
        Page<T> page = new Page<>(request.getPageNo(), request.getPageSize(), records.size());
        long start = (page.getCurrent() - 1) * page.getSize();
        if (start >= total) {
            page.setRecords(Lists.newArrayList());
        } else {
            long end = Math.min(start + page.getSize(), page.getTotal());
            page.setRecords(records.subList((int) start, (int) end));
        }
        return toPage(page);
    }

    public static <T> PageResponse<T> toPage(IPage<T> page) {

        PageResponse<T> response = new PageResponse<>();
        response.setPageNo(page.getCurrent());
        response.setPageSize(page.getSize());
        response.setTotalPage(page.getPages());
        response.setTotalCount(page.getTotal());
        response.setItems(page.getRecords());
        return response;
    }

    public static <T, R> PageResponse<R> toPage(IPage<T> page, Function<T, R> function) {

        PageResponse<R> response = new PageResponse<>();
        response.setPageNo(page.getCurrent());
        response.setPageSize(page.getSize());
        response.setTotalPage(page.getPages());
        response.setTotalCount(page.getTotal());
        response.setItems(page.getRecords().stream().map(function).collect(Collectors.toList()));
        return response;
    }

    public static <T, R> PageResponse<R> toEmptyPage(IPage<T> page) {

        PageResponse<R> response = new PageResponse<>();
        response.setPageNo(page.getCurrent());
        response.setPageSize(page.getSize());
        response.setTotalPage(0);
        response.setTotalCount(0);
        response.setItems(Lists.newArrayList());
        return response;
    }
}
