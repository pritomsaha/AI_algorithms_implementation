package NeuralNetwork;

import java.util.Random;

/**
 * Created by Akash on 27-Sep-16.
 */
public class Perceptron {
    float[] weights={.3F,-.1F};
    float learningRate;
    float threshold;

    public Perceptron(int n,float threshold,float learningrate){
    	this.threshold=threshold;
    	this.learningRate=learningrate;
        /*for(int i=0;i<n;i++) {
            weights[i] = 0 + 1 * (new Random().nextfloat());
        }*/
    }

    private int activate(float sum) {
        if (sum < threshold) return 0;
        else return 1;
    }

    private int feedForward(float[] inputs){
        float sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i]*weights[i];
        }
        return activate(sum);
    }

    public boolean train(float[] inputs, int desired) {
        float error;
        int guess = feedForward(inputs);
        error = desired - guess;
        
        for (int i = 0; i < weights.length; i++) {
        	weights[i] += learningRate * error * inputs[i];
        }
        return (error==0);
    }
}
