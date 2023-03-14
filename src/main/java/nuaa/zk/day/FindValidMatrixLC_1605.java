package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/14 8:47
 */
public class FindValidMatrixLC_1605 {
    //表上作业法求可行解
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rowLen = rowSum.length;
        int columnLen = colSum.length;
        int[][] resMatrix = new int[rowLen][columnLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < columnLen; j++) {
                int temp = Math.min(rowSum[i], colSum[j]);
                resMatrix[i][j] = temp;
                rowSum[i] -= temp;
                colSum[j] -= temp;
            }
        }
        return resMatrix;
    }

    public int[][] restoreMatrix2(int[] rowSum, int[] colSum) {
        int rowLen = rowSum.length;
        int columnLen = colSum.length;
        int[][] resMatrix = new int[rowLen][columnLen];
        for (int i = 0, j = 0; i < rowLen && j < columnLen;) {
            if (rowSum[i] < colSum[j]){
                colSum[j] -= rowSum[i];
                resMatrix[i][j] = rowSum[i++];
            }else {
                rowSum[i] -= colSum[j];
                resMatrix[i][j] = colSum[j++];
            }
        }
        return resMatrix;
    }

    @Test
    public void test(){
        int[] rowSum = {3,8}, colSum = {4,7};
        int[][] res = restoreMatrix2(rowSum, colSum);
        System.out.println(Arrays.toString(res));
    }
}
