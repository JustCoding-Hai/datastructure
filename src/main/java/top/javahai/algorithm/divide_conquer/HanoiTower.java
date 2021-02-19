package top.javahai.algorithm.divide_conquer;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 汉诺塔问题
 * @create 2021/2/10 - 10:29
 **/
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }


    /**
     * 代码写完用n=2判断是否正确
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public static void hanoiTower(int n,char a,char b,char c){
        //如果只有一个盘
        if (n==1){
            System.out.println("第1个盘 "+a+"->"+c);
        }else{
            //如果超过两个盘，则把A塔的盘子看作，最下面的盘和上边的所有盘，首先将上边的n-1个盘移动到B塔
            hanoiTower(n-1,a,c,b);
            //将最下面的盘移动到C塔
            System.out.println("第"+n+"个盘 "+a+"->"+c);
            //将B塔所有的n-1个盘移动到C塔
            hanoiTower(n-1,b,a,c);
        }
    }


}
