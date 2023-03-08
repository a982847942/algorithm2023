package nuaa.zk.doublepointer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/8 10:29
 */
public class ReverseStringLC_344 {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    public void swap(char[] s, int index1, int index2) {
        char temp = s[index1];
        s[index1] = s[index2];
        s[index2] = temp;
    }

    @Test
    public void test() {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
