package A_star_aligorithmImpMaze;

import javax.swing.*;

/**
 * Created by Akash on 13-Aug-16.
 */
public class MazePanel extends JPanel{
    JLabel label1=new JLabel();
    JTextField rowNumTextField=new JTextField(),columnNumTextField=new JTextField();
    JLabel label2=new JLabel();
    JLabel label3=new JLabel();
    JTextField sXTextField=new JTextField(),sYTextField=new JTextField();
    JTextField dXTextField=new JTextField(),dYTextField=new JTextField();
    JButton defaultButton=new JButton();
    JButton changedButton=new JButton();
    JButton generateButton=new JButton();
    GridPane gridPane;

    public MazePanel(){
        setLayout(null);

        label1.setText("Number of row and column (r,c) ");
        label1.setBounds(10, 10, 200, 20);
        add(label1);

        rowNumTextField.setBounds(200, 10, 30, 20);
        add(rowNumTextField);

        columnNumTextField.setBounds(240, 10, 30, 20);
        add(columnNumTextField);

        label2.setText("Source Cell (x,y) ");
        label2.setBounds(10, 40, 200, 20);
        add(label2);

        sXTextField.setBounds(200, 40, 30, 20);
        add(sXTextField);

        sYTextField.setBounds(240, 40, 30, 20);
        add(sYTextField);

        label3.setText("Destination Cell (x,y) ");
        label3.setBounds(10, 70, 200, 20);
        add(label3);

        dXTextField.setBounds(200, 70, 30, 20);
        add(dXTextField);

        dYTextField.setBounds(240, 70, 30, 20);
        add(dYTextField);

        generateButton.setText("Generate Maze");
        generateButton.setBounds(290, 10, 150, 20);
        add(generateButton);

        defaultButton.setText("Default Maze");
        defaultButton.setBounds(290, 40, 150, 20);
        add(defaultButton);

        changedButton.setText("Change Walls");
        changedButton.setBounds(290, 70, 150, 20);
        add(changedButton);

        gridPane=new GridPane(10,5);
        gridPane.draw(0,1);
        gridPane.setBounds(5,100,400,400);
        add(gridPane);


    }

}
