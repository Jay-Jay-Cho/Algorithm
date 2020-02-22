package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class Programmers_RamenFactory {
	
	static int getAnswer(int stock, int[] dates, int[] supplies, int k) {
		if(stock>=k) return 0;
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		int answer=0;
		int idx = 0;
		
		for(int day=0;day<k;day++) {
			if(idx<dates.length && day==dates[idx]) {
				q.offer(supplies[idx]);
				idx++;
			}
			if(stock==0) {
				answer++;
				stock+=q.poll();
			}
			stock--;
		}
		return answer;
	}

	public static void main(String[] args) {
		int stock = 4;
		int[] dates = {4,10,15};
		int[] supplies = {20,5,10};
		int k = 30;
		System.out.println(getAnswer(stock,dates,supplies,k));
	}

}
