package nuaa.zk.day;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/9 9:42
 */
public class CheckDistancesBetweenSameLettersLC_2399 {
    //时间复杂度O(n2)
    public boolean checkDistances(String s, int[] distance) {
        int len = distance.length;
        for (int i = 0; i < len; i++) {
            int d = distance[i];
            int start = s.indexOf(i + 97);
            if (start != -1) {
                int end = s.indexOf(i + 97, start + 1);
                if (end - start - 1 != d) {
                    return false;
                }
            }
        }
        return true;
    }

    //借助hash表 时间复杂度O(n)
    public boolean checkDistances2(String s, int[] distance) {
        int[] d = new int[26];
        for (int i = 1, n = s.length(); i <= n; ++i) {
            int j = s.charAt(i - 1) - 'a';
            //d[j] > 0 代表已经出现过一次，这是第二次
            if (d[j] > 0 && i - d[j] - 1 != distance[j]) {
                return false;
            }
            d[j] = i;
        }
        return true;
    }

    //上面的比较难理解，这个是直接处理后再遍历
    public boolean checkDistances3(String s, int[] distance) {
        int[] d = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            d[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < len; i++) {
            if (d[s.charAt(i) - 'a'] != i && (d[s.charAt(i) - 'a'] - i  - 1 != distance[s.charAt(i) - 'a']))return false;
        }
        return true;
    }


    @Test
    public void test() {
        String s = "abaccb";
        int[] distance = {1, 3, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(checkDistances3(s, distance));
    }
}
