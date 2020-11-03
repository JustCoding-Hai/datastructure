package top.javahai.tree.threaded;

import java.util.Objects;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 线索化二叉树
 * @create 2020/11/2 - 23:01
 **/
public class ThreadedBinaryTreeDemo {
    /**
     * 1
     * 3    6
     * 8 10  14
     *
     * @param args
     */
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1);
        HeroNode node2 = new HeroNode(3);
        HeroNode node3 = new HeroNode(6);
        HeroNode node4 = new HeroNode(8);
        HeroNode node5 = new HeroNode(10);
        HeroNode node6 = new HeroNode(14);

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);


        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(node1);
        threadedBinaryTree.threadedNodes();

        System.out.println(node6.getLeft());
        System.out.println(node6.getRight());

        System.out.println("中序遍历线索化二叉树：");
        threadedBinaryTree.threadedList();


    }
}

class ThreadedBinaryTree {
    /**
     * 二叉树第一层的父节点
     */
    private HeroNode root;

    /**
     * 为了线索化，pre是用于指向当前节点的前驱结点的指针
     */
    private HeroNode pre;

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }

    }

    /**
     * 线索化二叉树，方法重载
     */
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 对二叉树进行中序线索化
     *
     * @param node
     */
    private void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.getLeft());

        //2.线索化当前结点

        //先处理当前结点的前驱结点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继结点,这里相当于处理上一个结点的后继结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        //3.处理当前结点的右子节点，线索化右子树
        threadedNodes(node.getRight());

    }


    public boolean isNull(HeroNode root) {
        if (Objects.isNull(root)) {
            System.out.println("二叉树为空！");
            return true;
        }
        return false;
    }

    public void preOrderTraversal() {
        if (!isNull(root)) {
            this.root.preOrderTraversal();
        }

    }

    public void midOrderTraversal() {
        if (!isNull(root)) {
            this.root.midOrderTraversal();
        }
    }

    public void postOrderTraversal() {
        if (!isNull(root)) {
            this.root.postOrderTraversal();
        }
    }

    public HeroNode preOrderSearch(int no) {
        return this.root.preOrderSearch(no);

    }

    public HeroNode midOrderSearch(int no) {
        return this.root.midOrderSearch(no);
    }

    public HeroNode postOrderSearch(int no) {
        return this.root.postOrderSearch(no);
    }

    public void delete(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delete(no);
            }

        } else {
            System.out.println("二叉树为空不能继续操作");
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    /**
     * 左右结点的类型，0表示为树（有多个结点），1表示结点
     */
    private int leftType;
    private int rightType;

    public HeroNode() {
    }

    public HeroNode(int no) {
        this.no = no;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历的递归实现
     * 父节点-》左节点-》右节点
     */
    public void preOrderTraversal() {
        System.out.println(this);
        if (left != null) {
            left.preOrderTraversal();
        }
        if (right != null) {
            right.preOrderTraversal();
        }

    }

    /**
     * 中序遍历-递归实现
     * 左节点-》父节点-》右节点
     */
    public void midOrderTraversal() {
        if (left != null) {
            left.midOrderTraversal();
        }
        System.out.println(this);
        if (right != null) {
            right.midOrderTraversal();
        }
    }


    /**
     * 后序遍历
     * 左节点-》右结点-》父节点
     */
    public void postOrderTraversal() {
        if (left != null) {
            left.postOrderTraversal();

        }
        if (right != null) {
            right.postOrderTraversal();
        }

        System.out.println(this);
    }


    /**
     * 前序查找
     *
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {

        System.out.println("前序查找");

        if (this.no == no) {
            return this;
        }
        HeroNode resultNode = null;
        //找左子树
        if (left != null) {
            resultNode = this.left.preOrderSearch(no);
        }
        if (!Objects.isNull(resultNode)) {
            return resultNode;
        }
        if (resultNode != null) {
            resultNode = this.right.preOrderSearch(no);
        }
        return resultNode;

    }

    /**
     * 中序查找
     *
     * @param no
     * @return
     */
    public HeroNode midOrderSearch(int no) {
        System.out.println("中序查找");
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.midOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resultNode = this.right.midOrderSearch(no);
        }

        return resultNode;

    }

    public HeroNode postOrderSearch(int no) {
        System.out.println("后续查找");
        HeroNode resultNode = null;
        if (this.left != null) {
            resultNode = this.left.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.right != null) {
            resultNode = this.right.postOrderSearch(no);
        }
        if (resultNode != null) {
            return resultNode;
        }
        if (this.no == no) {
            return this;
        }
        return resultNode;
    }

    /**
     * 根据树的节点进行删除,
     * 1.先看左节点
     * 2.看右节点
     * 3.看左子树
     * 4.右子树
     *
     * @param no
     */
    public void delete(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delete(no);
        }
        if (this.right != null) {
            this.right.delete(no);
        }


    }
}
