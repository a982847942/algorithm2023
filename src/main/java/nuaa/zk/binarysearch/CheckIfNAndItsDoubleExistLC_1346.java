package nuaa.zk.binarysearch;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/15 20:59
 */
public class CheckIfNAndItsDoubleExistLC_1346 {
    //hash表
    public boolean checkIfExist(int[] arr) {
        int len = arr.length;
        Set<Integer> cache = new HashSet<>();
        int zeroCount = 0;
        for (int i = 0; i < len; i++) {
            cache.add(arr[i]);
            if (arr[i] == 0)zeroCount++;
        }
        if (zeroCount > 1)return true;
        cache.remove(0);
        for (int i = 0; i < len; i++) {
            if (cache.contains(arr[i] * 2))return true;
        }
        return false;
    }
    public boolean checkIfExist2(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(2 * i) || (i % 2 == 0 && set.contains(i / 2)))
                return true;
            set.add(i);
        }
        return false;
    }

    @Test
    public void test(){
        int[] arr = {0,0};
        System.out.println(checkIfExist(arr));
    }
}
