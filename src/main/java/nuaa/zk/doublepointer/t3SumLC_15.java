package nuaa.zk.doublepointer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/11 10:12
 */
public class t3SumLC_15 {
    //几种方法时间复杂度完全相同，只是对比一下写法
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int index1 = 0;
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        while (index1 < len) {
            int cur = nums[index1];
            int diff = -nums[index1];

            int left = index1 + 1;
            int right = len - 1;
            while (left < right) {
                int num1 = nums[left];
                int num2 = nums[right];
                int temp = num1 + num2;
                if (temp == diff) res.add(Arrays.asList(cur, nums[left], nums[right]));
                if (temp <= diff) {
                    while (++left <= right && nums[left] == num1) ;
                }
                if (temp >= diff) {
                    while (--right >= left && nums[right] == num2) ;
                }
            }
            while (++index1 < len && nums[index1] == cur) ;
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1, k = n - 1; j < k; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                while (k - 1 > j && nums[i] + nums[j] + nums[k - 1] >= 0) k--;
                if (nums[i] + nums[j] + nums[k] == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return ans;
    }
    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int k = 0; k < nums.length - 2; k++){
            if(nums[k] > 0) break;
            if(k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;
            while(i < j){
                int sum = nums[k] + nums[i] + nums[j];
                if(sum < 0){
                    while(i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while(i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while(i < j && nums[i] == nums[++i]);
                    while(i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums = {0,0,0};
        List<List<Integer>> res = threeSum(nums);
        res.forEach(t->{
            System.out.println(t);
        });
    }
}
