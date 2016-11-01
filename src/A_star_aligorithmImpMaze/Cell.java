package A_star_aligorithmImpMaze;

/**
 * Created by Akash on 10-Aug-16.
 */
public class Cell {
    public int h = 0;
    public int g = 0;
    public int i, j;
    public Cell parent;

    public Cell(int i, int j){
        this.i = i;
        this.j = j;
    }
    public int getFinalCost(){
        return g+h;
    }
    @Override
    public String toString(){
        return "["+(this.i-1)+", "+(this.j-1)+"]";
    }
}
