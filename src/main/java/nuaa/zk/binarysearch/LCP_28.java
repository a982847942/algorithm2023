package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/17 21:21
 */
public class LCP_28 {
    int mod = 1000000007;

    //排序 + 二分
    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int cur = findLowerBound(nums, target - nums[i]);
            int min = Math.max(cur - i, 0);
            res = (res + min) % mod;
            if (i <= cur - 1) {
                res -= 1;
            }

        }
        return res;
    }

    private int findLowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1) + 1;
            if (nums[middle] <= target) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return nums[left] <= target ? left + 1 : left;
    }

    //排序 + 计数
    public int purchasePlans2(int[] nums, int target) {
        Arrays.sort(nums);
        int index1 = 0;
        int index2 = nums.length - 1;
        int res = 0;
        while (index1 < index2){
            if (nums[index1] + nums[index2] <= target){
                res = (res + index2  - index1) % mod;
                index1++;
            }else {
                index2--;
            }
        }
        return res;
    }

    //计数 + 前缀和
    public int purchasePlans3(int[] nums, int target) {
        long ans = 0;
        int[] count = new int[target];
        for(int num : nums) {
            if(num < target) {
                count[num]++;
            }
        }
        // 前缀和
        for(int i = 1; i < target; i++){
            count[i] += count[i - 1];
        }
        for(int num : nums) {
            if(num < target){
                ans += count[target - num];
                // 抠去自己
                if(num <= target - num) ans--;
            }
        }
        return (int)((ans >> 1) % 1000000007);
    }

    //桶排序
    public int purchasePlans4(int[] nums, int target) {
        long ans = 0l, MOD = 1000000007;
        int[] bucket = new int[100001];

        for (int x : nums) {
            bucket[x]++;
        }

        int i = 0, j = 0, len = nums.length;
        while (i < len) {
            while (bucket[j] == 0) {
                j++;
            }
            //复制原数组
            while (bucket[j] > 0) {
                nums[i] = j;
                bucket[j]--;
                i++;
            }
        }

        int left = 0, right = len - 1;
        while (left < right) {
            while (right > left && nums[left] + nums[right] > target) {
                right--;
            }
            ans = (ans + right - left) % MOD;
            left++;
        }

        return (int)ans;
    }

    // TODO: 2023/3/17 看到题解有人用树状数组！ 后续有时间研究研究
    @Test
    public void test() {
        int[] arr = {2, 3, 5, 5};
        int k = 6;
        System.out.println(purchasePlans(arr, k));
    }
}
