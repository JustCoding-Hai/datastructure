package top.javahai.sort;

import top.javahai.utils.DateUtil;

import java.util.Date;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 基数排序(桶排序)
 * @create 2020/10/20 - 23:36
 **/
public class RadixSort {
    public static void main(String[] args) {
        int[] arr={5,4,3,2,1};
        radixSort(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
        testExecutionTime();
    }

    /**
     * 基数排序是使用空间换取时间的经典算法
     * 基数排序的基本思想：
     * 将所有待排序数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始到最高位，依次进行一次排序，待所有排序完成后
     * 数组就有序了。
     * @param arr
     */
    public static void radixSort(int[] arr){
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
        //记录在每一个一维数组（桶）中存放的元素个数，用于标志一维数组（桶）元素可存放的位置
        int[] buckElementCounts=new int[10];
        //3.遍历数组
        //3.1 第一层循环确定需要排序的次数
        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            //3.2 第二层循环，进行一次排序，位数比较：个位->十位....
            for (int j = 0; j < arr.length; j++) {
                //获取当前排序每个元素对应的位数
                int digitOfElement=(arr[j]/n)%10;
                //根据位数，将元素放到桶的对应位置,然后+1
                bucket[digitOfElement][buckElementCounts[digitOfElement]++]=arr[j];
            }
            //原数组下标
            int index=0;
            //一次排序完成将桶的元素按顺序放回到原本的数组中，完成一次排序
            for (int k = 0; k < buckElementCounts.length; k++) {
                if (buckElementCounts[k]!=0){
//                    for (int num : bucket[k]) {这里不能直接赋值进去，因为0也会赋值会原数组
//                       arr[index++]=num;
//                    }
                    for (int l = 0; l < buckElementCounts[k]; l++) {
                        arr[index++]=bucket[k][l];

                    }
                }
                //将buckElementCounts的记录初始化，进行下一次排序
                buckElementCounts[k]=0;
            }

        }
    }
    public static void testExecutionTime(){
        //生成测试数据，800000条
        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
        System.out.println("当前时间："+ DateUtil.getCurrentTime(new Date()));
        radixSort(arr);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));

    }
}
