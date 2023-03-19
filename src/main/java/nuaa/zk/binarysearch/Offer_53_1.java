package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/19 11:19
 */
public class Offer_53_1 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)return 0;
        int right = findLowerBound(nums,target);
        int left = findLowerBound(nums,target - 1);
        int res = right - left;
        return res < 0 ? 0 : res;
    }

    private int findLowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length-1;
        while (left + 1 < right){
            int middle = left + ((right - left) >> 1);
            if (nums[middle] <= target){
                left = middle;
            }else {
                right = middle;
            }
        }
        return nums[right] <= target ? right :left;
    }
}
