package heap;

import java.util.PriorityQueue;

public class Programmers_MoreSpicy {
	
	
	static int getAnswer(int[] arr, int k) {
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for(int i=0;i<arr.length;i++) q.offer(arr[i]);
		if(q.size()==1) {
			if(q.peek()>=k) {
				return 0;
			}else return -1;
		}else if(q.size()==2) {
			if(q.poll()+(2*q.peek())>=k) return 1;
			else return -1;
		}else {
			int cnt = 0;
			while(!q.isEmpty()) {
				int first = q.poll();
				if(first >= k) return cnt;
				else if(first < k && q.size()==0) return -1;
				int second = q.poll();
				
				int new_food = first + (2*second);
				q.offer(new_food);
				cnt++;
			}
			return -1;
		}
		
		
	}

	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		System.out.println(getAnswer(scoville,K));

	}

}
