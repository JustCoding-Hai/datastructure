package top.javahai.linkedlist;

import java.awt.*;
import java.util.Stack;

/**
 * 单链表实现
 * @author Hai
 * @date 2020/8/26 - 0:14
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试数据
        HeroNode heroNode01 = new HeroNode(1, "张三", "33");
        HeroNode heroNode02 = new HeroNode(2, "李四", "44");
        HeroNode heroNode03 = new HeroNode(3, "王五", "55");
        HeroNode heroNode04 = new HeroNode(4, "赵六", "66");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode01);
        singleLinkedList.addByOrder(heroNode04);
        singleLinkedList.addByOrder(heroNode03);
        singleLinkedList.addByOrder(heroNode02);
        singleLinkedList.addByOrder(heroNode02);
        singleLinkedList.list();
        System.out.println("-----------更新测试-----------");
        HeroNode heroNode05 = new HeroNode(4, "田七", "77");
        singleLinkedList.update(heroNode05);
        singleLinkedList.list();
        System.out.println("-----------获取测试-----------");
        System.out.println(singleLinkedList.findLastIndexNode(2,singleLinkedList.getHead()));
        System.out.println("-----------删除测试-----------");
        singleLinkedList.delete(2);
        singleLinkedList.delete(4);
        singleLinkedList.list();
        System.out.println("当前有效的节点个数："+singleLinkedList.getLength(singleLinkedList.getHead()));
        singleLinkedList.addByOrder(heroNode04
        );
        singleLinkedList.addByOrder(heroNode02);
        System.out.println("-----------反转链表测试-----------");
        singleLinkedList.list();
        System.out.println("--------反转链表--------");
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        System.out.println("-----------逆向打印链表-----------");
        singleLinkedList.reversePrint(singleLinkedList.getHead());

    }


}
//定义SingleLinkedList管理英雄节点，表示单向链表
class SingleLinkedList{
    //初始化一个头节点，头节点不要动，并且不存放具体的数据
    public HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    /**
     *  添加节点到单向链表
     *  实现思路：
     *  1.找到当前链表的最后一个节点,由于头节点不能移动，需要一个临时节点（辅助指针）进行遍历
     *  2.将最后一个节点的next指向新的节点
     */
    public void add(HeroNode heroNode){
        HeroNode tempNode=head;
        while (true){
            if (tempNode.next==null){
                tempNode.next=heroNode;
                break;
            }else {
                tempNode=tempNode.next;
            }
        }

    }

    /**
     * 根据英雄的排名插入到链表的指定位置，如果插入失败（已存在排名），就给出提示。
     * temp.next.no比较heroNode.no：
     * 1.等于，节点已存在
     * 2.大于，就在temp的后面插入
     * 3.小于，继续找到下一个节点Node
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        //建立辅助指针
        HeroNode temp=head;
        while (true){
            //找到插入的位置或到达链表的最后一个节点（先判断避免空指针）
            if (temp.next==null||temp.next.no>heroNode.no){
                //插入结点到链表中,temp为最后一个节点也可以赋值为null
                heroNode.next=temp.next;
                temp.next=heroNode;
                break;
            }
            //已存在排名
            if (temp.next.no==heroNode.no){
                System.out.println("【"+heroNode.name+"】插入失败！已存在该英雄节点！");
                return;
            }
            temp=temp.next;
        }

    }

    /**
     * 修改节点的信息，根据no编号来修改，HeroNode的no属性不能变
     * @param heroNode
     */
    public void update(HeroNode heroNode){
        HeroNode temp=head;
        while (true){
            if (temp.no==heroNode.no){
                temp.name=heroNode.name;
                temp.nickname=heroNode.nickname;
                break;
            }
            if (temp.next==null){
                System.out.println("【"+heroNode.name+"】不存在！");
                break;
            }
            temp=temp.next;
        }

    }

    /**
     * 删除节点
     * 思路：
     * 1.head不能动，需要一个辅助指针找到删除节点的前一个节点
     * 2.比较时是temp.next.no和需要删除的no比较
     * @param no
     */
    public void delete(int no){
        HeroNode temp=head;
        while (true){
            if(temp.next==null){
                System.out.println("找不到要删除的节点！");
                return;
            }
            //优先考虑要删除的是节点为最后一个节点的情况
            if (temp.next.no==no&&temp.next.next==null){
                temp.next=null;
                break;
            }
            if (temp.next.no==no){
                temp.next=temp.next.next;
                break;
            }
            temp=temp.next;
    }
    }

    /**
     * 遍历当前链表，并输出
     */
    public void list(){
        //链表是否为空
        if (head.next==null){
            System.out.println("当前链表为空！");
            return;
        }
        //建立辅助指针
        HeroNode temp=head.next;
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }

    /**
     * 获取链表的有效节点个数
     * 如果是带头节点的链表，需求不统计头节点
     * @param head
     * @return
     */
    public Integer getLength(HeroNode head){
        if (head.next==null){
            return 0;
        }
        int size=0;
        HeroNode temp=head.next;
        while (temp!=null){
            size++;
            temp=temp.next;
        }
        return size;
    }

    /**
     * 找到单链表中的倒数第k个节点
     * 1.遍历单链表统计有效数
     * 2.遍历到（size-index）-1的结点就是倒数第k个结点(可以以两个结点的链表来模拟)
     * @param index
     * @return
     */
    public HeroNode findLastIndexNode(int index,HeroNode head){
       int size=getLength(head);
       if (index==-1||index>size){
           System.out.println("请输入有效的结点位置！");
           return null;
       }
       HeroNode temp=head.next;
       for (int i=0;i<size-index;i++){
           temp=temp.next;
       }
       return temp;
    }

    /**
     * 反转链表
     * 1.如果head的next为空
     * 2.新建临时结点，用于遍历链表：
     *    current作为当前结点
     *    next作为当前结点的下一个结点
     *    reverseNode作为反转后的头结点,一个新的结点
     *
     * 3.遍历链表，每次遍历：
     *             next=current.next
     *             current.next=reverseNode.next
     *             reverseNode.next=current，
     *             current=next;遍历源链表的下一个结点
     *
     *  4.遍历完成，连接回原本链表的头结点head。
     *
     * @param head
     */
    public HeroNode reverseList(HeroNode head){
        if (head.next==null){
            return head;
        }
        HeroNode current=head.next;
        HeroNode next=null;
        HeroNode reverseNode=new HeroNode(0,"","");
        while(current!=null){
            next=current.next;
            current.next=reverseNode.next;
            reverseNode.next=current;
            current=next;
        }
        head.next=reverseNode.next;
        return head;
    }

    /**
     * 逆序打印单链表
     * @param head
     */
    public void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        HeroNode current=head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (current!=null){
            stack.add(current);
            current=current.next;
        }
        //打印栈
        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }
}
//定义一个HeroNode，表示一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//表示指向的下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname+"'}";
    }
}
