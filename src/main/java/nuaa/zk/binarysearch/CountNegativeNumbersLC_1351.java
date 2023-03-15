package nuaa.zk.binarysearch;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/15 21:24
 */
public class CountNegativeNumbersLC_1351 {
    //1.暴力，
    //2.遍历 + 二分
    public int countNegatives(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            int left = 0;
            int right = column - 1;
            while (left < right){
                int middle = left + ((right - left) >> 1);
                if (grid[i][middle] >= 0){
                    left = middle + 1;
                }else {
                    right = middle - 1;
                }
            }
            count += grid[i][left] >= 0 ? (column - left -1) : (column - left);
        }
        return count;
    }
    //3.直接二分

    /**
     *          {4,3,2,-1},
     *         {3,2,1,-1},
     *         {1,1,-1,-2},
     *         {-1,-1,-2,-3}};
     * 如上，如果从右上角开始遍历，记为(i,j) 如果grid[i][j] >=0 则直接i++
     * 否则 count += row - i (统计了一列) 然后j--
     */
    public int countNegatives2(int[][] grid) {
        int count = 0;
        int row = grid.length;
        int column = grid[0].length;
        int i = 0;
        int j = column - 1;
        while (i < row && j >= 0){
            if (grid[i][j] >= 0){
                i++;
            }else {
                count += row - i;
                j--;
            }
        }
        return count;
    }
    @Test
    public void test(){
        int[][] grid = {{5,1,0},{-5,-5,-5}};
        System.out.println(countNegatives2(grid));
    }
}
