package nuaa.zk.binarysearch.mianshiti;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/19 14:12
 */
public class T_08_03 {
    public int findMagicIndex(int[] nums) {
        int left = 0;
        int len = nums.length;
        while (left < len){
            int temp = nums[left];
            if (temp == left)return left;
            if (temp > left){
                left = temp;
            }else {
                left++;
            }
        }
        return -1;
    }
}
