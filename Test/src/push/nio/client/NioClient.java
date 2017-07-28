package push.nio.client;

import java.io.IOException;

public class NioClient {

	public static void main(String[] args) throws IOException {
		int port=8080;
		//port=Integer.valueOf(args[0]);
        new Thread(new AsyncNioClientHandler("127.0.0.1",port),"nioclient-001").start();
	}

}
