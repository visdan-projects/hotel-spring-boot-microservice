package com.visdan.hotel.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.visdan.hotel.gateway.utils.FilterConstants;

@Component
public class ResponseFilter extends ZuulFilter {

	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static final String FILTER_TYPE = FilterConstants.POST_FILTER_TYPE.getValue();
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);
	
	@Autowired
	Tracer tracer;
	
	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		context.getResponse().addHeader(FilterConstants.TRACE_ID.getValue(), tracer.getCurrentSpan().traceIdString());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	@Override
	public String filterType() {
		return FILTER_TYPE;
	}
}
