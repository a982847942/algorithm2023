package nuaa.zk.binarysearch.medium;

import org.junit.Test;

import java.util.*;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/20 11:26
 */
public class FrequencyOfTheMostFrequentElementLC_1838 {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        //如果存在可行解，则可以表示为[l,r]区间内的数全部相等,前缀和判断sum[r] - sum[l] 是否小于等于nums[right] * (r - l + 1) - k即可
        int len = nums.length;
        long[] prefixSum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int left = 0;
        int res = 1;
        for (int right = 1; right < len; right++) {
            long temp = prefixSum[right + 1] - prefixSum[left];
            int curLen = (right - left + 1);
            //缩小左端点
            while (temp + k < nums[right] * curLen) {
                /**
                 * 为什么可以直接缩小left
                 * 1.left...right 已经无法满足 则 left ...right + N 自然无法满足
                 * 2.left + K ...right 即使满足也不会比left ...right更大(for循环每次都是right++)
                 * nums[right] * curLen 因为本题只能加1 不能减  因此该区间必须全部等于区间最大值
                 */
                left++;
                temp = prefixSum[right + 1] - prefixSum[left];
                curLen = (right - left + 1);
            }
            res = Math.max(res, curLen);
        }
        return res;
    }

    public int maxFrequency2(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        //频率统计
        for (int i : nums) map.put(i, map.getOrDefault(i, 0) + 1);
        List<Integer> list = new ArrayList<>(map.keySet());
        //排序
        Collections.sort(list);
        int ans = 1;
        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i), cnt = map.get(x);
            //如果存在多个数相等 cnt可以直接代替ans
            if (i > 0) {
                int p = k;
                for (int j = i - 1; j >= 0; j--) {
                    int y = list.get(j);
                    //求最近的两个数的差值
                    int diff = x - y;
                    if (p >= diff) {
                        //能补全几个y值
                        int add = p / diff;
                        //取数组中实际存在的y，和能够补全的y的最小值
                        int min = Math.min(map.get(y), add);
                        //下一次for循环会检测第二个最近的数
                        p -= min * diff;
                        //加上补全的数的个数
                        cnt += min;
                    } else {
                        break;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }


    int[] nums, sum;
    int n, k;

    public int maxFrequency3(int[] _nums, int _k) {
        nums = _nums;
        k = _k;
        n = nums.length;
        Arrays.sort(nums);
        sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        int l = 0, r = n;
        //区间长度尽进行二分 等价与找到满足条件的最后一个数(排序后数组)
        while (l < r) {
            int mid = l + r + 1 >> 1;
            //长度为mid是否可以
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    boolean check(int len) {
        //0 ....mid   n - mid + 1 ....n - 1枚举所有符合长度的区间
        for (int l = 0; l + len - 1 < n; l++) {
            int r = l + len - 1;
            int cur = sum[r + 1] - sum[l];
            int t = nums[r] * len;
            if (t - cur <= k) return true;
        }
        return false;
    }




    @Test
    public void test() {
        int[] nums = {3, 9, 6};
        int k = 2;
        System.out.println(maxFrequency(nums, k));
    }
}
