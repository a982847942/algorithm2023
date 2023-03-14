package nuaa.zk.binarysearch;

import java.rmi.server.RMIClassLoader;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/14 22:20
 */
public class ArrangingCoinsLC_441 {
    public int arrangeCoins(int n) {
        int left = 0;
        int right = n;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            long temp = (long) middle * (1 + middle) / 2;
            if (temp > n){
                right = middle - 1;
            }else if (temp < n){
                left = middle + 1;
            }else{
                return middle;
            }
        }
        return right;
    }

    public int arrangeCoins2(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //数学 求根公式
    public int arrangeCoins3(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }

}
