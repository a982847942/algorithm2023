package nuaa.zk.binarysearch;

import org.junit.Test;

/**
 * @Classname LC_69
 * @Description
 * @Date 2023/2/14 20:02
 * @Created by brain
 */
public class Sqrt_X {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = 0;
        while (left <= right){
            int mid = left + ((right - left) >> 1) ;
            if ((long)mid * mid <= x){
                ans = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return ans;
    }

    //牛顿迭代法
    public int mySqrt2(int a) {
        long x = a;
        while (x * x > a) x = (x + a / x) / 2;
        return (int)x;
    }
    @Test
    public void test(){
        System.out.println(mySqrt(2147395599));
    }
}
