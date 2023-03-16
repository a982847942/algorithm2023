package nuaa.zk.binarysearch;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/16 10:42
 */
public class KthMissingPositiveNumberLC_1539 {
    //枚举 模拟
    public int findKthPositive(int[] arr, int k) {
        int len = arr.length;
        int pre = 1;
        for (int i = 0; i < len; i++) {
            if (arr[i] != pre) {
                int temp = arr[i] - pre;
                if (k >= temp) {
                    k -= temp;
                } else {
                    return arr[i]  - (temp- k + 1);
                }
            }
            pre = arr[i] + 1;
            if (k == 0) return arr[i] - 1;
        }
        int res = arr[len - 1];
        while (k > 0) {
            res++;
            k--;
        }
        return res;
    }
    public int findKthPositive2(int[] arr, int k) {
        int missCount = 0, lastMiss = -1, current = 1, ptr = 0;
        for (missCount = 0; missCount < k; ++current) {
            if (current == arr[ptr]) {
                ptr = (ptr + 1 < arr.length) ? ptr + 1 : ptr;
            } else {
                ++missCount;
                lastMiss = current;
            }
        }
        return lastMiss;
    }
    //问题转换
    public int findKthPositive3(int[] arr, int k) {
        int len = arr.length;
        /**
         * 假设缺失数值全部在arr[0]左边则 结果应该就是k 否则表示缺失值应该右移一位即k++
         * 2 3 4 7 11     5
         * k = 5  2 3 4 都小于 5 表示5需要后移3位，此时7又被包含进来，说明8 应该继续后移一位
         */
        for (int i = 0; i < len; i++) {
            if (arr[i] <= k) k++;
        }
        return k;
    }

    //二分
    public int findKthPositive4(int[] arr, int k) {
        if (arr[0] > k) {
            return k;
        }

        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            int x = mid < arr.length ? arr[mid] : Integer.MAX_VALUE;
            if (x - mid - 1 >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return k - (arr[l - 1] - (l - 1) - 1) + arr[l - 1];
    }
    @Test
    public void test() {
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        System.out.println(findKthPositive(arr, k));
    }
}
