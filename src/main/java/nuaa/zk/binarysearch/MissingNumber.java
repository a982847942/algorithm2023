package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Classname MissingNumber
 * @Description
 * @Date 2023/2/16 10:16
 * @Created by brain
 */
public class MissingNumber {
    //排序
    public int missingNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) return i;
        }
        return n;
    }

    //数组hash
    public int missingNumber1(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len + 1];
        for (int num : nums) {
            arr[num] = 1;
        }
        for (int i = 0; i < len + 1; i++) {
            if (arr[i] == 0) return i;
        }
        return -1;
    }
    //原地hash 改变原数组，慎重！
    public int missingNumber4(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i && nums[i] < n) swap(nums, nums[i], i--);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) return i;
        }
        return n;
    }
    void swap(int[] nums, int i, int j) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }
    //作差法
    public int missingNumber2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int len = nums.length;
        int rightSum = (1 + len) * len / 2;
        return rightSum - sum;
    }
    //异或
    public int missingNumber3(int[] nums) {
       int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len + 1; i++) {
            sum ^= i;
        }
        for (int num : nums) {
            sum ^= num;
        }
        return sum;
    }
    @Test
    public void test(){
        int[] nums = {9,6,4,2,3,5,7,0,1};
//        System.out.println(missingNumber1(nums));
//        System.out.println(missingNumber2(nums));
//        System.out.println(missingNumber3(nums));
    }
}
