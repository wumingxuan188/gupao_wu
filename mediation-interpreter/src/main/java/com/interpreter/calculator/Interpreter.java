package com.interpreter.calculator;

//终结终止符
public  abstract  class Interpreter implements IArithmeticInterpreter {
    protected  IArithmeticInterpreter left;
    protected  IArithmeticInterpreter right;

    public Interpreter(IArithmeticInterpreter left, IArithmeticInterpreter right) {
        this.left = left;
        this.right = right;
    }
}
