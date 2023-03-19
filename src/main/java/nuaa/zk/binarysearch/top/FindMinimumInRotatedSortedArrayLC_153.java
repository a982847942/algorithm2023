package nuaa.zk.binarysearch.top;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/18 10:26
 */
public class FindMinimumInRotatedSortedArrayLC_153 {
    //剑指offer11(LC154)的删减版
    public int findMin(int[] nums) {
        int left = -1;
        int right = nums.length - 1;
        while (left + 1 < right){
            int middle = left + ((right - left) >> 1);
            if(nums[middle] > nums[right]){
                left = middle;
            }else {
                right = middle;
            }
        }
        return nums[right];
    }

}
