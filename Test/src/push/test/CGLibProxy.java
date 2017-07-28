package push.test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor{
	
	private static CGLibProxy cglibproxy= new CGLibProxy();
	public static CGLibProxy getCglibproxy() {
		return cglibproxy;
	}

	private CGLibProxy(){
		
	}
    
	public <T> T getProxy(Class<T> cls){
		return (T)Enhancer.create(cls, this);
	}
	
	@Override
	public Object intercept(Object obj, Method methos, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		before();
		Object  result=proxy.invokeSuper(obj,args);
		after();
		return result;
	}

	private void after() {
		// TODO Auto-generated method stub
		System.out.println("after");
	}

	private void before() {
		// TODO Auto-generated method stub
		System.out.println("before");
	}

}
