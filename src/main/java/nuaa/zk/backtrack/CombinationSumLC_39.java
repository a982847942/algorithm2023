package nuaa.zk.backtrack;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname CombinationSumLC_39
 * @Description
 * @Date 2023/2/27 21:21
 * @Created by brain
 */
public class CombinationSumLC_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, 0, candidates, res, target, new ArrayList<Integer>());
        return res;
    }

    /**
     * 这个解法应该算是从结果的角度来考虑dfs的遍历顺序
     *index代表当前使用的数在candidates中的下标
     * sum是当前的和
     * temp保存了当前选用的数字
     * 这里其实sum和target可以合并为一个变量
     */
    private void dfs(int index, int sum, int[] candidates, List<List<Integer>> res, int target, ArrayList<Integer> temp) {
        if (sum > target) return;
        if (sum == target){
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index == candidates.length) return;
        int cur = candidates[index];
        //考虑具体某个数时 直接枚举玩该数能出现的所有次数
        for (int i = 0; i * cur <= target; i++) {
            sum += cur * i;
            if (sum > target)break;
            for (int j = 0; j < i; j++) {
                temp.add(cur);
            }
            dfs(index + 1,sum,candidates,res,target,temp);
            sum -= cur * i;
            for (int j = 0; j < i; j++) {
                temp.remove(temp.size() - 1);
            }
        }
    }


    //从输入的角度来考虑遍历的顺序
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs2(0,res,target,candidates,new ArrayList<Integer>());
        return res;
    }

    private void dfs2(int index, List<List<Integer>> res,int target, int[] candidates, ArrayList<Integer> temp) {
        if (target == 0){
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int cur = candidates[i];
            if (target - cur < 0)break;
            temp.add(cur);
            dfs2(i,res,target - cur,candidates,temp);
            temp.remove(temp.size() - 1);
        }
    }

    // TODO: 2023/2/27 思考是否能用背包模型来解决
    @Test
    public void test(){
        int[] candidates = {2,3,5};
        int target = 8;
        List<List<Integer>> res = combinationSum(candidates, target);
        res.forEach(t->{
            System.out.println(t);
        });
    }
}
