package nuaa.zk.day;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @Classname MaximumAveragePassRatioLC_1792
 * @Description
 * @Date 2023/2/19 20:02
 * @Created by brain
 */
public class MaximumAveragePassRatioLC_1792 {
    public double maxAverageRatio(int[][] classes, int extraStudents) {

        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((t1, t2) -> {
            long value1 = (long) (t1[1] + 1) * t1[1] * (t2[1] - t2[0]);
            long value2 = (long) (t2[1] + 1) * t2[1] * (t1[1] - t1[0]);
            if (value1 == value2) return 0;
            return value1 < value2 ? -1 : 1;
        });
        int length = classes.length;
        for (int i = 0; i < length; i++) {
            maxHeap.add(classes[i]);
        }

        for (int i = 0; i < extraStudents; i++) {
            int[] temp = maxHeap.poll();
            temp[0] += 1;
            temp[1] += 1;
            maxHeap.add(temp);
        }
        double res = 0.0;
        Iterator<int[]> iterator = maxHeap.iterator();
        while (iterator.hasNext()) {
            int[] temp = iterator.next();
            res = res + (1.0 * temp[0] / temp[1]);
        }

        return res / length;

    }
}
