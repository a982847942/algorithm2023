package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Classname LargestLocalValues_2373
 * @Description
 * @Date 2023/3/1 8:42
 * @Created by brain
 */

// TODO: 2023/3/1 看灵神进阶问题，单调队列应用
public class LargestLocalValues_2373 {
    public int[][] largestLocal(int[][] grid) {
        int len = grid.length;
        int[][] res = new int[len - 2][len - 2];
        for (int row = 0; row < len - 2; row++) {
            for (int column = 0; column < len - 2; column++) {

                int max = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        max = Math.max(grid[row + i][column + j],max);
                    }
                }
                res[row][column] = max;
            }
        }
        return res;
    }

    @Test
    public void test(){
        int[][] grid = {{1,1,1,1,1},{1,1,1,1,1},{1,1,2,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        int[][] ans = largestLocal(grid);
        for (int[] t : ans) {
            System.out.println(Arrays.toString(t));
        }
    }
}
