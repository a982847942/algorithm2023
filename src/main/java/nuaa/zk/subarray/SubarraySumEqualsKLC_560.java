package nuaa.zk.subarray;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/12 9:15
 */
public class SubarraySumEqualsKLC_560 {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = nums[i] + prefixSum[i];
        }
        Map<Integer,Integer> cache = new HashMap<>();
        cache.put(0,1);
        int res = 0;
        for (int i = 1; i <= len; i++) {
            int temp = prefixSum[i] - k;

            if (cache.containsKey(temp)){
                res += cache.get(temp);
            }
            cache.put(prefixSum[i],cache.getOrDefault(prefixSum[i],0) + 1);
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums = {1,-1,0};
        int p = 0;
        System.out.println(subarraySum(nums, p));
    }
}
