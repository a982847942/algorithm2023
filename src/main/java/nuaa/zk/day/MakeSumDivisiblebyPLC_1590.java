package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/10 9:00
 */
public class MakeSumDivisiblebyPLC_1590 {
//    public int minSubarray(int[] nums, int p) {
//        long sum = 0;
//        for (int num : nums) {
//            sum += num;
//        }
//        if (sum < p) return -1;
//        if (sum % p == 0) return 0;
//        long sub = sum % p;
//        //找数组中结果为sub的最短子数组
//        int res = minSub(sub, nums,p);
//        return res;
//    }

    /**
     * 一次失败的记录，改题为什么不能用双指针！！！！
     */
//    private int minSub(long sub, int[] nums,int p) {
//        int len = nums.length;
//        int index = 0;
//        long sum = 0;
//        int left = 0;
//        int min = Integer.MAX_VALUE;
//        while (index < len) {
//            sum += nums[index];
//            long temp = sum % p;
//            if (sum > sub) {
//                while (left <= index && sum > sub) {
//                    sum -= nums[left++];
//                }
//                if (sum == sub){
//                    min = Math.min(index - left + 1, min);
//                    sum -= nums[left++];
//                }
//            } else if (sum == sub) {
//                min = Math.min(index - left + 1, min);
//                sum -= nums[left++];
//            }
//
//            index++;
//        }
//        return min == Integer.MAX_VALUE ? -1 : min;
//    }

    /**
     * 同余  (x - y) mod p = 0 --> x mod p  == y mod p
     * 如果一正一负  则  x mod p + p == y mod p
     * -17 % 10 + 10 == 3 % 10
     * 为了避免负数判断 可以 （x % p + p） % p
     * (s[right]modp−xmodp+p)modp=s[left]modp
     */
    public int minSubarray(int[] nums, int p) {
        int len = nums.length;
        long[] prefixSum = new long[len + 1];
        for (int i = 1; i < len + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < p) return -1;
        if (sum % p == 0) return 0;
        long mod = sum % p;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            for (int j = i; j < len + 1; j++) {
                if ((prefixSum[j] - prefixSum[j - i]) % p == mod) {
                    min = i;
                    break;
                }
            }
            if (min != Integer.MAX_VALUE) break;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 前缀和 hash表 优化
     *
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray2(int[] nums, int p) {
        int n = nums.length, ans = n;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i)
            s[i + 1] = (s[i] + nums[i]) % p;
        int x = s[n];
        if (x == 0) return 0; // 移除空子数组（这行可以不要）

        Map<Integer, Integer> last = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; ++i) {
            last.put(s[i], i);
            // 如果不存在，-n 可以保证 i-j >= n
            int j = last.getOrDefault((s[i] - x + p) % p, -n);
            ans = Math.min(ans, i - j);
        }
        return ans < n ? ans : -1;
    }

    //不使用前缀和也可以
    public int minSubarray3(int[] nums, int p) {
        long t = 0;
        for (int v : nums) t += v;
        int x = (int) (t % p);
        if (x == 0) return 0; // 移除空子数组（这行可以不要）

        int n = nums.length, ans = n, s = 0;
        Map<Integer, Integer> last = new HashMap<Integer, Integer>(); // 注：填入 n+1 可以加速
        last.put(s, -1); // 由于下面 i 是从 0 开始的，前缀和下标就要从 -1 开始了
        for (int i = 0; i < n; ++i) {
            s = (s + nums[i]) % p;
            last.put(s, i);
            // 如果不存在，-n 可以保证 i-j >= n
            int j = last.getOrDefault((s - x + p) % p, -n);
            ans = Math.min(ans, i - j);
        }
        return ans < n ? ans : -1;
    }

    // TODO: 2023/3/10 多复习这道题，技巧很多 很实用 注意看看灵神题解的推荐题目
    @Test
    public void test() {
        int[] nums = {26, 19, 11, 14, 18, 4, 7, 1, 30, 23, 19, 8, 10, 6, 26, 3};
        int p = 26;
        int sum = 0;
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            temp[i] = nums[i] % 26;
        }
        System.out.println(sum);
        System.out.println(sum % 26);
        System.out.println(Arrays.toString(temp));
        System.out.println(minSubarray(nums, p));
    }

}
