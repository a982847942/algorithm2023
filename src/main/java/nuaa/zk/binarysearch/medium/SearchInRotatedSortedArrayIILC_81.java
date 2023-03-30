package nuaa.zk.binarysearch.medium;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/30 10:06
 */
public class SearchInRotatedSortedArrayIILC_81 {
    public boolean search(int[] nums, int target) {
        int index = findLowerBound(nums);
        if (target <= nums[nums.length - 1]) {
            return binarySearch(nums, index, nums.length - 1,target);
        } else {
            return binarySearch(nums, 0, index,target);
        }
    }

    private boolean binarySearch(int[] nums, int start, int end,int target) {
        int left = start;
        int right = end;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target){
                right = middle - 1;
            }else if (nums[middle] < target){
                left = middle + 1;
            }else {
                return true;
            }
        }
        return false;
    }

    private int findLowerBound(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right && nums[left] == nums[right])left++;
        if (left == right)return right;
        while (left < right){
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > nums[right]){
                left = middle + 1;
            }else {
                right = middle;
            }
        }
        return right;
    }

    @Test
    public void test(){
//        int[] arr = {2,2,2,3,2,2,2};
//        int target = 3;
//        System.out.println(findLowerBound(arr));
//        System.out.println(search(arr,target));

        System.out.println(findLowerBound(new int[]{3, 1, 3}));
    }


}
