package nuaa.zk.backtrack;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname CombinationSumIILC_40
 * @Description
 * @Date 2023/2/27 21:55
 * @Created by brain
 */
public class CombinationSumIILC_40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[candidates.length];
        dfs(0, target, candidates, res, used, new ArrayList<Integer>());
        return res;
    }

    //从输入考虑  决定当前输入要还是不要
    private void dfs(int index, int target, int[] candidates, List<List<Integer>> res, boolean[] used, ArrayList<Integer> temp) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index == candidates.length) return;

        //不用
        dfs(index + 1, target, candidates, res, used, temp);
        //用
        if (index != 0 && candidates[index] == candidates[index - 1] && !used[index - 1])return;
        int cur = candidates[index];
        if (target - cur < 0)return;
        temp.add(cur);
        used[index]  = true;
        dfs(index + 1,target - cur,candidates,res,used,temp);
        used[index] = false;
        temp.remove(temp.size() - 1);
    }

    @Test
    public void test(){
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        List<List<Integer>> res = combinationSum2(candidates, target);
        res.forEach(t->{
            System.out.println(t);
        });
    }

    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        dfs2(0,candidates,target,res,new ArrayList<Integer>());
        return res;
    }

    //从结果考虑，每一次直接枚举结果可能的前缀
    private void dfs2(int index, int[] candidates, int target, List<List<Integer>> res, ArrayList<Integer> temp) {
        if (target == 0){
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < candidates.length ; i++) {
            int cur = candidates[i];
            if (i != index && cur == candidates[i - 1])continue;
            if (target - cur < 0)break;
            temp.add(cur);
            dfs2(i + 1,candidates,target - cur,res,temp);
            temp.remove(temp.size() - 1);
        }
    }


}
