package nuaa.bishi.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/8 19:27
 * 有一片 n × m 大小的网格，共 n 行 m 列，其中行和列都用从 1 开始的整数编号，网格中有 k 个格子中埋有地雷。我们记第 a 行第 b 列的
 * 格子为 (a, b)。小美现在位于 (x1, y1)，她想要移动到 (x2, y2) 处。小美每次移动可以移动到与她所处格子的相邻的一格中，形式化地说，
 * 如果小美位于 (x, y)，则小美可以移动到 (x-1, y), (x+1, y), (x, y-1), (x, y+1) 的格子之一，但小美不能移动到网格之外。
 * 小美想要在移动过程中，离这些地雷越远越好，而不是走最短路径。这里定义两个格子之间的距离为曼哈顿距离，
 * 即格子 (a, b) 和 (c, d) 之间的距离是 |a-c|+|b-d|。小美想知道，移动中与地雷之间距离的最小值最大可能是多少。
 * 请注意，如果无论小美如何移动，都会进入一个有地雷的格子的话，这个最大可能值为 0。
 *
 * 第一行三个整数 n, m, k，分别表示网格的行数，列数和地雷个数。
 * 接下来 k 行，每行两个整数 p, q，表示一个地雷放置在格子 (p, q) 中。任意两地雷的放置位置不同。
 * 接下来一行四个整数 x1, y1, x2, y2，表示小美的出发位置和目的位置。保证小美的出发位置和目的位置上没有地雷。
 * 对于全部数据，1 ≤ n, m ≤ 500, n × m ≥ 3, 1 ≤ k ≤ min{n × m-2, 400},
 * 1 ≤ p, x1, x2 ≤ n, 1 ≤ q, y1, y2 ≤ m, (x1, y1) ≠ (x2, y2)，
 * 保证 (x1, y1) 和 (x2, y2) 中没有地雷，并且一个格子中最多放置一个地雷。
 *
 * 输出一行一个整数，表示移动过程中与地雷之间距离的最小值的可能最大值。
 *
 * 5 6 2
 * 2 1
 * 2 3 1 1 5 1
 *
 * 1
 */
public class M2023_4_8_4 {
    static int[][] arr, mine;
    static boolean[][] visited;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] cache;
    static int row, column;
    static int startX = 0, startY = 0, endX = 0, endY = 0;
    static int ans = Integer.MAX_VALUE;

    public static int getMaxDistance() {
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        column = sc.nextInt();
        int mineNum = sc.nextInt();
        arr = new int[row + 1][column + 1];
        mine = new int[mineNum][2];
        visited = new boolean[row + 1][column + 1];
        cache = new int[row + 1][column + 1];
        for (int i = 0; i < mineNum; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[x][y] = 1;
            mine[i][0] = x;
            mine[i][1] = y;
        }
        startX = sc.nextInt();
        startY = sc.nextInt();
        endX = sc.nextInt();
        endY = sc.nextInt();
        visited[startX][startY] = true;
        for (int i = 0; i <= row; i++) {
            Arrays.fill(cache[i],-1);
        }
        //求所有可能方案中每一次距离雷区的最小距离中的最大值
        ans = Math.min(dfs(startX, startY),ans);
        if (ans == Integer.MAX_VALUE)return 0;
        for (int[] m : mine) {
            ans = Math.min(getDistance(startX,startY,m[0],m[1]),ans);
        }
        return ans;
    }

    private static int dfs(int curX, int curY) {
        int temp = Integer.MAX_VALUE;
        if (cache[curX][curY] != - 1)return cache[curX][curY];
        if (curX == endX && curY == endY) {
            for (int[] m : mine) {
                temp = Math.min(getDistance(curX,curY,m[0],m[1]),temp);
            }
            return cache[curX][curY] = temp;
//            return temp;
        }
        for (int[] direction : direction) {
            int nextX = curX + direction[0];
            int nextY = curY + direction[1];
            if (nextX != 0 && nextY != 0 && nextX <= row && nextY <= column && !visited[nextX][nextY] && arr[nextX][nextY] != 1) {
                visited[nextX][nextY] = true;

                temp = Math.min(dfs(nextX, nextY),temp);
                visited[nextX][nextY] = false;
                if (temp == Integer.MAX_VALUE)continue;
                for (int[] m : mine) {
                    temp = Math.min(getDistance(nextX,nextY,m[0],m[1]),temp);
                }
            }
        }
        return cache[curX][curY] = temp;
//        return temp;
    }

    private static int getDistance(int a, int b, int c, int d) {
        return Math.abs(a-c) + Math.abs(b-d);
    }

    public static void main(String[] args) {
        System.out.println(getMaxDistance());
    }
}
