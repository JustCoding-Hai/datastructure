package top.javahai.stack;

import java.util.Scanner;

/**
 * 用数组模拟栈的DEMO
 * @author Hai
 * @date 2020/9/22 - 23:35
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
            case "show":
                stack.list();
                break;
            case "push":
                System.out.println("请输入一个数");
                int value = scanner.nextInt();
                stack.push(value);
                break;
            case "pop":
                try {
                    Object res = stack.pop();
                    System.out.printf("出栈的数据是 %d\n", res); }
                catch (Exception e) { // TODO: handle exception
                  System.out.println(e.getMessage());
                }
                break;
            case "exit":
                    scanner.close();
                    loop = false;
                    break;
            default: break;
             }
        }
        System.out.println("程序退出~~~");
    }
}
class ArrayStack{
    //数组模拟的栈
    private Object[] stack;
    //栈顶指针(数组下标)
    private int top=-1;
    //最大的容量
    private int max_capacity=0;
/**
 *
 构造器
 */
    public ArrayStack(int max_capacity){
        this.max_capacity=max_capacity;
        this.stack=new Object[max_capacity];
    }
    /**
     * 是否已满
      */
    public boolean isFull(){
        return top==max_capacity-1;
    }
    /**
     * 是否为空
     */
    public boolean isEmpty(){
        return top==-1;
    }
    /**
     * 入栈
     */
    public void push(Object o){
        if (isFull()){
            System.out.println("栈当前已满！无法添加元素");
            return;
        }
        top++;
        stack[top]=o;
    }
    /**
     * 出栈
     */
    public Object pop(){
        if (isEmpty()){
            throw new RuntimeException("没有元素在栈中！");
        }
        Object o = stack[top];
        top--;
        return o;
    }
    /**
     * 输出栈中所有的元素
     */
    public void list(){
        if (isEmpty()){
            System.out.println("栈为空！");
            return;
        }
        for (int i = 0; i <=top; i++) {
            System.out.println("stack["+i+"]-"+stack[i]);
        }
    }
}
