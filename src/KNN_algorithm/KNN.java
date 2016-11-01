package KNN_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class KNN {
	private ArrayList<ArrayList<Double>> trainData; 
	private int k;
	
	public KNN(ArrayList<ArrayList<Double>> trainData){
		this.trainData=trainData;
		initDiastance();
		int temp=(int)Math.sqrt(trainData.size());
		this.k=(temp%2==0)?temp+1:temp;
	}
	
	void initDiastance(){
		for(ArrayList<Double> list: this.trainData){
			list.add(0.0);
		}
	}
	public double getAccuracy(ArrayList<ArrayList<Double>> testData){
		double accuracy=0.0;
		ArrayList<Double> decisions=getDecisionList(testData);	
		int testSize=testData.size();
		for(int i=0;i<testSize;i++){
			accuracy+=(testData.get(i).get(0)-decisions.get(i)==0)? 1.0 : 0.0;
		}
		accuracy=(accuracy/testSize)*100;
		return accuracy;
	}
	
	public ArrayList<Double> getDecisionList(ArrayList<ArrayList<Double>> testData){
		ArrayList<Double> decisions=new ArrayList<Double>();	
		for(ArrayList<Double> test:testData){
			for(ArrayList<Double> train: this.trainData){
				train.set(train.size()-1, getDistance(train, test));
			}
			Collections.sort(this.trainData, new Comparator<ArrayList<Double>>() {
				@Override
				public int compare(ArrayList<Double> arg0,ArrayList<Double> arg1) {
					return arg0.get(arg0.size()-1).compareTo(arg1.get(arg1.size()-1));
				}
			});
			
			int [] counter={0,0,0,0};
			for(int i=0;i<this.k;i++){
				//System.out.println(this.trainData.get(i));
				double d=this.trainData.get(i).get(0);
				counter[(int)d]++;
			}
			//System.out.println();
			int decision=0,max=0;
			for(int i=1;i<counter.length;i++){
				if(counter[i]>max){
					max=counter[i];
					decision=i;
				}
			}
			decisions.add((double)decision);
		}
		return decisions;
	}
		
	public double getDistance(List<Double> list1,List<Double> list2){
		double distance=0.0;
		for(int i=1;i<list1.size()-1;i++){
			distance+=(list1.get(i)-list2.get(i))*(list1.get(i)-list2.get(i));
		}
		return Math.sqrt(distance);
	}
	
	public static void main(String[] args){
		ArrayList<ArrayList<Double>> train=new FileReader("train.txt").getRecords();	
		ArrayList<ArrayList<Double>> test=new FileReader("test.txt").getRecords();
		
		System.out.println("The accuracy is  : "+new KNN(train).getAccuracy(test)+"%");
	}	
}
