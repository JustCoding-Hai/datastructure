package top.javahai.search;

import java.util.Arrays;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 斐波那契（黄金分割）查找算法
 * @create 2020/10/28 - 23:36
 **/
public class FibonacciSearch {


    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] fibonacci = fib();
//        for (int i = 0; i < fibonacci.length; i++) {
//            System.out.println(fibonacci[i]);
//        }
        int[] arr = {1, 2, 4, 6, 7, 90, 98};
        System.out.println(fibonacciSearch(arr, 10));
        System.out.println(fibonacciSearch(arr, 7));
        System.out.println(fibonacciSearch(arr, 98));


    }


    /**
     * 使用非递归的方法获得一个斐波那契数列
     *
     * @return
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }


    /**
     * 斐波那契查找
     *
     * @param arr 查找的数组
     * @param key 目标的数
     * @return
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        //表示斐波那契数列的数组下标
        int k = 0;
        //存放mid的值
        int mid = 0;
        //获得的斐波那契数列
        int[] fibonacci = fib();
        //获取到斐波那契数列分割数值的下标
        while (high > fibonacci[k] - 1) {
            k++;
        }
        //因为 f[k] 值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组,并指向 temp[],不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, fibonacci[k]);
        //将原本用0补充的数使用arr数组最后的一个数进行补充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            mid = low + fibonacci[k - 1] - 1;
            //向数组的前面进行查找
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}
