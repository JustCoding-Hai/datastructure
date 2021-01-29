package top.javahai.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 构造哈夫曼树
 * @create 2021/1/28 - 23:41
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }



    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }
    }

    public static Node createHuffmanTree(int[] arr){
        //为了操作方便，将arr的数组转成Node，并放入到集合中
        List<Node> nodeList = new ArrayList<>();
        for (int i : arr) {
            Node node = new Node(i);
            nodeList.add(node);
        }
        while (nodeList.size()>1){
            //从小到大排序
            Collections.sort(nodeList);
            //取出权值最小的结点
            Node left = nodeList.get(0);
            //取出权值第二小的结点
            Node right = nodeList.get(1);
            //构造成新的结点（二叉树）
            Node parent = new Node(left.value + right.value);
            parent.left=left;
            parent.right=right;
            //从List中删除处理过的两个结点
//            nodeList.remove(left);
//            nodeList.remove(right);
            //用下标删除
            nodeList.remove(0);
            nodeList.remove(0);
            //将新生成的结点加入到集合中
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }
}

class Node implements Comparable<Node>{
    /**
     * 结点的权值
     */
    int value;
    /**
     * 左子结点
     */
    Node left;
    /**
     * 右子结点
     */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value+'}';
    }

    /**
     * 前序遍历
     *
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    /**
     * 将此对象与指定对象进行比较。 当此对象小于，等于或大于指定的对象时，返回负整数，零或正整数
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return this.value- o.value;
    }
}
