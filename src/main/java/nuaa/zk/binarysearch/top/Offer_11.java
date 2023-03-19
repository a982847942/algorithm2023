package nuaa.zk.binarysearch.top;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/17 22:57
 */
public class Offer_11 {
    public int minArray(int[] numbers) {
        int left = 0;
        int len = numbers.length;
        int right = len - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (numbers[middle] > numbers[right]) {
                //一定在最小值左边
                left = middle + 1;
            } else if (numbers[middle] < numbers[right]){
                //在最小值右边 或者等于最小值
                right = middle;
            }else {
                right--;
            }
        }
        return numbers[left];
    }
    public int minArray2(int[] numbers) {
        int left = -1;
        int right = numbers.length - 1;
        while (left + 1 < right){
            int middle = left + ((right - left) >> 1);
            if (numbers[middle] > numbers[right]){
                left = middle;
            }else if (numbers[middle] < numbers[right]){
                right = middle;
            }else {
                right--;
            }
        }
        return numbers[right];
    }

}
