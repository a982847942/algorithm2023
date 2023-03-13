package nuaa.zk.subarray;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/13 9:12
 */
public class ContinuousSubarraySumLC_523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = (prefixSum[i] + nums[i]) % k;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 0);
        for (int i = 1; i <= len; i++) {
            int temp = prefixSum[i];
            if (cache.containsKey(temp)) {
                if (i - cache.get(temp) >= 2) return true;
            } else {
                cache.put(temp, i);
            }
        }
        return false;
    }
    //思路通上
    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) return true;
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = { 0};
        int k = 1;
        System.out.println(checkSubarraySum(nums, k));
    }
}
