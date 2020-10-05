package top.javahai.recursion;

/**
 * 八皇后问题，递归实现
 *
 * @author Hai
 * @date 2020/10/5 - 23:44
 */
public class Queue8 {
    //皇后问题规模
    private static int size=8;
    //一维数组代表结果,例如{0 , 4, 7, 5, 2, 6, 1, 3}，0作为起始下标，下标代表行，值代表列
    private static int[] result=new int[size];

    private static int count=0;

    public static void main(String[] args) {
        check(0);
        System.out.printf("解法共有%d种",count);
    }


    /**
     *
     * 每在一行进行摆放，就从第一列开始到最后一列放置，其中一列符合条件，就放下一行。
     *
     * 这里每次循环，表示在每一行放置皇后的时候都会考虑所有的列的情况。(回溯)
     *
     * 循环边界： 达到最后一行
     * @param n 表示在棋盘第n+1行添加的第n+1个皇后
     */
    public static void check(int n){
        if (n==size){
            print();
            return;
        }
        for (int i = 0; i < size; i++) {
            result[n]=i;
            if (judge(n)){
                check(n+1);
            }
        }

    }


    /**
     *与前面添加的皇后做比较，查看是否符合条件
     *
     * 如果同一列或者处于对角线，那就不符合规则.
     *
     * 对角戏的判断根据等腰直角三角形进行判断，比较两个位置的水平和垂直方法距离，如果二者相等就说明两个位置处于对角线
     *
     * @param n 表示在棋盘第n+1行添加的第n+1个皇后
     * @return fasle-冲突，true-不冲突，符合摆放规则。
     */
    public static boolean judge(int n){
        for (int i=0;i<n;i++){
            //如果同意了
            if (result[i]==result[n]||Math.abs(i-n)==Math.abs(result[i]-result[n])){
                return false;
            }
        }
        return true;
    }


    /**
     * 打印结果
     */
    public static void print(){
        count++;
        for (int i : result) {
            System.out.print(i+" ");
        }
        System.out.println();

    }
}
