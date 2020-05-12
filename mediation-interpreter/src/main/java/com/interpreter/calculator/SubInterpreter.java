package com.interpreter.calculator;

public class SubInterpreter extends Interpreter {
    public SubInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        super(left, right);
    }

    public int interpreter() {
        return left.interpreter()-right.interpreter();
    }
}
