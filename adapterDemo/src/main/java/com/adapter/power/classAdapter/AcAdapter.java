package com.adapter.power.classAdapter;

/**
 * 类适配器
 */
public class AcAdapter extends Ac220 implements Ac5 {
    public int output() {
        int in = super.input();
        return in / 44;
    }
}
