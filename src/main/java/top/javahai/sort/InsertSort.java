package top.javahai.sort;

import top.javahai.utils.DateUtil;

import java.util.Date;

/**
 * 插入排序
 * @author Hai
 * @date 2020/10/6 - 17:51
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr={5,4,3,2,1};
        insertSort(arr);
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        testExecutionTime();
    }

    /**
     * 插入排序实现
     *
     * 实现思路：
     *
     * 1.以数组的第一个值就是有序的，进行插入，insertVal保留当前遍历的值，insertIndex当前遍历的前一个下标。
     *2.如果前一个数比当前遍历的数insertVal大，就将前一个数赋值给当前遍历下标。insertIndex再向前一位。重复该步骤直到前一个数比当前遍历的数小
     * 3。此时跳出循环，如果insertIndex+1不等于insertVal的下标，就说明insertVal需要放到数组的前面的位置。
     *
     *
     * @param arr
     */
    public static void insertSort(int[] arr){
        //要插入的值
        int insertVal=0;
        //插入的下标，取要插入的值的前一位
        int insertIndex=0;

        for (int i = 1; i < arr.length; i++) {
            insertVal=arr[i];
            insertIndex=i-1;

            while (insertIndex>=0&&insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }

            if (insertIndex+1!=i){
                arr[insertIndex+1]=insertVal;
            }
        }
    }
    public static void testExecutionTime(){
        //生成测试数据，80000条
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        System.out.println("当前时间："+ DateUtil.getCurrentTime(new Date()));
        insertSort(arr);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));

    }
}


