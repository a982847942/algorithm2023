package nuaa.zk.day;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/5 9:05
 */
public class MaximumProfitCentennialWheelLC_1599 {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (4 * boardingCost - runningCost <= 0) return -1;
        int maxProfit = 0;
        int maxTimes = 0;
        int len = customers.length;
        int waitNum = 0;
        int preProfit = 0;
        for (int i = 0; i < len; i++) {
            if (maxProfit < preProfit) {
                maxTimes = i;
                maxProfit = preProfit;
            }
            waitNum += customers[i];
            preProfit = preProfit + boardingCost * (waitNum >= 4 ? 4 : waitNum) - runningCost;
            waitNum = waitNum >= 4 ? waitNum - 4 : 0;
        }
        if (maxProfit < preProfit) {
            maxTimes = len;
            maxProfit = preProfit;
        }
        int count = len;
        while (waitNum >= 4){
           count++;
            preProfit = preProfit + boardingCost * 4 - runningCost;
            waitNum -= 4;
            if (maxProfit < preProfit){
                maxTimes = count;
                maxProfit = preProfit;
            }
        }
        preProfit = preProfit + boardingCost * waitNum - runningCost;
        if (maxProfit < preProfit){
            maxTimes++;
            maxProfit = preProfit;
        }
        return maxTimes = maxProfit <= 0 ? -1 : maxTimes;
    }
    public int minOperationsMaxProfit2(int[] customers, int boardingCost, int runningCost) {
        int ans = -1;
        int mx = 0, t = 0;
        int wait = 0, i = 0;
        while (wait > 0 || i < customers.length) {
            wait += i < customers.length ? customers[i] : 0;
            int up = Math.min(4, wait);
            wait -= up;
            ++i;
            t += up * boardingCost - runningCost;
            if (t > mx) {
                mx = t;
                ans = i;
            }
        }
        return ans;
    }
    @Test
    public void test() {
        int[] customers = {3,4,0,5,1};
        int boardingCost = 1, runningCost = 92;
        System.out.println(minOperationsMaxProfit(customers, boardingCost, runningCost));
    }
}
