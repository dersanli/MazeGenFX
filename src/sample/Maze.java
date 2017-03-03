package sample;

/**
 * Created by dersanli on 3/3/17.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Random;

class Maze {

    Cell[][] cells;
    private GraphicsContext gc;
    private Canvas canvas;
    private int width, height, cols, rows, w;
    private LinkedList<Cell> stack = new LinkedList<>();
    private LinkedList<Cell> grid = new LinkedList<>();
    private Cell current;


    Maze(GraphicsContext gc){
        this.gc = gc;
        this.canvas = gc.getCanvas();

        Initialize();

        do {
            DrawCells();
        } while (stack.size() > 0);

    }

    private void Initialize()
    {
        gc.setFill(Color.PURPLE);
        gc.setStroke(Color.BEIGE);
        gc.setLineWidth(2);

        canvas.setWidth(400);
        canvas.setHeight(400);

        width = (int) canvas.getWidth();
        height = (int) canvas.getHeight();
        w = 40;

        cols = width/w; rows = height/w;

        //cells = new Cell[cols][rows];

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                Cell cell = new Cell (i, j);

                //cells[j][i] = cell;

                grid.add(cell);
            }
        }

        current = grid.get(0);
    }


    private int index(int i, int j)
    {
        if( i < 0 || j < 0 || i > cols - 1 || j > rows - 1 ){
            return -1;
        }

        return i + j * cols;
    }

    private Cell checkNeighbors(Cell current) {
        LinkedList<Cell> neighbors = new LinkedList<>();

        int index = index(current.i, current.j - 1);

        if (-1 != index) {
            if (!grid.get(index).visited) {
                neighbors.push(grid.get(index));
            }
        }

        index = index(current.i + 1, current.j);
        if (-1 != index) {
            if (!grid.get(index).visited) {
                neighbors.push(grid.get(index));
            }
        }

        index = index(current.i, current.j + 1);
        if (-1 != index) {
            if (!grid.get(index).visited) {
                neighbors.push(grid.get(index));
            }
        }

        index = index(current.i - 1, current.j);
        if (-1 != index) {
            if (!grid.get(index).visited) {
                neighbors.push(grid.get(index));
            }
        }

        if (neighbors.size() > 0) {
            int r = new Random().nextInt(neighbors.size()); //floor(random(0, neighbors.length));
            return neighbors.get(r);
        } else {
            return null;
        }
    }

    private void DrawCells()
    {
        for (int i = 0; i < grid.size() ; i++) {

            Cell cell = grid.get(i);

            int x = cell.i*w;
            int y = cell.j*w;

            if(cell.walls[0]) {
                gc.strokeLine(x, y, x + w, y); //top
            }
            if(cell.walls[1]) {
                gc.strokeLine(x + w, y, x + w, y + w); //right
            }
            if(cell.walls[2]) {
                gc.strokeLine(x + w, y + w, x, y + w); //bottom
            }
            if(cell.walls[3]) {
                gc.strokeLine(x, y + w, x, y); //left
            }
        }

        current.visited = true;
        gc.fillRect(current.i * w, current.j * w, w, w);

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // STEP 1
        Cell next = checkNeighbors(current);
        if (next != null) {
            next.visited = true;

            // STEP 2
            stack.push(current);

            // STEP 3
            removeWalls(current, next);

            // STEP 4
            current = next;
        } else if (stack.size() > 0) {
            current = stack.pop();
        }

        //System.out.printf("%d : %d\n",x,y);
    }

    void removeWalls(Cell a, Cell b)
    {
        int x = a.i - b.i;
        if (x == 1) {
            a.walls[3] = false;
            b.walls[1] = false;
        } else if (x == -1) {
            a.walls[1] = false;
            b.walls[3] = false;
        }
        int y = a.j - b.j;
        if (y == 1) {
            a.walls[0] = false;
            b.walls[2] = false;
        } else if (y == -1) {
            a.walls[2] = false;
            b.walls[0] = false;
        }
    }
}
