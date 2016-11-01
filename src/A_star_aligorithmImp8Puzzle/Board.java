package A_star_aligorithmImp8Puzzle;

import java.util.Map;

/**
 * Created by Akash on 14-Aug-16.
 */
public class Board{

    public int matrix[][];
    public int emptyCellX,emptyCellY;
    public Board parent;
    public int h=0,g;

    public Board(int matrix[][],int emptyCellX,int emptyCellY){
        int r=matrix.length,c=matrix[0].length;
        this.g=Integer.MAX_VALUE;
        this.matrix=new int[r][c];
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)this.matrix[i][j]=matrix[i][j];
        this.emptyCellX=emptyCellX;
        this.emptyCellY=emptyCellY;
    }
    public int getFinalCost(){
        return g+h;
    }

    public void setHeuristic(Map<Integer,Cell> mapGoal){
        int h=0,r=matrix.length,c=matrix[0].length;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(i!=emptyCellX||j!=emptyCellY){
                    h+=Math.abs(mapGoal.get(matrix[i][j]).i-i)+Math.abs(mapGoal.get(matrix[i][j]).j-j);
                }
            }
        }
        this.h=h;
    }

    public void printMatrix(){
        int r=this.matrix.length,c=this.matrix[0].length;
        for(int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                System.out.print(" "+this.matrix[i][j]);
            }
            System.out.println();
        }
    }
}
