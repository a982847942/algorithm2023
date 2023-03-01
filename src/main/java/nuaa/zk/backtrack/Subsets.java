package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Subsets
 * @Description
 * @Date 2023/2/28 20:43
 * @Created by brain
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//        res.add(new ArrayList<Integer>());
        dfs(0,res,nums,new ArrayList<Integer>());
        return res;
    }

    //从输入来看
    private void dfs(int index, List<List<Integer>> res, int[] nums, ArrayList<Integer> temp) {
        if (index == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }

        //不要
        dfs(index + 1,res,nums,temp);
        //要
        temp.add(nums[index]);

        dfs(index + 1,res,nums,temp);
        temp.remove(temp.size() - 1);
    }

    //从结果过来看
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs2(0,res,nums,new ArrayList<Integer>());
        return res;
    }

    private void dfs2(int index, List<List<Integer>> res, int[] nums, ArrayList<Integer> temp) {
        res.add(new ArrayList<>(temp));
        if (index == nums.length) return;

        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs2(i + 1,res,nums,temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test(){
        int[] nums = {1,2,3};
        List<List<Integer>> res = subsets2(nums);
        res.forEach(t->{
            System.out.println(t);
        });
    }
}
