package top.javahai.stack;

/**
 * 使用双向链表模拟栈
 * @author Hai
 * @date 2020/9/22 - 23:55
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkdeListStack linkdelistStack = new LinkdeListStack();
        linkdelistStack.push(1);
        linkdelistStack.push(2);
        linkdelistStack.push(3);
        linkdelistStack.push(4);
        linkdelistStack.list();
        linkdelistStack.pop();
        linkdelistStack.pop();
        linkdelistStack.pop();
        linkdelistStack.pop();
        linkdelistStack.pop();
        linkdelistStack.list();
    }
}

class LinkdeListStack{

    //双向链表模拟栈
    ListNode stack=new ListNode(-1);
    //指向最后一个节点
    ListNode top=stack;

    //是否为空
    public boolean isEmpty(){
        return top.getVal()==-1;

    }

    //入栈
    public void push(int val){
        ListNode node = new ListNode(val);
        top.setNext(node);
        node.setPre(top);
        top=top.getNext();
    }
    //出栈
    public void pop(){
        if (isEmpty()){
            System.out.println("链表为空");
            return;
        }
        top=top.getPre();
        top.setNext(null);

    }

    //输出栈的元素
    public void list(){
        if (isEmpty()){
            System.out.println("链表为空");
            return;
        }
        ListNode cur=stack;
        while (cur.getNext()!=null){
            System.out.println(cur.getNext().getVal());
            cur=cur.getNext();
        }
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode pre;

    public ListNode getPre() {
        return pre;
    }

    public void setPre(ListNode pre) {
        this.pre = pre;
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
