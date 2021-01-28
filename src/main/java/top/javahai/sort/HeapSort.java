package top.javahai.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 堆排序算法实现
 * @create 2021/1/25 - 23:51
 **/
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int[] simpleArr = {4, 6, 8, 5, 9};
        heapSort(simpleArr);
        System.out.println(Arrays.toString(simpleArr));
        // 创建要给 80000 个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);

        }
        // 生成一个[0, 8000000) 数 }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        heapSort(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    public static void heapSort(int[] arr){
        int temp;
        //步骤1：初始化，将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        //这里的i取值为当前树最底层的非叶子节点的索引
        for (int i = arr.length/2-1; i >=0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //步骤2：将堆顶元素与末尾元素进行交换，使末尾元素最大。然后继续调整堆，再将堆顶元素与末尾元素交换，得到第二大元素。如此反复进行交换、重建、交换。
        for (int i = arr.length-1; i >0 ; i--) {
            //将堆顶元素与末尾元素交换，将最大的元素放到数组末端
            temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 作用：将以i对应的非叶子节点的树调整成大顶堆
     * 举例：
     * int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用 adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     *
     * @param arr 待调整的数组
     * @param i  表示需要调整的非叶子节点在数组的索引
     * @param length 表示对多少个元素继续调整，length是逐渐减少的
     */
    public static void adjustHeap(int[] arr,int i,int length){
        //先取出当前元素的值，保存在临时变量
        int temp=arr[i];
        //开始调整，k=i*2+1是i结点的左子节点
        for (int k = i*2+1; k < length ; k=k*2+1) {
            //先找到子节点的最大值，如果左子节点的值小于右子节点的值
            if(k+1<length&&arr[k]<arr[k+1]){
                //k指向右子节点
                k++;
            }
            //如果子节点大于父节点
            if (arr[k]>temp){
                //将父节点所在索引的值设置为当前k所在结点的值
                arr[i]=arr[k];
                //完成了一层的大顶堆构造。i指向k，继续循环，构造大顶堆
                i=k;
            }
            //上述两个条件不满足，则可以结束循环。
            // 因为由调用方法heapSort()，决定堆从从左到右，从下到上开始构建，如果当前这一层局部满足了大/小顶堆堆定义，则后续的结点则不需要比较了
            else{
                break;
            }
        }
        //当for循环结束后，将原本i的值放到调整后的位置
        arr[i]=temp;
    }
}
