package nuaa.bishi;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/8 10:52
 */
public class BC2023_48_4th {
    //皮克定理
    public static int getDotNum() {
        Scanner scanner = new Scanner(System.in);
        int dotNum = scanner.nextInt();
        int[][] arr = new int[dotNum][2];
        for (int i = 0; i < dotNum; i++) {
            arr[i][0] = scanner.nextInt();
            arr[i][1] = scanner.nextInt();
        }
//        for (int i = 0; i < dotNum; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }
        int len = arr.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                double l1 = Math.sqrt(Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2));
                int line1 = gcd(Math.abs(arr[i][0] - arr[j][0]), Math.abs(arr[i][1] - arr[j][1]));
                for (int k = j + 1; k < len; k++) {
                    double l2 = Math.sqrt(Math.pow(arr[i][0] - arr[k][0], 2) + Math.pow(arr[i][1] - arr[k][1], 2));
                    double l3 = Math.sqrt(Math.pow(arr[j][0] - arr[k][0], 2) + Math.pow(arr[j][1] - arr[k][1], 2));
                    if (canConstructTriangle(l1, l2, l3)) {
                        int line2 = gcd(Math.abs(arr[i][0] - arr[k][0]), Math.abs(arr[i][1] - arr[k][1]));
                        int line3 = gcd(Math.abs(arr[j][0] - arr[k][0]), Math.abs(arr[j][1] - arr[k][1]));
                        ans = Math.max(line1 + line2 + line3,ans);
                    }
                }
            }
        }
        return ans;
    }

    private static boolean canConstructTriangle(double l1, double l2, double l3) {
        //判断方法不对，只需要判断是否共线即可
        return ((l1 + l2 > l3) && (l1 + l3 > l2) && (l2 + l3 > l1));
    }

    public static int gcd(int a, int b) {

        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[][] arr = {{2,2},{5,5},{2,5},{3,4}};
        System.out.println(getDotNum());
    }
}
