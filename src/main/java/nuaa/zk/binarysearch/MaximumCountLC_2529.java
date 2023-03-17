package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/17 9:38
 */
public class MaximumCountLC_2529 {
    //二分
    public int maximumCount(int[] nums) {
        int negative = findLowerBound(nums);
        int positive = findUpperBound(nums);
        return Math.max(negative + 1,nums.length - positive);
    }

    private int findLowerBound(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] >= 0) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return nums[left] < 0 ? left : left - 1;
    }

    private int findUpperBound(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1) + 1;
            if (nums[middle] <= 0) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return nums[right] > 0 ? right : right + 1;
    }
    //遍历
    public int maximumCount2(int[] nums) {
        int less = 0;
        int more = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] < 0)less++;
            if (nums[i] > 0)more++;
        }
        return Math.max(less,more);
    }

}
