package nuaa.zk.day;

import org.junit.Test;
import sun.plugin.javascript.navig.Array;

import java.util.*;

/**
 * @Classname MakeArrayZeroLC_2357
 * @Description
 * @Date 2023/2/24 8:58
 * @Created by brain
 */
public class MakeArrayZeroLC_2357 {
    public int minimumOperations(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        int len = nums.length;
        if (nums[len - 1] == 0) return res;

        int curIndex = -1;
        while (nums[++curIndex] == 0);
        for (; curIndex < len;) {
            int temp = nums[curIndex];
            for (int i = curIndex; i < len; i++) {
                nums[i] -= temp;
            }
            while (++curIndex < len && nums[curIndex]==0);
            res++;
        }
        return res;
    }

    public int minimumOperations2(int[] nums) {
        Set<Integer> map = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0){
                map.add(nums[i]);
            }
        }
        return map.size();
    }

    @Test
    public void test(){
        int[] nums = {0};
        System.out.println(minimumOperations(nums));
    }
}
