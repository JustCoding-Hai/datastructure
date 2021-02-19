package top.javahai.algorithm.search;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 二分查找
 * @create 2021/2/9 - 23:38
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,3,6,8,10,23,24,60,81};
        System.out.println(binarySearch(arr,10));
        System.out.println(binarySearch(arr,10,0,arr.length-1));
    }


    /**
     * 二分查找的非递归实现
     * @param arr 查询数组
     * @param target 目标数
     * @return  返回数组中等于目标数的下标
     */
    private static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){
            int middle=(left+right)/2;
            if (arr[middle]==target){
                return middle;
            }else if (arr[middle]>target){
                right=middle-1;
            }else{
                left=middle+1;
            }
        }
        return -1;
    }

    /**
     * 二分查找的递归实现
     * @param arr 查询数组
     * @param target 目标数
     * @param left 左边界
     * @param right 右边界
     * @return 返回数组中等于目标数的下标
     */
    private static int binarySearch(int[] arr,int target,int left,int right){
        int middle=(left+right)/2;

        if (arr[middle]==target){
            return middle;
        }
        if (left>=right){
            return -1;
        }
        else if (arr[middle]>target){
            binarySearch(arr,target,left,middle-1);
        }else{
            binarySearch(arr,target,middle+1,right);
        }
        return -1;
    }
}
