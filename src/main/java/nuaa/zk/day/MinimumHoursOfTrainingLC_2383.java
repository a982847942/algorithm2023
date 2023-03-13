package nuaa.zk.day;

import org.junit.Test;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/13 8:45
 */
public class MinimumHoursOfTrainingLC_2383 {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int needExperience = 0;
        int len = experience.length;
        int temp = initialExperience;
        for (int i = 0; i < len; i++) {
            if (temp <= experience[i]){
               needExperience =  needExperience + experience[i] - temp + 1;
               temp = experience[i]*2 + 1;
            }else {
                temp += experience[i];
            }
        }

        int needEnergy = 0;
        temp = initialEnergy;
        for (int i = 0; i < len; i++) {
            if (temp <= energy[i]){
                needEnergy = needEnergy + energy[i] - temp + 1;
                temp = 1;
            }else {
                temp -= energy[i];
            }
        }
        System.out.println(needEnergy);
        System.out.println(needExperience);
        return needExperience + needEnergy;
    }

    @Test
    public void test(){
        int initialEnergy = 1, initialExperience = 1;
        int[] energy = {1,1,1,1}, experience = {1,1,1,50};
        System.out.println(minNumberOfHours(initialEnergy, initialExperience, energy, experience));
    }
}
