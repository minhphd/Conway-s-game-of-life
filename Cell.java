import java.util.ArrayList;

public class Cell {

    private boolean alive;

    public Cell() {
        this.alive = false;
    }

    public Cell(boolean status) {
        this.alive = status;
    }

    public boolean getAlive() {
        return this.alive;
    }

    public void setAlive(boolean status) {
        this.alive = status;
    }
    public void updateState(int numAliveNeighbors){
        //check with game rules
        if (this.alive) {
            if (numAliveNeighbors != 2 && numAliveNeighbors != 3){
                this.alive = false;
            }
        } else {
            if (numAliveNeighbors == 3) {
                this.alive = true;
            }
        } 
    }

    public String toString() {
        if (this.alive){
            return "1";
        } else {
            return "0";
        }
    }
}
