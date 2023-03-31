package nuaa.zk.day;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/31 9:16
 */
public class NumberOfArithmeticTripletsLC_2367 {
    public int arithmeticTriplets(int[] nums, int diff) {
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[j] - nums[i] != diff) continue;
                for (int k = j + 1; k < len; k++) {
                    if (nums[k] - nums[j] == diff) ans++;
                }
            }
        }
        return ans;
    }
    public int arithmeticTriplets2(int[] nums, int diff) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i] + diff) && set.contains(nums[i] + 2*diff)) ans++;
        }
        return ans;
    }

    public int arithmeticTriplets3(int[] nums, int diff) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0, j = 1, k = 2; i < n - 2 && j < n - 1 && k < n; i++) {
            j = Math.max(j, i + 1);
            while (j < n - 1 && nums[j] - nums[i] < diff) {
                j++;
            }
            if (j >= n - 1 || nums[j] - nums[i] > diff) {
                continue;
            }
            k = Math.max(k, j + 1);
            while (k < n && nums[k] - nums[j] < diff) {
                k++;
            }
            if (k < n && nums[k] - nums[j] == diff) {
                ans++;
            }
        }
        return ans;
    }
}
