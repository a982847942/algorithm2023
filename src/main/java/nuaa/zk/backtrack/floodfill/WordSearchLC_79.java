package nuaa.zk.backtrack.floodfill;

import org.junit.Test;

/**
 * @Classname WordSearchLC_79
 * @Description
 * @Date 2023/3/1 22:02
 * @Created by brain
 */
public class WordSearchLC_79 {

    //思考为什么用dfs 能否使用bfs
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int column = board[0].length;
        char first = word.charAt(0);
        boolean[][] used = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == first) {
                    if (dfs(i, j, 0, board, word,used)) return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int column, int cur, char[][] board, String word,boolean[][] used) {

        if (cur == word.length()) {
            return true;
        }
        if (row < 0 || column < 0 || row >= board.length || column >= board[0].length
        ||board[row][column] != word.charAt(cur) || used[row][column]) return false;
        used[row][column] = true;
        boolean res = dfs(row + 1, column, cur + 1, board, word,used) ||
                dfs(row - 1, column, cur + 1, board, word,used) ||
                dfs(row, column + 1, cur + 1, board, word,used) ||
                dfs(row, column - 1, cur + 1, board, word,used);
        used[row][column] = false;
        return res;
    }

    @Test
    public void test(){
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }


    //各种dfs剪枝
    char[] wordArray;
    boolean[][] visited;

    public boolean exist3(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        this.wordArray = word.toCharArray();
        this.visited = new boolean[m][n];

        // dfs优化加速:如果满足头部字符比较多，那就反转wordArray，从尾部开始dfs
        int len = wordArray.length;
        int head = 0;
        for (char[] row : board) {
            for (char ch : row) {
                if (ch == wordArray[0]) {
                    head++;
                } else if (ch == wordArray[len - 1]) {
                    head--;
                }
            }
        }
        if (head > 0) {
            // reverse
            int l = 0;
            int r = len - 1;
            while (l < r) {
                char temp = wordArray[r];
                wordArray[r] = wordArray[l];
                wordArray[l] = temp;
                l++;
                r--;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从(i,j)坐标开始进行dfs
                if (dfs(board, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 从(i,j)开始做dfs搜索，考察遍历到的字符能不能和wordArray[index]匹配
    boolean dfs(char[][] board, int i, int j, int index) {
        // 只有所有字符都能匹配上时，index才能等于wordArray.length
        // 注意该判断语句位置必须在最前面！
        if (index == wordArray.length) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        // 使用过的字母不能重复使用
        if (visited[i][j]) {
            return false;
        }
        // 当前字符不能匹配，返回false
        if (board[i][j] != wordArray[index]) {
            return false;
        }

        // 标记已经遍历过的格子
        visited[i][j] = true;
        // 递归遍历上下左右，只要有一个路径能匹配上就返回true，所以使用||或 连接
        if (dfs(board, i - 1, j, index + 1) ||
                dfs(board, i + 1, j, index + 1) ||
                dfs(board, i, j - 1, index + 1) ||
                dfs(board, i, j + 1, index + 1)) {
            return true;
        }
        // 还原全局变量
        visited[i][j] = false;
        // 易漏：4个方向都匹配不上，返回false！！
        return false;
    }
}
