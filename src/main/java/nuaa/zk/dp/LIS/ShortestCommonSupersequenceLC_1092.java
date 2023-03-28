package nuaa.zk.dp.LIS;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/28 9:02
 */
public class ShortestCommonSupersequenceLC_1092 {
    //暴力递归
    public String shortestCommonSupersequence(String str1, String str2) {
        if (str1.isEmpty()) return str2;
        if (str2.isEmpty()) return str1;
        char c1 = str1.charAt(str1.length() - 1);
        char c2 = str2.charAt(str2.length() - 1);
        String cur1 = str1.substring(0, str1.length() - 1);
        String cur2 = str2.substring(0, str2.length() - 1);
        if (c1 == c2) {
            return shortestCommonSupersequence(cur1, cur2) + c1;
        }
        String ans1 = shortestCommonSupersequence(cur1, str2);
        String ans2 = shortestCommonSupersequence(str1, cur2);
        if (ans1.length() > ans2.length()) {
            return ans2 + c2;
        }
        return ans1 + c1;
    }

    //记忆化搜索  超过内存限制了
    String[][] cache;
    String s, t;

    public String shortestCommonSupersequence2(String str1, String str2) {
        s = str1;
        t = str2;
        cache = new String[s.length()][t.length()];
        return dfs1(str1.length() - 1, str2.length() - 1);
    }

    private String dfs1(int index1, int index2) {
        if (index1 < 0) return t.substring(0, index2 + 1);
        if (index2 < 0) return s.substring(0, index1 + 1);
        if (cache[index1][index2] != null) return cache[index1][index2];
        char c1 = s.charAt(index1);
        char c2 = t.charAt(index2);
        if (c1 == c2) {
            //状态计算的时间复杂度主要在这里(拼接字符串)
            return cache[index1][index2] = dfs1(index1 - 1, index2 - 1) + c1;
        }
        String ans1 = dfs1(index1 - 1, index2);
        String ans2 = dfs1(index1, index2 - 1);
        if (ans1.length() > ans2.length()) {
            return cache[index1][index2] = ans2 + c2;
        }
        return cache[index1][index2] = ans1 + c1;
    }

    String s1, t1;
    int[][] memory;

    public String shortestCommonSupersequence3(String str1, String str2) {
        s1 = str1;
        t1 = str2;
        memory = new int[s1.length()][t1.length()];
        return makeAs(s1.length() - 1, t1.length() - 1);
    }

    private String makeAs(int index1, int index2) {
        if (index1 < 0) return t1.substring(0, index2 + 1);
        if (index2 < 0) return s1.substring(0, index1 + 1);
        if (s1.charAt(index1) == t1.charAt(index2)) return makeAs(index1 - 1, index2 - 1) + s1.charAt(index1);
        if ((dfs(index1 - 1, index2) + 1) == dfs(index1, index2)) {
            return makeAs(index1 - 1, index2) + s1.charAt(index1);
        }
        return makeAs(index1, index2 - 1) + t1.charAt(index2);
    }

    private int dfs(int i, int j) {
        if (i < 0) return j + 1;
        if (j < 0) return i + 1;
        if (memory[i][j] != 0) return memory[i][j];
        if (s1.charAt(i) == t1.charAt(j)) return memory[i][j] = dfs(i - 1, j - 1) + 1;
        return memory[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - 1)) + 1;
    }


    public String shortestCommonSupersequence4(String str1, String str2) {
        // f[i+1][j+1] 表示 s 的前 i 个字母和 t 的前 j 个字母的最短公共超序列的长度
        char[] s = str1.toCharArray(), t = str2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int j = 1; j < m; ++j) f[0][j] = j; // 递归边界
        for (int i = 1; i < n; ++i) f[i][0] = i; // 递归边界
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                if (s[i] == t[j]) // 最短公共超序列一定包含 s[i]
                    f[i + 1][j + 1] = f[i][j] + 1;
                else // 取更短的组成答案
                    f[i + 1][j + 1] = Math.min(f[i][j + 1], f[i + 1][j]) + 1;

        int na = f[n][m];
        char[] ans = new char[na];
        for (int i = n - 1, j = m - 1, k = na - 1; ; ) {
            if (i < 0) { // s 是空串，剩余的 t 就是最短公共超序列
                System.arraycopy(t, 0, ans, 0, j + 1);
                break; // 相当于递归边界
            }
            if (j < 0) { // t 是空串，剩余的 s 就是最短公共超序列
                System.arraycopy(s, 0, ans, 0, i + 1);
                break; // 相当于递归边界
            }
            if (s[i] == t[j]) { // 公共超序列一定包含 s[i]
                ans[k--] = s[i--]; // 倒着填 ans
                --j; // 相当于继续递归 makeAns(i - 1, j - 1)
            } else if (f[i + 1][j + 1] == f[i][j + 1] + 1)
                ans[k--] += s[i--]; // 相当于继续递归 makeAns(i - 1, j)
            else
                ans[k--] += t[j--]; // 相当于继续递归 makeAns(i, j - 1)
        }
        return new String(ans);
    }

    //LCS记录状态转移 倒推即可
    public String shortestCommonSupersequence5(String str1, String str2) {
        char c1[]=str1.toCharArray(),c2[]=str2.toCharArray();
        int sub[][][]=new int[c1.length+1][c2.length+1][2];//位置2：0从斜向转移过来，1：i方向，2：j方向
        for(int i=1;i<=c1.length;i++){
            for(int j=1;j<=c2.length;j++){
                if(c1[i-1]==c2[j-1]){sub[i][j][0]=1+sub[i-1][j-1][0];}
                else{
                    if(sub[i-1][j][0]>=sub[i][j-1][0]){
                        sub[i][j][0]=sub[i-1][j][0];
                        sub[i][j][1]=1;
                    }
                    else{
                        sub[i][j][0]=sub[i][j-1][0];
                        sub[i][j][1]=2;
                    }
                }
            }
        }
        int p1=c1.length,p2=c2.length;
        StringBuilder ans=new StringBuilder();
        while(true){
            if(p1==0&&p2==0){break;}
            else if(p1==0){
                for(;p2>0;p2--){ans.append(c2[p2-1]);}
                break;
            }
            else if(p2==0){
                for(;p1>0;p1--){ans.append(c1[p1-1]);}
                break;
            }
            else{
                if(sub[p1][p2][1]==0){
                    ans.append(c1[p1-1]);
                    p1--;
                    p2--;
                }
                else if(sub[p1][p2][1]==1){
                    ans.append(c1[p1-1]);
                    p1--;
                }
                else{
                    ans.append(c2[p2-1]);
                    p2--;
                }
            }
        }
        return ans.reverse().toString();
    }
    @Test
    public void test() {
        String str1 = "abac", str2 = "cab";
        System.out.println(shortestCommonSupersequence(str1, str2));
    }
}
