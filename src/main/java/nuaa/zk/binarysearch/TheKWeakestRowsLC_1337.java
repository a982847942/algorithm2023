package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/15 9:53
 */
public class TheKWeakestRowsLC_1337 {
    //纯暴力
    public int[] kWeakestRows(int[][] mat, int k) {
        int row = mat.length;
        int column = mat[0].length;
        int[] total = new int[row];
        int min = Integer.MAX_VALUE;
        int[] res = new int[k];
        for (int i = 0; i < row; i++) {
            int index = 0;
            while (index < column && mat[i][index++] == 1) total[i]++;
            res[0] = min <= total[i] ? res[0] : i;
            min = Math.min(min, total[i]);

        }
        int index = 1;
        int temp = min;
        while (k > 1) {
            for (int i = 0; i < row; i++) {
                if (k > 1 && total[i] == temp && i != res[0]) {
                    res[index++] = i;
                    k--;
                }
            }
            temp += 1;
        }
        return res;
    }

    //遍历 + 排序
    public int[] kWeakestRows2(int[][] mat, int k) {
        int row = mat.length;
        int column = mat[0].length;
        int[][] total = new int[row][2];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                total[i][0] += mat[i][j];
            }
            total[i][1] = i;
        }

        Arrays.sort(total, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = total[i][1];
        }
        return res;
    }

    public int[] kWeakestRows3(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return b[1] - a[1];
        });
        for (int i = 0; i < m; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (mat[i][mid] >= 1) l = mid;
                else r = mid - 1;
            }
            int cur = mat[i][r] >= 1 ? r + 1 : r;
            if (q.size() == k && q.peek()[0] > cur) q.poll();
            if (q.size() < k) q.add(new int[]{cur, i});
        }
        int[] ans = new int[k];
        int idx = k - 1;
        while (!q.isEmpty()) ans[idx--] = q.poll()[1];
        return ans;
    }

    public int[] kWeakestRows4(int[][] mat, int k) {
        int row = mat.length;
        int column = mat[0].length;
        int[][] total = new int[row][2];//这里可以使用两个变量交替来代替数组的每一行
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return b[1] - a[1];
        });
        for (int i = 0; i < row; i++) {
            int left = 0;
            int right = column - 1;
            while (left < right) {
                int middle = left + ((right - left) >> 1);
                if (mat[i][middle] == 1) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
            total[i][0] = mat[i][left] == 1 ? left + 1 :left;
            total[i][1] = i;
            if (maxHeap.size() == k && maxHeap.peek()[0] > total[i][0]) maxHeap.poll();
            if (maxHeap.size() < k) maxHeap.add(new int[]{total[i][0],i});
        }
        int[] ans = new int[k];
        int index = k - 1;
        while (index >= 0) {
            ans[index--] = maxHeap.poll()[1];
        }
        return ans;
    }

    @Test
    public void test() {
        int[][] mat = {{1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0,0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}};
        int k = 3;
        System.out.println(Arrays.toString(kWeakestRows4(mat, k)));
    }
}
