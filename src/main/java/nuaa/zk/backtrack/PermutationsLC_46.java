package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname PermutationsLC_46
 * @Description
 * @Date 2023/2/26 22:20
 * @Created by brain
 */
public class PermutationsLC_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(0,res,nums,used,new ArrayList<Integer>());
        return res;
    }

    private void dfs(int index, List<List<Integer>> res,int[] nums, boolean[] used, ArrayList<Integer> temp) {
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                temp.add(nums[i]);
                dfs(index + 1, res, nums, used, temp);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }

    @Test
    public void test(){
        int[] nums = {1,2,3};
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> list : permute) {
            System.out.print(list + ",");
        }
        System.out.println();
    }

    private int[] nums;
    private List<Integer> path;
    private boolean[] onPath;
    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute2(int[] nums) {
        this.nums = nums;
        path = Arrays.asList(new Integer[nums.length]);
        onPath = new boolean[nums.length];
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < nums.length; ++j) {
            if (!onPath[j]) {
                path.set(i, nums[j]);
                onPath[j] = true;
                dfs(i + 1);
                onPath[j] = false; // 恢复现场
            }
        }
    }

    public List<List<Integer>> permute3(int[] nums) {
        if (nums == null || nums.length == 0)return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backTracking(nums,0,res,temp);
        return res;
    }

    private void backTracking(int[] nums, int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }else {
            for (int i = index; i < nums.length; i++) {
                temp.add(nums[i]);
                swap(nums,index,i);
                backTracking(nums,index + 1,res,temp);
                temp.remove(temp.size() - 1);
                swap(nums,index,i);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
