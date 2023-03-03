package nuaa.zk.day;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingFileNamesUniqueLC_1487 {
    public String[] getFolderNames(String[] names) {
        int len = names.length;
        String[] res = new String[len];
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int minFileSuffixNum = 0;
            String tempName = names[i];
            if (map.containsKey(tempName)){
                minFileSuffixNum = map.get(tempName);
                tempName = tempName + "(" + minFileSuffixNum + ")";
                while (map.containsKey(tempName)){
                    minFileSuffixNum++;
                    int end = tempName.lastIndexOf('(');
                    tempName = tempName.substring(0,end) + "(" + minFileSuffixNum + ")";
                }
                //这里一定要注意，新的name下一个minNum都是1，但是要更新老的name对应的min
                map.put(names[i],minFileSuffixNum + 1);
            }

            res[i] = tempName;
            map.put(tempName,1);
        }
        return res;
    }

    @Test
    public void test(){
        String[] names = {"gta","gta(1)","gta","avalon"};
        String[] ans = getFolderNames(names);
        for (String t : ans) {
            System.out.println(t);
        }
    }

}
