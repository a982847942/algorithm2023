package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/15 21:54
 */
public class FindDistanceValueLC_1385 {
    //1.暴力
    //2.枚举 + 二分
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int res = 0;
        /**
         * 这里二分的思路是 找到第一个比cur大的数的下标left 然后比较arr2[left] arr2[left -1]与cur的距离
         */
        for (int i = 0; i < len1; i++) {
            int cur = arr1[i];
            int left = 0;
            int right = len2 - 1;
            while (left < right) {
                int middle = left + ((right - left) >> 1);
                if (arr2[middle] > cur) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }
            int t1 = Math.abs(arr2[left] - cur);
            int t2 = left > 0 ? Math.abs(arr2[left - 1] - cur) : d + 1;
            if (t1 > d && t2 > d) res++;
        }
        return res;
    }

    // TODO: 2023/3/16
    //这也是二分的思路，主要在于Arrays.binarySearch的原理！！
    public int findTheDistanceValue2(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int i1 : arr1) {
            int low = i1 - d;
            int high = i1 + d;
            int index1 = Arrays.binarySearch(arr2, low);
            int index2 = Arrays.binarySearch(arr2, high);
            if (index1 < 0 && index1 == index2) {
                ans++;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] arr1 = {4, 5, 8};
        int[] arr2 = {10, 9, 1, 8};
        int d = 2;
        System.out.println(findTheDistanceValue(arr1, arr2, d));
    }
}
