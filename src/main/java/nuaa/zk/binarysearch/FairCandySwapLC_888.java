package nuaa.zk.binarysearch;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/15 9:25
 */
public class FairCandySwapLC_888 {
    /**
     * 思路 排序后 找到两个总量的差值 则两个数组中必然存在某一对数差值为 diff / 2,采用二分查找在另一个数组查找
     * 时间复杂度nlogn
     *
     * @param aliceSizes
     * @param bobSizes
     * @return
     */
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int totalAlice = 0;
        int totalBob = 0;
        Arrays.sort(aliceSizes);
        Arrays.sort(bobSizes);
        int len1 = aliceSizes.length;
        int len2 = bobSizes.length;
        for (int i = 0; i < len1; i++) {
            totalAlice += aliceSizes[i];
        }
        for (int i = 0; i < len2; i++) {
            totalBob += bobSizes[i];
        }
        int diff = Math.abs(totalAlice - totalBob) / 2;
        if (totalAlice > totalBob) {
            for (int i = 0; i < len1; i++) {
                int temp = aliceSizes[i] - diff;
                int res = binarySearch(bobSizes, temp);
                if (res != -1) {
                    return new int[]{aliceSizes[i], temp};
                }
            }
        } else {
            for (int i = 0; i < len1; i++) {
                int temp = aliceSizes[i] + diff;
                int res = binarySearch(bobSizes, temp);
                if (res != -1) {
                    return new int[]{aliceSizes[i], temp};
                }
            }
        }
        return new int[]{};
    }

    private int binarySearch(int[] bobSizes, int temp) {
        int left = 0;
        int right = bobSizes.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            int t = bobSizes[middle];
            if (t > temp) {
                right = middle - 1;
            } else if (t < temp) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    //哈希表
    public int[] fairCandySwap2(int[] aliceSizes, int[] bobSizes) {
        int totalAlice = 0;
        int totalBob = 0;
        int len1 = aliceSizes.length;
        int len2 = bobSizes.length;
        Set<Integer> cache = new HashSet<>();
        for (int i = 0; i < len1; i++) {
            totalAlice += aliceSizes[i];
        }
        for (int i = 0; i < len2; i++) {
            totalBob += bobSizes[i];
            cache.add(bobSizes[i]);
        }
        int diff = Math.abs(totalAlice - totalBob) / 2;


        if (totalAlice > totalBob) {
            for (int i = 0; i < len1; i++) {
                int temp = aliceSizes[i] - diff;
                if (cache.contains(temp)) {
                    return new int[]{aliceSizes[i], temp};
                }
            }
        } else {
            for (int i = 0; i < len1; i++) {
                int temp = aliceSizes[i] + diff;
                if (cache.contains(temp)) {
                    return new int[]{aliceSizes[i], temp};
                }
            }
        }
        return new int[]{};
    }

    public int[] fairCandySwap3(int[] aliceSizes, int[] bobSizes) {
        int sumA = Arrays.stream(aliceSizes).sum();
        int sumB = Arrays.stream(bobSizes).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<Integer>();
        for (int num : aliceSizes) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : bobSizes) {
            int x = y + delta;
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }


    @Test
    public void test() {
        int[] aliceSizes = {1, 2, 5}, bobSizes = {2, 4};
        int[] res = fairCandySwap(aliceSizes, bobSizes);
        System.out.println(Arrays.toString(res));
    }

}
