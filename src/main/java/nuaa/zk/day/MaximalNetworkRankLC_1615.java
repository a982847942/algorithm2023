package nuaa.zk.day;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/15 8:43
 */
public class MaximalNetworkRankLC_1615 {
    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads.length == 0 || roads[0].length == 0)return 0;
        int[][] matrix = new int[n][n];
        int row = roads.length;
        int column = roads[0].length;
        for (int i = 0; i < row; i++) {
            int i1 = roads[i][0];
            int i2 = roads[i][1];
            matrix[i1][i2] = 1;
            matrix[i2][i1] = 1;
        }
        int[] total = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) total[i]++;
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = i + 1; j < n; j++) {
                temp = total[i] + total[j] - matrix[i][j];
                max = Math.max(temp, max);
            }
        }
        return max;
    }

    // TODO: 2023/3/15 贪心，不太懂，后续有机会看看
    public int maximalNetworkRank2(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] road : roads) {
            int x = road[0], y = road[1];
            connect[x][y] = true;
            connect[y][x] = true;
            degree[x]++;
            degree[y]++;
        }

        int first = -1, second = -2;
        List<Integer> firstArr = new ArrayList<Integer>();
        List<Integer> secondArr = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] > first) {
                second = first;
                secondArr = new ArrayList<Integer>(firstArr);
                first = degree[i];
                firstArr.clear();
                firstArr.add(i);
            } else if (degree[i] == first) {
                firstArr.add(i);
            } else if (degree[i] > second){
                secondArr.clear();
                second = degree[i];
                secondArr.add(i);
            } else if (degree[i] == second) {
                secondArr.add(i);
            }
        }
        if (firstArr.size() == 1) {
            int u = firstArr.get(0);
            for (int v : secondArr) {
                if (!connect[u][v]) {
                    return first + second;
                }
            }
            return first + second - 1;
        } else {
            int m = roads.length;
            if (firstArr.size() * (firstArr.size() - 1) / 2 > m) {
                return first * 2;
            }
            for (int u : firstArr) {
                for (int v : firstArr) {
                    if (u != v && !connect[u][v]) {
                        return first * 2;
                    }
                }
            }
            return first * 2 - 1;
        }
    }
    @Test
    public void test() {
        int n = 2;
        int[][] roads = {};
        System.out.println(maximalNetworkRank(n, roads));
    }
}
