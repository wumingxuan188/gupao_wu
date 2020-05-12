package com.interpreter.calculator;

public class NumInterpreter implements  IArithmeticInterpreter {
    private  int value;

    public NumInterpreter(int value) {
        this.value = value;
    }

    public int interpreter() {
        return this.value;
    }
}
