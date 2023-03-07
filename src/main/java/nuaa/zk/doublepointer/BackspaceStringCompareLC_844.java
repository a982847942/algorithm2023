package nuaa.zk.doublepointer;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/7 9:26
 */
public class BackspaceStringCompareLC_844 {
    /**
     * 和栈的思想类似，更像是手动用数组实现了栈的一些功能，时间复杂度达不到O(1)
     */
    public boolean backspaceCompare(String s, String t) {
        s =  parse(s.toCharArray());
        t =  parse(t.toCharArray());
        return s.equals(t);
    }
    private String parse(char[] source){
        int right = -1;
        int len = source.length;
        int start = -1;
        while (source[++start] == '#' && start < len);
        for (int i = start; i < len; i++) {
            if (source[i] != '#'){
                swap(source,i,++right);
            }else {
                right = right == -1 ? right : right - 1;
            }
        }
        return right == -1 ? "" : new String(source).substring(0,right + 1);
    }
    private void swap(char[] arr,int index1,int index2){
        char temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     *双指针，时间复杂度O(1)
     * 为什么从后面遍历？ 因为当前字符是否需要删除，与前面的#无关，只取决于后面的#号。
     */
    public boolean backspaceCompare2(String s, String t) {
        int index1 = s.length() - 1;
        int index2 = t.length()  - 1;
        int skipS = 0;
        int skipT = 0;
        while (index1 >= 0 || index2 >= 0){
            while (index1 >= 0){
                char temp = s.charAt(index1);
                if (temp == '#'){
                    skipS++;
                    index1--;
                }else if (skipS != 0){
                    skipS--;
                    index1--;
                }else {
                    break;
                }
            }

            while (index2 >= 0){
                char temp = t.charAt(index2);
                if (temp == '#'){
                    skipT++;
                    index2--;
                }else if (skipT != 0){
                    skipT--;
                    index2--;
                }else {
                  break;
                }
            }
            if (index1 >= 0 && index2 >= 0){
                if (s.charAt(index1) != t.charAt(index2)){
                    return false;
                }
            }else {
                if (index1 >= 0 || index2 >= 0)return false;
            }
            index1--;
            index2--;
        }
        return true;
    }

    @Test
    public void test(){
        String s = "a##c", t = "#a#c";
        System.out.println(backspaceCompare(s, t));
    }
}
