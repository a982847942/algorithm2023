package nuaa.zk.binarysearch.medium;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/28 21:48
 */
public class FindFirstAndLastPositionLC_34 {
    public int[] searchRange(int[] nums, int target) {
        int i = lowerBound(nums, target);
        if (i >= nums.length || nums[i] != target) return new int[]{-1, -1};
        int j = moreBound(nums, target);
        return new int[]{i, j};
    }

    private int moreBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] <= target) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return left;
    }

    private int lowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] >= target) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return right;
    }
}
