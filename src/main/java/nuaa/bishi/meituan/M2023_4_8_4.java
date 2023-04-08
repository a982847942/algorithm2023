package nuaa.bishi.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/8 19:27
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
