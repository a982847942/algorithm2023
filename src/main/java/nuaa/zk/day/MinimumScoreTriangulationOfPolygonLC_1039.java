package nuaa.zk.day;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/2 9:26
 */
//区间DP  类似最长回文子序列
public class MinimumScoreTriangulationOfPolygonLC_1039 {

    public int minScoreTriangulation(int[] values) {
        int len = values.length;
        int[][] cache = new int[len][len];
        return dfs(0,len - 1,values,cache);
    }

    private int dfs(int start, int end, int[] values,int[][] cache) {
        if (start + 1 == end)return 0;
        if (cache[start][end] != 0)return cache[start][end];
        int min = Integer.MAX_VALUE;
        for (int k = start + 1; k < end; k++) {
            min = Math.min(dfs(start,k,values,cache) + dfs(k,end,values,cache) + values[start] * values[end]*values[k],min);
        }
        return cache[start][end] = min;
    }

    public int minScoreTriangulation2(int[] values) {
        int len = values.length;
        int[][] dp = new int[len][len];
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                int temp = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    temp = Math.min(dp[i][k] + dp[k][j] + values[i]*values[j]*values[k],temp);
                }
                dp[i][j] = temp;
            }
        }
        return dp[0][len - 1];
    }

}
