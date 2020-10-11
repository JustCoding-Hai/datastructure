package top.javahai.sort;

import top.javahai.utils.DateUtil;

import java.util.Date;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 希尔排序
 * @create 2020/10/11 - 13:02
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr={5,4,3,2,1};
        shellSort2(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        testExecutionTime();
    }


    /**
     * 希尔排序思想：
     * 是对直接插入排序算法的改进，数组按一定的增量进行划分组，每个组进行直接插入排序，将小的数排到前面，
     * 增量逐次减少，最后，当增量为1时，再进行一次直接插入排序(这里采用的移位法，发现两个逆序就交换)，就得到有序数组.
     *
     * 先定增量为arr.length/2
     * 第一层for循环，改变增量
     * 第二层for循环，一个一个地移动下标
     * 第三层for循环，同个组中进行普通插入排序
     * @param arr
     */
    public static void shellSort(int[] arr){
        int temp=0;
        for (int gap=arr.length/2;gap>0;gap/=2){
            for (int i=gap;i<arr.length;i++){
                for (int j=i-gap;j>=0;j-=gap){
                    if (arr[j]>arr[j+gap]){
                        temp=arr[j+gap];
                        arr[j+gap]=arr[j];
                        arr[j]=temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序改进-移位法
     *
     * @param arr
     */
    public static void shellSort2(int[] arr){
        for (int gap=arr.length/2;gap>0;gap/=2){
            for (int i = gap; i < arr.length; i++) {
                int j=i;
                int temp=arr[j];
                if (arr[j]<arr[j-gap]){
                    while (j-gap>=0&&temp<arr[j-gap]){
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=temp;
                }
            }
        }
    }

    public static void testExecutionTime(){
        //生成测试数据，80000条
        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
        System.out.println("当前时间："+ DateUtil.getCurrentTime(new Date()));
        shellSort2(arr);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));

    }
}
