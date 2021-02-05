package top.javahai.tree.huffmantree.code;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 哈夫曼编码
 * @create 2021/1/30 - 23:43
 **/
public class HuffmanTreeCode {

    private static Map<Byte,String> huffmanCodes=new HashMap<>();
    private static StringBuilder stringBuilder=new StringBuilder();

    public static void main(String[] args) {
        String content="i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("字节统计列表：");
        nodes.forEach(System.out::println);
        Node node = createHuffmanTree(nodes);
        System.out.println("遍历生成的赫夫曼树:");
        preOrder(node);
        System.out.println("生成的赫夫曼编码：");
        Map<Byte, String> huffmanCodes = getCodes(node);
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
        System.out.println("使用生成的赫夫曼编码压缩数组");
        byte[] huffmanCodeByte = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(huffmanCodeByte));

        byte[] zipByte = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(zipByte));
        byte[] decodeByte = decode(huffmanCodes, zipByte);
        System.out.println(new String(decodeByte));

        System.out.println("文件压缩：");
//        String srcFile="D://1.jpg";
//        String dstFile="D://1.zip";
////        zipFile(srcFile,dstFile);
//        String dstFile2="D://2.jpg";
//        unZipFile(dstFile,dstFile2);






    }

    /**
     * 文件压缩
     * @param srcFile 源文件的源路径
     * @param dstFile 压缩后的文件的存放路径
     */
    public static void zipFile(String srcFile,String dstFile){
        //输出流
        OutputStream outputStream=null;
        ObjectOutputStream objectOutputStream=null;
        //文件的输入流
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=new FileInputStream(srcFile);
            //创建与源文件的字节数相同的字节数组
            byte[] bytes = new byte[fileInputStream.available()];
            //读取字节格式的文件到bytes数组中
            fileInputStream.read(bytes);
            //对文件进行压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            //创建文件的输出流
            outputStream=new FileOutputStream(dstFile);
            //创建文件输出流相关联的ObjectOutputStream
            objectOutputStream=new ObjectOutputStream(outputStream);
            //将赫夫曼编码后的字节数组写入到输出流
            objectOutputStream.writeObject(huffmanBytes);
            //将赫夫曼编码map写入到输出流中
            objectOutputStream.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                objectOutputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("压缩成功！");
        }

    }

    /**
     * 解压文件
     * @param zipFile 准备解压的文件
     * @param dstFile 解压文件保存路径
     */
    private static void unZipFile(String zipFile,String dstFile){
        InputStream inputStream=null;
        ObjectInputStream objectInputStream=null;
        OutputStream outputStream=null;
        //创建文件输入流
        try {
            inputStream=new FileInputStream(zipFile);
            objectInputStream=new ObjectInputStream(inputStream);
            //读取字节数组
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            //读取赫夫曼编码map
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            //解码
            byte[] decodeBytes = decode(huffmanCodes, huffmanBytes);
            outputStream=new FileOutputStream(dstFile);
            //写字节数据到输出流
            outputStream.write(decodeBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 封装方法,使用赫夫曼编码压缩数组
     * @param bytes
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        Node root = createHuffmanTree(nodes);
        Map<Byte, String> codes = getCodes(root);
        byte[] zipByte = zip(bytes, codes);
        return zipByte;
    }

    /**
     * 对赫夫曼编码的数据解码成原有的字节数组
     * @param huffmanCodes  赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的数据
     * @return
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag=(i==huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag,huffmanBytes[i]));
        }
        System.out.println(stringBuilder.toString());
        //将赫夫曼编码表的key和value调换,得到解码用的解码表
        HashMap<String, Byte> map = new HashMap<>(16);
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        //存放解码得到的字节
        ArrayList<Byte> list = new ArrayList<>();
        //遍历编码后的字符串的下标；累加器
        int count;
        for (int i = 0; i < stringBuilder.length();i+=count) {
            count=1;
            boolean flag=true;
            Byte b=null;

            while (flag){
                //递增地对取key，直到找到
                String key = stringBuilder.substring(i, i + count);
                if (map.containsKey(key)){
                    flag=false;
                    b=map.get(key);
                }else{
                    count++;
                }
            }
            list.add(b);
        }
        //将list转换为byte输出
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i]+=list.get(i);
        }
        return result;
    }

    /**
     * //TODO 补充复习二进制的知识
     * 将一个字节转成二进制的字符串
     * @param flag flag标志是否需要补高位，如果是true，表示需要补高位
     * @param b b对应的二进制的字符串，以补码返回
     * @return
     */
    private static String byteToBitString(boolean flag,byte b){
        //将b转化成int类型
        int temp=b;
        if (flag){
            temp|=256;
        }
        String str=Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }
    }

    private static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }
    }


    /**
     * 获取赫夫曼编码
     * @param root
     * @return
     */
    private static Map<Byte,String> getCodes(Node root){
        if (root==null){
            return null;
        }
        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }


    /**
     * 获取存入结点的所有叶子结点的赫夫曼编码，加入到huffmanCodes中
     * @param node
     * @param code
     * @param stringBuilder
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(code);
        if (node!=null){
            //非叶子节点不做处理,往下进行递归处理
            if (node.data==null){
                //向左子节点进行递归
                getCodes(node.left,"0",builder);
                //向右子节点进行递归
                getCodes(node.right,"1",builder);
            }else {
                //当前是叶子节点将code加入到huffmanCodes中
                huffmanCodes.put(node.data,builder.toString());
            }
        }
    }

    /**
     * 将字节数组转换为List形式的Node
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodeList = new ArrayList<>();
        //统计byte的个数
        Map<Byte, Integer> map = new HashMap<>(bytes.length);
        for (byte b : bytes) {
            if (map.containsKey(b)){
                map.put(b,map.get(b)+1);
            }else{
                map.put(b,1);
            }
        }
        //将map的键值对转化为Node
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(),entry.getValue());
            nodeList.add(node);
        }
        return nodeList;
    }

    /**
     * 将字符串对应的byte数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte数组
     * @param bytes 原始字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte数组
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用huffmanCodes将byte转成赫夫曼编码对应的字符串
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(huffmanCodes.get(b));
        }
        System.out.println(builder.toString());
        //统计返回的结果数组的长度
        int length;
        if (builder.length()%8==0){
            length=builder.length()/8;
        }else{
            length=builder.length()/8+1;
        }
        //存储赫夫曼编码处理后的byte数组
        byte[] decodeByte = new byte[length];
        int index=0;
        for (int i = 0; i < builder.length(); i+=8) {
            String strByte;
            if (i+8>builder.length()){
                strByte=builder.substring(i);
            }else{
                strByte=builder.substring(i,i+8);
            }
            //将strByte存入到huffmanCodeByte中
            decodeByte[index]=((byte) Integer.parseInt(strByte, 2));
            index++;
        }
        return decodeByte;
    }


    /**
     * 创建哈夫曼树
     * @param nodeList
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodeList){
        while (nodeList.size()>1){
            //排序
            Collections.sort(nodeList);
            //取出最小的两个结点
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            //组装父节点
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left=leftNode;
            parentNode.right=rightNode;
            //将最小的两个结点移除
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            //将组装的父节点(二叉树)放入到集合中
            nodeList.add(parentNode);
        }
        return nodeList.get(0);
    }


}




/**
 * Node类用于存放数据和数据的权值
 */
class Node implements Comparable<Node>{
    /**
     * 存放数据(字符)本身，如'a'=97(ASCII码)
     */
    Byte data;
    /**
     * 权值，表示字符出现的次数
     */
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }
}



