package nuaa.zk.day;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/4 15:55
 */
public class BitwiseANDEqualToZeroLC_982 {
    public int countTriplets(int[] nums) {
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int first = nums[i];
            for (int j = 0; j < len; j++) {
                int second = nums[j];
                for (int k = 0; k < len; k++) {
                    if ((first & second & nums[k]) == 0) ans++;
                }
            }
        }
        return ans;
    }

    public int countTriplets2(int[] nums) {
        int[] cache = new int[1 << 16];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int first = nums[i];
            for (int j = 0; j < len; j++) {
                cache[first & nums[j]]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int temp = nums[i];
            for (int j = 0; j < (1 << 16); j++) {
                if ((j & temp) == 0) ans += cache[j];
            }
        }
        return ans;
    }

    public int countTriplets3(int[] nums) {
        int[] cache = new int[1 << 16];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int first = nums[i];
            for (int j = 0; j < len; j++) {
                cache[first & nums[j]]++;
            }
        }
        /**
         * 子集优化(状态压缩)
         * 把一个数的二进制位理解为全集中的第i个数是否在集合中
         * 如 1101 表示第1 2 3个数在集合中
         * 因此num[j] & ? == 0 ?所代表的的数一定是num[j]补集的子集
         * num[j] ^ 全集的二进制表示(0xffff)即得到补集
         * 然后列举补集 的子集  设补集位temp 则 s & temp == s即表示是temp的子集！
         */
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int temp = nums[i] ^ 0xffff;
            for (int j = temp; j >= 0; j--) {
                if ((j & temp) == j) ans += cache[j];
            }
        }
        return ans;
    }

    public int countTriplets4(int[] nums) {
        int[] cache = new int[1 << 16];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int first = nums[i];
            for (int j = 0; j < len; j++) {
                cache[first & nums[j]]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int temp = nums[i] ^ 0xffff;
            /**
             * 一样是枚举子集 这里的做法是直接找到下一个子集。为什么可以这样？
             * 假设当前temp = 110010
             * j - 1 = 110001 这样只是把最右边第一个二进制1 变为0 同时把 这个1右边的0全变为1
             * 然后(j - 1) & temp 得到的结果可以这样理解
             * 以j最右边的1为界  1右边 & 之后得到的都是temp中本该是1的位置，1左边同样如此(因为-1不改变左边的值)
             * 而此时j中最右边的1变为0 因此是下一个子集
             *
             * 同时需要注意当j == 0 时 ((j - 1) & temp) 即-1&temp 又变为temp,因此j = 0需要单独判断！
             */
            for (int j = temp; j > 0; j = ((j - 1) & temp)) {
                ans += cache[j];
            }
            ans += cache[0];
        }
        return ans;
    }

    @Test
    public void test() {
        int[] nums = {2, 1, 3};
//        System.out.println(countTriplets(nums));
//        System.out.println(countTriplets2(nums));
//        System.out.println(countTriplets3(nums));
        System.out.println(countTriplets4(nums));
    }
    //预处理每个nums[k]的补集的子集出现的次数cnt，最后累加cnt[nums[i]ANDnums[j]]
    public int countTriplets5(int[] nums) {
        int[] cnt = new int[1 << 16];
        cnt[0] = nums.length; // 直接统计空集
        for (int m : nums) {
            m ^= 0xffff;
            for (int s = m; s > 0; s = (s - 1) & m) // 枚举 m 的非空子集
                ++cnt[s];
        }
        int ans = 0;
        for (int x : nums)
            for (int y : nums)
                ans += cnt[x & y];
        return ans;
    }
    //仔细计算cnt的实际大小u，相应的全集就是u-1
    public int countTriplets6(int[] nums) {
        int u = 1;
        for (int x : nums)
            while (u <= x)
                u <<= 1;
        int[] cnt = new int[u];
        cnt[0] = nums.length; // 直接统计空集
        for (int m : nums) {
            m ^= u - 1;
            for (int s = m; s > 0; s = (s - 1) & m) // 枚举 m 的非空子集
                ++cnt[s];
        }
        int ans = 0;
        for (int x : nums)
            for (int y : nums)
                ans += cnt[x & y];
        return ans;
    }
}
