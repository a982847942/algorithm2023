package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/14 22:40
 */
public class FindSmallestLetterGreaterThanTargetLC_744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            char c = letters[middle];
            if (c > target){
                //因为要找到第一个大于target的数，因此此时不能middle-1,必须保留上一次的大于信息
                right = middle;
            }else {
                left = middle + 1;
            }
        }
        return letters[right] > target ? letters[right] : letters[0];
    }
    public char nextGreatestLetter2(char[] letters, char target) {
        int length = letters.length;
        //避免方法一的右边界也小于target的情况判断
        if (target >= letters[length - 1]) {
            return letters[0];
        }
        int low = 0, high = length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (letters[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return letters[low];
    }

}
