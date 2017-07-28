package com.push;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class QueryParamPerFilter  extends ZuulFilter{
	@Override
	public int filterOrder() {
		return  7; // run before PreDecoration
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return true;
	}
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		/*if (request.getParameter("foo") != null) {
		    // put the serviceId in `RequestContext`
    		ctx.put("foo", request.getParameter("foo"));
    	}*/
        return null;
    }
}
