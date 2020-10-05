package top.javahai.linkedlist;

/**
 * 约瑟夫问题求解
 * @author Hai
 * @date 2020/9/20 - 23:21
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.show();
    }
}


/**
 *单向环形链表实现
 */
class CircleSingleLinkedList{
    //first指针，指向链表的第一个节点
    private Boy first=null;

    /**
     *根据输入的数量，添加多个节点，构建环形链表
     * 步骤：
     * 1.如果是第一个，first指针指向它
     * 2.current节点的next指向添加的节点，添加的节点的next指向first指向的节点
     * 3.
     */
    public void add(int num){
        if (num<1){
            System.out.println("请输入正确的值");
            return;
        }
        //辅助指针,指向当前新添加的节点
        Boy current=null;
        for (int i = 1; i <=num; i++) {
            Boy boy = new Boy(i);
            if (i==1){
                first=boy;
                //构成环形
                boy.setNext(first);
                current=first;
            }else{
                current.setNext(boy);
                boy.setNext(first);
                current=boy;
            }
        }
    }

    /**
     * 遍历单向环形链表
     */
    public void show(){
        //用作指向当前节点
        Boy current=first;
        while (true){
            System.out.println("男孩的编号："+current.getNo());
            if (current.getNext()==first){
                break;
            }
            current=current.getNext();
        }

    }
}

/**
 * 模拟链表的节点
 */
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
