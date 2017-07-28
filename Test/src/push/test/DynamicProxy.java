package push.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler{
	
	private Object target;
	
	public DynamicProxy(Object obj){
		this.target=obj;
	}
    
	public <T> T getPorxy(){
		return (T)Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				this				
				);
	}
	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		before();
		Object result=method.invoke( target,args);
		after();
		return result;
	}

	private void after() {
		// TODO Auto-generated method stub
		System.out.println("fater");
	}

	private void before() {
		// TODO Auto-generated method stub
		System.out.println("before");
	}

}
