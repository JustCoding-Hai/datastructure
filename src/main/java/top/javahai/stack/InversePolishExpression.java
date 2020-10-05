package top.javahai.stack;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 计算逆波兰表达式的计算器
 *
 * 这里没有考虑括号的情况
 *
 * @author Hai
 * @date 2020/9/24 - 23:34
 */
public class InversePolishExpression {
    public static void main(String[] args) {
        //20+5*4-4->54*20+4- =18
        String s="5 4 * 20 + 4 -";
        List<String> list = addExpressionToList(s);
        System.out.println(calculate(list));


    }



    /**
     * 将输入的逆波兰表达式放入list中
     * @param expression
     * @return
     */
    public static List<String> addExpressionToList(String expression){
        ArrayList<String> list = new ArrayList<>();
        String[] split = expression.split("\\s+");
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    /**
     * 使用栈计算逆波兰表达式
     *1.遇到运算符就弹出两个数进行计算，结果放入到栈中
     *2.不是运算符就直接放入栈中
     *3.
     *
     * @return
     */
    public static int calculate(List<String> expressionList){
        Stack<String> stack=new Stack();

        for (String s : expressionList) {
            if (s.matches("\\d+")){
                stack.push(s);
            }else{
                Integer num2 = Integer.parseInt(stack.pop());
                Integer num1 = Integer.parseInt(stack.pop());
                //根据运算符进行计算
                Integer result;
                switch (s){
                    case "+":
                        result=num1+num2;
                        break;
                    case "-":
                        result=num1-num2;
                        break;
                    case "*":
                        result=num1*num2;
                        break;
                    case "/":
                        result=num1/num2;
                        break;
                    default:
                        result=0;
                }
                stack.push(String.valueOf(result));
            }
        }

        return Integer.valueOf(stack.pop());
    }
}
