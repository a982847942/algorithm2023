package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/4 17:49
 */
public class LetterCasePermutationLC_784 {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        dfs2(0, s, res, new StringBuilder());
        return res;
    }

    private void dfs(int index, String s, List<String> res, StringBuilder temp) {
        if (index == s.length()) {
            res.add(new String(temp));
            return;
        }
        char c = s.charAt(index);
        if (Character.isDigit(c)) {
            temp.append(c);
            dfs(index + 1, s, res, temp);
            temp.deleteCharAt(temp.length() - 1);
        } else {
            //不选
            temp.append(c);
            dfs(index + 1, s, res, temp);
            temp.deleteCharAt(temp.length() - 1);
            //选
            if (Character.isUpperCase(c)) {
                temp.append(Character.toLowerCase(c));
                dfs(index + 1, s, res, temp);
                temp.deleteCharAt(temp.length() - 1);
            } else {
                temp.append(Character.toUpperCase(c));
                dfs(index + 1, s, res, temp);
                temp.deleteCharAt(temp.length() - 1);
            }


        }
    }

    /*
    大写字母 A~Z 的 ASCII 码范围为 [65, 90]；
    小写字母 a~z 的 ASCII 码范围为 [97, 122]；
    65 + 32 = 01000001 + 00100000 = 01100001，即 97。 总结：
    大变小，小变大（ 大写变小写、小写变大写 ）: 字符 ^= 32
    大变小 （大写变小写、小写变小写 ）: 字符 |= 32
    小变大 （小写变大写、大写变大写 ）: 字符 &= -33
     */
    private void dfs2(int index, String s, List<String> res, StringBuilder temp) {
        if (index == s.length()) {
            res.add(new String(temp));
            return;
        }
        char c = s.charAt(index);
        temp.append(c);
        dfs2(index + 1, s, res, temp);
        temp.deleteCharAt(temp.length() - 1);
        if (Character.isLetter(c)) {
            temp.append((char) (c ^ (1 << 5)));
            dfs2(index + 1, s, res, temp);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    /**
     * 从结果角度考虑,结果长度固定
     * @param str
     * @return
     */
    public List<String> letterCasePermutation2(String str) {
        List<String> res = new ArrayList<>();
        char[] s = str.toCharArray();
        dfs3(0, s, res);
        return res;
    }

    private void dfs3(int index, char[] s, List<String> res) {
        //该位置未更改，直接加入
        res.add(new String(s));
        //判断是否可以更改
        for (int i = index; i < s.length; i++) {
            if (s[i] >= 'A'){
                s[i] ^= 32;
                //更改后往下遍历
                dfs3(i + 1,s,res);
                //恢复现场 即该位置不更改继续遍历下一次
                s[i] ^= 32;
            }
        }
    }

    /*
    共有m个字符  实际方案数就是1 << m种
    但本题要求具体的内容，因此还需要枚举出每一种方案的内容
    m的第k位为1，代表第k个字符进行翻转
     */
    public List<String> letterCasePermutation3(String str) {
        List<String> ans = new ArrayList<>();
        int charCount = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (Character.isLetter(str.charAt(i))) charCount++;
        }
        for (int i = 0; i < (1 << charCount); i++) {
            char[] temp = str.toCharArray();
            for (int j = 0,k = 0; j < len; j++) {
                if (!Character.isLetter(temp[j]))continue;
                //查看改为是否为1
                temp[j] = ((i >> k) & 1) == 1 ? (char) (temp[j] ^ 32) : temp[j];
                k++;
            }
            ans.add(String.valueOf(temp));
        }
        return ans;
    }
    //广度优先遍历
    public List<String> letterCasePermutation4(String s) {
        List<String> ans = new ArrayList<String>();
        Queue<StringBuilder> queue = new ArrayDeque<StringBuilder>();
        queue.offer(new StringBuilder());
        while (!queue.isEmpty()) {
            StringBuilder curr = queue.peek();
            if (curr.length() == s.length()) {
                ans.add(curr.toString());
                queue.poll();
            } else {
                int pos = curr.length();
                if (Character.isLetter(s.charAt(pos))) {
                    StringBuilder next = new StringBuilder(curr);
                    next.append((char) (s.charAt(pos) ^ 32));
                    queue.offer(next);
                }
                curr.append(s.charAt(pos));
            }
        }
        return ans;
    }

    @Test
    public void test() {
        String s = "3z4";
        List<String> res = letterCasePermutation2(s);
        res.forEach(t -> {
            System.out.println(t);
        });
    }
}
