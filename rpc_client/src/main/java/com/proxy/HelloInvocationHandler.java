package com.proxy;

import com.pojo.RequestPkg;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class HelloInvocationHandler implements InvocationHandler {
   private  String ip;

   private int port;

    public HelloInvocationHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestPkg pkg = new RequestPkg();
        pkg.setClassName(method.getDeclaringClass().getName());
        pkg.setMethodName(method.getName());
        pkg.setTypes(method.getParameterTypes());
        pkg.setArgs(args);
        Socket socket=new Socket(ip,port);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(pkg);
        ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
        Object o = ois.readObject();
        return o;
    }
}
