package nuaa.zk.binarysearch.medium;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/30 9:35
 */
public class Search2DMatrixLC_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;
        int curRow = 0;
        int curColumn = column - 1;
        while (curRow < row && curColumn >= 0 ){
            if (matrix[curRow][curColumn] < target){
                curRow++;
            }else if (matrix[curRow][curColumn] > target){
                curColumn--;
            }else {
                return true;
            }
        }
        return false;
    }


}
