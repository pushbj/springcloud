package push.nio.server;

public class NioServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
            int port =8080;
            /*if(args!=null && args.length>0){
            	try{
            		port=Integer.valueOf(args[0]);
            	}catch (NumberFormatException e){
            		
            	}
            }*/
            AsyncNioServerHandler niohandler=new AsyncNioServerHandler(port);
            new Thread(niohandler,"niao-001").start();
            
            
            
	}

}
