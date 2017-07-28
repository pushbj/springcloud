package push.test;

public class HelloProxy implements Hello {
    
	private Hello hello;
	
	public HelloProxy(){
		this.hello=new HelloImpl();
	}
	@Override
	public void sayHello(String name) {
		before();
		hello.sayHello(name);
		after();

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
