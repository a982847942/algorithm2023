package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/13 21:51
 */
public class FirstBadVersionLC_278 {
    boolean isBadVersion(int version){
        return true;
    }
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int ans = 0;
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            if (!isBadVersion(middle)){
                left = middle + 1;
            }else {
                ans = middle;
                right = middle - 1;
            }
        }
        return ans;
    }
}
