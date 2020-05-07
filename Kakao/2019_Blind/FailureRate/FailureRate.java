package kakao_2019;
import java.util.*;
public class FailureRate {
	
	static class Node{
		int stage;
		double rate;
		Node(int stage, double rate){
			this.stage = stage;
			this.rate = rate;
		}
	}
	
	static int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		int[] people = new int[N+1];
		double[] rate_arr  = new double[N+1];
		
		int total = stages.length;
		for(int i=0;i<stages.length;i++) {
			if(stages[i]==N+1) continue;
			people[stages[i]]++;
		}
		for(int i=1;i<=N;i++) {
			if(people[i]==0) rate_arr[i]=0;
			else {
				double rate = (double)people[i]/total;
				rate_arr[i] = rate;
				total -= people[i];
			}
		}
		ArrayList<Node> list = new ArrayList<Node>();
        for(int i=1;i<=N;i++) list.add(new Node(i,rate_arr[i]));
        Collections.sort(list, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if(n1.rate > n2.rate) return -1;
				else if(n1.rate == n2.rate) {
					if(n1.stage < n2.stage) return -1;
					else return 1;
				}else return 1;
			}
        });
        for(int i=0;i<list.size();i++) {
        	answer[i] = list.get(i).stage;
        }
        
        for(int val:answer)
        	System.out.println(val);
		
		return answer;
	}
	
	
	

	public static void main(String[] args) {
		int n = 5;
		int[] states = {2, 1, 2, 6, 2, 4, 3, 3};
		int n2 = 4;
		int[] states2 = {4,4,4,4,4};
		int n3 = 5;
		int[] states3 = {1,1,4,4,5};
		solution(n2,states2);
	}

}
