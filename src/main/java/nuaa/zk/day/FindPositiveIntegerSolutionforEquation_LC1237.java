package nuaa.zk.day;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname FindPositiveIntegerSolutionforEquation_LC1237
 * @Description
 * @Date 2023/2/18 21:07
 * @Created by brain
 */
public class FindPositiveIntegerSolutionforEquation_LC1237 {

    // This is the custom function interface.
    // You should not implement it, or speculate about its implementation
    interface CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        public int f(int x, int y);
    }


    //暴力
    public List<List<Integer>> findSolution1(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                if (customfunction.f(i, j) == z) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(j);
                    res.add(pair);
                }
            }
        }
        return res;
    }

    //二分
    public List<List<Integer>> findSolution2(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            int left = 1;
            int right = 1000;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (customfunction.f(i, mid) > z) {
                    right = mid - 1;
                } else if (customfunction.f(i, mid) < z) {
                    left = mid + 1;
                } else {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(mid);
                    res.add(pair);
                    break;
                }
            }
        }
        return res;
    }

    //双指针
    public List<List<Integer>> findSolution3(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        int y = 1000;
        for (int i = 1; i <= 1000; i++) {
            while (y >= 1) {
                if (customfunction.f(i, y) == z) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(y);
                    res.add(pair);
                    break;
                }
               if (customfunction.f(i,y) > z){
                   y--;
               }else {
                   break;
               }
            }
        }
        return res;
    }

    public List<List<Integer>> findSolution4(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int x = 1, y = 1000;
        while (x <= 1000 && y > 0) {
            int temp = customfunction.f(x, y);
            if (temp < z) ++x;
            else if (temp > z) --y;
            else {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(x++);
                pair.add(y--);
                res.add(pair);
            }
        }
        return res;
    }


}
