package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/9 10:25
 */
public class Offer_05 {
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') {
                res.append("%20");
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    public String replaceSpace2(String s) {
        int len = s.length();
        int spaceCount = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ')spaceCount++;
        }
        char[] res = new char[len + 2*spaceCount];
        int right = len + 2*spaceCount - 1;
        int index = len - 1;
        while (right >= 0){
            if (s.charAt(index) == ' '){
                res[right--] = '0';
                res[right--] = '2';
                res[right--] = '%';
                index--;
            }else {
                res[right--] = s.charAt(index--);
            }
        }
        return new String(res);
    }

    @Test
    public void test() {
        String s = "We are happy.";
        System.out.println(replaceSpace2(s));
    }
}
