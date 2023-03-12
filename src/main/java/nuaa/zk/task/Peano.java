package nuaa.zk.task;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/12 10:43
 */
public class Peano {
    private static final int ZERO = 0;

    /**
     * peano数
     * @param i
     * @return
     */
    public int succ(int i) {
        return i = -~i;//取反 变号
    }

    /**
     * 获取一个自然数
     * @param number
     * @return
     */
    public int getNumber(int number) {
        int t = ZERO;
        for (int i = 0; i < number; i++) {
            t = succ(t);
        }
        return t;
    }

    /**
     * 加法
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while (b != 0) { // 当进位为 0 时跳出
            int sum = 0;
            int carry = 0;
            sum = a ^ b; // a = 非进位和(对应位的和)
            carry = (a & b) << 1;  // c = 进位(对应位和的进位，既然是进位，就要整体左移一位)
            a = sum;
            b = carry; // b = 进位
        }
        return a;
    }

    /**
     *减法，其实就是加法的逆运算
     * @param a
     * @param b
     * @return
     */
    int sub(int a, int b)
    {
        return add(a, add(~b, 1));
    }

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    public int muli(int a,int b){
        int sum = 0;
        while(b != 0){
            if((b & 1)!=0){
                sum = add(sum,a);
            }
            a<<=1;
            b>>>=1;
        }
        return sum;
    }
    private boolean isNeg(int x){
        return x<0;
    }
    private int negNum(int n){
        return add(~n,1);
    }
    private  int div(int a,int b){
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for(int i = 30;i>=0; i = sub(i,1)){
            if((x>>i)>=y){
                res |= (1<<i);
                x = sub(x, y<< i);
            }
        }
        return isNeg(a) != isNeg(b) ? negNum(res) : res;
    }
    public  int divide(int a,int b){
        if(a == Integer.MIN_VALUE && b==Integer.MIN_VALUE){
            return 1;
        }else if(b == Integer.MIN_VALUE){
            return 0;
        }else if(a == Integer.MIN_VALUE){
            if(b == negNum(1)){
                return Integer.MAX_VALUE;
            }else{
                int c = div(add(a,1),b);
                return add(c,div(sub(a,muli(c,b)),b));
            }
        }else{
            return div(a,b);
        }
    }

    /**
     * 偶数
     * @param num
     * @return
     */
    public boolean even(int num){
        return (num & 1) == 0;
    }

    /**
     * 奇数
     * @param num
     * @return
     */
    public boolean odd(int num){
        return (num & 1) != 0;
    }

    /**
     * 结果为正数表示 a < b
     * 否则不小于
     * @param a
     * @param b
     * @return
     */
    public int LT(int a, int b) {
        return a - b >>> 31;
    }

    /**
     * 结果为正数表示 a > b
     * 否则不大于
     * @param a
     * @param b
     * @return
     */
    public  int GT(int a, int b) {
        return b - a >>> 31;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     * 结果为0表示相等，否则不相等
     */
    public int EQ(int a, int b) {
        return a ^ b;
    }
    @Test
    public void test() {
        int num1 = getNumber(5);
        int num2 = getNumber(6);
        System.out.println(add(num1, num2));
        System.out.println(sub(num1,num2));
        System.out.println(muli(num1,num2));
        System.out.println(divide(num1,num2));
        System.out.println(even(num2));
        System.out.println(odd(num1));
        System.out.println(LT(num2,num1));
        System.out.println(GT(num1,num1));
        System.out.println(EQ(num1,num1));
    }
}
