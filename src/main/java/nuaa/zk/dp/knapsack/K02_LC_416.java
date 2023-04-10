package nuaa.zk.dp.knapsack;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/10 9:52
 */
public class K02_LC_416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum / 2 * 2 != sum) return false;
        int len = nums.length;
        int c = sum / 2;
        int[][] dp = new int[len + 1][c + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= c; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }
        return dp[len][c] == c;
    }

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum / 2 * 2 != sum) return false;
        int len = nums.length;
        int c = sum / 2;
        int[] dp = new int[c + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = c; j >= nums[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i - 1]] + nums[i - 1]);
            }
        }
        return dp[c] == c;
    }

    //修改dp定义 考虑前i件物品，选择的物品价值之和恰好为j
    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum / 2 * 2 != sum) return false;
        int len = nums.length;
        int c = sum / 2;
        boolean[][] dp = new boolean[len + 1][c + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= c; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] |= dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][c];
    }

    public boolean canPartition4(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum / 2 * 2 != sum) return false;
        int len = nums.length;
        int c = sum / 2;
        boolean[] dp = new boolean[c + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = c; j >= nums[i - 1]; j--) {
                dp[j] |= dp[j - nums[i - 1]];
            }
        }
        return dp[c];
    }

    @Test
    public void test() {
        System.out.println(canPartition4(new int[]{1, 5, 11, 5}));
    }
}
