package com.adapter.power.objectAdapter;

public class Test {
    public static void main(String[] args) {
        AcAdapter adapter = new AcAdapter(new Ac220());
        System.err.println("输出电压 : " + adapter.output() + "V");
    }
}
