package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname PermutationsIILC_47
 * @Description
 * @Date 2023/2/27 8:51
 * @Created by brain
 */
public class PermutationsIILC_47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(0,nums,used,res,new ArrayList<Integer>());
        return res;
    }

    private void dfs(int index, int[] nums, boolean[] used, List<List<Integer>> res, ArrayList<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i != 0 && nums[i] == nums[i - 1] && !used[i - 1])continue;
            if (!used[i]){
                used[i] = true;
                temp.add(nums[i]);
                dfs(index + 1,nums,used,res,temp);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }

    @Test
    public void test(){
        int[] nums = {1,2,3};
        List<List<Integer>> res = permuteUnique(nums);
        res.forEach(t->{
            System.out.println(t);
        });
    }
}
