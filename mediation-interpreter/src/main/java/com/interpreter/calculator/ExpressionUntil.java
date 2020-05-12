package com.interpreter.calculator;

//import com.sun.istack.internal.NotNull;

//import org.jetbrains.annotations.NotNull;

public class ExpressionUntil {

    public static  boolean check( String expr){
        if(expr.equals("+")||expr.equals("-")||expr.equals("*")||expr.equals("/")) return true;
        return false;
    }

    public static  IArithmeticInterpreter getInterpreter(IArithmeticInterpreter left,IArithmeticInterpreter right,String operator){
        if("+".equals(operator)){
           return new AddInterpreter(left,right);
        }else if("-".equals(operator)){
            return new SubInterpreter(left,right);
        }else if("*".equals(operator)){
            return new MultiplicationInterpreter(left,right);
        }
        else if("/".equals(operator)){
            return new DivInterpreter(left,right);
        }
        return null;
    }
}
