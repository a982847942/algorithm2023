package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/14 22:34
 */
public class BinarySearchLC_704 {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            int temp = nums[middle];
            if (temp == target){
                return middle;
            }else if (temp > target){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
