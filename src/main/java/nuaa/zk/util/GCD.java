package nuaa.zk.util;

import org.junit.Test;

/**
 * @Classname GCD
 * @Description
 * @Date 2023/2/15 20:56
 * @Created by brain
 */
public class GCD {

    //辗转相除法 缺点在于受计算机机器字长限制，比如RAS加密256位需要自己手动设计
    public int gcd1(int a, int b) {

        if (b == 0) {
            return a;
        }
        return gcd1(b, a % b);
    }

    public int gcd11(int a, int b) {
        int r = 0;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    //更相减损法
    public int gcd2(int a, int b) {
        if (a == b) return a;
        if (a < b) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        return gcd2(b, a - b);
    }

    public int gcd21(int a, int b) {
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }


    //枚举法
    public int gcd3(int a, int b) {
        int gcd = 1;
        for (int i = 2; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    //stein算法
    public int stein(int a, int b) {
        return 0;
    }

    @Test
    public void test() {
//        int times = 100;
//        int max = 10_0000;
//        int min = 0;
//        for (int i = 0; i < times; i++) {
//            int a = (int) (Math.random() * (max - min + 1)) + min;
//            int b = (int) (Math.random() * (max - min + 1)) + min;
//            if (gcd1(a,b) != gcd2(a,b)){
//                System.out.println("a is :"  + a);
//                System.out.println("b is :" + b);
//            }
//        }

        System.out.println(gcd21(15, 20));
    }

}
