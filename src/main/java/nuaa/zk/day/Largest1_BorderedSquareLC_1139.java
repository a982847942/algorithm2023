package nuaa.zk.day;

import javafx.util.Pair;
import org.junit.Test;

/**
 * @Classname Largest1_BorderedSquareLC_1139
 * @Description
 * @Date 2023/2/17 9:58
 * @Created by brain
 */
public class Largest1_BorderedSquareLC_1139 {

    //前缀和
    public int largest1BorderedSquare2(int[][] grid) {
        //降维打击 行和列的一维前缀和
        int m = grid.length, n = grid[0].length;
        int maxEdge = Math.min(m, n);
        int[][] rowSum = new int[m][n + 1];
        int[][] colSum = new int[m + 1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i][j + 1] = rowSum[i][j] + grid[i][j];
                colSum[i + 1][j] = colSum[i][j] + grid[i][j];
            }
        }
        int ans = 0;
        //遍历左上角的点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int edge = ans + 1; edge <= maxEdge; edge++) {
                    //如果左边和上边都满足条件，再判断下边和右边是否满足条件，
                    //如果左边或上边不满足条件，切换下一个左上角点，因为继续从该左上角出发，更大的正方形也不能满足条件
                    //如果左边和上边都满足条件，如果下边和右边满足条件，那么ans可以为当前edge，然后试图探寻更大的edge
                    //如果左边和上边都满足条件，如果下边或右边不满足条件，也可以试图探寻更大的edge，知道超出边界
                    //下一个左上角的遍历，就可以以ans+1的edge试图查找了
                    if (i + edge - 1 >= m || j + edge - 1 >= n) {
                        break;
                    }
                    int left = colSum[i + edge][j] - colSum[i][j];
                    int top = rowSum[i][j + edge] - rowSum[i][j];
                    if (left != edge || top != left) {
                        break;
                    }
                    int right = colSum[i + edge][j + edge - 1] - colSum[i][j + edge - 1];
                    int bottom = rowSum[i + edge - 1][j + edge] - rowSum[i + edge - 1][j];
                    if (right == edge && bottom == edge) {
                        ans = edge;
                    }
                }
            }
        }
        return ans * ans;
    }

    //前缀和
    public int largest1BorderedSquare3(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[][] rowPrefixSum = new int[rowLen][colLen + 1];
        int[][] colPrefixSum = new int[colLen][rowLen + 1];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                rowPrefixSum[i][j + 1] = rowPrefixSum[i][j] + grid[i][j];//行前缀和
                colPrefixSum[j][i + 1] = colPrefixSum[j][i] + grid[i][j];//列前缀和
            }
        }

        int maxLen = Math.min(rowLen, colLen);
        for (int d = maxLen; d > 0; d--) {
            for (int i = 0; i <= rowLen - d; i++) {
                for (int j = 0; j <= colLen - d; j++) {
                    if (rowPrefixSum[i][j + d] - rowPrefixSum[i][j] == d &&//上
                            rowPrefixSum[i + d - 1][j + d] - rowPrefixSum[i + d - 1][j] == d &&//下
                            colPrefixSum[j][i + d] - colPrefixSum[j][i] == d &&//左
                            colPrefixSum[j + d - 1][i + d] - colPrefixSum[j + d - 1][i] == d//右
                    ) return d * d;
                }
            }
        }
        return 0;
    }

    //暴力解法
    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int maxLen = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int curLen = maxLen;
                boolean flag1 = true;
                if (grid[i][j] == 1) {
                    while (i + curLen < row && j + curLen < column) {
                        //左、上
                        for (int k = 0; k <= curLen; k++) {
                            if (grid[i + k][j] == 0) {
                                flag1 = false;
                                break;
                            }
                        }
                        if (!flag1) break;
                        for (int k = 0; k <= curLen; k++) {
                            if (grid[i][j + k] == 0) {
                                flag1 = false;
                                break;
                            }
                        }
                        //左或上边界出现0 直接更换起始位置
                        if (!flag1) break;

                        //右、下
                        boolean flag2 = true;
                        for (int k = 0; k <= curLen; k++) {
                            if (grid[i + curLen][j + k] == 0) {
                                curLen += 1;
                                flag2 = false;
                                break;
                            }
                        }
                        if (!flag2) continue;
                        for (int k = 0; k <= curLen; k++) {
                            if (grid[i + k][j + curLen] == 0) {
                                curLen += 1;
                                flag2 = false;
                                break;
                            }
                        }
                        if (!flag2) continue;
                        curLen += 1;
                        maxLen = curLen;
                    }
                }

            }
        }
        return maxLen * maxLen;
    }

    //动态规划
    public int largest1BorderedSquare4(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        Pair<Integer, Integer>[][] dp = new Pair[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = new Pair<>(0, 0);
            }
        }

        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 0) {
                    dp[i][j] = new Pair<>(0, 0);
                } else {
                    dp[i][j] = new Pair<>(dp[i][j - 1].getKey() + 1, dp[i - 1][j].getValue() + 1);
                }
            }
        }

        for (int i = m; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                if (dp[i][j].getKey() != 0 && dp[i][j].getValue() != 0) {
                    int tmp = Math.min(dp[i][j].getKey(), dp[i][j].getValue());
                    for (int len = tmp; len > 0; len--) {
                        int x = i - len + 1, y = j - len + 1;
                        if (dp[x][j].getKey() >= len && dp[i][y].getValue() >= len) ans = Math.max(ans, len * len);
                    }
                }
            }
        }
        return ans;
    }

    //动态规划
    public int largest1BorderedSquare1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //dp[i][j][0]: (i,j)横向连续1的个数
        //dp[i][j][1]: (i,j)竖向连续1的个数
        int[][][] dp = new int[m + 1][n + 1][2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果当前位置是0，就跳过
                if (grid[i - 1][j - 1] == 0)
                    continue;
                //如果是1，我们就计算横向和竖向连续1的个数
                dp[i][j][0] = dp[i][j - 1][0] + 1;
                dp[i][j][1] = dp[i - 1][j][1] + 1;
            }
        }
        int maxSide = 0;//记录正方形的最大长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //沿着当前坐标往上和往左找出最短的距离，暂时看做是正方形的边长(正方形的具体边长
                //还要看上边和左边的长度，所以这里要判断一下)
                int curSide = Math.min(dp[i][j][0], dp[i][j][1]);
                //如果边长小于maxSide，即使找到了也不可能再比maxSide大，所以我们没必要再找，直接跳过，
                if (curSide <= maxSide)
                    continue;
                //curSide可以认为是正方形下边和右边的长度，我们还需要根据正方形上边和左边的长度
                //来确认是否满足正方形的条件
                for (; curSide > maxSide; curSide--) {
                    //判断正方形的左边和上边的长度是否大于curSide，如果不大于，我们就缩小正方形
                    //的长度curSide，然后继续判断
                    if (dp[i][j - curSide + 1][1] >= curSide && dp[i - curSide + 1][j][0] >= curSide) {
                        maxSide = curSide;
                        //更短的就没必要考虑了，这里直接中断
                        break;
                    }
                }
            }
        }
        //返回正方形的边长
        return maxSide * maxSide;
    }


    @Test
    public void test() {
//        int[][] grid = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] grid = {{1, 1}, {0, 0}};
        System.out.println(largest1BorderedSquare(grid));
        System.out.println(largest1BorderedSquare3(grid));
    }
}
