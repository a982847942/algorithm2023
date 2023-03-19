package nuaa.zk.binarysearch.top;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/18 10:30
 */
public class SearchInRotatedSortedArrayLC_33 {
    //第一步先找到最小值(剑指offer11)，然后再对应区间二分找到target
    public int search(int[] nums, int target) {
        int index = findLowerBound(nums);
        int len = nums.length - 1;
        if (target > nums[len]) {
            return binarySearch(nums, 0, index - 1, target);
        } else {
            return binarySearch(nums, index, len, target);
        }
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        int left = start;
        int right = end;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    private int findLowerBound(int[] nums) {
        int left = -1;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > nums[right]) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return right;
    }

    public int search2(int[] nums, int target) {
        int left = -1;
        int len = nums.length;
        int right = len;
        /**
         * 开区间-1,len
         * 一次二分，思路在于，判断当前值是否在target右边或左边
         * 1.首先判断当前的middle在旋转数组的升序部分 还是降序部分nums[middle] > nums[len - 1]
         * 2.如果在升序部分，为了将middle右侧染为蓝色，需要满足target也在升序部分，且middle处的值大于等于target
         * 3.如果在降序部分，则要满足 1. target在升序部分 2. 或者target在降序部分 且middle处的值大于等于target
         */

        while (left  + 1< right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > nums[len - 1] && target > nums[len - 1] && nums[middle] >= target) {
                right = middle;
            } else if (nums[middle] <= nums[len - 1] && (nums[len - 1] < target || nums[middle] >= target)) {
                right = middle;
            } else {
                left = middle;
            }
        }
        if (right == len) return -1;
        return nums[right] == target ? right : -1;
    }

    @Test
    public void test() {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search2(nums, target));
    }
}
