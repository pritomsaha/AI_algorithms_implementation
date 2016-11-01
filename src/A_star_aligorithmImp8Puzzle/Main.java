package A_star_aligorithmImp8Puzzle;

/**
 * Created by Akash on 14-Aug-16.
 */
public class Main {

    public static void main(String[] args){
        int goalMatrix[][]={{1,2,3},{8,0,4},{7,6,5}};
        int initMatrix[][]={{3,1,0},{2,8,4},{5,7,6}};
        new AStar(goalMatrix).execute(initMatrix,0,2);
    }
}
