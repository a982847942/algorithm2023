package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/14 22:16
 */
public class GuessNumberHigherOrLower {
    private int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int left = 0;
        int right = n;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            int temp = guess(middle);
            if (temp == -1){
                right = middle - 1;
            }else if (temp == 1){
                left = middle + 1;
            }else {
                return middle;
            }
        }
        return left;
    }
}
