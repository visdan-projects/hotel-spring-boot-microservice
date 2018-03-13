package com.visdan.hotel.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.visdan.hotel.gateway.utils.FilterConstants;
import com.visdan.hotel.gateway.utils.FilterUtils;

@Component
public class TraceFilter extends ZuulFilter {
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static final String FILTER_TYPE = FilterConstants.PRE_FILTER_TYPE.getValue();
	
	@Autowired
	private FilterUtils filterUtils;
	
	@Autowired
	Tracer tracer;
	
	@Override
	public Object run() {
		if (filterUtils.getTraceId() == null) {
			filterUtils.setTraceId(tracer.getCurrentSpan().traceIdString());
		}
		
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
