package com.interpreter.calculator;

public class MultiplicationInterpreter extends Interpreter {
    public MultiplicationInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        super(left, right);
    }

    public int interpreter() {
        return left.interpreter()*right.interpreter();
    }
}
