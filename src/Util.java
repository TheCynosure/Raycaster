/**
 * Created by jack on 12/14/16.
 */
public class Util {
    protected static int SignModulo(int num, int modBy) {
        if(num < 0) {
            return modBy - Math.abs(num % modBy);
        } else {
            return num % modBy;
        }
    }
}
