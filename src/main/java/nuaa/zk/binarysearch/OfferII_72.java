package nuaa.zk.binarysearch;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/19 14:04
 */
public class OfferII_72 {
    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        while (left < right) {
            long middle = left + ((right - left) >> 1) + 1;
            if (middle * middle <= x){
                left = middle;
            }else {
                right = middle - 1;
            }
        }
        return (int) left;
    }
    @Test
    public void test(){
        int x = 2147395599;
        System.out.println(mySqrt(x));
    }
}
