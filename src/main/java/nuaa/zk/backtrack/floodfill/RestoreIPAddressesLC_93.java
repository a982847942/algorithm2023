package nuaa.zk.backtrack.floodfill;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RestoreIPAddressesLC_93
 * @Description
 * @Date 2023/3/1 9:26
 * @Created by brain
 */
public class RestoreIPAddressesLC_93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return res;
        dfs(0,0,s,res,new StringBuilder());
        return res;
    }

    //枚举结果 ，枚举输入也可以但是在这个问题上比较麻烦
    private void dfs(int index,int curIndex, String s, List<String> res, StringBuilder temp) {
        if (index == 4){
            if (curIndex == s.length()){
                res.add(new String(temp));
            }
            return;
        }
        if (curIndex >= s.length()) return;

        char c = s.charAt(curIndex);
        if (c == '0'){
            temp.append(c);
            if (index != 3)temp.append(".");
            dfs(index + 1,curIndex + 1,s,res,temp);
            if (index != 3)temp.deleteCharAt(temp.length() - 1);
            temp.deleteCharAt(temp.length() - 1);
        } else{
            for (int i = 0; i < 3 && (curIndex + i < s.length()); i++) {
                String curSub = s.substring(curIndex, curIndex + i + 1);
                if (isLess255(curSub)){
                    temp.append(curSub);
                    if (index != 3)temp.append(".");
                    dfs(index + 1,curIndex + i + 1,s,res,temp);
                    if (index != 3)temp.deleteCharAt(temp.length() - 1);
                    temp.delete(curIndex + index,curIndex + index + i +1);
                }
            }
        }
    }

    private boolean isLess255(String curSub) {
        char[] temp = curSub.toCharArray();
        int num = 0;
        for (char c : temp) {
            num = num * 10 + (c - '0');
        }
        return num <= 255;
    }

    @Test
    public void test(){
        String s = "101023";
        List<String> ans = restoreIpAddresses(s);
        for (String t : ans) {
            System.out.println(t);
        }
    }
}
