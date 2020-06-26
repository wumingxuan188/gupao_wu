package com.example.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLocke {

    static Lock lock=new ReentrantLock();

    public static void main(String[] args) {

        lock.lock();
        System.err.println("这是按顺序输出的");

        lock.unlock();
    }

}
