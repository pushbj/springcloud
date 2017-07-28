package push.nio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncNioServerHandler> {

	public void completed(AsynchronousSocketChannel result, AsyncNioServerHandler attachment) {
		attachment.asyncChannel.accept(attachment,this);
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		result.read(buffer,buffer,new ReadCompletionHandler(result));
		

	}

	@Override
	public void failed(Throwable exc, AsyncNioServerHandler attachment) {
		// TODO Auto-generated method stub
		
	}

}
