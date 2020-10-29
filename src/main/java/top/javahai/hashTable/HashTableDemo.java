package top.javahai.hashTable;

import java.util.Objects;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 哈希表的模拟实现，使用数组加链表的实现
 * @create 2020/10/29 - 22:45
 **/
public class HashTableDemo {
    public static void main(String[] args) {

        EmpHashTable empHashTable = new EmpHashTable(5);
        empHashTable.list();
        empHashTable.add(new Employee(1, "张三"));
        empHashTable.add(new Employee(2, "李四"));
        empHashTable.add(new Employee(3, "王五"));
        empHashTable.add(new Employee(4, "赵六"));
        empHashTable.add(new Employee(5, "王琦"));
        empHashTable.add(new Employee(6, "老色批"));
        empHashTable.list();
        empHashTable.findEmpById(6);
        empHashTable.findEmpById(10);


    }


}

/**
 * 表示哈希表
 */
class EmpHashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public EmpHashTable(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //数组默认每个元素为null,需要初始化
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加Employee到哈希表中
     *
     * @param employee
     */
    public void add(Employee employee) {
        int empLinkedListNo = hash(employee.getId());
        empLinkedListArray[empLinkedListNo].add(employee);
    }

    /**
     * 展示出所有的哈希表的数据
     */
    public void list() {
        System.out.println("=》哈希表的数据：");
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i);
        }

    }

    /**
     * 根据ID查找雇员信息
     *
     * @param id
     */
    public void findEmpById(int id) {
        int empLinkedListNo = hash(id);

        empLinkedListArray[empLinkedListNo].findEmpById(id);

    }

    /**
     * 散列函数，通过Employee的id获取散列值
     *
     * @param no
     * @return
     */
    public int hash(int no) {
        return no % size;
    }


}

/**
 * 表示链表
 */
class EmpLinkedList {
    Employee head;

    /**
     * 添加雇员到链表中
     *
     * @param e
     */
    public void add(Employee e) {
        //如果head为空
        if (Objects.isNull(head)) {
            head = e;
            return;
        } else {//head不为空，找到最后一个节点进行添加
            Employee current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = e;
            return;
        }
    }

    /**
     * 遍历链表控制台输出
     */
    public void list(int no) {
        if (head == null) {
            System.out.printf("第%d个链表为空\n", no + 1);
            return;
        }
        System.out.printf("第%d个链表的信息：", no + 1);
        Employee current = head;

        while (current != null) {
            System.out.print(current.toString() + "->");
            current = current.next;
        }
        System.out.println();

    }

    public void findEmpById(int id) {
//        if (head==null){
//            System.out.printf("第%d个链表为空\n",no+1);
//            return;
//        }
        Employee current = head;

        while (current != null) {
            if (current.getId() == id) {
                System.out.println("找到的信息：" + current.toString());
                return;
            }
            current = current.next;
        }
        System.out.println("找不到对应的id=" + id + "的雇员信息");

    }


}

/**
 * 表示链表的节点
 */
class Employee {

    /**
     * 指向下一个节点
     */
    Employee next;
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

