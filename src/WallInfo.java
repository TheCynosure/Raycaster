import java.awt.*;

/**
 * Created by jack on 1/2/17.
 * An object to store intersection, side, and type info of wall that was hit during a raycast.
 */
public class WallInfo {
    protected enum Side {
        VERTICAL,
        HORIZONTAL
    }

    protected Point intersectionPoint;
    protected int type;
    protected Side side;

    public WallInfo(Point intersectionPoint, int type, Side side) {
        this.intersectionPoint = intersectionPoint;
        this.type = type;
        this.side = side;
    }

}
