package nuaa.zk.doublepointer;

import org.junit.Test;

import java.util.*;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/9 10:54
 */
public class ReverseWordsStringLC_151 {
    public String reverseWords(String s) {
        String str = s.trim();
        int left = 0;
        int len = str.length();
        int right = 0;
        StringBuilder res = new StringBuilder();
        while (right < len){
            if (str.charAt(right) == ' ') {
                res.append(reverse(str.substring(left, right)));
                res.append(" ");
                while (str.charAt(right++) == ' ') ;
                left =--right;
            }
            right++;
        }
        res.append(reverse(str.substring(left, len)));
        return res.reverse().toString();
    }
    public String reverse(String str){
        char[] s = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        while (left <= right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        return new String(s);
    }

    public String reverseWords2(String s) {

        int left = 0;
        int right = s.length() - 1;
        while(left <= right && s.charAt(left) == ' '){
            left++;
        }
        while(left <= right && s.charAt(right) == ' '){
            right--;
        }
        Deque<String> d = new ArrayDeque();

        StringBuilder word = new StringBuilder();
        while(left <= right){
            char c = s.charAt(left);
            if(word.length() != 0 && c == ' '){
                d.offerFirst(word.toString());
                word.setLength(0);
            }else if(c != ' '){
                word.append(c);
            }
            left++;
        }
        d.offerFirst(word.toString());
        return String.join(" ",d);
    }
    public String reverseWords3(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }



    @Test
    public void test(){
        String s = "F R  I   E    N     D      S      ";
//        "S D  N  E  I  R  F"
//        "S D N E I R F"
        System.out.println(reverseWords(s));
    }
}
