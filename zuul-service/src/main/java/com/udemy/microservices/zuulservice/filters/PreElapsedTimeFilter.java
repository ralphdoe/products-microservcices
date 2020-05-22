package com.udemy.microservices.zuulservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreElapsedTimeFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(PreElapsedTimeFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        LOG.info("Joining to Pre Filter");
        final RequestContext requestContext = RequestContext.getCurrentContext();
        final HttpServletRequest httpServletRequest = requestContext.getRequest();
        final Long initialTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("initialTime", initialTime);
        LOG.info(String.format("%s Request routed to %s", httpServletRequest.getMethod(), httpServletRequest.getRequestURL().toString()));
        return null;
    }
}
