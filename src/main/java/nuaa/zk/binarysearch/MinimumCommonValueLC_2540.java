package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/17 9:52
 */
public class MinimumCommonValueLC_2540 {
    //双指针
    public int getCommon(int[] nums1, int[] nums2) {
        int index1 = 0;
        int index2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (index1 < len1 && index2 < len2){
            if (nums1[index1] < nums2[index2]){
                index1++;
            }else if (nums1[index1] > nums2[index2]){
                index2++;
            }else {
                return nums1[index1];
            }
        }
        return -1;
    }
    //二分
    public int getCommon2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        for (int i = 0; i < len1; i++) {
            int cur = nums1[i];
            if (binarySerach(nums2,cur))return cur;
        }
        return -1;
    }

    private boolean binarySerach(int[] nums2, int target) {
        int left = 0;
        int right = nums2.length - 1;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            if (nums2[middle] > target){
                right = middle - 1;
            }else if (nums2[middle] < target){
                left = middle + 1;
            }else {
                return true;
            }
        }
        return false;
    }
}
