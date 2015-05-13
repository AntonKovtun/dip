package com.sulin.frontend.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class BaseLogInterceptor extends HandlerInterceptorAdapter {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        Map<String, String[]> parameters = request.getParameterMap();

        StringBuilder sb = new StringBuilder();
        for (String k : parameters.keySet()) {
            sb.append(k).append('=');
            String[] values = parameters.get(k);
            for (int i = 0; i < values.length; i++) {
                sb.append(values[i]);
                if (i != values.length - 1)
                    sb.append(", ");
            }
            sb.append(", ");
        }

        if (sb.length() > 1)
            sb.delete(sb.length() - 2, sb.length());

        if (sb.length() > 0)
            log.info("{} {} (ThreadId={}) parameters: {}", new Object[] {
                    request.getMethod(), request.getRequestURI(),
                    Thread.currentThread().getId(), sb.toString() });
        else
            log.info("{} {} (ThreadId={})", new Object[] { request.getMethod(),
                    request.getRequestURI(), Thread.currentThread().getId() });

        return true;
    }
}
