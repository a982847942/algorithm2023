package nuaa.zk.day;

import org.junit.Test;

/**
 * @Classname MinimumSwaps
 * @Description
 * @Date 2023/2/25 19:44
 * @Created by brain
 */
public class MinimumSwapsLC_1247 {

    public int minimumSwap(String s1, String s2) {
        int xy = 0;
        int yx = 0;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            char t1 = s1.charAt(i);
            char t2 = s2.charAt(i);
            if (t1 != t2){
                if (t1 == 'x' && t2 == 'y'){
                    xy++;
                }else {
                    yx++;
                }
            }
        }
        int total = xy + yx;
        return (total & 1) != 0 ? -1 : ((xy & 1) == 0 ? total / 2 : total / 2 + 1);
    }

    public int minimumSwap2(String s1, String s2) {
        int[] cnt = new int[2];
        for (int i = 0, n = s1.length(); i < n; ++i)
            if (s1.charAt(i) != s2.charAt(i))
                ++cnt[s1.charAt(i) % 2]; // x 和 y ASCII 值的二进制最低位不同
        int d = cnt[0] + cnt[1];
        return d % 2 != 0 ? -1 : d / 2 + cnt[0] % 2;
    }


    @Test
    public void test(){
        String s1 = "xy", s2 = "yx";
        System.out.println(minimumSwap(s1, s2));
    }
}
