package nuaa.zk.binarysearch.hard;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/30 10:35
 */
public class FindMinimumInRotatedSortedArrayIILC_154 {
    //相等时 直接去掉右边的值，此时最小值仍然在搜索区间
    public int findMin(int[] nums) {
        int left = -1, right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            if (nums[mid] < nums[right]) right = mid; // 蓝色
            else if (nums[mid] > nums[right]) left = mid; // 红色
            else --right;
        }
        return nums[right];
    }

    //预处理回复二段性
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
        return nums[right];
    }
}
