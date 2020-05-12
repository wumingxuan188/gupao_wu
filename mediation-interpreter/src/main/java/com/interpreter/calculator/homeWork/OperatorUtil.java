package com.interpreter.calculator.homeWork;

import com.interpreter.*;

import java.util.Stack;

public class OperatorUtil {
    public static boolean isOperator(String symbol) {
        return (symbol.equals("+") || symbol.equals("-") || symbol.equals("*"));
    }

    public static Interpreter getInterpreter(Stack<IArithmeticInterpreter> numStack, Stack<String> operatorStack) {
        IArithmeticInterpreter rightExpr = numStack.pop(); // 第一次：8
        IArithmeticInterpreter leftExpr = numStack.pop(); //第二次：2
        String symbol = operatorStack.pop(); // 第一次：*
       // System.out.println("数字出栈："+rightExpr.interpret()+","+leftExpr.interpret()+",操作符出栈:"+symbol);
        if (symbol.equals("+")) {
            return new AddInterpreter(leftExpr, rightExpr);
        } else if (symbol.equals("-")) {
            return new SubInterpreter(leftExpr, rightExpr);
        } else if (symbol.equals("*")) {
            return new MultiplicationInterpreter(leftExpr, rightExpr);
        } else if (symbol.equals("/")) {
            return new DivInterpreter(leftExpr, rightExpr);
        }
        return null;
    }
}
