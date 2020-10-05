package top.javahai.stack;

import java.util.Stack;

/**
 * 计算器实现：
 * 1.中缀表达式转后缀表达式，再进行计算。（效率不高）
 * 2.以下这种方法，效率高很多.
 * https://leetcode-cn.com/problems/basic-calculator-ii/solution/chai-jie-fu-za-wen-ti-shi-xian-yi-ge-wan-zheng-ji-/
 *
 * @author Hai
 * @date 2020/10/5 - 12:31
 */
public class Calculator_improve {
    public static void main(String[] args) {
        System.out.println(calculate("1+15* 5"));
    }
    static int calculate(String s) {
        Stack<Integer> stk=new Stack<>();
        // 记录算式中的数字
        int num = 0;
        // 记录 num 前的符号，初始化为 +
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是数字，连续读取到 num
            if (Character.isDigit(c))
                num = 10 * num + (c - '0');
            // 如果不是数字，就是遇到了下一个符号，
            // 之前的数字和符号就要存进栈中
            if ((!Character.isDigit(c)&&c!=' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stk.push(num); break;
                    case '-':
                        stk.push(-num); break;
                    case '*':
                        stk.push(stk.pop()*num);
                        break;
                    case '/':
                        stk.push(stk.pop()/num);
                        break;
                }
                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
        }
        // 将栈中所有结果求和就是答案
        int res = 0;
        while (!stk.empty()) {
            res += stk.pop();
        }
        return res;
    }

}
