package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Classname MakeArrayZigzag_1144
 * @Description
 * @Date 2023/2/27 9:11
 * @Created by brain
 */
public class MakeArrayZigzag_1144 {
    public int movesToMakeZigzag(int[] nums) {
        int len = nums.length;
        int min1 = 0;
        int[] copyArr1 = Arrays.copyOf(nums,nums.length);
        int[] copyArr2 = Arrays.copyOf(nums,nums.length);
        //偶数大
        for (int i = 0; i < len; i++) {
            if ((i & 1) == 0) {
                int left = 0;
                if (i != 0) {
                    left = copyArr1[i - 1] - copyArr1[i];
                    if (left >= 0) {
                        left += 1;
                        copyArr1[i - 1] -= left;
                    }else {
                        left = 0;
                    }
                }
                int right = 0;
                if (i != len - 1) {
                    right = copyArr1[i + 1] - nums[i];
                    if (right >= 0) {
                        right += 1;
                        copyArr1[i + 1] -= right;
                    }else {
                        right = 0;
                    }
                }
                min1 = min1 + left + right;
            }
        }

        int min2 = 0;
        //奇数大
        for (int i = 0; i < len; i++) {
            if ((i & 1) != 0) {
                int left = 0;
                left = copyArr2[i - 1] - copyArr2[i];
                if (left >= 0) {
                    left += 1;
                    copyArr2[i - 1] -= left;
                }else {
                    left = 0;
                }
                int right = 0;
                if (i != len - 1) {
                    right = copyArr2[i + 1] - nums[i];
                    if (right >= 0) {
                        right += 1;
                        copyArr2[i + 1] -= right;
                    }else {
                        right = 0;
                    }
                }
                min2 = min2 + left + right;
            }
        }
        return Math.min(min1, min2);
    }
    public int movesToMakeZigzag2(int[] nums) {
        int len = nums.length;
        int[] res = new int[2];
        for (int i = 0; i < len; i++) {
            int left = i > 0 ? nums[i - 1] : Integer.MAX_VALUE;
            int right = i < len - 1 ? nums[i + 1] : Integer.MAX_VALUE;
            /**
             * res[偶数]表示需要满足奇数大的情况
             * 不管需要满足奇数大 还是偶数大，都需要让当前位置小于左右两边的最小值
             */

            res[i & 1] += Math.max(nums[i] - Math.min(left,right) + 1,0);
        }
        return Math.min(res[0],res[1]);
    }


    @Test
    public void test() {
        int[] nums = {2, 7, 10, 9, 8, 9};
        System.out.println(movesToMakeZigzag3(nums));
    }

    public int movesToMakeZigzag3(int[] nums) {
        return Math.min(help(nums, 0), help(nums, 1));
    }

    public int help(int[] nums, int pos) {
        int res = 0;
        for (int i = pos; i < nums.length; i += 2) {
            int a = 0;
            if (i - 1 >= 0) {
                a = Math.max(a, nums[i] - nums[i - 1] + 1);
            }
            if (i + 1 < nums.length) {
                a = Math.max(a, nums[i] - nums[i + 1] + 1);
            }
            res += a;
        }
        return res;
    }

}
