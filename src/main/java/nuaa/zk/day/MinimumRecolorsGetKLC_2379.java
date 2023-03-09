package nuaa.zk.day;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/9 10:02
 */
public class MinimumRecolorsGetKLC_2379 {
    public int minimumRecolors(String blocks, int k) {
        int min = Integer.MAX_VALUE;
        int cur = 0;
        int index = 0;
        while (index < k){
            if (blocks.charAt(index++) == 'W')cur++;
        }
        min = cur;
        index = 1;
        int len = blocks.length();
        while (index + k - 1 < len){
            if (blocks.charAt(index - 1) == 'W') cur--;
            if (blocks.charAt(index + k - 1) == 'W')cur++;
            index++;
            min = Math.min(cur,min);
        }
        return  min;
    }

    //这道题使用前缀和优化，感觉没什么必要
    public  int minimumRecolors2(String blocks, int k) {
        int[] ints = new int[blocks.length()];
        int bmum=0;
        for (int i = 0; i <ints.length; i++) {
            if (blocks.charAt(i)=='W'){
                bmum++;
            }
            ints[i]=bmum;
        }
        //数组是从0开始计数，第k个元素是ints[k-1]，ints[k]是第k+1个元素
        int min = ints[k-1];
        for (int i = k; i <ints.length ; i++) {
            min = Math.min(ints[i]-ints[i-k],min);
        }
        return min;
    }

    @Test
    public void test(){
        String blocks = "WBB";
        int k = 1;
        System.out.println(minimumRecolors(blocks, k));
    }
}
