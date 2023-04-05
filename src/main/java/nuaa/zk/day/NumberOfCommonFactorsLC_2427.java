package nuaa.zk.day;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/5 9:33
 */
public class NumberOfCommonFactorsLC_2427{
    //枚举到最小值
    public int commonFactors(int a, int b) {
        int min = a > b ?  b : a;
        int ans = 0;
        for (int i = 1; i <= min; i++) {
            if ((a % i == 0) && (b % i == 0))ans++;
        }
        return ans;
    }

    //枚举到最大公约数
    public int commonFactors2(int a, int b) {
        int g = gcd(a, b);
        int ans = 0;
        for (int x = 1; x * x <= g; ++x) {
            if (g % x == 0){
                ans++;
                if (x * x < g){
                    ans++;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    public int commonFactors3(int a, int b) {
        int g = GCD(a,b);
        int ans = 0;
        for (int i = 1; i <= g; i++) {
            if ((a % i == 0) && (b % i == 0))ans++;
        }
        return ans;
    }

    private int GCD(int a,int b){
        if (b == 0)return a;
        return GCD(b,a % b);
    }

}
