package nuaa.zk.doublepointer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/7 10:19
 */
public class SquaresofSortedArrayLC_977 {
    //找边界点
    public int[] sortedSquares(int[] nums) {
        int start = -1;
        int len = nums.length;
        int[] res = new int[len];
        while (++start < len &&  nums[start] < 0 );
        int left = start - 1;
        int index = 0;
        while (left >= 0 && start < len){
            int num1 = nums[left];
            int num2 = nums[start];
            res[index++] = num1 * num1 > num2*num2 ? nums[start++] * num2 : nums[left--] * num1;
        }
        while (start < len){
            res[index++] =  nums[start] * nums[start++];
        }
        while (left >= 0){
            res[index++] = nums[left] * nums[left--];
        }
        return res;
    }


    // TODO: 2023/3/7 所有排序都可  双指针时间复杂度最优
    //不用找边界点
    public int[] sortedSquares2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }

    @Test
    public void test(){
        int[] nums = {-7,-3,2,3,11};
        System.out.println(Arrays.toString( sortedSquares(nums)));
    }
}
