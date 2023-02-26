package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname LetterCombinationsPhoneNumberLC_17
 * @Description
 * @Date 2023/2/26 10:01
 * @Created by brain
 */
public class LetterCombinationsPhoneNumberLC_17 {
    String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    ArrayList<String> res = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)return new ArrayList<String>();
        dfs(0,digits);
        return res;
    }

    private void dfs(int index, String digits) {
        if (index == digits.length()){
            res.add(new String(temp));
            return;
        }

        //只能选
        String str = mapping[digits.charAt(index) - '0'];
        for (char c : str.toCharArray()) {
            temp.append(c);
            dfs(index + 1,digits);
            /**
             * 这里需要 回溯
             * 其实这个问题中每次必然会选择一个字母，而最后字母的长度必然是digits.length()
             * 可以采用char[digits.length()] 数组来保存，在遍历index时 char[index]更改此处字符
             * 这样不需要回溯！
             */
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    @Test
    public void test(){
        String digits = "";
        List<String> res = letterCombinations(digits);
        for (String re : res) {
            System.out.println(re);
        }
    }
}
