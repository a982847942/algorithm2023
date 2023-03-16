package nuaa.zk.binarysearch;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/16 12:56
 */
public class TemplateBinarySearch {
    //1.标准二分  数组元素有序，且不重复，查找目标值是否存在
    public int binarySearch(int[] nums,int target){
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        /**
         * 注意事项：
         * 1.right =  len - 1 表示搜索区间是[0,len - 1] 因此不会出现索引越界
         * 2.left<= right 表示每一次的区间都是 [left , right]表示的闭区间
         * 3.循环结束时 left = right + 1 此时区间大小为0
         * 二分问答重点关注 右边界初始取值、while退出条件、结合两个极端数据分析(比如找到小于某一数的最大数 可以假设数组全是大于 或全是小于 看看情况如何)
         */
        while (left <= right){
            int middle = left + ((right - left) >> 1);
            int temp = nums[middle];
            if (temp > target){
                //缩小右边界
                right = middle - 1;
            }else if (temp < target){
                //缩小左边界
                left = middle + 1;
            }else {
                return middle;
            }
        }
        //遍历结束 仍未找到target
        return -1;
    }
    /**
     * 二分查找边界(左右边界)
     * 1.数组有序，但包含重复元素
     * 2.数组部分有序，且不包含重复元素
     */
    //1.数组有序 包含重复元素 查找左边界
    public int leftBoundary(int[] nums,int target){
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        //一定不能用等号  举个极端例子[6,6,6,6,6,6] target = 5 最后死循环停留在 left = tight = 0处
        while (left < right){
            int middle = left + ((right - left) >> 1);
            if (nums[middle] >= target){
                right = middle;
            }else {
                left = middle + 1;
            }
        }
        //这里因为退出while时候 left == right 因此还差一个数没判断 需要在这里判断一下
        return nums[right] == target ? right : -1;
    }
    //数组部分有序，且包含重复元素
    public int binarySearch2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 收缩左边界
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 收缩右边界
                right = mid;
            } else { // nums[mid] == target
                // 保守收缩右边界
                right--;
            }
        }

        // 打补丁
        return nums[left] == target ? left : -1;
    }

    public int rightBoundary(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            //重点关注这里为什么mid要 + 1  [3 4 5 5 5 6] target=5  此时如果不加1 最后一次l = 4 r = 5则/2之后 永远偏向于左边 导致无法更新
            int mid = left + ((right - left) / 2) + 1;
            // 收缩右边界
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                // 收缩左边界
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

    // 二分查找某个数的左右边界
    public int[] searchRange(int[] nums, int target) {

        // 存储左右边界 res[0] 左边界, res[1] 右边界
        int[] res = new int[]{-1, -1};

        if(nums == null || nums.length == 0)
            return res;

        // 寻找左边界
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = ((left + right) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        res[0] = nums[left] == target ? left : -1;

        // 如果左边界是最后一个数的下标或者该数没有重复只存在一个，那么可以直接令右边界 = 左边界
        if (res[0] != -1) {
            if (left == nums.length - 1 || nums[left + 1] != target) {
                res[1] = left;
            } else {
                // 寻找右边界
                right = nums.length - 1;
                while (left < right) {
                    int mid = ((left + right) >> 1) + 1;
                    if (nums[mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid;
                    }
                }
                res[1] = nums[right] == target ? right : -1;
            }
        }

        return res;
    }

    //二分查找极值点
    public int binarySearch3(int[] nums){
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = left + ((right - left) >> 1);
            // 极值点
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            } else if (nums[mid] > nums[mid + 1]){
                // 极值点在 mid 左边
                right = mid - 1;
            } else {
                // 极值点在 mid 右边
                left = mid + 1;
            }
        }

        return -1;
    }
}
