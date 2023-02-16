package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Classname LC_2341
 * @Description
 * @Date 2023/2/16 8:47
 * @Created by brain
 */
public class LC_2341 {
    public int[] numberOfPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Set<Integer> keys = map.keySet();
        int[] answers = new int[2];
        for (Integer key : keys) {
            answers[0] += map.get(key) / 2;
            answers[1] += map.get(key) % 2;
        }
        return answers;
    }
    public int[] numberOfPairs2(int[] nums) {
        Map<Integer, Boolean> cnt = new HashMap<Integer, Boolean>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, !cnt.getOrDefault(num, false));
            if (!cnt.get(num)) {
                res++;
            }
        }
        return new int[]{res, nums.length - 2 * res};
    }
    public int[] numberOfPairs3(int[] nums) {
        int[] arr = new int[101];
        for (int i : nums) {
            ++arr[i];
        }
        int count = 0;
        for (int i : arr) {
            if ((i&1) == 1) {
                ++count;
            }
        }
        return new int[]{(nums.length-count)/2, count};
    }


    @Test
    public void test() {
        int[] nums = {1,3,2,1,3,2,2};
        System.out.println(Arrays.toString(numberOfPairs(nums)));
    }
}
