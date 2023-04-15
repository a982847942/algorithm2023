package nuaa.zk.day;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/15 15:12
 */
public class CamelcaseMatchingLC_1023 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n = queries.length;
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            int p = 0;
            boolean flag = true;
            for (int j = 0; j < queries[i].length(); j++) {
                char c = queries[i].charAt(j);
                if (p < pattern.length() && c == pattern.charAt(p)) {
                    p++;
                } else if (Character.isUpperCase(c)) {
                    flag = false;
                    break;
                }
            }
            if (p < pattern.length()) flag = false;
            res.add(flag);
        }
        return res;
    }
}
