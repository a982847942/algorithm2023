package nuaa.zk.dp.knapsack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/10 21:30
 */
public class K08 {
    public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
        int[][] dp = new int[N + 1][C + 1];
        for (int i = 1; i <= N; i++) {
            int temp = v[i - 1];
            int count = s[i - 1];
            for (int j = 0; j <= C; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; j - k * temp >= 0 && k <= count; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * temp] + k * w[i - 1]);
                }
            }
        }
        return dp[N][C];
    }

    public int maxValue2(int N, int C, int[] s, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 0; i < N; i++) {
            int temp = v[i];
            int count = s[i];
            for (int j = C; j >= temp; j--) {
                /**
                 * 不能降低复杂度 原因在于不能像完全背包一样无限使用某一物品。
                 * 可能出现这种情况：dp[j - temp] 使用了s[i]件物品 此时dp[j-temp] + w[i]就超过限制了。
                 * 问题在于我们不知道每一个状态使用的物品的具体数目
                 * 解决问题的量级在10^2
                 */
                for (int k = 0; j - k * temp >= 0 && k <= count; k++) {
                    dp[j] = Math.max(dp[j], dp[j - temp] + k * w[i]);
                }
            }
        }
        return dp[C];
    }

    //践行flatten操作展开，将多重背包转换为0 1背包，反之也可以将0 1背包转换为多重背包
    public int maxValue3(int N, int C, int[] s, int[] v, int[] w) {
        // 将多件数量的同一物品进行「扁平化」展开，以 [v, w] 形式存储
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int cnt = s[i];
            while (cnt-- > 0) {
                arr.add(new int[]{v[i], w[i]});
            }
        }

        // 使用「01 背包」进行求解
        int[] dp = new int[C + 1];
        for (int i = 0; i < arr.size(); i++) {
            int vi = arr.get(i)[0], wi = arr.get(i)[1];
            for (int j = C; j >= vi; j--) {
                dp[j] = Math.max(dp[j], dp[j - vi] + wi);
            }
        }
        return dp[C];
    }

    /**
     * 二进制优化:
     * 所谓的「二进制优化」其实是指，我们如何将一个多重背包问题彻底转化为 0-1 背包问题，同时降低其复杂度。
     * 解决问题的量级在10^3
     * 二进制优化的本质，是对「物品」做分类，使得总数量为 m 的物品能够用更小的 logm 个数所组合表示出来。
     */
    public int maxValue4(int N, int C, int[] s, int[] v, int[] w) {
        List<Integer> volume = new ArrayList<>();
        List<Integer> worth = new ArrayList<>();
        //扁平化7--> 1 2 4   1+2 = 3 2+4 =6 1+4 =5 1+2+4 = 7
        for (int i = 0; i < N; i++) {
            int count = s[i];
            for (int k = 1; k <= count; k *= 2) {
                worth.add(k * w[i]);
                volume.add(k * v[i]);
                count -= k;
            }
            if (count > 0) {
                worth.add(count * w[i]);
                volume.add(count * v[i]);
            }
        }
        int[] dp = new int[C + 1];
        int len = worth.size();
        for (int i = 0; i < len; i++) {
            Integer temp = volume.get(i);
            for (int j = C; j >= temp; j--) {
                dp[j] = Math.max(dp[j], dp[j - temp] + worth.get(i));
            }
        }
        return dp[C];
    }

    /**
     * 单调队列优化：
     * 单调队列优化，某种程度上也是利用「分类」实现优化。只不过不再是针对「物品」做分类，而是针对「状态」做分类。
     * 状态取余 窗口最值 单调队列 leetcode 30 1787也是类似题目
     */
    public int maxValue5(int N, int C, int[] s, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        int[] g = new int[C + 1]; // 辅助队列，记录的是上一次的结果
        int[] q = new int[C + 1]; // 主队列，记录的是本次的结果 是一个单调递减队列

        // 枚举物品
        for (int i = 0; i < N; i++) {
            int vi = v[i];
            int wi = w[i];
            int si = s[i];

            // 将上次算的结果存入辅助数组中
            g = dp.clone();

            // 枚举余数
            for (int j = 0; j < vi; j++) {
                // 初始化队列，head 和 tail 分别指向队列头部和尾部
                int head = 0, tail = -1;
                // 枚举同一余数情况下，有多少种方案。
                // 例如余数为 1 的情况下有：1、vi + 1、2 * vi + 1、3 * vi + 1 ...
                for (int k = j; k <= C; k += vi) {
                    dp[k] = g[k];
                    // 将不在窗口范围内的值弹出
                    if (head <= tail && q[head] < k - si * vi) head++;
                    // 如果队列中存在元素，直接使用队头来更新 dp[k]表示不选任何一件  选小于等于si件的最优值在队列q的头部 直接计算即可
                    if (head <= tail) dp[k] = Math.max(dp[k], g[q[head]] + (k - q[head]) / vi * wi);
                    /**
                     * q[tail] 队伍元素(之前遍历过程中的k)  k当前的空间大小
                     *  当前值比对尾值更优，队尾元素没有存在必要，队尾出队 g[q[tail]] 队尾空间大小下的最优值  g[k]当前空间大小下的最优值
                     *  为什么是 <=  因为相等时如果g[q[tail]]可以满足则g[k]一定可以满足，而有可能后面g[q[tail]]不满足窗口值需要弹出，但是g[k]
                     *  却满足，因此需要保留g[k]
                     */

//                    while (head <= tail && g[q[tail]] - (q[tail] - j) / vi * wi <= g[k] - (k - j) / vi * wi) tail--;
                    while (head <= tail && g[k] >= g[q[tail]] + (k - q[tail]) / vi * wi) tail--;//比上面写法更优雅一些
                    // 将新下标入队
                    q[++tail] = k;
                }
            }
        }
        return dp[C];
    }

    @Test
    public void test() {
        int N = 2, C = 5;
        int[] v = {1, 2}, w = {1, 2}, s = {2, 1};
        System.out.println(maxValue(N, C, s, v, w));
        System.out.println(maxValue2(N, C, s, v, w));
        System.out.println(maxValue4(N, C, s, v, w));
        System.out.println(maxValue5(N, C, s, v, w));
    }


}
