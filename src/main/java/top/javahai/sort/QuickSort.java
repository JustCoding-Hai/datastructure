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
        quickSort2(arr,0,4);
        for (int i : arr) {
            System.out.println(i+" ");
        }
        System.out.println();
        testExecutionTime();
    }

    //快速排序
//    static void quick_sort(int s[], int l, int r)
//    {
//        if (l < r)
//        {
//            int i = l, j = r, x = s[l];
//            while (i < j)
//            {
//                // 从右向左找第一个小于x的数
//                while(i < j && s[j] >= x) {
//                    j--;
//                }
//                if(i < j){
//                    s[i++] = s[j];
//                }
//                // 从左向右找第一个大于等于x的数
//                while(i < j && s[i] < x) {
//                    i++;
//                }
//                if(i < j){
//                    s[j--] = s[i];
//                }
//            }
//            s[i] = x;
//            // 递归调用
//            quick_sort(s, l, i - 1);
//            quick_sort(s, i + 1, r);
//        }
//    }

    /**
     * 快速排序
     * @param arr
     * @param left
     * @param right
     */
    static void quickSort2(int[] arr,int left,int right){
        if (left<right){
            //x未基准数
            int i=left,j=right,x=arr[left];
            while(i<j) {
                //从最右边找到比x小的数
                while (i < j && arr[j] >= x) {
                    j--;
                }
                //找到了就进行填i的位置
                if (i < j) {
                    arr[i++] = arr[j];
                }
                //从左边开始找比X大的数
                while (i < j && arr[i] < x) {
                    i++;
                }
                //找到了就进行填x的坑
                if (i < j) {
                    arr[j--] = arr[i];
                }
            }
            //放置基准数
            arr[i]=x;
            //递归调用
            quickSort2(arr,left,i-1);
            quickSort2(arr,i+1,right);
        }
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
            //将arr[l]和arr[r]进行交换
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            //
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
        quickSort2(arr,0,arr.length-1);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));
        System.out.println("当前时间："+ DateUtil.getCurrentTime(new Date()));
//        quick_sort(arr,0,arr.length-1);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));

    }
}
