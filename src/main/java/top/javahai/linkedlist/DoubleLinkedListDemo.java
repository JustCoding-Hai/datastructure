package top.javahai.linkedlist;

/**
 * 双向链表Demo
 * @author Hai
 * @date 2020/9/20 - 21:50
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试数据
        HeroNode2 heroNode01 = new HeroNode2(1, "张三", "33");
        HeroNode2 heroNode02 = new HeroNode2(2, "李四", "44");
        HeroNode2 heroNode03 = new HeroNode2(3, "王五", "55");
        HeroNode2 heroNode04 = new HeroNode2(4, "赵六", "66");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        System.out.println("-----------增加测试-----------");
        doubleLinkedList.add(heroNode01);
        doubleLinkedList.add(heroNode04);
        doubleLinkedList.add(heroNode03);
        doubleLinkedList.add(heroNode02);
        doubleLinkedList.list();
        System.out.println("-----------更新测试-----------");
        HeroNode2 heroNode05 = new HeroNode2(4, "田七", "77");
        doubleLinkedList.update(heroNode05);
        doubleLinkedList.list();
        System.out.println("-----------删除测试-----------");
        doubleLinkedList.delete(4);
        doubleLinkedList.delete(2);
        doubleLinkedList.list();
        System.out.println("-----------按序增加测试-----------");
        doubleLinkedList.addByOrder(heroNode01);
        doubleLinkedList.addByOrder(heroNode04);
        doubleLinkedList.addByOrder(heroNode03);
        doubleLinkedList.addByOrder(heroNode02);
        doubleLinkedList.addByOrder(heroNode02);
        doubleLinkedList.list();
    }


}

/**
 * 双向链表实现
 */
class DoubleLinkedList{

    //初始化一个头节点，头节点不要动，并且不存放具体的数据
    public HeroNode2 head=new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     *  添加节点到双链表
     *  实现思路：
     *  1.找到当前链表的最后一个节点,由于头节点不能移动，需要一个临时节点（辅助指针）进行遍历
     *  2.将最后一个节点的next指向新的节点，新的节点的pre指向原本最后的一个节点
     */
    public void add(HeroNode2 heroNode){
        HeroNode2 tempNode=head;
        while (true){
            if (tempNode.next==null){
                tempNode.next=heroNode;
                heroNode.pre=tempNode;
                break;
            }else {
                tempNode=tempNode.next;
            }
        }

    }

    /**
     * 根据英雄的排名从小到大插入到双向链表的指定位置，如果插入失败（已存在排名），就给出提示。
     * temp.next.no比较heroNode.no：
     * 1.等于，节点已存在
     * 2.大于，就在temp的后面插入
     * 3.小于，继续找到下一个节点Node
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode){
        //建立辅助指针
        HeroNode2 temp=head;
        while (true){

            //找到插入的位置或到达链表的最后一个节点（先判断避免空指针）,就是没有比它大的节点
            if (temp.next==null){
                temp.next=heroNode;
                heroNode.pre=temp;
                //避免重复使用节点受前面的影响，导致死循环
                heroNode.next=null;
            }
            if (temp.next!=null&&temp.next.no>heroNode.no){
                //插入结点到链表中,temp为最后一个节点也可以赋值为null
                heroNode.next=temp.next;
                temp.next.pre=heroNode;
                temp.next=heroNode;
                heroNode.pre=temp;
                break;
            }
            //已存在排名(不能放前面)
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
    public void update(HeroNode2 heroNode){
        HeroNode2 temp=head;
        while (true){
            if (temp.no==heroNode.no){
                temp.name=heroNode.name;
                temp.nickname=heroNode.nickname;
                break;
            }
            //找到最后都找不到
            if (temp.next==null){
                System.out.println("【"+heroNode.name+"】不存在！！！");
                break;
            }
            temp=temp.next;
        }

    }

    /**
     * 双向链表删除节点
     * 思路：
     * 因为链表可以实现自我删除
     * @param no
     */
    public void delete(int no){
        HeroNode2 temp=head;
        if (head.next==null){
            System.out.println("链表为空，无法正常删除");
        }
        while (true){
            if(temp==null){
                System.out.println("找不到要删除的节点！");
                return;
            }
            //优先考虑要删除的是节点为最后一个节点的情况
            if (temp.no==no&&temp.next==null){
                temp.pre.next=null;
                break;
            }
            if (temp.no==no){
                temp.pre.next=temp.next;
                temp.next.pre=temp.pre;
                break;
            }
            temp=temp.next;
        }
    }

    /**
     * 遍历当前双向链表，并输出
     */
    public void list(){
        //链表是否为空
        if (head.next==null){
            System.out.println("当前链表为空！");
            return;
        }
        //建立辅助指针
        HeroNode2 temp=head.next;
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
    public Integer getLength(HeroNode2 head){
        if (head.next==null){
            return 0;
        }
        int size=0;
        HeroNode2 temp=head.next;
        while (temp!=null){
            size++;
            temp=temp.next;
        }
        return size;
    }

}
//定义一个HeroNode，表示一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//表示指向的下一个节点
    public HeroNode2 pre;//表示指向的上一个节点

    public HeroNode2(int no, String name, String nickname) {
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
