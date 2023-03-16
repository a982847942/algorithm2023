package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/16 22:21
 */
public class FindTargetIndicesAfterSortingArrayLC_2089 {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] == target) res.add(i);
        }
        return res;
    }

    public List<Integer> targetIndices2(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        List<Integer> res = new ArrayList<>();
        int left = findLeftBoundary(nums, target);
        int right = findRightBoundary(nums, target);
        if(left == -1)return res;
        for (int i = left; i <= right; i++) {
            res.add(i);
        }
        return res;
    }

    private int findLeftBoundary(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int middle = left + ((right - left) >> 1);
            if (nums[middle] >= target){
                right = middle;
            }else {
                left = middle + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int findRightBoundary(int[] nums, int target) {
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
        return nums[left] == target ? left : -1;
    }
    /**
     *排序后数组中等于target的下表可以这样来求
     * 1.统计数组中小于target元素个数 记为less
     * 2.统计数组中等于target元素个数 记为equal
     * 则下表为less...less + equal - 1
     */
    public List<Integer> targetIndices3(int[] nums, int target) {
        int less = 0;
        int equal = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int temp = nums[i];
            if (temp < target)less++;
            if (temp == target)equal++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = less; i < less + equal; i++) {
            res.add(i);
        }
        return res;
    }

    @Test
    public void test(){
        int[] nums = {1,2,5,2,3};
        int target = 2;
        System.out.println(targetIndices2(nums, target));
    }
}
