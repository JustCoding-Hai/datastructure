package top.javahai.graph;


import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 图
 * @create 2021/2/8 - 16:45
 **/
public class Graph {

    /**
     * 顶点集合
     */
    private ArrayList<String> vertexList;
    /**
     * 存储图对应的邻接矩阵
     */
    private int[][] edges;
    /**
     * 边界的数量
     */
    private int edgeNum;
    /**
     * 结点是否被遍历到
     */
    private boolean[] isVisited;

    public Graph(int n){
        vertexList=new ArrayList<>(n);
        edges=new int[n][n];
        edgeNum=0;
        isVisited=new boolean[n];
    }

    /**
     * 插入顶点到vertexList
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 顶点在edges的下标
     * @param v2
     * @param weight 1或0，1表示有连接，0表示无连接
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        edgeNum++;
    }

    /**
     * 从下标为0的顶点开始遍历
     */
    private void dfs(){
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i]= false;
        }
        //因为可能存在无连接的结点，所以需要遍历每个结点
        for (int i = 0; i < getVertexNum(); i++) {
            if (!isVisited[i]){
                dfs(i);
            }
        }
    }

    /**
     * 深度优先遍历算法
     * @param i 顶点的下标
     */
    private void dfs(int i){
        System.out.print(getValueByIndex(i)+"=>");
        //标记为已经访问过
        isVisited[i]=true;
        //查找下一个邻接结点w
        int w=getFirstNeighbor(i);
        while (w!=-1){
            //如果没有访问过,就继续往下遍历
            dfs(w);
            //如果w被访问过了，访问v下一个邻接结点
            w=getNextNeighbor(i,w);
        }
    }


    private void bfs(){
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i]= false;
        }
        //因为可能存在无连接的结点，所以需要遍历每个结点
        for (int i = 0; i < getVertexNum(); i++) {
            if (!isVisited[i]){
                bfs(i);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param i
     */
    private void bfs(int i){
        //表示队列头节点对应的下标
        int u;
        //邻接结点w
        int w;
        //使用LinkedList做队列
        LinkedList<Integer> queue = new LinkedList<>();
        //标记为已访问
        isVisited[i]=true;
        System.out.print(getValueByIndex(i)+"=>");
        //将结点加入队列中
        queue.addLast(i);
        while (!queue.isEmpty()){
             //取出队列头节点的坐标
             u= queue.removeFirst();
             //得到第一个邻接结点
             w=getFirstNeighbor(u);
             //广度优先算法体现，遍历当前结点所有连接的结点，并添加到队列中
             while (w!=-1){
                 System.out.print(getValueByIndex(w)+"=>");
                 //标记为已经访问
                 isVisited[w]=true;
                 queue.addLast(w);
                 w=getNextNeighbor(u,w);
             }
        }
    }

    /**
     * 获取index的第一个未访问过的邻接结点
     * @param index
     * @return
     */
    private int getFirstNeighbor(int index) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i]>0&&!isVisited[i]){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据上一个邻接结点的下标edgeIndex，获取结点vertexIndex的下一个未访问过的邻接结点
     * @param vertexIndex
     * @param edgeIndex
     * @return
     */
    private int getNextNeighbor(int vertexIndex,int edgeIndex){
        for (int i = edgeIndex+1; i < edges.length; i++) {
            if (edges[vertexIndex][i]>0&&!isVisited[i]){
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回结点的数量
     * @return
     */
    public int getVertexNum(){
        return vertexList.size();
    }

    /**
     * 得到图的边数量
     * @return
     */
    public int getEdgeNum(){
        return edgeNum;
    }

    /**
     * 通过下标获取结点对应的数据
     * @param index
     * @return
     */
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 返回v1和v2之间的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    /**
     * 显示矩阵
     */
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    public static void main(String[] args) {
//        String[] vertexes={"A","B","C","D","E"};
        String[] vertexes={"1","2","3","4","5","6","7","8"};

        int size=8;
        Graph graph = new Graph(size);
        //插入顶点
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        //添加边
//        //A-B
//        graph.insertEdge(0,1,1);
//        //A-C
//        graph.insertEdge(0,2,1);
//        //B-C
//        graph.insertEdge(1,2,1);
//        //B-D
//        graph.insertEdge(1,3,1);
//        //B-E
//        graph.insertEdge(1,4,1);
//        //C-E
//        graph.insertEdge(2,4,1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.showGraph();
        System.out.println("深度优先遍历：");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先遍历：");
        graph.bfs();
    }
}
