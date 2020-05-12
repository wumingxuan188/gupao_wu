package com.interpreter.calculator;

public class AddInterpreter extends Interpreter {
    public AddInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        super(left, right);
    }

    public int interpreter() {
        return left.interpreter()+right.interpreter();
    }
}
