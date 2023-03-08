package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/8 10:35
 */
public class ReverseStringIILC_541 {
    public String reverseStr(String s, int k) {
        int len = s.length();
        int index = 0;
        StringBuilder res = new StringBuilder();
        while (index + 2 * k - 1 < len) {
            res.append(reverse(s.substring(index, index + k)));
            res.append(s.substring(index + k,index + 2 * k));
            index += 2 * k;
        }
        if (index + k - 1 < len) {
            res.append(reverse(s.substring(index, index + k)));
            res.append(s.substring(index + k, len));
        } else {
            res.append(reverse(s.substring(index, len)));
        }
        return res.toString();
    }

    private String reverse(String substring) {
        char[] arr = substring.toCharArray();
        int right = substring.length() - 1;
        for (int left = 0; left <= right; left++, right--) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        return new String(arr);
    }

    public String reverseStr2(String s, int k) {
        char[] ch = s.toCharArray();
        for(int i = 0;i < ch.length;i += 2 * k){
            int start = i;
            // 判断尾数够不够k个来取决end指针的位置
            int end = Math.min(ch.length - 1,start + k - 1);
            while(start < end){

                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;

                start++;
                end--;
            }
        }
        return new String(ch);
    }

    @Test
    public void test() {
        String s = "abcd";
        int k = 2;
        String res = reverseStr(s, k);
        System.out.println(res);
    }
}
