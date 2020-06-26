package com.server;

import com.pojo.RequestPkg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class HandleMethod implements Runnable {
    private Socket socket;
    private  Object service;

    public HandleMethod(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois=new ObjectInputStream(  socket.getInputStream())  ;
            RequestPkg requestPkg = (RequestPkg)ois.readObject();
            Object res = invoke(requestPkg);
            oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(ois!=null){
                    ois.close();
                }
                if(oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

  //反射调用方法
    public Object invoke( RequestPkg requestPkg){
        try {
            Class<?> aClass = Class.forName(requestPkg.getClassName());
            Method method = aClass.getMethod(requestPkg.getMethodName(), requestPkg.getTypes());
            Object res = method.invoke(service, requestPkg.getArgs());
            return res;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
