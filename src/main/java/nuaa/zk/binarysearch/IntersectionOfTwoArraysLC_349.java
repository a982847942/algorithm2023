package nuaa.zk.binarysearch;

import java.util.*;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/13 21:58
 */
public class IntersectionOfTwoArraysLC_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        Set<Integer> cache1 = new HashSet<>();
        for (int i = 0; i < len1; i++) {
            cache1.add(nums1[i]);
        }
        Set<Integer> cache2 = new HashSet<>();
        for (int i = 0; i < len2; i++) {
            cache2.add(nums2[i]);
        }
        cache1.retainAll(cache2);
        Iterator<Integer> iterator = cache1.iterator();
        int index = 0;
        int[] res = new int[cache1.size()];
        while (iterator.hasNext()) {
            res[index++] = iterator.next();
        }
        return res;
    }
    //排序 双指针
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<Integer> res = new ArrayList<>();
        while (index1 < len1 && index2 < len2){
            if (nums1[index1] < nums2[index2]){
                index1++;
            }else if (nums1[index1] > nums2[index2]){
                index2++;
            }else {
                res.add(nums1[index1]);
                while (++index1 < len1 && nums1[index1] == nums1[index1 - 1]);
                while (++index2 < len2 && nums2[index2] == nums2[index2 - 1]);
            }
        }
        int[] ans = new int[res.size()];
        int index = 0;
        Iterator<Integer> iterator = res.iterator();
        while (iterator.hasNext()){
            ans[index++] = iterator.next();
        }
        return ans;
    }

    //和上面一样
    public int[] intersection3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
