package com.dip.frontend.web.interceptor;

import com.dip.frontend.web.security.http.DipHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DipUrlFilter implements Filter {
    protected static final Logger log = LoggerFactory.getLogger(DipUrlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("================ DipUrlFilter handle the request =======================");
        final HttpServletRequest httpRequest = (HttpServletRequest)request;
        final HttpServletResponse httpResponse = (HttpServletResponse)response;

        chain.doFilter(new DipHttpServletRequest(httpRequest, true), response);
    }

    @Override
    public void destroy() {

    }
}
