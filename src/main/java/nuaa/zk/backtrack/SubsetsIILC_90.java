package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname SubsetsIILC_90
 * @Description
 * @Date 2023/2/28 21:04
 * @Created by brain
 */
public class SubsetsIILC_90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        dfs(0, nums, res, new ArrayList<Integer>(), used);
        return res;
    }

    //从输入角度考虑
    private void dfs(int index, int[] nums, List<List<Integer>> res, ArrayList<Integer> temp, boolean[] used) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        //不要
        dfs(index + 1, nums, res, temp, used);
        //要
        if (index != 0 && nums[index] == nums[index - 1] && !used[index - 1]) return;
        temp.add(nums[index]);
        used[index] = true;
        dfs(index + 1, nums, res, temp, used);
        used[index] = false;
        temp.remove(temp.size() - 1);
    }

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs2(0, res, nums, new ArrayList<Integer>());
        return res;
    }

    //从结果角度考虑
    private void dfs2(int index, List<List<Integer>> res, int[] nums, ArrayList<Integer> temp) {

        res.add(new ArrayList<>(temp));
        if (index == nums.length) return;
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1])continue;
            temp.add(nums[i]);
            dfs2(i + 1,res,nums,temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {
        int[] nums = {1,2,2};
        List<List<Integer>> res = subsetsWithDup2(nums);
        res.forEach(t -> {
            System.out.println(t);
        });
    }
}
