package sample;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by dersanli on 3/3/17.
 */
class Cell {

    int i, j;

    Boolean[] walls = new Boolean[4];
    Boolean visited = Boolean.FALSE;
    LinkedList<Cell> neighbors = new LinkedList<>();

    Cell(int i, int j)
    {
        this.i = i;
        this.j = j;
        Arrays.fill(walls, Boolean.TRUE);

        //walls[0] = walls[1] = walls[2] = Boolean.FALSE;
    }

}
