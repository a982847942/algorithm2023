package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Classname MinimumNumberToWaterGardenLC_1326
 * @Description
 * @Date 2023/2/21 9:24
 * @Created by brain
 */
public class MinimumNumberToWaterGardenLC_1326 {

    public int getMin(int n, int[] ranges, boolean[] use) {
        //0-range[0] 0+range[0]


        int p2 = process(0, 0, n, ranges, 1, 0, use);//不用0
        use[0] = true;
        int p1 = process(0 - ranges[0] - 1, 0 + ranges[0] + 1, n, ranges, 1, 1, use);//用0
        int res = Math.min(p1, p2);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process(int leftBorder, int rightBorder, int n, int[] ranges, int index, int useTotal, boolean[] use) {

        if (leftBorder < 0 && rightBorder > n) {
            System.out.println(Arrays.toString(use));
            return useTotal;
        }
        if (index == n + 1) {
            if (leftBorder < 0 && rightBorder > n) {
                System.out.println(Arrays.toString(use));
                return useTotal;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        int p1 = process(leftBorder, rightBorder, n, ranges, index + 1, useTotal, use);//不用
        leftBorder = Math.min(index - ranges[index] - 1, leftBorder);
        rightBorder = Math.max(index + ranges[index] + 1, rightBorder);
        use[index] = true;
        int p2 = process(leftBorder, rightBorder, n, ranges, index + 1, useTotal + 1, use);//用
        return Math.min(p1, p2);
    }

    public int minTaps2(int n, int[] ranges) {
        boolean[] use = new boolean[n + 1];

        return getMin(n, ranges, use);
    }

    public int minTaps(int n, int[] ranges) {
        int[] rightMost = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            int r = ranges[i];
            // 这样写可以在 i>r 时少写一个 max
            // 凭借这个优化，恭喜你超越了 100% 的用户
            // 说「超越」是因为原来的最快是 2ms，现在优化后是 1ms
            if (i > r) rightMost[i - r] = i + r; // 对于 i-r 来说，i+r 必然是它目前的最大值
            else rightMost[0] = Math.max(rightMost[0], i + r);
        }

        int ans = 0;
        int curRight = 0; // 已建造的桥的右端点
        int nextRight = 0; // 下一座桥的右端点的最大值
        for (int i = 0; i < n; ++i) { // 注意这里没有遍历到 n，因为它已经是终点了
            nextRight = Math.max(nextRight, rightMost[i]);
            if (i == curRight) { // 到达已建造的桥的右端点
                if (i == nextRight) return -1; // 无论怎么造桥，都无法从 i 到 i+1
                curRight = nextRight; // 造一座桥
                ++ans;
            }
        }
        return ans;
    }

    public int minTaps3(int n, int[] ranges) {
        int[] land = new int[n];
        for (int i = 0; i < n + 1; i++) {
            int left = Math.max(i - ranges[i], 0);
            int right = Math.min(n, i + ranges[i]);
            for (int j = left; j < right; j++) {
                land[j] = Math.max(right, land[j]);
            }
        }

        int total = 0;
        int cur = 0;
        while (cur < n) {
            if (land[cur] == 0) return -1;
            cur = land[cur];
            total++;

        }
        return total;
    }

    // TODO: 2023/2/22   弄清楚相关路径覆盖问题，以及本题的动态规划解法
    @Test
    public void test() {
        int n = 7;
        int[] range = {1, 2, 1, 0, 2, 1, 0, 1};
    }
}
