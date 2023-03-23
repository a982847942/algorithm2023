package nuaa.zk.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/22 9:32
 */
public class LongestIncreasingSubsequenceLC_300 {
    private int solution1(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] cache = new int[len];
        Arrays.fill(cache, -1);
        return process(nums, Integer.MIN_VALUE, len, 0, cache);
    }

    /**
     * 此方法使用记忆化搜索后 结果不对，因为cache[index] 含义代表index开始的子数组的最大递增子序列，因此pre需要是
     * index + 1开始的后续子序列的最大值，该怎么样传递？
     */
    private int process(int[] nums, int pre, int len, int index, int[] cache) {
        if (index == len) return 0;
        if (cache[index] != -1) return cache[index];
        //不选
        int p1 = process(nums, pre, len, index + 1, cache);
        //选
        int p2 = 0;
        if (nums[index] > pre) {
            p2 = process(nums, nums[index], len, index + 1, cache) + 1;
        }
        cache[index] = Math.max(p1, p2);
        return cache[index];
    }


    /**
     * 这里使用cache[index]表示以index结尾的子数组的最大递增子序列
     * @return
     */
    private int[] nums;
    private int[] cache;
    private int solution2(int[] _nums){
        nums = _nums;
        int len = nums.length;
        cache  = new int[len];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(dfs(i),ans);
        }
        return ans;
    }

    private int dfs(int index) {
        if (cache[index] > 0)return cache[index];
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]){
                cache[index] = Math.max(dfs(i),cache[index]);
            }
        }
        //加上自身
        return ++cache[index];
    }

    //接方法2 改为dp
    public int dp2(int[] nums){
        int len = nums.length;
        int[] dp = new int[len];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]);
                }
            }
            ans = Math.max(++dp[i],ans);
        }
        return ans;
    }

    //二分法！！！！！
    public int lengthOfLIS3(int[] nums) {
        List<Integer> g = new ArrayList<>();
        for (int x : nums) {
            int j = lowerBound(g, x);
            if (j == g.size()) g.add(x); // >=x 的 g[j] 不存在
            else g.set(j, x);
        }
        return g.size();
    }

    // 开区间写法
    private int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = (left + right) >>> 1;
            if (g.get(mid) < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right; // 或者 left+1
    }
    //原地二分
    public int lengthOfLIS4(int[] nums) {
        int ng = 0; // g 的长度
        for (int x : nums) {
            int j = lowerBound(nums, ng, x);
            nums[j] = x;
            if (j == ng) // >=x 的 g[j] 不存在
                ++ng;
        }
        return ng;
    }

    // 开区间写法
    private int lowerBound(int[] nums, int right, int target) {
        int left = -1; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = (left + right) >>> 1;
            if (nums[mid] < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right; // 或者 left+1
    }

    public int lengthOfLIS(int[] nums) {
        return dp2(nums);
    }

    @Test
    public void test() {
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS(nums));
    }


}
