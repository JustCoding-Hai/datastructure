package top.javahai.tree;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 二叉排序树DEMO
 * @create 2021/6/13 - 23:03
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(1);
        binarySortTree.delNode(7);
        System.out.println("删除后的二叉排序树：");
        binarySortTree.infixOrder();

    }
}

/**
 * 二叉排序树
 */
class BinarySortTree {
    private Node root;

    /**
     * 添加节点
     */
    public void add(Node node) {
        if (root == null) {
            this.root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历二叉排序树
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }


    /**
     * 删除节点
     *
     * @param value
     */
    public void delNode(int value) {
        Node targetNode = search(value);
        if (targetNode == null) {
            System.out.println("找不到目标节点");
            return;
        }
        Node parent = searchParent(value);
        //如果目标节点是叶子节点的情况
        if (targetNode.left == null && targetNode.right == null) {
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        //如果目标节点有两个子树的情况
        else if (targetNode.left != null && targetNode.right != null) {
//            int minVal = delRightTreeMin(targetNode);
//            targetNode.value = minVal;
            int maxVal = delLeftTreeMax(targetNode);
            targetNode.value = maxVal;
        }
        //如果目标节点有一个子树的情况
        else {
            //目标节点有一个左子树
            if (targetNode.left != null) {
                if (parent != null) {
                    //判断该目标节点是其父节点的左子节点还是右子结点
                    if (parent.left != null && parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {
                    parent = targetNode.left;
                }
            }
            //目标节点有一个右子树
            else {
                if (parent != null) {
                    //判断该目标节点是其父节点的左子节点还是右子结点
                    if (parent.right != null && parent.right.value == value) {
                        parent.right = targetNode.right;
                    } else {
                        parent.left = targetNode.right;
                    }
                } else {
                    parent = targetNode.right;
                }

            }

        }
    }

    /**
     * 找到右子树最小的值的节点，并删除该节点
     *
     * @param node
     * @return
     */
    private int delRightTreeMin(Node node) {
        Node target = node.right;
        //循环的查找右子结点
        while (target.left != null) {
            target = target.left;
        }
        //找到了最小的节点
        delNode(target.value);
        return target.value;
    }

    /**
     * 找到左子树最大的值的节点，并删除该节点
     *
     * @param node
     * @return
     */
    private int delLeftTreeMax(Node node) {
        Node target = node.left;
        //循环查找左子树的最大值
        while (target.right != null) {
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }


}

/**
 * 树节点
 */
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    /**
     * 查找要删除的节点
     *
     * @param value
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }


    /**
     * 查找要删除的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            }
            //如果查找的值大于当前节点的值，并且当前节点的右子节点不为空
            else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }


    /**
     * 递归处理添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历二叉排序树
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
