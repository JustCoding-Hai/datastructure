package top.javahai.sort;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 基数排序(桶排序)
 * @create 2020/10/20 - 23:36
 **/
public class RadixSort {

    /**
     * 基数排序是使用空间换取时间的经典算法
     * 基数排序的基本思想：
     * 将所有待排序数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始到最高位，依次进行一次排序，待所有排序完成后
     * 数组就有序了。
     * @param arr
     */
    public void radixSort(int[] arr){
        //1.获取数组中最大的数的位数,确定循环次数
        int max=arr[0];
        for (int i : arr) {
            if (i>max){
                max=i;
            }
        }
           //1.1 最大的数的位数
        int maxLength=(max+"").length();
        //2.创建二维数组，其中x（一维数组）为10，表示1~9;y（每个一维数组的长度定为待排序数组的长度，因为不确定相同的数字有多少个）
        int[][] bucket=new int[10][arr.length];
        //记录在每一个一维数组（桶）中存放的
        //3.遍历数组
        //3.1 第一层循环确定需要排序的次数
        for (int i = 0,n=1; i < max; i++,n*=10) {
            //3.2 第二层循环，进行一次排序，位数比较：个位->十位....
            for (int j = 0; j < arr.length; j++) {
                //获取当前排序每个元素对应的位数
                int digitOfElement=arr[i]/n%10;
            }

        }
        for (int i = 0; i < arr.length; i++) {






         //将bucket桶中的数据依次放回到数组中，完成一次排序。

        }



    }
}
