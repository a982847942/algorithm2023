package nuaa.zk.day;

/**
 * @Classname BestPokerHand_2347
 * @Description
 * @Date 2023/2/20 9:21
 * @Created by brain
 */
public class BestPokerHand_2347 {
    public String bestHand(int[] ranks, char[] suits) {
        int len = ranks.length;
        boolean isFlush = true;
        for (int i = 1; i < len; i++) {
            if (suits[i] != suits[i - 1]){
                isFlush = false;
                break;
            }
        }
        if (isFlush) return "Flush";

        int[] arr = new int[14];
        for (int i = 0; i < len; i++) {
            arr[ranks[i]]++;
        }
        boolean isThreeKind = false;
        boolean isPair = false;
        for (int i = 0; i < len; i++) {
            if (arr[ranks[i]] >= 3){
                isThreeKind = true;
                break;
            }
            if (arr[ranks[i]] >= 2){
                isPair = true;
            }
        }
        return isThreeKind ? "Three of a Kind" : (isPair ? "Pair" : "High Card");
    }

    //位运算
    public String bestHand2(int[] ranks, char[] suits) {
        if (isFlush(suits)) {
            return "Flush";
        }

        boolean isPair = false;
        int cnt = 0;
        for (int rank : ranks) {
            int mark = 1 << (rank - 1 << 1);
            if ((cnt & mark << 1) != 0) {
                return "Three of a Kind";
            }

            isPair |= (cnt & mark) != 0;
            cnt += mark;
        }
        return isPair ? "Pair" : "High Card";
    }

    private static boolean isFlush(char[] suits) {
        for (int c = suits[0], i = suits.length - 1; i > 0; i--) {
            if (suits[i] != c) {
                return false;
            }
        }
        return true;
    }
}
