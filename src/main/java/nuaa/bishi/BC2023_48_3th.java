package nuaa.bishi;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/8 10:13
 */
public class BC2023_48_3th {

    public static int[] getDotEqualNumber() {
        Scanner scanner = new Scanner(System.in);
        int arrNum = scanner.nextInt();
        Map<Integer, Integer[]> input = new HashMap<>();
        for (int i = 0; i < arrNum; i++) {
            int curArrLen = scanner.nextInt();
            Integer[] curArr = new Integer[curArrLen];
            for (int j = 0; j < curArrLen; j++) {
                curArr[j] = scanner.nextInt();
            }
            input.put(i, curArr);
        }
//        for (Integer integer : input.keySet()) {
//            System.out.println(Arrays.toString(input.get(integer)));
//        }

        int[] res  = new int[arrNum];
        for (int i = 0; i < arrNum; i++) {
            Map<Integer, Integer[]> map = new HashMap<>();
            Integer[] curArr = input.get(i);
            int len = curArr.length;
            for (int k = 0; k < len; k++) {
                for (int j = k + 1; j < len; j++) {
                    int temp = curArr[k] * curArr[j];
                    if (map.containsKey(temp)) {
                        Integer end = map.get(temp)[0];
                        if (end != k && end != j) {
                            Integer curNum = map.get(temp)[1] + 1;
                            map.put(temp, new Integer[]{j, curNum});
                        }
                    } else {
                        map.put(temp, new Integer[]{j, 1});
                    }
                }
            }
            int ans = 0;
            for (Integer key : map.keySet()) {
                ans = Math.max(map.get(key)[1], ans);
            }
            res[i] = (ans == 1 ? -1 : ans);
        }
        return res;
    }

//    @Test
//    public void test() {
//
//    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 6, 1, 12};
        System.out.println(Arrays.toString(getDotEqualNumber()));
    }
}
