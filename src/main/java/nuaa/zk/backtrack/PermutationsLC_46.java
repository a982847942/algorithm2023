package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    /**
     *用什么办法可以去掉used数组？
     */
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

    public List<List<Integer>> permute1_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            temp.add(nums[i]);
        }
        dfs2(0,temp,res,len);
        return res;
    }

    /*
    去掉used数组,想象一下将temp分为  0...first...len
    first代表没有选择的第一个数  每次选择i后将其与first交换位置，然后first + 1进入下一轮遍历
    比如 2 3,1(first) 4 5 选择5 则交换后 2 3 5 4(first) 1不影响后续选择
    值得注意的是求得的结果不是有序的
     */
    private void dfs2(int first, List<Integer> temp, List<List<Integer>> res,int len) {
        if (first == len){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = first; i < len; i++) {
            //选择i
            Collections.swap(temp,first,i);
            dfs2(first + 1,temp,res,len);
            Collections.swap(temp,first,i);
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
    // TODO: 2023/2/27 看看这几种不同实现的参数含义

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
