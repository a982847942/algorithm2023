package nuaa.zk.backtrack;

import org.junit.Test;

/**
 * @Classname MaximumScoreWords
 * @Description
 * @Date 2023/2/26 9:37
 * @Created by brain
 */
public class MaximumScoreWordsLC_1255 {
    /**
     * 回溯核心
     * 1.当前问题是什么？
     * 2.子问题是什么？
     * 3.下一步是什么？
     * @param words
     * @param letters
     * @param score
     * @return
     */
    String[] words;
    int[] score;
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] frequency = new int[26];
        for (char letter : letters) {
            frequency[letter - 'a']++;
        }
        this.words = words;
        this.score = score;
        return dfs(words.length - 1,frequency);
    }

    private int dfs(int index, int[] frequency) {
        if (index < 0) return 0;//base case
        //不选
        int res = dfs(index-1,frequency);
        //选
        String temp = words[index];
        int total = 0;
        int[] clone = frequency.clone();
        for (char c : temp.toCharArray()) {
            if (clone[c - 'a']-- == 0 ){
                //个数不够 只能不选  注意这里有可能是temp遍历到一半发现不行，直接在frequency操作会导致删除前面的单词
                return res;
            }
            total += score[c - 'a'];
        }
        return Math.max(res,total + dfs(index - 1,clone));
    }

    @Test
    public void test(){
        String[] words = {"dog","cat","dad","good"};
        char[] letters = {'a','a','c','d','d','d','g','o','o'};
        int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(maxScoreWords(words, letters, score));
    }
}
