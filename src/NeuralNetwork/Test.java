package NeuralNetwork;

/**
 * Created by Akash on 27-Sep-16.
 */
public class Test {

    public static void main(String[] args){
        float input[]={10,9};
        Perceptron perceptron=new Perceptron(input.length,0.2F,0.1F);
        int desiredAns=getDesiredAns(input[0], input[1]);
        perceptron.train(input,desiredAns);
    }

    public static int getDesiredAns(double x,double y){
        double YLine=2*x+1;
        return (YLine>y)? -1:1;
    }
}
