package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;

public class TestChm {
    @Test
    public void test(){
        int i = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));
        int i1 = (i << 16) + 2;
        System.err.println(i1);


        ArrayBlockingQueue<String> queue=new ArrayBlockingQueue<String>(5);
        try {
            queue.put("aaa");
            queue.offer("1111");
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
