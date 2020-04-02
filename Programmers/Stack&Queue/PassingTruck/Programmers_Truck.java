package stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_Truck {
	
	static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int time = 0;
        int entry = 0;
        int temp_weight = 0;
        int complete = 0;
        int end = truck_weights.length-1;
        
        Queue<Truck> q = new LinkedList<Truck>();
        
        while(complete < truck_weights.length) {
        	
        	//insert new truck
        	if(entry <= end && time > 0 && temp_weight + truck_weights[entry] <= weight) {
        		Truck truck = new Truck(truck_weights[entry],1);
        		q.add(truck);
        		temp_weight += truck_weights[entry];
        		entry++;
        	}
        	
        	// remove truck
        	if(time>0) {
        		Truck temp_truck = q.peek();
        		if(temp_truck.time >= bridge_length) {
        			complete++;
        			temp_weight -= temp_truck.weight;
        			answer = time+1;
        			q.poll();
        		}
        	}
        	
        	// time spend
        	int q_size = q.size();
        	for(int i=0;i<q_size;i++) {
        		Truck temp = q.poll();
        		q.add(new Truck(temp.weight,temp.time+1));
        	}
        	
        	time++;
        }
        
        
        return answer;
    }
	
	static class Truck{
		int weight;
		int time;
		Truck(int w, int t){
			weight = w;
			time = t;
		}
	}
	
	public static void main(String[] args) {
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};
		int ans = solution(bridge_length,weight,truck_weights);
		System.out.println(ans);
	}

}
