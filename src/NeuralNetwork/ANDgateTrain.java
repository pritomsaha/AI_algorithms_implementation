package NeuralNetwork;

public class ANDgateTrain {
	
	public static void main(String[] args){
		Perceptron perceptron=new Perceptron(2,0.2F,0.1F);
		
		boolean flag;
		
		do{
			flag=true;
			for(int i=0;i<=1;i++)
				for(int j=0;j<=1;j++){
					float input[]={i,j};
					flag&=perceptron.train(input,i&j);
				}
			System.out.println(perceptron.weights[0]+" "+perceptron.weights[1]);
		}while(flag==false);
		
	}

}
