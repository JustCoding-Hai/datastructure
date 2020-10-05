package top.javahai.queue;

/**
 * 循环队列，数组实现
 *
 * 边界条件：
 * 1.(rear+1)%maxSize==font 时，循环队列满
 * 2.rear==font时，队列为空
 * 变量定义：
 * 1.front指向队列的第一个元素，front的初始值为0
 * 2.rear指向队列的最后一个元素的后一个位置，rear的初始值为0
 *
 *
 * 通过以上分析，队列中有效的数据个数(rear+maxSize+front)%maxSize
 * @author Hai
 * @date 2020/8/24 - 23:40
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue<String> queue = new CircleArrayQueue<>(5);
    }

    /**
     * 使用数组模拟队列
     */
    static class CircleArrayQueue<T>{
        //最大容量
        private int max_capacity;
        //头部指针
        private int head=0;
        //尾部指针
        private int rear=0;
        //数组模拟队列
        private Object[] queue;
        /**
         * 构造方法
         */
        public CircleArrayQueue(int max_capacity) {
            this.max_capacity = max_capacity;
            this.queue=new Object[max_capacity];
        }
        /**
         *  是否已满
         * 两种情况：以容量为5的队列为例
         * 1.正好全部填满，head=0，rear=4
         * 2.rear在head前面，循环存放，head=2，rear=1
         */
        public boolean isFull(){

            return (rear+1)%max_capacity==head;
        }
        //是否为空
        public boolean isEmpty(){
            return head==rear;
        }
        /**
         *    添加数据到队列
         *    rear指向最后一个元素的后一个位置
         */
        public void addData(T a){
           if (isFull()){
               System.out.println("队列已满，请稍后！");
           }else{
               queue[rear]=a;
               rear=(rear+1)%max_capacity;
               System.out.println(a+"进入队列");
           }
        }
        /**
         从队列获取数组，先进先出并且出列
         */
        public T getData(){
            if (isEmpty()){
                System.out.println("队列为空");
                return null;
            }else{
                T result=(T) queue[head];
                queue[head]=null;
                head=(head+1)%max_capacity;
                return result;
            }
        }

        /**
         * 队列中的有效数据个数
         */
        public int size(){
            return (rear+max_capacity-head)%max_capacity;
        }
        //显示队列所有数据
        public void showQueue(){
            if (isEmpty()){
                System.out.println("队列为空！");
                return;
            }
            System.out.println("当前队列情况：");
            for (int i = head; i <head+size() ; i++) {
                System.out.printf("arr[%d]=%d\n",i%max_capacity,queue[i%max_capacity]);
            }
        }

        /**
         *         显示队列的头数据 peek()
         * @return
         */
        public void peek(){
            if (isEmpty()){
                System.out.println("队列为空！");
                return;
            }
            System.out.println("当前队列第一个数据："+(T) queue[head]);
        }
    }
}
