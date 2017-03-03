package sample;

/**
 * Created by dersanli on 3/3/17.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

class Maze {

    private GraphicsContext gc;
    private Canvas canvas;

    private int width, height, cols, rows, w;

    private LinkedList<Cell> grid = new LinkedList<>();
    private Cell current;


    Maze(GraphicsContext gc){
        this.gc = gc;
        this.canvas = gc.getCanvas();

        Initialize();
        DrawCells();
    }

    private void Initialize()
    {
        canvas.setWidth(400);
        canvas.setHeight(400);

        width = (int) canvas.getWidth();
        height = (int) canvas.getHeight();
        w = 40;

        cols = width/w; rows = height/w;

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                Cell cell = new Cell (i, j);
                grid.add(cell);
            }
        }

        current = grid.get(0);
        current.visited = Boolean.TRUE;
        checkNeighbors(current);


    }

    private int index(int i, int j)
    {
        return i + j * cols;
    }

    private void checkNeighbors(Cell current)
    {
        Cell[] neighbors = new Cell[4];
        Cell top = grid.get(index(current.i, current.j-1));
        Cell right = grid.get(index(current.i+1, current.j));
        Cell bottom = grid.get(index(current.i, current.j+1));
        Cell left = grid.get(index(current.i-1, current.j));

    }

    private void DrawCells()
    {
        gc.setFill(Color.PURPLE);
        gc.setStroke(Color.BEIGE);
        gc.setLineWidth(2);

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

            if(cell.visited)
            {
                gc.fillRect(x,y,w,w);
            }

            //System.out.printf("%d : %d\n",x,y);

        }







    }



    void PaintCanvas()
    {
        //gc.setFill(Color.CHOCOLATE);

        //gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

    }

}
