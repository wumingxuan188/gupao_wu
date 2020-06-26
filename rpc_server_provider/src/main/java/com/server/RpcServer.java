package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RpcServer {

    private  int port;
private Object service;
    public RpcServer(int port,Object service) {
        this.port = port;
        this.service=service;
    }

    private Executor pool = Executors.newCachedThreadPool();

   public void publish(){
       ServerSocket serverSocket=null;
       try {
            serverSocket=new ServerSocket(port);
           Socket socket = serverSocket.accept();
           pool.execute(new HandleMethod(socket,service));

       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
