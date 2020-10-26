package top.javahai.tree;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 顺序存储二叉树，以数组的形式存储二叉树，数组<==>二叉树
 * @create 2020/10/25 - 23:26
 **/
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        /*
           二叉树：
                           1
                        2     3
                      4  5   6  7
                    8  9
         */
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        //前序：1 2 4 8 9 5 3 6 7
        arrBinaryTree.preOrder();
        System.out.println();
        //中序：8 4 9 2 5 1 6 3 7
        arrBinaryTree.midOrder();
        System.out.println();
        //后序：8 9 4 5 2 6 7 3 1
        arrBinaryTree.postOrder();
    }

}

/**
 * 数组形式存储的二叉树
 * 顺序存储二叉树（通常只考虑完成二叉树）的特点：
 * （n以0开始编号，表示二叉树的第几个元素）
 * 1. 第n个元素的左子节点为2*n+1
 * 2. 第n个元素的右子节点为2*n+2
 * 3. 第n个元素的父节点(n-1)/2
 */
class ArrBinaryTree {

    public int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void midOrder() {
        this.midOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 前序遍历
     *
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组是空的，无法完成遍历");
            return;
        }
        System.out.print(arr[index] + " ");

        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中序遍历
     *
     * @param index
     */
    public void midOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组是空的，无法完成遍历");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            midOrder(2 * index + 1);
        }

        System.out.print(arr[index] + " ");

        if ((2 * index + 2) < arr.length) {
            midOrder(2 * index + 2);
        }

    }

    /**
     * 后序遍历
     *
     * @param index
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组是空的，无法完成遍历");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }


        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.print(arr[index] + " ");
    }

}
