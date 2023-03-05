package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/5 21:14
 */
public class RemoveDuplicatesLC_26 {
    public int removeDuplicates(int[] nums) {
        int left = 0;
        int len = nums.length;
        int num = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] != num){
                num = nums[i];
//                swap(nums,++left,i);
                nums[++left] = nums[i];
            }
        }
        return left + 1;
    }

    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        int right = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != nums[right]){
                nums[++right] = nums[i];
            }
        }
        return right + 1;
    }
    public void swap(int[] nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    @Test
    public void test(){
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates2(nums));
    }
}
