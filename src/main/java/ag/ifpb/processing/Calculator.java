package ag.ifpb.processing;

import java.nio.ByteBuffer;

public class Calculator {

	public static int process(ByteBuffer msg){
		return UtilServidor.processor(msg);
	}
	
	public static int process(String msg){
		return UtilServidor.processor(msg);
	}
}
