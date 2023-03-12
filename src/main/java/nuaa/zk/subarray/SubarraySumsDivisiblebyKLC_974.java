package nuaa.zk.subarray;

import org.junit.Test;
import sun.misc.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/12 11:40
 */
public class SubarraySumsDivisiblebyKLC_974 {
    public int subarraysDivByK(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int res = 0;
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        for (int i = 1; i <= len; i++) {
            int temp = (prefixSum[i] % k + k) % k;
            if (cache.containsKey(temp)) {
                res += cache.get(temp);
            }
            cache.put(temp, cache.getOrDefault(temp, 0) + 1);
        }
        return res;
    }
    //同上 一次遍历
    public int subarraysDivByK2(int[] nums, int k) {
        Map<Integer, Integer> record = new HashMap<Integer, Integer>();
        record.put(0, 1);
        int sum = 0, ans = 0;
        for (int elem : nums) {
            sum += elem;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % k + k) % k;
            int same = record.getOrDefault(modulus, 0);
            ans += same;
            record.put(modulus, same + 1);
        }
        return ans;
    }

    //排列组合
    // TODO: 2023/3/12 有机会看看，不常用
    public int subarraysDivByK3(int[] nums, int k) {
        Map<Integer, Integer> record = new HashMap<Integer, Integer>();
        record.put(0, 1);
        int sum = 0;
        for (int elem : nums) {
            sum += elem;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % k + k) % k;
            record.put(modulus, record.getOrDefault(modulus, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry: record.entrySet()) {
            ans += entry.getValue() * (entry.getValue() - 1) / 2;
        }
        return ans;
    }




    @Test
    public void test() {
        int[] nums = {-1, 2, 9};
        int k = 2;
        System.out.println(subarraysDivByK(nums, k));
    }
}
