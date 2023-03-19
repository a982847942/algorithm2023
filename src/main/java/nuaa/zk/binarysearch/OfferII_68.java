package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/19 11:55
 */
public class OfferII_68 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int middle = left + ((right - left) >> 1) + 1;
            if (nums[middle] <= target){
                left = middle;
            }else {
                right = middle - 1;
            }
        }
        return nums[left] >= target ? left : left + 1;
    }
}
