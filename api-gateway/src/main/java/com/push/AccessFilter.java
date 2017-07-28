package com.push;




import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter{

	  private static Logger log=LoggerFactory.getLogger(AccessFilter.class);
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request= ctx.getRequest();
		System.out.println("abc--parameter:"+request.getParameter("abc"));
		log.info("send{} request to{}  ----"+request.getParameter("abc"),request.getMethod(),request.getRequestURL().toString());
		
		Object accessToken=request.getParameter("accessToken");
		/*if(accessToken==null){
			log.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}*/
		log.info("access token ok");
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 6;
	}

}
