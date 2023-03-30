package nuaa.zk.day;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/30 10:38
 */
public class WidestVerticalAreaLC_1637 {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points,(a, b)->{return a[0] - b[0];});
        int max = 0;
        int len = points.length;
        for(int i = 1;i < len; i++){
            max = Math.max(points[i][0] - points[i-1][0],max);
        }
        return max;
    }
}
