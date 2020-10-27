package top.javahai.search;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 插值查找
 * @create 2020/10/27 - 23:46
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 55));
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 44));
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 22));

    }


    /**
     * 插值查找    规则：同样只能在有序数组中使用
     *
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return 返回目标数所在的下标
     */
    public static int insertValueSearch(int[] arr, int left, int right, int target) {
        System.out.println("插值查找");
        //边界条件
        if (left > right || target < arr[left] || target > arr[right]) {
            return -1;
        }
        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] > target) {
            return insertValueSearch(arr, left, mid - 1, target);
        } else if (arr[mid] < target) {
            return insertValueSearch(arr, mid + 1, right, target);
        } else {
            return mid;
        }


    }
}
