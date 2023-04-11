package nuaa.zk.day;

/**
 * @author brain
 * @version 1.0
 * @date 2023/4/11 10:11
 */
public class RobotBoundedInCircleLC_1041 {
    /**
     * 转完一圈 如果面朝北方且x == 0 y ==0 则可以
     * 否则 1. 面朝北面且有一个不为0 多转再多圈也不可能陷入循环
     *     2. 面朝其它方向 则继续循环3 1 3次即可进入循环
     */
    public boolean isRobotBounded(String instructions) {
        int[][] direc = {{0,1},{1,0},{0,-1},{-1,0}};
        int direction = 0;
        int x = 0,y = 0;
        int len = instructions.length();
        for (int i = 0; i < len; i++) {
            char c = instructions.charAt(i);
            if (c == 'G'){
                x += direc[direction][0];
                y += direc[direction][1];
            }else if (c == 'L'){
                direction = (direction + 3) % 4;
            }else {
                direction = (direction + 1) % 4;
            }
        }
        return direction != 0 || (x == 0 && y == 0);
    }
}
