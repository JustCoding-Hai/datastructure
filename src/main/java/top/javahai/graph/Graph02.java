package top.javahai.graph;

import java.util.ArrayList;


/**
 * @author Hai
 * @program: DataStructure
 * @description: 图
 * @create 2021/2/8 - 16:45
 **/
public class Graph02 {

    /**
     * 顶点集合
     */
    private ArrayList<String> vertexList;
    /**
     * 存储图对应的邻接矩阵
     */
    private VertexNode[] edges;
    /**
     * 边界的数量
     */
    private int edgeNum;

    public Graph02(int n){
        vertexList=new ArrayList<>(n);
        edges=new VertexNode[n];
        edgeNum=0;
    }

    /**
     * 插入顶点到vertexList
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }


    /**
     *插入边
     * @param index  数组下标
     * @param vertex 连接的顶点
     */
    public void insertEdge(int index,VertexNode vertex){
        if (edges[index]==null){
            edges[index]=vertex;
        }else{
            VertexNode temp=edges[index];
            while (temp.next!=null){
                temp=temp.next;
            }
            temp.next=vertex;
        }
    }

    /**
     *
     * @param v1 顶点1的下标
     * @param v2 顶点2的下标
     */
    public void insertEdge(int v1,int v2){
        insertEdge(v1,new VertexNode(v2));
        insertEdge(v2,new VertexNode(v1));
        edgeNum++;
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
    public String getVauleByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 返回顶点v1和顶点v2之间的权值，1表示有连接，0表示无连接
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2) {
        if (v1>=vertexList.size()||v2>=vertexList.size()){
            return 0;
        }
        if (edges[v1]==null){
            return 0;
        }
        VertexNode temp=edges[v1];
        while (temp!=null){
            if (temp.data==v2){
                return 1;
            }
            temp=temp.next;
        }
        return 0;
    }

    /**
     *
     */
    public void showVertexNode(VertexNode vertexNode){
        VertexNode temp=vertexNode;
        while (temp!=null){
            System.out.print(temp.toString());
            temp=temp.next;
        }
    }

    /**
     * 显示矩阵
     */
    public void showGraph(){
        int i=0;
        for (VertexNode edge : edges) {
            if (edge!=null){
                System.out.print(i++ +": ");
            }
            showVertexNode(edge);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] vertexes={"A","B","C","D","E"};
        int size=5;
        Graph02 graph = new Graph02(size);
        //插入顶点
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B,
        graph.insertEdge(0,1);
        //A-C
        graph.insertEdge(0,2);
        //B-C
        graph.insertEdge(1,2);
        //B-D
        graph.insertEdge(1,3);
        //B-E
        graph.insertEdge(1,4);
        System.out.println("该图的边的数量："+graph.getEdgeNum());
        System.out.println("该图的结点数量："+graph.getVertexNum());
        graph.showGraph();
    }
}
/**
 * 顶点类
 */
class VertexNode{
    public int data;
    public VertexNode next;

    public VertexNode(int data) {
        this.data = data;
    }

    public VertexNode(int data, VertexNode next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "VertexNode{" +
                "data=" + data+"}->";
    }
}

