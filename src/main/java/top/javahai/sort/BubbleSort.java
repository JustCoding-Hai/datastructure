package top.javahai.sort;

import top.javahai.utils.ArrayUtil;
import top.javahai.utils.DateUtil;

import java.util.Date;

/**
 * 冒泡排序实现
 * @author Hai
 * @date 2020/10/6 - 13:22
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr={5,4,3,2,1};
        bubbleSort(arr);
        System.out.println("最终结果如下：");
        ArrayUtil.print(arr);
        int[] arr2={1,2,3,6,5,4};
        bubbleSort(arr2);
        System.out.println("最终结果如下：");
        ArrayUtil.print(arr2);
        System.out.println("测试排序速度：");
        testExecutionTime();
    }


    public static void testExecutionTime(){
        //生成测试数据，80000条
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        System.out.println("当前时间："+DateUtil.getCurrentTime(new Date()));
        bubbleSort(arr);
        System.out.println("排序完成时间："+DateUtil.getCurrentTime(new Date()));

    }
    /**
     *
     * 每趟排序两两比较，如果前一个比后一个大就交换位置，每次找出最大的数放在最右边。
     * 外面套一层for循环用于控制每趟的排序的边界，每次边界向左移动一个位置。
     *
     * arr.length-1-i最小取1，最后一趟排序只比较1次。
     *
     * 优化：
     * 如果发现其中一趟排序中没有发生一次交换，就表示该数组已经有序了。
     *
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr){

        int temp=0;
        //一趟排序中是否发生交换的标志
        boolean flag=false;

        for (int i=0;i<arr.length-1;i++){
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if (flag){
                flag=false;
                //print(arr);
                continue;
            }
            break;
        }
    }
}
