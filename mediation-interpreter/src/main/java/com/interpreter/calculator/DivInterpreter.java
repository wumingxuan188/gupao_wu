package com.interpreter.calculator;

public class DivInterpreter extends Interpreter {
    public DivInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        super(left, right);
    }

    public int interpreter() {
        return left.interpreter()/right.interpreter();
    }
}
