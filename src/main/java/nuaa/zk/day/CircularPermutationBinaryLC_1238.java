package nuaa.zk.day;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CircularPermutationBinaryLC_1238
 * @Description
 * @Date 2023/2/23 9:05
 * @Created by brain
 */
public class CircularPermutationBinaryLC_1238 {

    public List<Integer> getPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        int len = (int) Math.pow(2, n);
        boolean[] used = new boolean[len];
        used[start] = true;
        res.add(start);
        process(len, used, 1, res);
        return res;
    }

    private void process(int len, boolean[] used, int index, List<Integer> res) {
        if (res.size() == len) return;
        for (int i = 0; i < len; i++) {
            int temp = (i ^ res.get(res.size() - 1));//不同的一位
            if (!used[i] && ((temp & (temp - 1)) == 0)) {
                if (index == len - 1) {
                    //最后一位
                    int first = (i ^ res.get(0));
                    if ((first & (first - 1)) != 0) {
                        continue;
                    }
                }
                used[i] = true;
                res.add(i);
                process(len, used, index + 1, res);
                if (res.size() == len) return;
                res.remove(res.size() - 1);
                used[i] = false;
            }
        }
    }

    //回溯法 思路正确  需要剪枝优化
    public List<Integer> circularPermutation(int n, int start) {
        return getPermutation(n, start);
    }

    //位运算 有点类似树状数组
    public List<Integer> circularPermutation2(int n, int start) {
        List<Integer> res = new ArrayList<>();
        res.add(start);
        int len = 1 << n;
        /**
         * 重点理解
         * 1.(len & (-len)) 提取出最右侧1之后 整体只有一个二进制位是1
         * 2.start ^= (len & (-len))  会出现什么结果？
         *      不考虑 仅有的二进制位1  其余位置如果不同 则^结果为1(这个1一定是start的！！)
         *                          如果相同 也符合要求
         *            单独考虑二进制为1的位置  如果start该出也是1 则结果为0 符合要求
         *                                 如果start该处为0 则结果为1  仍然符合要求！
         */
        while (--len > 0) {
            res.add(start ^= (len & (-len)));
        }
        return res;
    }


    // TODO: 2023/2/23  了解格雷码 以及相关题目 优化回溯版本代码的时间复杂度
    @Test
    public void test() {
//        System.out.println(circularPermutation(4, 1));
        System.out.println(circularPermutation2(4, 1));
    }


    private static final List<Integer> LIST = new java.util.AbstractList<Integer>() {

        @Override
        public Integer get(int index) {
            return (index >>> 1) ^ index ^ stt;
        }

        @Override
        public int size() {
            return size;
        }
    };
    private static int size;
    private static int stt;

    //格雷码优化代码
    public List<Integer> circularPermutation33(int n, int start) {
        size = 1 << n;
        stt = start;
        return LIST;
    }

    //格雷码1
    public List<Integer> circularPermutation31(int n, int start) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(start);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(((ret.get(j) ^ start) | (1 << (i - 1))) ^ start);
            }
        }
        return ret;
    }
    //格雷码2
    public List<Integer> circularPermutation32(int n, int start) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i >> 1) ^ i ^ start);
        }
        return ret;
    }

}

