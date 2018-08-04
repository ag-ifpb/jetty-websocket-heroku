/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag.ifpb;

import java.nio.ByteBuffer;

public class UtilServidor {
        
    public static int processor(ByteBuffer bb) {
        ByteBuffer wrap = bb;
        int n1 = wrap.getInt();
        int n2 = wrap.getInt();
        char operation = wrap.getChar();
        int result = 0;
        switch(operation) {
            case '+':
                result = n1 + n2;
            break;
                
            case '-':
                result = n1 - n2;
            break;
        }
        //
        System.out.println(" " + n1 + " " + n2 + " " + operation);
        //
        return result;
    }
    
    public static int processor(String msg) {
    	//
        ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
        int n1 = wrap.getInt();
        int n2 = wrap.getInt();
        char operation = wrap.getChar();
        int result = 0;
        switch(operation) {
            case '+':
                result = n1 + n2;
            break;
                
            case '-':
                result = n1 - n2;
            break;
        }
        //
        System.out.println(" " + n1 + " " + n2 + " " + operation);
        //
        return result;
    }
    
}
