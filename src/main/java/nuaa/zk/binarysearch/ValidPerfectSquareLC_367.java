package nuaa.zk.binarysearch;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/14 9:20
 */
public class ValidPerfectSquareLC_367 {
    public boolean isPerfectSquare(int num) {
        int temp = (int) Math.sqrt(num);
        return temp * temp == num;
    }

    //二分法(对暴力法优化了一点)
    public boolean isPerfectSquare2(int num) {
        int left = 0;
        int right = num;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            long temp = (long) middle * middle;
            if (temp == num) {
                return true;
            } else if (temp > num){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }
        return false;
    }
    //牛顿迭代法
    public boolean isPerfectSquare3(int num) {
        double x0 = num;
        while (true) {
            double x1 = (x0 + num / x0) / 2;
            if (x0 - x1 < 1e-6) {
                break;
            }
            x0 = x1;
        }
        int x = (int) x0;
        return x * x == num;
    }


    @Test
    public void test() {
        int num = 14;
        System.out.println(isPerfectSquare2(num));
    }
}
