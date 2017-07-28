package async;
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String ping) {
		// TODO Auto-generated method stub
		return ping !=null?ping+"-->i am ok.":"i am ok.";
	}

}
