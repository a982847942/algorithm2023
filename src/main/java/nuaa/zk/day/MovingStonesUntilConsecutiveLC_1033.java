package nuaa.zk.day;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/7 10:01
 */
public class MovingStonesUntilConsecutiveLC_1033 {
    public int[] numMovesStones(int a, int b, int c) {
        int max = Math.max(a,Math.max(b,c));
        int min = Math.min(a,Math.min(b,c));
        int mid = a + b + c - max - min;
        int maxStep = max - min - 2;
        int minStep = Integer.MIN_VALUE;
        if (max - mid == 2 || mid -min == 2){
            minStep = 1;
        }else {
            minStep = (mid + 1 == max ? 0 : 1) + (min + 1 == mid ? 0 : 1);
        }
        return new int[]{minStep,maxStep};
    }
}
