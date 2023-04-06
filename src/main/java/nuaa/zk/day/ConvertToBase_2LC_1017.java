package nuaa.zk.day;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/6 9:18
 */
public class ConvertToBase_2LC_1017 {

    public String baseNeg2(int n){
        if (n == 0)return "0";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; n > 0; i++) {
            builder.append((n&1));
            if ((i&1)==1){n+=(n&1)<<1;}
            n>>=1;
        }
        return builder.reverse().toString();
    }
    private static final int MAX = 0b1010101010101010101010101010101;

    public String baseNeg2_2(int n) {
        return Integer.toBinaryString(MAX ^ MAX - n);
    }
}
