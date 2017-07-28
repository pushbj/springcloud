package push.nio.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsyncNioClientHandler implements CompletionHandler<Void,AsyncNioClientHandler>, Runnable {

	private AsynchronousSocketChannel client; 
	private String host;
	private int port;
	private CountDownLatch latch;
	
	public AsyncNioClientHandler(String host, int port) throws IOException {
		this.host=host;
		this.port=port;
		client=AsynchronousSocketChannel.open();
	}

	@Override
	public void run() {
		latch= new CountDownLatch(1);
		client.connect(new InetSocketAddress(host,port), this, this);
        try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void completed(Void result, AsyncNioClientHandler attachment) {
		byte[] req="nioserver".getBytes();
		ByteBuffer writeBuffer= ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		client.write(writeBuffer,writeBuffer,new CompletionHandler<Integer,ByteBuffer>(){

			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				if(buffer.hasRemaining()){
					client.write(buffer,buffer,this);
				}else{
					ByteBuffer readBuffer=ByteBuffer.allocate(1024);
					client.read(readBuffer, readBuffer,new CompletionHandler<Integer,ByteBuffer>(){

						@Override
						public void completed(Integer result, ByteBuffer buffer) {
							buffer.flip();
							byte[] bytes=new byte[buffer.remaining()];
							buffer.get(bytes);
							String body;
							try {
								body=new String(bytes,"UTF-8");
								System.out.println("now is:"+body);
								latch.countDown();
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							
						}

						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							// TODO Auto-generated method stub
							try {
								client.close();
								latch.countDown();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					});
					
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				// TODO Auto-generated method stub
				try {
					client.close();
					latch.countDown();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		});
		
		
	}

	@Override
	public void failed(Throwable exc, AsyncNioClientHandler attachment) {
		// TODO Auto-generated method stub
		try {
			client.close();
			latch.countDown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
