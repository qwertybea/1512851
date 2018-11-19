package ca.cours5b5.justinfofana.global;

import java.util.ArrayList;
import java.util.List;

public class GDirection {

    public int incrementHorizontal;
    public int incrementVertical;

    public static List<GDirection> directions;

    static {

        directions = new ArrayList<>();

        directions.add(new GDirection(0, 1)); // down
        directions.add(new GDirection(1, 0)); // right
        directions.add(new GDirection(1, 1)); // up left to low right
        directions.add(new GDirection(1, -1)); // low left to up right

        /*
         * Créer les directions
         *
         * (BONUS: a-t-on besoin de toutes les créer?)
         *
         */

    }

    public GDirection(int incrementHorizontal, int incrementVertical){

        this.incrementHorizontal = incrementHorizontal;
        this.incrementVertical = incrementVertical;

    }

}
