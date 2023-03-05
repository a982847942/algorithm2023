package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/5 21:35
 */
public class MoveZeroesLC_283 {
    public void moveZeroes(int[] nums) {
        int right = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                nums[++right] = nums[i];
            }
        }
        for (int i = right + 1; i < len; i++) {
            nums[i] = 0;
        }
    }

    //这个无法保证相对顺序，本体不可用！！
    public void moveZeroes2(int[] nums) {
        int len = nums.length;
        int right = len - 1;
        for (int i = 0; i <= right; i++) {
            if (nums[i] == 0) {
              swap(nums,right--,i--);
            }
        }
        for (int i = right + 1; i < len; i++) {
            nums[i] = 0;
        }
    }
    public void swap(int[] nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
    }
}
