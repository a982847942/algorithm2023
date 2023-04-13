package nuaa.zk.day;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/13 8:22
 */
public class MostFrequentEvenElementLC_2404 {
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if ((num & 1) == 0) map.put(num, map.getOrDefault(num, 0) + 1);
        }
        if (map.isEmpty()) return -1;
        int max = -1;
        for (Integer key : map.keySet()) {
            if (max == -1) max = key;
            int s = map.get(key);
            int p = map.get(max);
            if (s > p || (s == p && key < max)) max = key;
        }
        return max;
    }
    public int mostFrequentEven2(int[] nums) {
      int[] map = new int[100001];
        for (int num : nums) {
            if ((num & 1) ==0)map[num]++;
        }
        int max = 0;
        for (int i = 1; i < 100001; i++) {
            if (map[i] > map[max])max = i;
        }
        return map[max] == 0 ? -1 : max;
    }
    public int mostFrequentEven3(int[] nums) {
        int[] cnt = new int[100001];
        int ret = -1, retCnt = 0;
        for(int x: nums){
            if((x & 1) == 1) continue;
            int c = ++cnt[x];
            if(c > retCnt || c == retCnt && x < ret){
                retCnt = c;
                ret = x;
            }
        }
        return ret;
    }

    @Test
    public void test() {
        int[] arr = {0, 1, 2, 0, 0, 0, 2, 4, 4, 1};
        System.out.println(mostFrequentEven(arr));
    }
}
