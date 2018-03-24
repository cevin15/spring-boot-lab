package com.youbenzi;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreFilter extends ZuulFilter {

	private static Logger log = Logger.getLogger(PreFilter.class.getName());
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println();
		log.info("Pre过滤器1，请求的servlet path 为" + RequestContext.getCurrentContext().getRequest().getServletPath());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
