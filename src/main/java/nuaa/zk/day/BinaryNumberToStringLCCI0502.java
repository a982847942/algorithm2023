package nuaa.zk.day;

import org.junit.Test;

/**
 * @Classname BinaryNumberToStringLCCI0502
 * @Description
 * @Date 2023/3/2 10:16
 * @Created by brain
 */
public class BinaryNumberToStringLCCI0502 {
    public String printBin(double num) {
        StringBuilder res = new StringBuilder();
        res.append("0.");
        int cur = 2;
        double src = num;
        while (cur < 32) {
            src = src * 2;
            double decimal = src - (int) src;
            if (src >= 1.0) {
                res.append((int) src);
                if (decimal == 0.0) break;
                src -= (int) src;
            } else {
                res.append("0");
                if (decimal == 0.0) break;
            }
            cur++;
        }
        return cur == 32 ? "ERROR" : res.toString();
    }

    //本题输入保证10进制数的小数位最多6位，因此二进制的小数位最多也是6位(证明见灵神题解)
    public String printBin2(double num) {
        StringBuilder bin = new StringBuilder("0.");
        for (int i = 0; i < 6; ++i) { // 至多循环 6 次
            num *= 2;
            if (num < 1)
                bin.append('0');
            else {
                bin.append('1');
                if (--num == 0)
                    return bin.toString();
            }
        }
        return "ERROR";
    }

    @Test
    public void test() {
        System.out.println(printBin(0.1));
    }
}
