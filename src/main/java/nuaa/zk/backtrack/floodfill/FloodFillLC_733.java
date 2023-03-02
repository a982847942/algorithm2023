package nuaa.zk.backtrack.floodfill;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname FloodFillLC_733
 * @Description
 * @Date 2023/3/1 20:29
 * @Created by brain
 */
public class FloodFillLC_733 {
    //用used数组来做标记
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int root = image[sr][sc];
        boolean[][] used = new boolean[image.length][image[0].length];
        dfs(image, sr, sc, color, root,used);
        return image;
    }

    private void dfs(int[][] image, int row, int column, int color, int root, boolean[][] used) {
        if (row < 0 || column < 0 || row >= image.length || column >= image[0].length || image[row][column] != root || used[row][column])
            return;
        used[row][column] = true;
        image[row][column] = color;
        dfs(image, row + 1, column, color, root,used);
        dfs(image, row - 1, column, color, root,used);
        dfs(image, row, column + 1, color, root,used);
        dfs(image, row, column - 1, color, root,used);
    }

    //如果root和color一样根本不用遍历修改
    public int[][] floodFill2(int[][] image, int sr, int sc, int color) {
        int root = image[sr][sc];
        dfs2(image, sr, sc, color, root);
        return image;
    }

    private void dfs2(int[][] image, int row, int column, int color, int root) {
        if (row < 0 || column < 0 || row >= image.length || column >= image[0].length || image[row][column] != root || root == color)
            return;
        image[row][column] = color;
        dfs2(image, row + 1, column, color, root);
        dfs2(image, row - 1, column, color, root);
        dfs2(image, row, column + 1, color, root);
        dfs2(image, row, column - 1, color, root);
    }

    // TODO: 2023/3/1 后续尝试使用并查集和bfs来解决一下
    @Test
    public void test() {
        int[][] image = {{0,0,0}, {0,0,0}};
        int sr = 0, sc = 0,newColor = 0;
        int[][] res = floodFill(image, sr, sc, newColor);
        for (int[] t : res) {
            System.out.println(Arrays.toString(t));
        }
    }



    //广度优先搜索
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    public int[][] floodFill3(int[][] image, int sr, int sc, int color) {
        int currColor = image[sr][sc];
        if (currColor == color) {
            return image;
        }
        int n = image.length, m = image[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = color;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = color;
                }
            }
        }
        return image;
    }
}
