package top.javahai.recursion;


/**
 * 迷宫问题，递归实现
 * @author Hai
 * @date 2020/10/5 - 18:31
 */
public class Maze {
    public static void main(String[] args) {
        int[][] maze = new int[8][7];
        for (int i = 0; i <= 6; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 1; i <= 6; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[4][3] = 1;
//        Maze[1][2]=1;
//        Maze[2][2]=1;

        maze[3][4]=1;


        System.out.println("迷宫图：");
        print(maze);
        //寻找出路
        System.out.println("出迷宫的路线：");
//        findWayOut(Maze,1,1);
        findWayOut2(maze,1,1);
        print(maze);



    }

    public static void print(int[][] maze) {
        for (int[] row : maze) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * 规则：
     * 迷宫数组中，0-没有走过，可以走，1-墙，不能走，2-走过了，可以走；3-走过了，走不通不可以走。
     * 寻找策略： 下->右->上->左
     * 到达了maze[6][5]，就表达找到了出路
     *
     * @param maze 代表迷宫的数组
     * @param i    i和j表示出发的坐标
     * @param j
     * @return true表示找的到出路，false表示找不到出路
     */
    public static boolean findWayOut(int[][] maze, int i, int j) {
        if (maze[6][5] == 2) {
            return true;
        } else {
            //表示该点走得通
            if (maze[i][j] == 0) {
                maze[i][j] = 2;
                if (findWayOut(maze, i + 1, j)) {
                    return true;
                } else if (findWayOut(maze, i, j + 1)) {
                    return true;
                } else if (findWayOut(maze, i - 1, j)) {
                    return true;
                } else if (findWayOut(maze, i, j - 1)) {
                    return true;
                } else {
                    maze[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 更改寻找策略为：上->右->下->左
     * @param maze
     * @param i
     * @param j
     * @return
     */
    public static boolean findWayOut2(int[][] maze, int i, int j) {
        //如果到达maze[6][5]就表示找到出路，return true结束递归
        if (maze[6][5]==2){
            return true;
        }
        //只有坐标值为0才能继续走下去
        if (maze[i][j]==0){
            maze[i][j]=2;
            //每到达一个新的位置，就按寻找策略为：上->右->下->左来走，直到无路可走，退出这条路
            if (findWayOut2(maze,i-1,j)){
                return true;
            }else if (findWayOut2(maze,i,j+1)){
                return true;
            }else if (findWayOut2(maze,i+1,j)){
                return true;
            }else if (findWayOut2(maze,i,j-1)){
                return true;
            }else{
                //走不通，就设置为3
                maze[i][j]=3;
                return false;
            }
        }else{
            return false;
        }

    }
}
