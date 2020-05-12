package com.adapter.power.objectAdapter;

/**
 * 对象适配器
 */
public class AcAdapter implements Ac5 {
    private Ac220 ac220;

    public AcAdapter(Ac220 ac220) {
        this.ac220 = ac220;
    }

    public int output() {
        int in = ac220.input();
        return in / 44;
    }
}
