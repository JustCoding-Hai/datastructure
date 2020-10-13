package top.javahai.sort;

import top.javahai.utils.DateUtil;

import java.util.Date;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 快速排序
 * @create 2020/10/11 - 23:40
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr={5,4,3,2,1};
        quickSort(arr,0,4);
        for (int i : arr) {
            System.out.println(i+" ");
        }
        System.out.println();
        testExecutionTime();
    }



    public static void quickSort(int[] arr,int left,int right){
        //左指针
        int l=left;
        //右指针
        int r=right;
        //中轴值
        int pivot=arr[(left+right)/2];
        //临时值，交换时用
        int temp=0;
        while (l<r){
            //在pivot的左边一直找到大于pivot
            while (arr[l]<pivot){
                l++;
            }
            //在pivot的右边一直找到小于pivot
            while (arr[r]>pivot){
                r--;
            }
            //
            if (l>=r){
                break;
            }
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            if (arr[l]==pivot){
                r--;
            }
            if (arr[r]==pivot){
                l++;
            }
            if (l==r){
                l++;
                r--;
            }
            if (left<r){
                quickSort(arr,left,r);
            }
            if (right>l){
                quickSort(arr,l,right);
            }
        }

    }
    public static void testExecutionTime(){
        //生成测试数据，80000条
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*800000);
        }
        System.out.println("当前时间："+ DateUtil.getCurrentTime(new Date()));
        quickSort(arr,0,arr.length-1);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));

    }
}
