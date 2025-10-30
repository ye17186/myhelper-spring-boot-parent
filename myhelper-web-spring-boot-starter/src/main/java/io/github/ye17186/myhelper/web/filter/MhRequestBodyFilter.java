package io.github.ye17186.myhelper.web.filter;

import io.github.ye17186.myhelper.web.servlet.RepeatableHttpServletRequestWrapper;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ye17186
 */
public class MhRequestBodyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request instanceof RepeatableHttpServletRequestWrapper) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(new RepeatableHttpServletRequestWrapper(request), response);
        }
    }
}
