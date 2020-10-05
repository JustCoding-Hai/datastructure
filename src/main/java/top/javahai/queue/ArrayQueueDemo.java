package top.javahai.queue;

/**
 * 单向队列，数组实现
 * @author Hai
 * @date 2020/8/24 - 23:40
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>(5);
        queue.peek();
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        queue.addData("a");
        queue.addData("b");
        queue.addData("c");
        queue.addData("d");
        queue.addData("e");
        queue.addData("f");
        queue.showQueue();
        System.out.println(queue.getData());
        queue.addData("f");
        queue.showQueue();
        queue.peek();


    }

    /**
     * 使用数组模拟队列
     */
    static class ArrayQueue<T>{
        //最大容量
        private int max_capacity=0;
        //头部指针,-1
        private int head=-1;
        //尾部指针
        private int rear=-1;
        //数组模拟队列
        private Object[] queue;
        /**
         * 构造方法
         */
        public ArrayQueue(int max_capacity) {
            this.max_capacity = max_capacity;
            this.queue=new Object[max_capacity];
        }
        //是否已满
        public boolean isFull(){
            return rear>=max_capacity-1;
        }
        //是否为空
        public boolean isEmpty(){
            return head==rear;
        }
        /**
         *    添加数据到队列
         */
        public void addData(T a){
           if (isFull()){
               System.out.println("队列已满，请稍后！");
           }else{
               rear++;
               queue[rear]=a;
               System.out.println(a+"进入队列");
           }
        }
        //从队列获取数组，先进先出并且出列
        public T getData(){
            if (isEmpty()){
                System.out.println("队列为空");
                return null;
            }else{
                head++;
                T result=(T) queue[head];
                queue[head]=null;
                return result;
            }
        }
        //显示队列所有数据
        public void showQueue(){
            if (isEmpty()){
                System.out.println("队列为空！");
                return;
            }
            System.out.println("当前队列情况：");
            for (int i = 0; i < queue.length; i++) {
                System.out.println("队列第"+i+"位数据："+queue[i]);
            }
        }
        //显示队列的头数据 peek()
        public void peek(){
            if (isEmpty()){
                System.out.println("队列为空！");
                return;
            }
            System.out.println("当前队列第一个数据："+(T) queue[head]);
        }
    }
}
