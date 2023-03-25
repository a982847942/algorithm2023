package nuaa.zk.dp;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/25 9:14
 */
public class ShortestSubarrayLC_1574 {

    /**
     * 同向双指针
     * 1.先找到最长非递减后缀 和 非递减最长前缀
     * 2.比较right == 0
     * 3.枚举left和right
     * 为什么left不会出现在中间位置？ 出现在中间 意味着要删除的数组数目>=2不符合题意
     * @param arr
     *
     * 本题变形
     * 1.至少修改多少个数，使得修改后的数组是非递减的？（等价于删除一个最短的子序列）
     * 等价于最长非递减子序列
     * 2.至少修改多少个数，使得修改后的数组是严格递增的？
     * 不等价最长递增子序列 eg:[1,2,2,3] 在本题下最小为2
     * 正确解法
     * arr[j] -  arr[i] >= j- i
     * 得arr[j] - j >= arr[i] - i
     * 构造b[i] = arr[i] - i
     * 求b的最长非递减子序列(在b中不满非递减条件的都是需要修改的)
     *
     * 注意在严格递增情况下，修改和删除不是等价的。
     * @return
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length, right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right])
            --right;
        if (right == 0) return 0; // arr 已经是非递减数组
        // 此时 arr[right-1] > arr[right]
        int ans = right; // 删除 0 到 right-1
        for (int left = 0; left == 0 || arr[left - 1] <= arr[left]; ++left) {
            while (right < n && arr[right] < arr[left])
                ++right;
            // 此时 arr[left] <= arr[right]，从 left+1 到 right-1 可以删除
            ans = Math.min(ans, right - left - 1);
        }
        return ans;
    }

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 6, 4, 2, 3, 5};
        System.out.println(findLengthOfShortestSubarray(arr));
    }
}
