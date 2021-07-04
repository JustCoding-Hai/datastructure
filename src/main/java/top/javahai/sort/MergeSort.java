package top.javahai.sort;

import top.javahai.utils.DateUtil;

import javax.lang.model.util.ElementFilter;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 归并排序
 * @create 2020/10/18 - 11:24
 **/
public class MergeSort {
    public static void main(String[] args) {
        int arr[] ={8,4,5,7,1,3,6,2};
        int[] temp=new int[8];
        mergeSort(arr,0,arr.length-1,temp);
        for (int i : arr) {
            System.out.print(i+" ");
        }
        testExecutionTime();
    }

    /**
     * 归并的思想是将一个数组分成两半，排序每一半，然后将这两个有序数组合并成一个有序的数组，以“空间换时间”的做法
     */
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left==right){
            return;
        }else{
            int mid=(left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }

    }


    /**
     * 合并两个有序数组（指针遍历原数组获得）到temp数组中，然后将temp数组的有序数组赋值回到原本的数组arr
     * @param arr 待排序的原始数组
     * @param left 左边有序数组的初始索引
     * @param mid  中间索引
     * @param right 原始数组的右边索引
     * @param temp 临时数组,做数据中转
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        //左边有序数组的初始索引
        int l=left;
        //右边有序数组的初始索引
        int r=mid+1;
        //temp数组的初始索引
        int t=0;

        //1.将有序子数组有序地合并到temp中
        while (l<=mid&&r<=right){
            if (arr[l]<=arr[r]){
                temp[t++]=arr[l++];
            }else{
                temp[t++]=arr[r++];
            }
        }
        //2.如果左边数组还有剩余就将剩余的都放入到temp
        while (l<=mid){
            temp[t++]=arr[l++];
        }
        //3.如果右边数组还有剩余就将剩余的都放入到temp
        while (r<=right){
            temp[t++]=arr[r++];
        }
        //4.将temp数组的数据重新放回到原来的数组arr
        t=0;
        int tempLeft=left;
        while (tempLeft<=right){
            arr[tempLeft++]=temp[t++];
        }
    }
    public static void testExecutionTime(){
        //生成测试数据，80000条
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*8000000);
        }
        int[] temp=new int[8000000];
        System.out.println("测试归并排序执行时间：");
        System.out.println("当前的时间戳："+System.currentTimeMillis());
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("完成后时间戳："+System.currentTimeMillis());


    }
}
