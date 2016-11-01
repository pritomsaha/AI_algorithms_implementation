package A_star_aligorithmImp8Puzzle;

import java.util.*;

/**
 * Created by Akash on 14-Aug-16.
 */
public class AStar {
    Map<Integer,Cell> goalMap=new HashMap<>();
    PriorityQueue<Board> open;
    List<Board> close;

    public AStar(int goalMatrix[][]){
        int r=goalMatrix.length;
        int c=goalMatrix[0].length;
        for(int i=0;i<r;i++) {
            for (int j = 0; j < c; j++) {
                goalMap.put(goalMatrix[i][j], new Cell(i, j));
            }
        }
        open=new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Board b1 = (Board)o1;
                Board b2 = (Board)o2;

                return b1.getFinalCost()<b2.getFinalCost()?-1:
                        b1.getFinalCost()>b2.getFinalCost()?1:0;            }
        });

    }

    public void execute(int initMatrix[][],int emptyX,int emptyY){
        close=new ArrayList<>();
        Board root=new Board(initMatrix,emptyX,emptyY);
        root.setHeuristic(goalMap);
        root.g=0;
        open.add(root);
        Board current;

        while (true){
            current=open.poll();
            if(current.h==0)break;
            close.add(current);
            int r=current.matrix.length,c=current.matrix[0].length;
            Board neighbor;
            if(current.emptyCellX-1>=0){
                neighbor=getNeighbor(current,current.emptyCellX-1,current.emptyCellY);
                checkAndUpdateCost(current, neighbor);
            }
            if(current.emptyCellX+1<r){
                neighbor=getNeighbor(current,current.emptyCellX+1,current.emptyCellY);
                checkAndUpdateCost(current, neighbor);
            }
            if(current.emptyCellY-1>=0){
                neighbor=getNeighbor(current,current.emptyCellX,current.emptyCellY-1);
                checkAndUpdateCost(current, neighbor);
            }
            if(current.emptyCellY+1<c){
                neighbor=getNeighbor(current,current.emptyCellX,current.emptyCellY+1);
                checkAndUpdateCost(current, neighbor);
            }
        }
        if(current.h==0){
            System.out.println("Goal");
            current.printMatrix();
            System.out.println("\nWay to Solve");
            printPath(current);
        }

        else System.out.println("No solution");
    }

    public void checkAndUpdateCost(Board parent,Board neighbor){
        if(isContainedInClose(neighbor)){
            return;
        }
        int t_g=parent.g+1;
        boolean isInOpen=isContainedInOpen(neighbor);
        if(!isInOpen||t_g<neighbor.g){
            neighbor.g=t_g;
            neighbor.parent=parent;
            if(!isInOpen)open.add(neighbor);
        }
    }

    public Board getNeighbor(Board parent,int x,int y){
        Board neighbor= new Board(parent.matrix,x,y);
        neighbor.h=parent.h;
        neighbor.matrix[x][y]=parent.matrix[parent.emptyCellX][parent.emptyCellY];
        neighbor.matrix[parent.emptyCellX][parent.emptyCellY]=parent.matrix[x][y];

        int h=Math.abs(goalMap.get(parent.matrix[x][y]).i-x)+Math.abs(goalMap.get(parent.matrix[x][y]).j-y);

        neighbor.emptyCellX=x;neighbor.emptyCellY=y;
        h=Math.abs(goalMap.get(neighbor.matrix[parent.emptyCellX][parent.emptyCellY]).i-parent.emptyCellX)+Math.abs(goalMap.get(neighbor.matrix[parent.emptyCellX][parent.emptyCellY]).j-parent.emptyCellY)-h;
        neighbor.h+=h;
        return neighbor;
    }

    public boolean isContainedInClose(Board board){
        int r=board.matrix.length,c=board.matrix[0].length;
        int l=close.size();
        boolean ret=false;
        for(int k=0;k<l;k++){
            Board board1=close.get(k);
            if(board.h!=board1.h)continue;
            ret=true;
            for(int i=0;i<r&&ret;i++) {
                for (int j = 0; j < c; j++){
                    if (board1.matrix[i][j] != board.matrix[i][j]) {
                        ret = false;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public boolean isContainedInOpen(Board board){
        int r=board.matrix.length,c=board.matrix[0].length;
        boolean ret=false;
        int l=open.size();
        Board[] temp=new Board[l];
        open.toArray(temp);

        for(int k=0;k<l;k++){
            Board board1=temp[k];
            if(board.h!=board1.h)continue;
            int [][] matrix=board1.matrix;
            ret=true;
            for(int i=0;i<r&&ret;i++) {
                for (int j = 0; j < c; j++){
                    if (matrix[i][j] != board.matrix[i][j]) {
                        ret = false;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public void printPath(Board current){
        if(current==null)return;
        printPath(current.parent);
        current.printMatrix();
        System.out.println();
    }
}
