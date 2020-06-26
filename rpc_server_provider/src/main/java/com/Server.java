package com;

import com.server.RpcServer;
import com.serviceImpl.HelloServiceImpl;

public class Server {
    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        RpcServer server = new RpcServer(8080,helloService);
        server.publish();
    }
}
