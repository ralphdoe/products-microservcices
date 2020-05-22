package com.udemy.microservices.zuulservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostElapsedTimeFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(PostElapsedTimeFilter.class);
    @Override
    public String filterType() {
        return "post";
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
        LOG.info("Joining to Post Filter");
        final HttpServletRequest httpServletRequest = RequestContext.getCurrentContext().getRequest();
        final Long initialTime = (Long) httpServletRequest.getAttribute("initialTime");
        final Long finalTime = System.currentTimeMillis();
        final long elapsedTime = finalTime - initialTime;

        LOG.info(String.format("Elapsed time in milliseconds %s", elapsedTime));
        LOG.info(String.format("Elapsed time in seconds %s", elapsedTime/1000));
        return null;
    }
}
