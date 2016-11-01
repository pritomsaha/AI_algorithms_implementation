package A_star_aligorithmImpMaze;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Akash on 13-Aug-16.
 */
public class GridPane extends JPanel{
    int row,col;
    public GridPane(int row,int col){
        this.row=row;this.col=col;
        setLayout(new GridLayout(row, col));
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

    }

    public void draw(int x,int y){
        for(int i = 0; i <row; i++)
            for (int j=0;j<col;j++ ) {
                JPanel pan = new JPanel();

                pan.setEnabled(true);
                //if(i==x&&j==y)pan.setBackground(Color.red);
                pan.setBackground(Color.red);

                pan.setPreferredSize(new Dimension(3, 3));
                pan.setBorder(BorderFactory.createLineBorder(Color.black));
                add(pan);
            }
    }
}
