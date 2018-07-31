package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import junit.framework.TestCase;

public class AppTest extends TestCase{

    public void testApp() throws IOException{
      Socket socket = new Socket("ag-heroku-java.herokuapp.com", 80);
      //
      socket.getOutputStream().write("HTTP 1.1\n".getBytes());
      socket.getOutputStream().flush();
      //
      InputStreamReader reader = new InputStreamReader(socket.getInputStream());
      BufferedReader buffer = new BufferedReader(reader);
      //
      System.out.println(buffer.readLine());
      //socket.shutdownInput();
      //
      socket.close();
    }
}
