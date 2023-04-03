package nuaa.zk.day;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/3 10:26
 */
public class PreviousPermutationWithOneSwapLC_1053 {
    public int[] prevPermOpt1(int[] arr) {
        int len = arr.length;
        int[] lowerIndex = new int[len];
        for (int i = 0; i < len; i++) {
            lowerIndex[i] = i;
            int temp = arr[i];
            for (int j = i + 1; j < len; j++) {
                if (temp > arr[j] && (lowerIndex[i] == i || arr[lowerIndex[i]] < arr[j])){
                    lowerIndex[i] = j;
                }
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if (lowerIndex[i] != i){
                int temp = arr[i];
                arr[i] = arr[lowerIndex[i]];
                arr[lowerIndex[i]] = temp;
                break;
            }
        }
        return arr;
    }

    public int[] prevPermOpt1_2(int[] arr) {
        int n = arr.length;
        for (int i = n - 1; i > 0; --i) {
            //先找到第一个递减的位置
            if (arr[i - 1] > arr[i]) {
                /**
                 * 找到的递减位置，至少能保证交换i和i-1后排列比原来小
                 * 但是有可能i 后面的数还是比 i-1 小，因此需要找到比i-1小的最大的数(当只有i比i-1小时，也包括在这种情况下)
                 */
                for (int j = n - 1; j > i - 1; --j) {
                    //同时要注意如果arr[j]==arr[j-1]要交换位置靠前的数
                    if (arr[j] < arr[i - 1] && arr[j] != arr[j - 1]) {
                        int t = arr[i - 1];
                        arr[i - 1] = arr[j];
                        arr[j] = t;
                        return arr;
                    }
                }
            }
        }
        return arr;
    }
    @Test
    public void test(){
        System.out.println(Arrays.toString(prevPermOpt1(new int[]{1,9,4,6,7})));
    }

}
