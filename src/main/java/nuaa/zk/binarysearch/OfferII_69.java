package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/19 12:10
 */
public class OfferII_69 {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int middle = left  + ((right - left) >> 1);
            if (middle > 0 && arr[middle] > arr[middle - 1] && arr[middle] > arr[middle + 1]){
                return middle;
            }else if (arr[middle] < arr[middle + 1]){
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        return -1;
    }
    //三分
    public int peakIndexInMountainArray2(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int m1 = l + (r - l) / 3;
            int m2 = r - (r - l) / 3;
            if (arr[m1] > arr[m2]) r = m2 - 1;
            else l = m1 + 1;
        }
        return r;
    }

}
