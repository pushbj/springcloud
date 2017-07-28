package push.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncNioServerHandler implements Runnable{
	private int port;
	CountDownLatch latch;
	AsynchronousServerSocketChannel asyncChannel;
    
	public AsyncNioServerHandler(int port){
		this.port=port;
		try{
			asyncChannel=AsynchronousServerSocketChannel.open();
			asyncChannel.bind(new InetSocketAddress(port));
		System.out.println("the nioserver is start in port:"+port);
		}catch(IOException e){
			
		}
		
		
	}
	@Override
	public void run() {
		latch =new CountDownLatch(1);
		doAccept();
		try{
			latch.await();
			
		}catch(InterruptedException e){
			e.printStackTrace();
			
		}
		
		
	}
	private void doAccept() {
		asyncChannel.accept(this,new AcceptCompletionHandler());
		
		
	}

}
