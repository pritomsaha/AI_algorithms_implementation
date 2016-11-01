package A_star_aligorithmImpMaze;

import java.util.PriorityQueue;

/**
 * Created by Akash on 10-Aug-16.
 */
public class AStar {
    private static final int V_H_COST = 10;
    private Cell [][] grid;
    private int moveX[]={0,1,0,-1};
    private int moveY[]={-1,0,1,0};
    private PriorityQueue<Cell> open;
    private boolean closed[][];
    private int startI, startJ;
    private int endI, endJ;

    private void setBlocked(int i, int j){
        grid[i][j] = null;
    }

    private void setStartCell(int i, int j){
        startI = i+1;
        startJ = j+1;
    }

    private void setEndCell(int i, int j){
        endI = i+1;
        endJ = j+1;
    }

    private void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if (t == null || closed[t.i][t.j]) return;
        int t_g = current.g+cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || t_g < t.g) {
            t.g = t_g;
            t.parent = current;
            if (!inOpen) open.add(t);
        }
    }

    public void AStar(){

        open.add(grid[startI][startJ]);

        Cell current;

        while(true){
            current = open.poll();
            if(current==null)break;
            closed[current.i][current.j]=true;

            if(current.equals(grid[endI][endJ]))return;
            Cell t;
            for(int l=0;l<4;l++){
                t=grid[current.i+moveX[l]][current.j+moveY[l]];
                checkAndUpdateCost(current,t,V_H_COST);
            }
        }
    }

    public void test(int x, int y, int si, int sj, int ei, int ej, int[][] blocked){
        grid = new Cell[x+2][y+2];
        closed = new boolean[x+2][y+2];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell)o1;
            Cell c2 = (Cell)o2;

            return c1.getFinalCost()<c2.getFinalCost()?-1:
                    c1.getFinalCost()>c2.getFinalCost()?1:0;
        });

        setStartCell(si, sj);
        setEndCell(ei, ej);

        for(int i=1;i<=x;++i){
            for(int j=1;j<=y;++j){
                grid[i][j] = new Cell(i, j);
                grid[i][j].h = Math.abs(i-endI)+Math.abs(j-endJ);
            }
        }
        for(int i=0;i<x+2;i++){
            setBlocked(i,0);setBlocked(0,i);
            setBlocked(x+1,i);setBlocked(i,x+1);
        }
        for(int i=0;i<blocked.length;++i){
            setBlocked(blocked[i][0]+1, blocked[i][1]+1);
        }

        System.out.println("\n\nInitial Grid:");
        for(int i=1;i<=x;++i){
            for(int j=1;j<=y;++j){
                if(i==si&&j==sj)System.out.print("SO  "); //Source
                else if(i==ei && j==ej)System.out.print("DE  ");  //Destination
                else if(grid[i][j]!=null)System.out.printf("%-3d ", 0);
                else System.out.print("BL  ");
            }
            System.out.println();
        }
        System.out.println();
        AStar();
        System.out.println("Scores for cells: ");
        for(int i=1;i<=x;++i){
            for(int j=1;j<=y;++j){
                if(grid[i][j]!=null)System.out.printf("%-3d ", grid[i][j].getFinalCost());
                else System.out.print("BL  ");
            }
            System.out.println();
        }
        System.out.println();

        if(closed[endI][endJ]){
            System.out.println("Path: ");
            printPath(grid[endI][endJ]);
            System.out.println();
        }else System.out.println("No possible path");
    }

    private void printPath(Cell current){
        if(current.parent==null){
            System.out.print(current);
            return;
        }
        printPath(current.parent);
        System.out.print(" -> "+current);
    }
}
