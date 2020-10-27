package top.javahai.search;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 线性/顺序查找
 * @create 2020/10/26 - 23:08
 **/
public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(sequenceSearch(arr, 5));
    }


    public static int sequenceSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
