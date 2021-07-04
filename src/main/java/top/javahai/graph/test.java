package top.javahai.graph;

/**
 * @author Hai
 * @program: DataStructure
 * @description:
 * @create 2021/3/12 - 17:29
 **/
public class test {
    public static void main(String[] args) {
        int n=666;
        Integer a=2;
        n |= n>>1;

        n |= n>>2;

        n |= n>>4;

        n |= n>>8;

        n |= n>>16;
        System.out.println(n);
        int i=0;
        System.out.printf("%d",++i);
        String s="abc";
        System.out.println(s.substring(0,3));
    }

    final void test(){

    }
    final void test(String s){

    }
}
