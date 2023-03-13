package nuaa.zk.subarray;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/13 9:43
 */
public class ContiguousArrayLC_525 {
    public int findMaxLength(int[] nums) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            if ((nums[i] & 1) == 0){
                nums[i] = -1;
            }
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        Map<Integer,Integer> cache = new HashMap<>();
        int max = 0;
        cache.put(0,0);
        for (int i = 1; i <= len; i++) {
            int temp = prefixSum[i];
            if (cache.containsKey(temp)){
                max = Math.max(max,i - cache.get(temp));
            }else {
                cache.put(temp,i);
            }
        }
        return max;
    }
    @Test
    public void test(){
        int[] nums = {0,1,0};
        System.out.println(findMaxLength(nums));
    }

}
