package com.sulin.frontend.web.interceptor;

import com.sulin.frontend.web.security.http.SulinHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by: Alexander Duckardt
 * Date: 8/3/14.
 */
public class SulinUrlFilter implements Filter {
    protected static final Logger log = LoggerFactory.getLogger(SulinUrlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("================ SulinUrlFilter handle the request =======================");
        final HttpServletRequest httpRequest = (HttpServletRequest)request;
        final HttpServletResponse httpResponse = (HttpServletResponse)response;

        chain.doFilter(new SulinHttpServletRequest(httpRequest, true), response);
    }

    @Override
    public void destroy() {

    }
}
