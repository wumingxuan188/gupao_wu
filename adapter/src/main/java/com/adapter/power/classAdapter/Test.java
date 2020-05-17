package com.adapter.power.classAdapter;

public class Test {
    public static void main(String[] args) {
        AcAdapter adapter = new AcAdapter();
        int output = adapter.output();
        System.err.println("输出电压 : " + output + " V ");
    }
}
