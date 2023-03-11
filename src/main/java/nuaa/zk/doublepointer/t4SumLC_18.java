package nuaa.zk.doublepointer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/11 10:43
 */
public class t4SumLC_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        while (i < len){
            int j = i + 1;
            while (j < len){
                //可能会超出int范围
                long temp = (long) target - nums[i] - nums[j];
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    int cur = nums[left] + nums[right];
                    if (cur == temp) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                    }
                    if (cur <= temp) {
                        while (++left <= right && nums[left] == nums[left - 1]) ;
                    }
                    if (cur >= temp) {
                        while (--right >= left && nums[right] == nums[right + 1]) ;
                    }
                }
                while (++j < len && nums[j] == nums[j - 1]) ;
            }
            while (++i < len && nums[i] == nums[i - 1]);
        }

        return res;
    }

    //各种剪枝
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2]+ nums[i + 3] > target) break;
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) continue;
            for (int index = i + 1; index < nums.length - 2; index++) {
                int left = index + 1;
                int right = nums.length - 1;
                if (index > i + 1 && nums[index] == nums[index - 1]) continue;
                if (nums[index] + nums[index + 1] + nums[index + 2] + nums[i] > target) break;
                if (nums[index] + nums[i] + nums[nums.length - 1] + nums[nums.length - 2] < target) continue;
                int sum = 0;
                while (left < right) {
                    int lNum = nums[left];
                    int rNum = nums[right];
                    sum = nums[index] + lNum + rNum + nums[i];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[index], nums[left++], nums[right--],nums[i]));
                        while (left < right && nums[left] == lNum) left++;
                        while (left < right && nums[right] == rNum) right--;
                    } else {
                        if(sum > target) right--;
                        if(sum < target) left++;
                        // while (sum > 0 && left < right && nums[right] == rNum) right--;
                        // while (sum < 0 && left < right && nums[left] == lNum) left++;
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        List<List<Integer>> res = fourSum(nums, target);
        res.forEach(t->{
            System.out.println(t);
        });
    }
}
