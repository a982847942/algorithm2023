package nuaa.zk.dp;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/22 10:12
 */
public class BestTeamWithNoConflictsLC_1626 {
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        Integer[] indexArr = new Integer[len];
        for (int i = 0; i < len; i++) {
            indexArr[i] = i;
        }
        Arrays.sort(indexArr,(a,b)->{
            return scores[a] != scores[b] ? scores[a] - scores[b] : ages[a] - ages[b];
        });
        int[] dp = new int[len];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (ages[indexArr[j]] <= ages[indexArr[i]]){
                    dp[i] = Math.max(dp[i],dp[j]);
                }
            }
            dp[i] += scores[indexArr[i]];
            ans = Math.max(dp[i],ans);
        }
        return ans;
    }

    //基于值域的做法
    public int bestTeamScore2(int[] scores, int[] ages) {
        int n = scores.length, u = 0, ans = 0;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; ++i) {
            ids[i] = i;
            u = Math.max(u, ages[i]);
        }
        Arrays.sort(ids, (i, j) -> scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]);

        int[] maxSum = new int[u + 1];
        for (int i : ids) {
            int age = ages[i];
            for (int j = 1; j <= age; ++j)
                maxSum[age] = Math.max(maxSum[age], maxSum[j]);
            maxSum[age] += scores[i];
            ans = Math.max(ans, maxSum[age]);
        }
        return ans;
    }

    //树状数组
    public int bestTeamScore3(int[] scores, int[] ages) {
        int n = scores.length;
       Integer[] ids = new Integer[n];
        for (int i = 0; i < n; ++i)
            ids[i] = i;
        Arrays.sort(ids, (i, j) -> scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]);

        for (int i : ids)
            update(ages[i], query(ages[i]) + scores[i]);
        return query(MX);
    }

    private static final int MX = 1000;
    private final int[] t = new int[MX + 1];

    // 返回 max(maxSum[:i+1])
    private int query(int i) {
        int mx = 0;
        for (; i > 0; i &= i - 1)
            mx = Math.max(mx, t[i]);
        return mx;
    }

    // 更新 maxSum[i] 为 mx
    private void update(int i, int mx) {
        for (; i <= MX; i += i & -i)
            t[i] = Math.max(t[i], mx);
    }

}
