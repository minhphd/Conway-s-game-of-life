import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Landscape {

    private Cell[][] landscape;

    private double initialChance;

    private Queue<String> frameCheckQueue;

    public Landscape(int rows, int columns) {
        this.landscape = new Cell[rows][columns];
        this.initialChance = 0;
        reset();
    }

    public Landscape(int rows, int columns, double chance) {
        this.landscape = new Cell[rows][columns];
        this.initialChance = chance;
        reset();
    }

    public void reset() {
        this.frameCheckQueue = new LinkedList<String>();
        boolean status = false;
        Random random = new Random();
        for (int y = 0; y < this.getRows(); y++){
            for (int x = 0; x < this.getCols(); x++) {
                if (this.initialChance != 0){
                    //determine dead or alive
                    if (random.nextDouble() <= this.initialChance){
                        status = true;
                    }
                }
                this.landscape[y][x] = new Cell(status);
                status = false;
            }
        }
    }

    public int getRows() {
        return this.landscape.length;
    }

    public int getCols() {
        return this.landscape[0].length;
    }

    public Cell getCell(int row, int col) {
        return this.landscape[row][col];
    }

    public String toString() {
        String returnString = "";
        for (Cell[] row : this.landscape){
            String tempString = "";
            for (Cell cell: row){
                tempString += cell.toString() + ", ";
            }
            returnString += tempString.substring(0, tempString.length() -2 ) + "\n";
        }
        return returnString;
    }

    public boolean advance() {
        //create temp cells grid (simpler than original grid)
        Cell[][] tempLandscape = new Cell[this.getRows()][this.getCols()];

        for (int y = 0; y < this.getRows(); y++){
            for (int x = 0; x < this.getCols(); x++){
                //create new Cell with same value in temporary landscape
                tempLandscape[y][x] = new Cell(this.landscape[y][x].getAlive());
                tempLandscape[y][x].updateState(this.getAliveNeighbors(y, x));
            }
        }
        this.landscape = tempLandscape;

        //add method to check for repeat
        if (this.isRepeat(this.toString())){
            return false;
        }
        return true;
    }

    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.white);
                g.fillOval(x * scale - 1, y * scale - 1, scale, scale);
            }
        }
    }

    public int getAliveNeighbors(int row, int col){
        int numAliveNeighbors = 0;
        numAliveNeighbors = this.getCellStatus(row-1, col) +
                            this.getCellStatus(row+1, col) +
                            this.getCellStatus(row, col-1) +
                            this.getCellStatus(row, col+1) +
                            this.getCellStatus(row-1, col-1) +
                            this.getCellStatus(row-1, col+1) +
                            this.getCellStatus(row+1, col-1) +
                            this.getCellStatus(row+1, col+1); 
        return numAliveNeighbors;
    }

    public int getCellStatus(int row, int col){
        if ((row < 0 || row >= this.getRows()) || (col < 0 || col >= this.getCols())){
            return 0;
        } else {
            return Integer.valueOf(this.landscape[row][col].toString());
        }    
    }

    public boolean isRepeat(String stringOfCurrState){
        if (this.frameCheckQueue.size() > 15) {
            this.frameCheckQueue.poll();
        }
        if (this.frameCheckQueue.contains(stringOfCurrState)){
            return true;
        }
        this.frameCheckQueue.offer(stringOfCurrState);
        return false;
    }
}
