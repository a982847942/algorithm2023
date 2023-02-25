package nuaa.zk.day;

import org.junit.Test;

/**
 * @Classname Largest1_Square
 * @Description https://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247490443&idx=1&sn=fc1b5e2f52083366fa73c0b9b47c6d31&chksm=fb4188abcc3601bda60a042d2908d50f00b362cd14631b30bf4bff0e277e3187134a666ddd2c&scene=21#wechat_redirect
 * @Date 2023/2/25 22:17
 * @Created by brain
 */
public class Largest1_Square {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;

        int[][] dp = new int[row][column];
        for (int i = 0; i < column; i++) {
            dp[0][i] = (matrix[0][i] == '1') ? 1 : 0;
        }
        for (int i = 0; i < row; i++) {
            dp[i][0] = (matrix[i][0] == '1') ? 1 : 0;
        }

        int maxSide = 0;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }

            }
        }
        return maxSide * maxSide;
    }

    public int maximalSquare2(char[][] matrix) {
        //二维矩阵的宽和高
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] dp = new int[height + 1][width + 1];
        int maxSide = 0;//最大正方形的宽
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    //递推公式
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                    //记录最大的边长
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        //返回正方形的面积
        return maxSide * maxSide;
    }

    @Test
    public void test() {
        char[][] matrix = {{'0', '1', '1'}, {'1', '1', '0'}, {'1', '1', '1'}};
        System.out.println(maximalSquare(matrix));
        System.out.println(maximalSquare2(matrix));
    }
}
