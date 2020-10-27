package top.javahai.search;

import java.util.ArrayList;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 二分查找
 * @create 2020/10/26 - 23:13
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(binarySearch(arr, 0, arr.length - 1, 10));
        System.out.println(binarySearch(arr, 0, arr.length - 1, 8));

        System.out.println(binarySearchV2(arr, 0, arr.length - 1, 3));
        System.out.println(binarySearchV2(arr, 0, arr.length - 1, 7));
        System.out.println(binarySearchV2(arr, 0, arr.length - 1, 710));

    }

    /**
     * 二分查找的前提条件：数组必须是有序的
     * 二分查找的递归实现
     *
     * @param arr
     * @param left   查找范围的左边界
     * @param right  查找范围的右边界
     * @param target 要查找的数
     * @return 目标数在数组的下标
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        int mid = (left + right) / 2;
        //终止条件1
        if (arr[mid] == target) {
            return mid;
        }
        //终止条件2:找不到目标数，left
        if (left >= right) {
            return -1;
        }
        if (arr[mid] > target) {
            return binarySearch(arr, left, mid - 1, target);
        }
        if (arr[mid] <= target) {
            return binarySearch(arr, mid + 1, right, target);
        }
        return -1;
    }

    /**
     * 找出数组中所有的等于目标数的下标
     *
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return 使用ArrayList进行存储
     */
    public static ArrayList<Integer> binarySearchV2(int[] arr, int left, int right, int target) {
        int mid = (left + right) / 2;
        //终止条件1
        if (arr[mid] == target) {
            ArrayList<Integer> list = new ArrayList<>();
            //向左查看是否有相等的数
            int temp = mid;
            while (temp >= 0 && arr[temp] == target) {
                list.add(temp);
                temp--;
            }
            temp = mid + 1;
            //向右查看是否有相等的数
            while (temp < arr.length && arr[temp] == target) {
                list.add(temp);
                temp++;
            }
            return list;
        }
        //终止条件2:找不到目标数，left
        if (left >= right) {
            return new ArrayList<Integer>();
        }
        if (arr[mid] > target) {
            return binarySearchV2(arr, left, mid - 1, target);
        }
        if (arr[mid] <= target) {
            return binarySearchV2(arr, mid + 1, right, target);
        }
        return new ArrayList<Integer>();
    }


}
