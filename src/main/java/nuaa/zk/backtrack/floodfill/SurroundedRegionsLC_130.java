package nuaa.zk.backtrack.floodfill;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Classname SurroundedRegionsLC_130
 * @Description
 * @Date 2023/3/1 21:37
 * @Created by brain
 */
public class SurroundedRegionsLC_130 {
    public void solve(char[][] board) {
        int row = board.length;
        int column = board[0].length;
        boolean[][] used = new boolean[row][column];
        for (int i = 0; i < column; i++) {
            if (board[0][i] == 'O') {
                dfs(0, i, used, board);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(i, 0, used, board);
            }
        }
        for (int i = 1; i < column; i++) {
            if (board[row - 1][i] == 'O') {
                dfs(row - 1, i, used, board);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i][column - 1] == 'O') {
                dfs(i, column - 1, used, board);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (!used[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(int row, int column, boolean[][] used, char[][] board) {
        if (row < 0 || column < 0 || row >= board.length || column >= board[0].length
                || board[row][column] != 'O' || used[row][column]) return;
        used[row][column] = true;
        dfs(row + 1, column, used, board);
        dfs(row - 1, column, used, board);
        dfs(row, column + 1, used, board);
        dfs(row, column - 1, used, board);
    }

    @Test
    public void test() {
        char[][] board = {{'X', 'O', 'X', 'O', 'X', 'O'},{'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'}};
        solve(board);
        for (char[] t : board) {
            System.out.println(Arrays.toString(t));
        }
    }
}
