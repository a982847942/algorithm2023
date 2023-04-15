package nuaa.zk.day;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/15 9:03
 */
public class FlowerPlantingWithNoAdjacentLC_1042 {
    public int[] gardenNoAdj3(int n, int[][] paths) {
        List<Integer> g[] = new ArrayList[n];
        Arrays.setAll(g,e -> new ArrayList<>());
        //建图 类似邻接矩阵
        for (int[] path : paths) {
            int from = path[0] - 1;
            int to = path[1] - 1;
            g[from].add(to);
            g[to].add(from);
        }
        int[] color = new int[n];
        //每个点上色
        for (int i = 0; i < n; i++) {
            boolean[] used = new boolean[5];
            //查看所有相邻点
            for (Integer t : g[i]) {
                used[color[t]] = true;//用的颜色标记
            }
            while (used[++color[i]]);//color[i] = 0 ++从1开始 当while退出代表此时color[i]代表的颜色可以使用
        }
        return color;
    }

    //位运算
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer> g[] = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : paths) {
            int x = e[0] - 1, y = e[1] - 1; // 编号改从 0 开始
            g[x].add(y);
            g[y].add(x); // 建图
        }
        int[] color = new int[n];
        for (int i = 0; i < n; ++i) {
            //第 i 位为1表示该位在集合中
            int mask = 1; // 由于颜色是 1~4，把 0 加入 mask 保证下面不会算出 0
            for (int j : g[i])
                mask |= 1 << color[j];
            //找到从右到左的第一个 0 的位置  ~ 之后统计 尾部 0的个数
            color[i] = Integer.numberOfTrailingZeros(~mask);
        }
        return color;
    }
    @Test
    public void test() {
        int n = 3;
        int[][] paths = {{1,2},{2,3},{3,1}};
        System.out.println(Arrays.toString(gardenNoAdj3(n, paths)));
    }
}
