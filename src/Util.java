/**
 * Created by jack on 12/14/16.
 */
public class Util {
    protected static double SignModulo(double num, int modBy) {
        if(num < 0) {
            return modBy - Math.abs(num % modBy);
        } else {
            return num % modBy;
        }
    }

    protected static double mapValue(double val, double minVal, double maxVal, double minTarget, double maxTarget) {
        double percentage = (val - minVal)  / (maxVal - minVal);
        double diff = maxTarget - minTarget;
        return minTarget + (diff * percentage);
    }

}
