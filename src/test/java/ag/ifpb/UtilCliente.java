package ag.ifpb;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 *
 * @author natan
 */
public class UtilCliente {
	static final Random random = new Random();
	static final char[] operations = {'-', '+'};

    public static ByteBuffer prepare() {
    	//
    	int n1 = random.nextInt(10);
        int n2 = random.nextInt(10);
        char operation = operations[random.nextInt(2)];
        //
        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.putInt(n1);
        allocate.putInt(n2);
        allocate.putChar(operation);
        //
        System.out.println(n1 + " " + operation + " (" + n2 + ") =");
        //
        return allocate;
    }
    
    public static ByteBuffer prepareWithResult(int result, ByteBuffer allocate) {
    	//
    	int n1 = random.nextInt(10);
        int n2 = result;
        char operation = operations[random.nextInt(2)];
        //
        allocate.putInt(n1);
        allocate.putInt(n2);
        allocate.putChar(operation);
        //
        System.out.println(n1 + " " + operation + " (" + n2 + ") =");
        //
        return allocate;
    }
    
}
