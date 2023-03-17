package nuaa.zk.binarysearch;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/17 20:42
 */
public class LCP_18 {
    int mod = 1000000007;

    //二分
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int len = staple.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int cur = staple[i];
            res = (res + findLowerBound(drinks, x - cur)) % mod;
        }
        return res;
    }

    private int findLowerBound(int[] drinks, int targte) {
        int len = drinks.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1) + 1;
            if (drinks[middle] <= targte) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return drinks[left] <= targte ? left + 1 : left;
    }

    //双指针
    public int breakfastNumber2(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int len1 = staple.length;
        int len2 = drinks.length;
        int index1 = len1 - 1;
        int index2 = 0;
        int res = 0;
        while (index1 >= 0 && index2 < len2) {
            int cur1 = staple[index1];
            int cur2 = drinks[index2];
            if (cur1 + cur2 <= x) {
                index2++;
            } else {
                res = (res + index2) % mod;
                index1--;
            }
        }
        while (index1 >= 0) {
            res = (res + len2) % mod;
            index1--;
        }
        return res;
    }

    public int breakfastNumber3(int[] staple, int[] drinks, int x) {
        int[] arr = new int[x + 1];
        int len = staple.length;
        for (int i = 0; i < len; i++) {
            if (staple[i] <= x) {
                arr[staple[i]]++;
            }
        }

        //提前统计
        for (int i = 1; i < x + 1; i++) {
            arr[i] += arr[i - 1];
        }

        len = drinks.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int diff = x - drinks[i];
            if (diff >= 0){
                res  = (res + arr[diff]) % mod;
            }
        }
        return res;
    }

}
