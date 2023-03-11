package nuaa.zk.doublepointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/11 11:22
 */
public class t4SumIILC_454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> cache = new HashMap<>();
        int len = nums1.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Arrays.sort(nums3);
        Arrays.sort(nums4);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int temp = nums3[i] + nums4[j];
                cache.put(temp, cache.getOrDefault(temp, 0) + 1);
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int temp = nums1[i] + nums2[j];
//                if (cache.containsKey(-temp)){
//                    res += cache.get(-temp);
//                }
                res += cache.getOrDefault(-temp,0);
            }
        }
        return res;
    }
}
