package com.interpreter.calculator;

import java.lang.management.ThreadInfo;
import java.util.Stack;

public class Calculator {
   private Stack<IArithmeticInterpreter> data=new Stack<IArithmeticInterpreter>();
   private Stack<IArithmeticInterpreter> data1=new Stack<IArithmeticInterpreter>();

   public Calculator(String expression) {
        pars(expression);
    }

    private void pars(String expression ) {

        String[] expressions = expression.split(" ");
        IArithmeticInterpreter left ,right;
        //1+3*4+1
        for (int i = 0; i < expressions.length; i++) {
            String operator = expressions[i];
            if(ExpressionUntil.check(operator)){
                if(expressions[i=i+2].equals("*")||operator.equals("/")){

                }
                left=this. data.pop();
                right=new NumInterpreter(Integer.valueOf(expressions[++i]));
               this.data.push(ExpressionUntil.getInterpreter(left,right,operator)) ;

            }else {
                NumInterpreter numInterpreter = new NumInterpreter(Integer.valueOf(expressions[i]));
                this.data.push(numInterpreter);
            }
        }
    }
    public int getValue(){
       return this.data.pop().interpreter();
    }
}
