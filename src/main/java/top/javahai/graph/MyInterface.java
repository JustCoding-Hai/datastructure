package top.javahai.graph;

/**
 * @author Hai
 * @program: DataStructure
 * @description:
 * @create 2021/3/14 - 17:32
 **/
interface MyInterface {

    int i=1;
    default void method1(){
        System.out.println();
    }

    static void method2(){
        System.out.println();
    };
}
