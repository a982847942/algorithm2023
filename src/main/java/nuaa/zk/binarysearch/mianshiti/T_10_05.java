package nuaa.zk.binarysearch.mianshiti;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/19 14:28
 */
public class T_10_05 {
    public int findString(String[] words, String s) {
        int len = words.length;
        for (int i = 0; i < len; i++) {
            if (words[i].equals(s)) return i;
        }
        return -1;
    }


    public int findString2(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;
        int middle = 0;
        int temp = middle;
        while (left <= right) {
            // 1. 定位到不为空的左右边界
//            while (left <= right && words[left].length() == 0) left++;
//            while (left <= right && words[right].length() == 0) right--;
            // 边界找到之后，验证数据集是否依然有效：
            //     - 如果此时 left 已经比 right 大，则跳过下面的处理逻辑，直接返回 -1；
            //     - 如果此时 right 已经比 left 小，则跳过下面的处理逻辑，直接返回 -1；
            if (left <= right) {
                // 2. 使用二分查找，查找目标字符串
                // 防止数据溢出
                middle = left + ((right - left) >> 1);
                temp = middle;
                // 如果 middle 的值为空，则向右线性探查，直到不为空
                while (words[middle].length() == 0) middle++;
                if (words[middle].compareTo(s) > 0) right = temp - 1;
                else if (words[middle].compareTo(s) < 0) left = middle + 1;
                else return middle;
            }
        }
        return -1;
    }




    @Test
    public void test() {
        String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String s = "ball";
    }
}
