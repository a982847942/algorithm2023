package nuaa.zk.day;

import org.junit.Test;

import java.util.*;

/**
 * @Classname MergeSimilarItemsLC_2363
 * @Description 
 * @Date 2023/2/28 9:25
 * @Created by brain
 */
public class MergeSimilarItemsLC_2363 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer,Integer> map = new TreeMap<>();
        for (int[] temp : items1) {
            map.put(temp[0],map.getOrDefault(temp[0],0) + temp[1]);
        }
        for (int[] temp : items2) {
            map.put(temp[0],map.getOrDefault(temp[0],0) + temp[1]);
        }
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> keys = map.keySet();
        keys.forEach(key -> {
            List<Integer> cur = new ArrayList<>();
            cur.add(key);
            cur.add(map.get(key));
            res.add(cur);
        });
        return res;
    }

    @Test
    public void test(){
        int[][] items1 = {{1,1},{4,5},{3,8}};
        int[][] items2 = {{3,1},{1,5}};
        List<List<Integer>> res = mergeSimilarItems(items1, items2);
        res.forEach(t->{
            System.out.println(t);
        });
    }

    //本体数据量一直 长度不超过1000  这种思路和上面和Treemao基本一样，只不过用数组模拟了hash表
    public List<List<Integer>> mergeSimilarItems2(int[][] items1, int[][] items2) {
        int[] cnt = new int[1001];
        for (int[] temp : items1) {
            cnt[temp[0]] += temp[1];
        }
        for (int[] temp : items2) {
            cnt[temp[0]] += temp[1];
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0;i < cnt.length;i++) {
            if (cnt[i] > 0){
                ans.add(Arrays.asList(i,cnt[i]));
            }
        }
        return ans;
    }
    //双指针
    public List<List<Integer>> mergeSimilarItems3(int[][] items1, int[][] items2) {
        Arrays.sort(items1,(t1,t2)->{
            return t1[0] - t2[0];
        });
        Arrays.sort(items2,(t1,t2) ->{
            return t1[0] - t2[0];
        });

        int cur1 = 0;
        int cur2 = 0;
        int len1 = items1.length;
        int len2 = items2.length;
        List<List<Integer>> res = new ArrayList<>();
        while (cur1 < len1 && cur2 < len2){
            int[] temp1 = items1[cur1];
            int[] temp2 = items2[cur2];
            if (temp1[0] == temp2[0]){
                res.add(Arrays.asList(temp1[0],temp1[1] + temp2[1]));
                cur1++;
                cur2++;
            }else if (temp1[0] < temp2[0]){
                res.add(Arrays.asList(temp1[0],temp1[1]));
                cur1++;
            }else {
                res.add(Arrays.asList(temp2[0],temp2[1]));
                cur2++;
            }
        }
        while (cur1 < len1){
            res.add(Arrays.asList(items1[cur1][0],items1[cur1][1]));
            cur1++;
        }
        while (cur2 < len2){
            res.add(Arrays.asList(items2[cur2][0],items2[cur2][1]));
            cur2++;
        }
        return res;
    }
}
