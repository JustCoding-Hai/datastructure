package top.javahai.algorithm.dynamic_programming;


/**
 * @author Hai
 * @program: DataStructure
 * @description: 0-1 背包问题
 * @create 2021/2/19 - 11:44
 **/
public class KnapSackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] w={1,4,3};
        //物品的价值
        int[] val={1500,3000,2000};
        //背包的容量（承重）
        int m=4;

        solution(w,val,m);

    }

    /**
     * 求解0-1 背包问题
     * @param w 物品的重量
     * @param val 物品的价值
     * @param m 背包的容量
     * @return 最优摆放
     */
    private static int[][] solution(int[] w,int[] val,int m){
        //物品的个数
        int n=val.length;
        //v[i][j]表示第i个物品中能够放入容量为j的背包的最大价值
        int[][] v=new int[n+1][m+1];
        //记录摆放
        int[][] path=new int[n+1][m+1];

        //初始化第一行、第一列。（默认其实已经是0）
        for (int i = 0; i < v[0].length; i++) {
            v[0][i]=0;
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0]=0;
        }
        //不处理第一行第一列，i表示当前可放入背包的前i个物品
        for (int i = 1; i < v.length; i++) {
            //capacity表示当前背包的容量
            for (int capacity = 1; capacity < v[0].length; capacity++) {
                //准备放入背包的物品容量大于背包容量的话就使用上一个单元格的装入策略
                if (w[i-1]>capacity){
                    v[i][capacity]=v[i-1][capacity];
                }

                /**
                 * 准备加入的新增的商品的容量小于等于当前背包的容量,商品可放入到背包的最大值取
                 *（同样容量下的上一个放入策略的价值v[i-1][capacity]，当前物品的价值+剩余容量可放入物品的最大值val[i-1]+v[i-1][capacity-w[i-1]]）
                 * 中的背包最大价值策略
                 */
//                else{
//                    v[i][capacity]=Math.max(v[i-1][capacity],val[i-1]+v[i-1][capacity-w[i-1]]);
//                }
                else{
                    if (v[i-1][capacity]<val[i-1]+v[i-1][capacity-w[i-1]]){
                        v[i][capacity]=val[i-1]+v[i-1][capacity-w[i-1]];
                        path[i][capacity]=1;
                    }else{
                        v[i][capacity]=v[i-1][capacity];
                    }
                }
            }
        }
        for (int[] ints : v) {
            for (int i = 0; i < ints.length; i++) {
                System.out.print(ints[i]+" ");
            }
            System.out.println();
        }
        //行的最大下标
        int i = path.length - 1;
        //列的最大下标
        int j = path[0].length - 1;
        //从 path 的最后开始找
        while(i > 0 && j > 0 ) {
            if(path[i][j] == 1) {
                System.out.printf("第%d 个商品放入到背包\n", i);
                j -= w[i-1];
            }
            i--;
        }
        return v;
    }
}
