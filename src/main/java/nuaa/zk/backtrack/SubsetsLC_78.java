package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname Subsets
 * @Description
 * @Date 2023/2/26 10:21
 * @Created by brain
 */
public class SubsetsLC_78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        dfs(0,nums,res,new ArrayList<Integer>());
        return res;
    }

    //枚举输入,对每一个nums[index]选择要或不要
    private void dfs(int index, int[] nums, List<List<Integer>> res, ArrayList<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        //不选
        dfs(index + 1,nums,res,temp);
        //选
        temp.add(nums[index]);
        dfs(index + 1,nums,res,temp);
        //回溯
        temp.remove(temp.size() - 1);
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        dfs2(0,nums,res,new ArrayList<Integer>());
        return res;
    }

    //，枚举结果 
    private void dfs2(int index, int[] nums, List<List<Integer>> res, ArrayList<Integer> temp) {
        res.add(new ArrayList<>(temp));
        if (index == nums.length)return;

        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs2(i + 1,nums,res,temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test(){
        int[] nums = {1,2,3};
        List<List<Integer>> res = subsets2(nums);
        for (List<Integer> t : res) {
            t.forEach(temp ->{
                System.out.print(temp + " ");
            });
            System.out.println();
        }
    }
}
