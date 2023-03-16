package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/16 21:48
 */
public class SpecialArrayLC_1608 {

    //排序 + 二分
    public int specialArray(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len;
        Arrays.sort(nums);
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            int temp = check(nums,middle);
            if (temp > middle){
                left = middle + 1;
            }else if (temp < middle){
                right = middle - 1;
            }else {
                return middle;
            }
        }
        return -1;
    }

    private int check(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i]  >= target)return len - i;
        }
        return -1;
    }

    //结合数据量考虑
    public int specialArray2(int[] nums) {
        int[] cnts = new int[1010];
        for (int x : nums) cnts[x]++;
        //逆序遍历 并累计前面的数的数量
        for (int i = 1009, tot = 0; i >= 0; i--) {
            tot += cnts[i];
            if (i == tot) return i;
        }
        return -1;
    }

    public class MyComparator implements Comparator<Integer>{


        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    public int specialArray3(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        /**
         * 降序排列后 如果 i是特征值 则 num[i - 1] >= i 而且同时要满足后续未遍历的小于i 即num[i] < i
         * 另外一种情况是 数组遍历结束 i == n时 还未找到 也就是说数组中的n个数 都>= n
         * 如果都不满足 则 返回-1
         */
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] >= i && (i == n || nums[i] < i)) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test(){
        int[] nums = {3,5};
        System.out.println(specialArray(nums));
    }
}
