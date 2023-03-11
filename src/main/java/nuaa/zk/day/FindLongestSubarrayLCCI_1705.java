package nuaa.zk.day;

import org.junit.Test;
import org.omg.CORBA.CharSeqHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/11 9:20
 */
public class FindLongestSubarrayLCCI_1705 {
    public String[] findLongestSubarray(String[] array) {
        int len = array.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(array[i].charAt(0))) {
                temp[i] = 1;
            } else {
                temp[i] = -1;
            }
        }
        int max = Integer.MIN_VALUE;
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += temp[i];
            if (map.containsKey(sum)) {
                int cur = i - map.get(sum);
                if (cur > max) {
                    max = cur;
                    left = map.get(sum) + 1;
                }
            } else {
                map.put(sum, i);
            }
        }
        if (max == Integer.MIN_VALUE) return new String[0];
        String[] res = new String[max];
        for (int i = 0; i < max; i++) {
            res[i] = array[left + i];
        }
//        System.out.println(max);
        return res;
    }

    public String[] findLongestSubarray2(String[] array) {
        int n = array.length, begin = 0, end = 0, s = n;
        int[] first = new int[n * 2 + 1];
        Arrays.fill(first, -1); // 注：去掉可以再快 1ms（需要 s 下标改从 1 开始）
        first[s] = 0; // s[0] = 0
        for (int i = 1; i <= n; ++i) {
            s += (array[i - 1].charAt(0) >> 6 & 1) * 2 - 1;
            int j = first[s];
            if (j < 0)
                first[s] = i;
            else if (i - j > end - begin) {
                begin = j;
                end = i;
            }
        }
        String[] sub = new String[end - begin];
        System.arraycopy(array, begin, sub, 0, sub.length);
        return sub;
    }

    public String[] findLongestSubarray3(String[] array) {
        int n = array.length;
        int[] s = new int[n + 1]; // 前缀和
        for (int i = 0; i < n; ++i)
            s[i + 1] = s[i] + (array[i].charAt(0) >> 6 & 1) * 2 - 1;

        int begin = 0, end = 0; // 符合要求的子数组 [begin,end)
        Map<Integer, Integer> first = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; ++i) {
            int j = first.getOrDefault(s[i], -1);
            if (j < 0) // 首次遇到 s[i]
                first.put(s[i], i);
            else if (i - j > end - begin) { // 更长的子数组
                begin = j;
                end = i;
            }
        }

        String[] sub = new String[end - begin];
        System.arraycopy(array, begin, sub, 0, sub.length);
        return sub;
    }


    @Test
    public void test() {
        String[] arr = {"A", "A"};
//        for (String s : arr) {
//            System.out.println(s.charAt(0));
//        }
        System.out.println(Arrays.toString(findLongestSubarray(arr)));
    }
}
