package nuaa.zk.binarysearch;

import org.junit.Test;

/**
 * @Classname LC_35
 * @Description
 * @Date 2023/2/14 19:07
 * @Created by brain
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        //结果按照right索引，要注意right初始值要在数组范围内，防止left一直右移此时循环退出时超出len
        int right = len -1;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        //right一直左移，导致小于0
        if (right < 0) return 0;
        return nums[right] >= target ? right : (right + 1);
    }
    public int searchInsert2(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        //left一直右移，使用left作为索引要进行判断
        if(left >= len) return len;
        return nums[left] >= target ? left : (left + 1);
    }
    public int searchInsert3(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return nums[left] >= target ? left : (left + 1);
    }

    public int searchInsert4(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right){
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return left;
    }
    @Test
    public void test(){
        int[] nums = {1,3,5,6};
        int target = 7;
        System.out.println(searchInsert4(nums, target));
    }

}
