package com.adapter.power.interfaceAdapter;


public class AcAdapter implements DC {
    private Ac220 ac220;

    public AcAdapter(Ac220 ac220) {
        this.ac220 = ac220;
    }

    public int output5() {
        int input = ac220.input();
        return input / 44;
    }

    public int output12() {
        return 0;
    }

    public int output48() {
        return 0;
    }
}
