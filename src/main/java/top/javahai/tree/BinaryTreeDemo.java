package top.javahai.tree;


import java.util.Objects;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 二叉树相关Demo
 * @create 2020/10/25 - 13:36
 **/
public class BinaryTreeDemo {

    public static void main(String[] args) {
        /**
         * 二叉树
         *            1
         *         2    3
         *       4  5  6  7
         */
        HeroNode root = new HeroNode(1, "小明");
        HeroNode n2 = new HeroNode(2, "小偶");
        HeroNode n3 = new HeroNode(3, "小机");
        HeroNode n4 = new HeroNode(4, "小吖");
        HeroNode n5 = new HeroNode(5, "小上");
        HeroNode n6 = new HeroNode(6, "小流");
        HeroNode n7 = new HeroNode(7, "小化");
        root.setLeft(n2);
        root.setRight(n3);
        n2.setLeft(n4);
        n2.setRight(n5);
        n3.setLeft(n6);
        n3.setRight(n7);

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        System.out.println("前序遍历：");
        //1 2 4 5 6 3 7
        root.preOrderTraversal();
        System.out.println("中序遍历：");
        //4 2 5 1 6 3 7
        root.midOrderTraversal();
        System.out.println("后序遍历：");
        //4 5 2 6 7 3 1
        root.postOrderTraversal();

        System.out.println(binaryTree.preOrderSearch(4));

        System.out.println(binaryTree.midOrderSearch(4));

        System.out.println(binaryTree.postOrderSearch(4));

        System.out.println("删除节点：");
        binaryTree.delete(4);

        binaryTree.preOrderTraversal();


    }


}

class BinaryTree {
    /**
     * 二叉树第一层的父节点
     */
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    public HeroNode() {
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
