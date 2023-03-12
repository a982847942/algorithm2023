package nuaa.zk.subarray;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/12 9:52
 */
public class CountNumberOfNiceSubarraysLC_1248 {
    //前缀和 + hash表
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            if ((nums[i] & 1) == 0) {
                temp[i] = 0;
            } else {
                temp[i] = 1;
            }
            prefixSum[i + 1] = prefixSum[i] + temp[i];
        }
        Map<Integer, Integer> cache = new HashMap<>();
        int res = 0;
        //这里代表前面全为0，自身也可以作为一个子数组
        cache.put(0,1);
        for (int i = 1; i <= len; i++) {
            int cur = prefixSum[i];
            if (cache.containsKey(cur - k)) {
               res += cache.get(cur - k);
            }
            cache.put(cur, cache.getOrDefault(cur, 0) + 1);
        }
        return res;
    }


    //双指针
    public int numberOfSubarrays2(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            // 右指针先走，每遇到一个奇数则 oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }

            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }

        }

        return res;
    }

    //也是前缀和 + hash表
    public int numberOfSubarrays3(int[] nums, int k) {
        // 数组 prefixCnt 的下标是前缀和（即当前奇数的个数），值是前缀和的个数。
        int[] prefixCnt = new int[nums.length + 1];
        prefixCnt[0] = 1;
        // 遍历原数组，计算当前的前缀和，统计到 prefixCnt 数组中，
        // 并且在 res 中累加上与当前前缀和差值为 k 的前缀和的个数。
        int res = 0, sum = 0;
        for (int num: nums) {
            sum += num & 1;
            prefixCnt[sum]++;
            if (sum >= k) {
                res += prefixCnt[sum - k];
            }
        }
        return res;
    }


    @Test
    public void test() {
        int[] nums = {2,2,2,1,2,2,1,2,2,2};
        int k = 2;
        System.out.println(numberOfSubarrays2(nums, k));
    }
}
