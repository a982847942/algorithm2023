package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/29 10:28
 */
public class CountSortedVowelStringsLC_1641 {
//    char[] elements = {'a', 'e', 'i', 'o', 'u'};
    int[][] cache;

    public int countVowelStrings(int n) {
        cache = new int[n][5];
        return dfs(0, 0, n);
    }

    private int dfs(int index, int curLen, int len) {
        if (curLen == len) {
            return 1;
        }
        if (cache[curLen][index] != 0) return cache[curLen][index];
        int ans = 0;
        for (int i = index; i < 5; i++) {
            ans += dfs(i, curLen + 1, len);
        }
        return cache[curLen][index] = ans;
//        return ans;
    }

    public int countVowelStrings2(int n) {
        int[][] dp = new int[n + 1][5];
        for (int i = 4; i >= 0; i--) {
            dp[n][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 4; j >=  0; j--) {
                for (int k = j; k < 5 ; k++) {
                    dp[i][j] += dp[i + 1][k];
                }
            }
        }
        return dp[0][0];
    }

    public int countVowelStrings3(int n) {
        int[] f = {1, 1, 1, 1, 1};
        for (int i = 0; i < n - 1; ++i) {
            int s = 0;
            for (int j = 0; j < 5; ++j) {
                s += f[j];
                f[j] = s;
            }
        }
        return Arrays.stream(f).sum();
    }

    //组合数
    public int countVowelStrings4(int n) {
        return (n + 4) * (n + 3) * (n + 2) * (n + 1) / 24;
    }


    @Test
    public void test() {
        System.out.println(countVowelStrings2(1));
    }
}
