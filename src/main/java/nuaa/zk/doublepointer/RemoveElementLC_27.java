package nuaa.zk.doublepointer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/5 9:49
 */
public class RemoveElementLC_27 {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int left = -1;
        int right = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int temp = nums[i];
            if (temp == val){
                right++;
            }else {
                swap(nums,++left,++right);
            }
        }
        return left + 1;
    }
    public void swap(int[] nums,int index,int index2){
        int temp = nums[index];
        nums[index] = nums[index2];
        nums[index2] = temp;
    }

    //快慢指针  这里遍历时候的下表也是一个指针(fori就行)
    public int removeElement3(int[] nums, int val) {
        int idx = 0;
        for (int x : nums) {
            if (x != val) nums[idx++] = x;
        }
        return idx;
    }


    //相向双指针
    public int removeElement2(int[] nums, int val) {
        int len = nums.length;
        int right = len - 1;
        for (int left = 0; left <= right ; left++) {
            if (nums[left] == val){
                swap(nums,left--,right--);
            }
        }
        return right + 1;
    }

    @Test
    public void test(){
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(removeElement(nums, val));
    }

}
