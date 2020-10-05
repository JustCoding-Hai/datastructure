package top.javahai.stack;


/**
 * 使用栈实现计算器，以中缀表达式进行加减乘除的运算
 * @author Hai
 * @date 2020/9/23 - 22:31
 */
public class Calculator {
    public static void main(String[] args) {
        //需要进行计算的算式表达式
        String expression="3*4-16+40/2";
        //创建两个栈，数栈和运算符栈
        ArrayStackForCalculate numebrStack=new ArrayStackForCalculate(10);
        ArrayStackForCalculate operatorStack=new ArrayStackForCalculate(10);
        //由于计算的相关变量
        int index=0;
        int num1=0;
        int num2=0;
        int operator=0;
        int result=0;
        //算式表达式的每个字符
        char c=' ';
        String tempNum="";
        //while循环遍历表达式的每个字符, 当index>expression.length()-1时，已经遍历完了
        while (index<=expression.length()-1){
            c=expression.substring(index,index+1).charAt(0);
            //如果是运算符
            if (operatorStack.isOperator(c)){
                if (!operatorStack.isEmpty()) {
                    //如果当前遍历的运算符的优先级小于等于运算符栈的栈顶的优先级，就弹出两个数栈的值和运算符栈的元素符并进行计算，
                    if (operatorStack.priority(c) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numebrStack.pop();
                        num2 = numebrStack.pop();
                        operator = operatorStack.pop();
                        result = operatorStack.calculate(num1, num2, operator);
                        numebrStack.push(result);
                    }
                }
                //将当前遍历的运算符放入到运算符栈
                operatorStack.push(c);
            }
            //如果是数字放到栈中,需要考虑数是多位数的情况
            else{
                //如果当前字符为最后一个，就直接放入到数字栈中
                if(index==expression.length()-1){
                    numebrStack.push(c-48);
                }else{
                    //看字符的后一位,使用变量暂存数字字符
                    tempNum+=c;
                    //只有当这个数的下一个字符为运算符才将暂存的数字字符放入到数字栈中
                    if (operatorStack.isOperator(expression.substring(index+1,index+2).charAt(0))){
                        numebrStack.push(Integer.valueOf(tempNum));
                        //初始化暂存数字的tempNum
                        tempNum="";
                    }
                }
            }
            index++;
        }
        //此时操作符栈中只剩下同一优先级的运算符
        while (!operatorStack.isEmpty()){
            num1=numebrStack.pop();
            num2=numebrStack.pop();
            operator=operatorStack.pop();
            result=operatorStack.calculate(num1,num2,operator);
            numebrStack.push(result);
        }
        int finalResult=numebrStack.pop();
        System.out.printf("The result of expression : %s=%d",expression,finalResult);

    }
}
class ArrayStackForCalculate {
    //数组模拟的栈
    private int[] stack;
    //栈顶指针(数组下标)
    private int top = -1;
    //最大的容量
    private int max_capacity = 0;

    /**
     * 构造器
     */
    public ArrayStackForCalculate(int max_capacity) {
        this.max_capacity = max_capacity;
        this.stack = new int[max_capacity];
    }

    /**
     * 返回栈顶的值，不出栈
     * @return
     */
    public int peek(){
        return stack[top];
    }

    /**
     * 是否已满
     */
    public boolean isFull() {
        return top == max_capacity - 1;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int o) {
        if (isFull()) {
            System.out.println("栈当前已满！无法添加元素");
            return;
        }
        top++;
        stack[top] = o;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("没有元素在栈中！");
        }
        int o = stack[top];
        top--;
        return o;
    }

    /**
     * 输出栈中所有的元素
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空！");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.println("stack[" + i + "]-" + stack[i]);
        }
    }

    /**
     * 返回运算符的优先级
     * @param operator
     * @return
     */
    public int priority(int operator){
        if (operator=='*'||operator=='/'){
            return 1;
        }else if (operator=='+'||operator=='-'){
            return 0;
        }else{
            return -1;
        }
    }

    /**
     * 判断是否是运算符
     * @return
     */
    public boolean isOperator(char c){
        return c=='+'||c=='-'||c=='*'||c=='/';
    }

    /**
     * 两个数进行运算
     * @param num1 出栈的第一个数
     * @param num2 出栈的第二个数
     * @param operator 操作符
     * @return
     */
    public int calculate(int num1,int num2,int operator){
        int result=0;
        switch (operator){
            case '+':
                result=num1+num2;
                break;
            case '-':
                result=num2-num1;
                break;
            case '*':
                result=num1*num2;
                break;
            case '/':
                result=num2/num1;
                break;
        }
        return result;
    }
}
