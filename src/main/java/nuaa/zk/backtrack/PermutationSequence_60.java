package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname PermutationSequence_60
 * @Description
 * @Date 2023/2/28 21:58
 * @Created by brain
 */
public class PermutationSequence_60 {
    public String getPermutation(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[n + 1];
        dfs(0, n, k, used, res, new ArrayList<Integer>());
        List<Integer> temp = res.get(res.size() - 1);
        StringBuilder ans = new StringBuilder();
        temp.forEach(t -> {
            ans.append(t);
        });
        return ans.toString();
    }

    private void dfs(int index, int n, int k, boolean[] used, List<List<Integer>> res, ArrayList<Integer> temp) {
        if (index == n) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                temp.add(i);
                dfs(index + 1, n, k, used, res, temp);
                if (res.size() == k) return;
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * dfs最终版本
     * 1.预处理阶乘数组
     * 2.理解为什么不用传统dfs的保护现场
     * 3.利用阶乘数组避免逐个枚举全排列
     */
    private int[] factorial;
    public String getPermutation3(int n, int k) {
        StringBuilder res = new StringBuilder();
        boolean[] used = new boolean[n + 1];
        calculateFactorial2(n);
        dfs3(0,n,k,res,used);
        return res.toString();
    }

    private void dfs3(int index, int n, int k, StringBuilder res, boolean[] used) {
        if (index == n){
            return;
        }
        int temp = factorial[n - index - 1];
        for (int i = 1; i <= n; i++) {

            if (!used[i]){
                if (k - temp > 0){
                    k -= temp;
                    continue;
                }
                res.append(i);
                used[i] = true;
                dfs3(index + 1,n,k,res,used);
                //不用保护现场
                return;//不加return不会影响结果，但是找到结果后会继续向后查找，不过由于if (k - temp > 0)的判断导致结果不变。
            }
        }
    }

    private void calculateFactorial2(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }



    @Test
    public void test() {
        int n = 3, k = 2;
        System.out.println(getPermutation3(n, k));
    }

    //相比于第一个版本，该版本并没有真的保存每一个排列。
    public int total = 0;

    public String getPermutation2(int n, int k) {
        boolean[] used = new boolean[n + 1];
        ArrayList<Integer> res = new ArrayList<>();
        dfs2(0, n, k, used, res);
        StringBuilder ans = new StringBuilder();
        res.forEach(t -> {
            ans.append(t);
        });
        return ans.toString();
    }

    private void dfs2(int index, int n, int k, boolean[] used, ArrayList<Integer> res) {
        if (index == n) {
            total++;
            return;
        }
        int t = calculateFactorial(n - 1 - index);

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                if (total + t < k){
                    total += t;
                    continue;
                }
                used[i] = true;
                res.add(i);
                dfs2(index + 1, n, k, used, res);
                if (total == k) return;
                res.remove(res.size() - 1);
                used[i] = false;
                return;
            }
        }
    }

    private int calculateFactorial(int n) {
        int ans = 1;
        for (int i = 2; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }

}
