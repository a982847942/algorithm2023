package nuaa.zk.day;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/6 8:43
 */
public class MakeStringBalancedLC_1653 {
    public int minimumDeletions(String s) {
        int n = s.length();
        int countA = 0;
        for (int i = 0; i < n; i++) {
            countA += s.charAt(i) == 'a' ? 1 : 0;
        }
        int deleteA = countA;
        int deleteB = 0;
        int curDelete = deleteB + deleteA;
        int min = deleteA + deleteB;
        for (int i = 0; i < n; i++) {
//            char temp = s.charAt(i);
//            if (temp == 'a'){
//                deleteA--;
//            }else {
//                deleteB++;
//            }
            //a -> -1     b -> +1
            curDelete += (s.charAt(i) - 'a') * 2 - 1;
//            min = Math.min(deleteA + deleteB, min);
            min = Math.min(curDelete, min);
        }
        return min;
    }

    public int minimumDeletions2(String s) {
        int n = s.length();
        int countA = 0;
        char[] str = s.toCharArray();
        for (char c : str) {
            countA += 'b' - c;
        }
        int curDelete = countA;
        int min = countA;
        for (char c : str) {
            curDelete += (c - 'a') * 2 - 1;
            min = Math.min(curDelete, min);
        }
        return min;
    }

    /**
     * 动态规划
     * 当前为b 则不用管
     * 否则 1.删除a  则f[i] = f[i - 1] + 1
     *      2.保留a  则f[i] = cntB(前面b的数量)
     *      f[i] = min(1,2)
     */
    public int minimumDeletions3(String s) {
        int f = 0, cntB = 0;
        for (char c : s.toCharArray())
            if (c == 'b') ++cntB; // f 值不变
            else f = Math.min(f + 1, cntB);
        return f;
    }

    @Test
    public void test() {
        String s = "bbaaaaabb";
        System.out.println(minimumDeletions2(s));
    }
}
