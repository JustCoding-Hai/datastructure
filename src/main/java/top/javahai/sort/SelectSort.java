package top.javahai.sort;

import top.javahai.utils.ArrayUtil;
import top.javahai.utils.DateUtil;

import java.util.Date;

/**
 * 选择排序实现
 * @author Hai
 * @date 2020/10/6 - 14:23
 */
public class SelectSort {
    public static void main(String[] args) {

        int[] arr={5,4,3,2,1};
        selectSort(arr);
        ArrayUtil.print(arr);
        testExecutionTime();
    }

    /**
     * 选择排序实现.
     *
     *
     * 每次循环，找到最小的数，记录其下标，循环结束后，放在数组的最左侧。
     *
     *循环的边界由大到小，通过外层循环控制。
     *
     * 总结：
     *
     * 相较于冒泡排序，比较次数相同，减少了交换。
     * 另外通过同时记录最小值和最小值的下标，最后的“交换”只需要直接赋值。提高了一些效率。
     *
     * @param arr
     */
    public static void selectSort(int[] arr){

        int temp=0;
        //记录最小的数对应的数组下标
        int minIndex;
        //记录最小的数,这样最后就不需要进行交换，直接赋值（改进）
        int min;
        for (int i = 0; i < arr.length-1; i++) {
            minIndex=i;
            min=arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<min){
                    min=arr[j];
                    minIndex=j;
                }
            }
            if (minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
            //将找到的最小的值放到下标i的位置
//            temp=arr[i];
//            arr[i]=arr[min];
//            arr[min]=temp;
        }
    }
    public static void testExecutionTime(){
        //生成测试数据，80000条
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        System.out.println("当前时间："+ DateUtil.getCurrentTime(new Date()));
        selectSort(arr);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));

    }
}
