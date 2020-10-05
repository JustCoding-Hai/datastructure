package top.javahai.sparseArray;

import java.io.*;

/**
 * 待解决 IO 的读取和写入练习
 * @author Hai
 * @date 2020/8/24 - 0:14
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        int[][] arr={{0,1,0,9,0},{0,1,0,0,0},{0,1,0,0,0},{0,1,0,0,0},{0,0,0,0,0}};
        System.out.println("原始二维数组：");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%d\t",arr[i][j]);
            }
            System.out.println();
        }
        int[][] sparseArray = toSparseArray(arr);
        //输出稀疏数组
        System.out.println("稀疏数组：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        System.out.println("转化回二维数组：");
        int[][] revertArr = toTwoDimensionalArray(sparseArray);
        for (int i = 0; i < revertArr.length; i++) {
            for (int j = 0; j < revertArr[i].length; j++) {
                System.out.printf("%d\t",revertArr[i][j]);
            }
            System.out.println();
        }
        saveToDisk(sparseArray);
        readArrFromDisk("sparseArray.data");
    }


    /**
     * 读取硬盘中的文件保存的数组数据
     * @param fileName
     */
    public static void readArrFromDisk(String fileName) throws IOException {
        //1.创建源
        File file = new File(fileName);
        //2.选择读取的文件流
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int row=0;
        //3.确定数组的大小
        while (reader.readLine()!=null){
            row++;
        }
        int[][] arr=new int[row][3];
        //关闭流，重新获取
        reader.close();
        reader = new BufferedReader(new FileReader(file));
        //4.逐行读取数据
        String line;
        int row_temp=0;
        while ((line=reader.readLine())!=null){
            String[] split = line.split("\t");
            for (int i = 0; i < split.length; i++) {
                arr[row_temp][i]= Integer.parseInt(split[i]);
            }
            row_temp++;
        }
        //验证读取的数据是否正确
        System.out.println("读取文件中的稀疏数组：");
        for (int[] arr_row : arr) {
            for (int i : arr_row) {
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
        //关闭读取流
        reader.close();

    }
    /**
     * 保存稀疏数组到硬盘
     * @param sparseArray
     */
    public static void saveToDisk(int[][] sparseArray){
        //1.创建文件源
        File file = new File("SparseArray.data");
        //2.选择字符流操作
        FileWriter writer = null;
        try {
            boolean newFile = file.createNewFile();
             writer = new FileWriter(file);
            //3.拷贝数据到文件中
            for (int i = 0; i < sparseArray.length; i++) {
                writer.write(sparseArray[i][0]+"\t"+sparseArray[i][1]+"\t"+sparseArray[i][2]+"\n");
            }
            System.out.println("完成写入");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入出错！");
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 二维数组转为稀疏数组
     * 1.获取二维数组的有效数据的数量
     * 2.创建稀疏数组
     * 3.遍历二维数组将有效数字赋值给稀疏数组
     * @param twoDimensionalArray
     * @return
     */
    public static int[][] toSparseArray(int[][] twoDimensionalArray){
        //遍历二维数组获得有效元素的个数
        int sum=0;
        int row=twoDimensionalArray.length;
        int column=0;
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            int[] arr=twoDimensionalArray[i];
            column=arr.length;
            for (int j = 0; j < arr.length; j++) {
                if (twoDimensionalArray[i][j]!=0){
                  sum++;
                }
            }
        }
        //创建稀疏数组(n行3列)
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0]=row;
        sparseArray[0][1]=column;
        sparseArray[0][2]=sum;
        //遍历二维数组赋值到稀疏数组
        int row_temp=0;
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            int[] arr = twoDimensionalArray[i];
            for (int j = 0; j < arr.length; j++) {
                if (twoDimensionalArray[i][j]!=0){
                    row_temp++;
                    sparseArray[row_temp][0]=i;
                    sparseArray[row_temp][1]=j;
                    sparseArray[row_temp][2]=twoDimensionalArray[i][j];
                }
            }
        }
        return sparseArray;
    }

    /**
     * 稀疏数组转化为二维数组
     *
     * 思路：
     * 1.读取第一行创建二维数组
     * 2.遍历稀疏数组进行赋值
     *
     * 基本数据类型的数组，new就是进行了初始化了，无论是成员变量还是局部变量，默认给元素赋初值
     * 但是局部变量使用前必须赋初值
     * @param sparseArray
     * @return
     */
    public static int[][] toTwoDimensionalArray(int[][] sparseArray){
        int row=sparseArray[0][0];
        int column=sparseArray[0][1];
        int[][] twoDimensionalArray=new int[row][column];
        for (int i = 1; i < sparseArray.length; i++) {
            twoDimensionalArray[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }

        return twoDimensionalArray;
    }
}
