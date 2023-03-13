package nuaa.zk.binarysearch;

import java.util.*;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/13 22:23
 */
public class IntersectionOfTwoArraysIILC_350 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        List<Integer> res = new ArrayList<>();
        while (index1 < len1 && index2 < len2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {

                int t1 = index1;
                int t2 = index2;
                while (++index1 < len1 && nums1[index1] == nums1[index1 - 1]) ;
                while (++index2 < len2 && nums2[index2] == nums2[index2 - 1]) ;
                int diff = index1 - t1 > index2 - t2 ? index2 - t2 : index1 - t1;
                for (int i = 0; i < diff; i++) {
                    res.add(nums1[t1]);
                }
            }
        }
        int[] ans = new int[res.size()];
        int index = 0;
        Iterator<Integer> iterator = res.iterator();
        while (iterator.hasNext()) {
            ans[index++] = iterator.next();
        }
        return ans;
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect2(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

}
