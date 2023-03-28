package nuaa.zk.dp.LIS;

import org.junit.Test;

import java.util.*;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/26 21:26
 */
public class RussianDollEnvelopesLC_354 {

    //动态规划n2  超出时间限制
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        Integer[] indexs = new Integer[len];
        for (int i = 0; i < len; i++) {
            indexs[i] = i;
        }
        Arrays.sort(indexs, (x, y) -> {
            return envelopes[x][0] != envelopes[y][0] ? envelopes[x][0] - envelopes[y][0] : envelopes[x][1] - envelopes[y][1];
        });
        int[] dp = new int[len];
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[indexs[j]][0] < envelopes[indexs[i]][0] && envelopes[indexs[j]][1] < envelopes[indexs[i]][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            ans = Math.max(++dp[i], ans);
        }
        return ans;
    }

    //值域做法  本题值域很大，依然超出时间限制  不是标准的LIS不要用值域方法
//    public int maxEnvelopes2(int[][] envelopes) {
//        int len = envelopes.length;
//        Integer[] indexs = new Integer[len];
//        int max = 0;
//        for (int i = 0; i < len; i++) {
//            indexs[i] = i;
//            max = Math.max(max, envelopes[i][1]);
//        }
//        Arrays.sort(indexs, (x, y) -> {
//            return envelopes[x][0] != envelopes[y][0] ? envelopes[x][0] - envelopes[y][0] : envelopes[y][1] - envelopes[x][1];
//        });
//        int[] maxHeight = new int[max + 1];
//        int ans = 0;
//        for (int i = 0; i < len; i++) {
//            int height = envelopes[indexs[i]][1];
//            if (maxHeight[height] != 0)continue;
//            for (int j = 0; j < height; j++) {
//                maxHeight[height] = Math.max(maxHeight[height], maxHeight[j]);
//            }
//            ans = Math.max(ans, ++maxHeight[height]);
//        }
//        return ans;
//    }


    //这是标准的LIS问题，因此可以直接二分找<=最左边的值替换即可 如果是无矛盾球员问题则不能直接替换相等的数，而应该替换大于的第一个数
    public int maxEnvelopes3(int[][] envelopes) {
        int len = envelopes.length;
        Integer[] indexs = new Integer[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            indexs[i] = i;
            max = Math.max(max, envelopes[i][1]);
        }
        Arrays.sort(indexs, (x, y) -> {
            return envelopes[x][0] != envelopes[y][0] ? envelopes[x][0] - envelopes[y][0] : envelopes[y][1] - envelopes[x][1];
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int cur = envelopes[indexs[i]][1];
            int j = lowerBound(list, cur);
            if (j == list.size()) list.add(cur);
            else list.set(j, cur);
        }
        return list.size();
    }
    private int lowerBound(List<Integer> list, int target) {
        int left = -1;
        int right = list.size();
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    //Index Tree
    int[] tree;

    int lowbit(int x) {
        return x & -x;
    }

    public int maxEnvelopes4(int[][] es) {
        int n = es.length;
        if (n == 0) return n;

        // 由于我们使用了 g 记录高度，因此这里只需将 w 从小到达排序即可
        Arrays.sort(es, (a, b) -> a[0] - b[0]);

        // 先将所有的 h 进行离散化
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(es[i][1]);
        int cnt = set.size();
        int[] hs = new int[cnt];
        int idx = 0;
        for (int i : set) hs[idx++] = i;
        Arrays.sort(hs);
        for (int i = 0; i < n; i++) es[i][1] = Arrays.binarySearch(hs, es[i][1]) + 1;

        // 创建树状数组
        tree = new int[cnt + 1];

        // f(i) 为考虑前 i 个物品，并以第 i 个物品为结尾的最大值
        int[] f = new int[n];
        int ans = 1;
        for (int i = 0, j = 0; i < n; i++) {
            // 对于 w 相同的数据，不更新 tree 数组
            if (es[i][0] != es[j][0]) {
                // 限制 j 不能越过 i，确保 tree 数组中只会出现第 i 个信封前的「历史信封」
                while (j < i) {
                    for (int u = es[j][1]; u <= cnt; u += lowbit(u)) {
                        tree[u] = Math.max(tree[u], f[j]);
                    }
                    j++;
                }
            }
            f[i] = 1;
            for (int u = es[i][1] - 1; u > 0; u -= lowbit(u)) {
                f[i] = Math.max(f[i], tree[u] + 1);
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }


    //100%时间 解法 常数项小
    public int maxEnvelopes5(int[][] es) {
        int n = es.length;
        if (n == 0) return n;
        // 由于我们使用了 g 记录高度，因此这里只需将 w 从小到达排序即可
        Arrays.sort(es, (a, b) -> a[0] - b[0]);
        // f(i) 为考虑前 i 个物品，并以第 i 个物品为结尾的最大值
        int[] f = new int[n];
        // g(i) 记录的是长度为 i 的最长上升子序列的最小「信封高度」
        int[] g = new int[n];
        // 因为要取 min，用一个足够大（不可能）的高度初始化
        Arrays.fill(g, Integer.MAX_VALUE);
        g[0] = 0;
        int ans = 1;
        for (int i = 0, j = 0, len = 1; i < n; i++) {
            // 对于 w 相同的数据，不更新 g 数组
            if (es[i][0] != es[j][0]) {
                // 限制 j 不能越过 i，确保 g 数组中只会出现第 i 个信封前的「历史信封」
                while (j < i) {
                    int prev = f[j], cur = es[j][1];
                    if (prev == len) {
                        // 与当前长度一致了，说明上升序列多增加一位
                        g[len++] = cur;
                    } else {
                        // 始终保留最小的「信封高度」，这样可以确保有更多的信封可以与其行程上升序列
                        // 举例：同样是上升长度为 5 的序列，保留最小高度为 5 记录（而不是保留任意的，比如 10），这样之后高度为 7 8 9 的信封都能形成序列；
                        g[prev] = Math.min(g[prev], cur);
                    }
                    j++;
                }
            }

            // 二分过程
            // g[i] 代表的是上升子序列长度为 i 的「最小信封高度」
            int l = 0, r = len;
            while (l < r) {
                int mid = l + r >> 1;
                // 令 check 条件为 es[i][1] <= g[mid]（代表 w 和 h 都严格小于当前信封）
                // 这样我们找到的就是满足条件，最靠近数组中心点的数据（也就是满足 check 条件的最大下标）
                // 对应回 g[] 数组的含义，其实就是找到 w 和 h 都满足条件的最大上升长度
                if (es[i][1] <= g[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            // 更新 f[i] 与答案
            f[i] = r;
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    @Test
    public void test() {
        int[][] envelopes ={{1,2},{2,3},{3,4},{3,5},{4,5},{5,5},{5,6},{6,7},{7,8}};
        System.out.println(maxEnvelopes5(envelopes));
    }




}
