package com.visdan.hotel.gateway.utils;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

@Component
public class FilterUtils {

	public final String getAuthorizationToken() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.getRequest().getHeader(FilterConstants.AUTHORIZATION_TOKEN.getValue());
	}

	public String getTraceId() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return (ctx.getRequest().getHeader(FilterConstants.TRACE_ID.getValue()) != null)
				? ctx.getRequest().getHeader(FilterConstants.TRACE_ID.getValue())
				: ctx.getZuulRequestHeaders().get(FilterConstants.TRACE_ID.getValue());
	}

	public void setTraceId(String traceId) {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(FilterConstants.TRACE_ID.getValue(), traceId);
	}
}
