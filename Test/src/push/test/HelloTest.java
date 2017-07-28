package push.test;

public class HelloTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   //HelloProxy proxy= new HelloProxy();
   //proxy.sayHello("zhangsuan");
		//DynamicProxy dynamicProxy= new DynamicProxy(new HelloImpl());
		//Hello hello=dynamicProxy.getPorxy();
		//hello.sayHello("lisi");
		//CGLibProxy cglibproxy= new CGLibProxy();
		Hello hello= CGLibProxy.getCglibproxy().getProxy(HelloImpl.class);
		hello.sayHello("wangwu");
	}

}
