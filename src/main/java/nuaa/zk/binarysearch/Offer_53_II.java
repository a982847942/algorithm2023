package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/19 11:32
 */
public class Offer_53_II {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
           if (nums[middle] > middle){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }

        return left;
    }
}
