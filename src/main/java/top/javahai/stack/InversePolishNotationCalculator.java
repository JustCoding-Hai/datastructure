package top.javahai.stack;

import java.awt.event.ItemEvent;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UnknownFormatConversionException;

/**
 * 完整的逆波兰表达式（后缀表达式）计算器
 *
 * 难点：如何将中缀表达式转化为后缀表达式
 * @author Hai
 * @date 2020/10/3 - 16:22
 */
public class InversePolishNotationCalculator {
    public static void main(String[] args) {
        //String expression="12+((3+2)*4)-5";
        String expression=" 2-1 + 2 ";
        List<String> list = toInfixExpressionList(expression);
        System.out.print("InfixExpressionList:");
        list.forEach(System.out::print);
        System.out.println();

        List<String> suffixExpressionList = parseSuffixExpressionList(list);
        System.out.print("SuffixExpressionList:");
        suffixExpressionList.forEach(System.out::print);
        System.out.println();
        System.out.println(calculate(suffixExpressionList));

        //TODO 中缀转后缀错了
        /**
         * 2-1+2
         * s1  +
         * s2  21-2+
         * 原因：思想步骤没做，代码实现我自己写错了
         */
    }


    /**
     * 将中缀表达式转化为对应的List保存
     * 考虑多位数的情况，例：S="12+((3+2)*4)-5"
     *
     * 做法：逐个遍历字符，如果遇到的是非运算符，继续往后看，找到多位数
     * @param infixExpression
     * @return
     */
    public static List<String> toInfixExpressionList(String infixExpression){
        infixExpression=infixExpression.replaceAll("\\s+","");
        System.out.println("infixExpression:"+infixExpression);
        ArrayList<String> infixExpressionList = new ArrayList<>();
        //遍历指针
        int index=0;
        while (index<=infixExpression.length()-1){
            String s=String.valueOf(infixExpression.charAt(index));
            if (!s.matches("\\d")){
                infixExpressionList.add(s);
                index++;
            }else{
                String num=s;
                //往后看一个数字
                while (true){
                    //最后一个字符直接退出循环
                    if (index+1>=infixExpression.length()){
                        index++;
                        break;
                    }
                    String str=String.valueOf(infixExpression.charAt(index+1));
                    if (str.matches("\\d")){
                        num+=str;
                        index=index+1;
                    }else {
                        index++;
                        break;
                    }
                }
                infixExpressionList.add(num);
            }
        }

        return infixExpressionList;

    }


    /**
     * 将中缀表达式的List转化为后缀表达式的List
     * @param infixExpression
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> infixExpression){
        //运算符栈s1
        Stack<String> s1 = new Stack<>();
        //中间结果的栈s2，因不需要pop操作，这里使用List代替
        List<String> s2 = new ArrayList<>();
        for (String str : infixExpression) {
            if (str.matches("\\d+")){
                s2.add(str);
                continue;
            }else if (str.equals("(")){
                s1.push(str);
                continue;
            }else if (str.equals(")")){
//                while (s1.size()!=0){
//                    String temp=s1.pop();
//                    if (!temp.equals("(")){
//                        s2.add(temp);
//                    }else{
//                        break;
//                    }
//                }
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
//                if (s1.size()==0||s1.peek()=="("){
//                    s1.push(str);
//                    continue;
//                }else if (Operation.getValue(str)>Operation.getValue(s1.peek())){
//                    s1.push(str);
//                    continue;
//                }else {
//                    s2.add(s1.pop());
//                    continue;
//                }
                //这里优先级比栈顶优先级低或等于，就将s1的栈顶运算符加入并添加到s2中
                while (s1.size()!=0&&Operation.getValue(str)<=Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(str);
            }

        }
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将后缀表达式的各个符号放入到List中
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression){
        return InversePolishExpression.addExpressionToList(suffixExpression);
    }

    /**
     * 计算获得的后缀表达式的List
     * @param expressionList
     * @return
     */
    public static int calculate(List<String> expressionList){
        return InversePolishExpression.calculate(expressionList);
    }

}

/**
 * 返回运算符的优先级
 */
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    public static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("没有这个操作符");
        }
        return result;
    }
}
