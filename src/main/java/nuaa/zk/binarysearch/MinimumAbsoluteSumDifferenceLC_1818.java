package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/21 9:59
 */
public class MinimumAbsoluteSumDifferenceLC_1818 {
//    int mod = 1000000007;
//    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
//        int maxDiff = 0;
//        int maxIndex = 0;
//        int res = 0;
//        int len = nums1.length;
//        for (int i = 0; i < len; i++) {
//            int temp = Math.abs(nums1[i] - nums2[i]);
//            res = (res + temp) % mod;
//            if (temp > maxDiff) {
//                maxDiff = Math.abs(nums1[i] - nums2[i]);
//                maxIndex = i;
//            }
//        }
//        if (maxDiff == 0)return 0;
//        int num = 0;
//        num = Math.abs(findNum(nums1,nums2[maxIndex])- nums2[maxIndex]);
//        return res - maxDiff + num;
//    }
//
//    private int findNum(int[] nums, int target) {
//        int res = -1;
//        int minDiff  = Integer.MAX_VALUE;
//        int len = nums.length;
//        for (int i = 0; i < len; i++) {
//            if (Math.abs(nums[i] - target) < minDiff){
//                minDiff = Math.abs(nums[i] - target);
//                res = nums[i];
//            }
//        }
//        return res;
//    }

    int mod = (int)1e9+7;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int a = nums1[i], b = nums2[i];
            if (a == b) continue;
            int x = Math.abs(a - b);
            sum += x;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (sorted[mid] <= b) l = mid;
                else r = mid - 1;
            }
            int nd = Math.abs(sorted[r] - b);
            if (r + 1 < n) nd = Math.min(nd, Math.abs(sorted[r + 1] - b));
            if (nd < x) max = Math.max(max, x - nd);
        }
        return (int)((sum - max) % mod);
    }
    @Test
    public void test(){
        int[] nums1 = {1,10,4,4,2,7}, nums2 = {9,3,5,1,7,4};
        System.out.println(minAbsoluteSumDiff(nums1, nums2));
    }
}
