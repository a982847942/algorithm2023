package nuaa.zk.day;

/**
 * @author brain
 * @version 1.0
 * @date 2023/3/21 8:55
 */
public class ConvertTheTemperatureLC_2469 {
    public double[] convertTemperature(double celsius) {
        double kelvin = celsius + 273.15;
        double fahrenheit = celsius * 1.80 + 32.00;
        return new double[]{kelvin,fahrenheit};
    }
}
