package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/17 9:09
 */
public class LongestSubsequenceWithLimitedSumLC_2389 {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int len1 = queries.length;
        int len2 = nums.length;
        int[] prefixSum = new int[len2 + 1];
        for (int i = 0; i < len2; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            int cur = queries[i];
            //找到<=cur 的最后一个数
            int lower = findLEBoundary(prefixSum,cur);
            res[i] = lower;
        }
        return res;
    }

    private int findLEBoundary(int[] nums, int cur) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int middle = left + ((right - left) >> 1) + 1;
            if (nums[middle] <= cur){
                left = middle;
            }else {
                right = middle - 1;
            }
        }
        return nums[left] <= cur ? left : left - 1;
    }

    @Test
    public void test(){
        int[] nums = {4,5,2,1}, queries = {3,10,21};
        System.out.println(Arrays.toString(answerQueries(nums,queries)));
    }
}
