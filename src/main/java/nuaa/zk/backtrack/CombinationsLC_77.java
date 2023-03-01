package nuaa.zk.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CombinationsLC_77
 * @Description
 * @Date 2023/2/28 18:02
 * @Created by brain
 */
public class CombinationsLC_77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(1,n,k,res,new ArrayList<Integer>());
        return res;
    }

    //从输入的角度看，每次只有要或不要
    private void dfs(int index, int n, int k, List<List<Integer>> res, ArrayList<Integer> temp) {
        if (k == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index > n) return;

        //不要
        dfs(index + 1,n,k,res,temp);
        //要
        temp.add(index);
        dfs(index + 1,n,k - 1,res,temp);
        temp.remove(temp.size() - 1);
    }

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs2(0,0,n,k,res,new ArrayList<Integer>());
        return res;
    }

    //从结果的角度考虑，结果的第index个位置可能出现什么数
    private void dfs2(int index, int pre,int n, int k, List<List<Integer>> res, ArrayList<Integer> temp) {
        if (index == k){
            if (temp.size() == k){
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = pre + 1; i <= n ; i++) {
            temp.add(i);
            dfs2(index + 1,i,n,k,res,temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test(){
        int n = 4, k = 2;
        List<List<Integer>> res = combine2(n, k);
        res.forEach(t->{
            System.out.println(t);
        });
    }

    //和2一样 都是从结果考虑，这里主要是加了剪枝操作
    public List<List<Integer>> combine3(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < k) return res;
        List<Integer> list = new ArrayList<>();
        backTracking(n, k, 0, res, list);
        return res;
    }

    private void backTracking(int n, int k, int index, List<List<Integer>> res, List<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        } else {
            //注意剪枝
            for (int i = index; i < (n - (k - list.size()) + 1); i++) {
                list.add(i + 1);
                backTracking(n, k, i + 1, res, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
