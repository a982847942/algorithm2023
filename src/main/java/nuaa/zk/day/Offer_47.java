package nuaa.zk.day;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/8 9:40
 */
public class Offer_47 {
    public int maxValue(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[] dp = new int[column + 1];
        for (int i = 1; i <= row ; i++) {
            for (int j = 1; j <= column; j++) {
                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
            }
        }

        return dp[column];
    }

    public int maxValue2(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int[][] cache = new int[row][column];
        return dfs(row,column,0,0,cache,grid);
    }

    private int dfs(int row, int column, int curRow, int curColumn, int[][] cache, int[][] grid) {
        if (curRow >= row || curColumn >= column) return 0;
        if (cache[curRow][curColumn] != 0)return cache[curRow][curColumn];
        if (curRow == row - 1 && curColumn == column - 1){
            return grid[curRow][curColumn];
        }
        int temp1 = dfs(row,column,curRow ,curColumn + 1,cache,grid);
        int temp2 = dfs(row,column,curRow + 1,curColumn,cache,grid);
        int res = Math.max(temp1, temp2) + grid[curRow][curColumn];
        cache[curRow][curColumn] = res;
        return res;
    }

    @Test
    public void test() {
        int[][] grid = {{1,2,5},{3,2,1}};
        System.out.println(maxValue2(grid));
    }
}
