package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Programmers_DoublePriorityQueue {
	
	
	static int[] getAnswer(String[] operations) {
		
		int[] answer = new int[] {0,0};
		
		PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();
		PriorityQueue<Integer> max_heap = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for(int i=0;i<operations.length;i++) {
			String[] cmd = operations[i].split(" ");
			if(cmd[0].equals("I")) { //insert
				int value = Integer.parseInt(cmd[1]);
				min_heap.offer(value);
				max_heap.offer(value);
			}else { //delete
				if(min_heap.isEmpty()) {
					continue;
				}else {
					if(cmd[1].equals("-1")) { // min
						int min_value = min_heap.peek();
						max_heap.remove(min_value);
						min_heap.poll();
					}else {	// max
						int max_value = max_heap.peek();
						min_heap.remove(max_value);
						max_heap.poll();
					}
				}
			}
			
			if(i==operations.length-1) {
				if(min_heap.isEmpty()) answer =  new int[] {0,0};
				else {
					answer =  new int[] {max_heap.peek(),min_heap.peek()};
				}
			}
		}
		
		
		
		return answer;
	}
	
	

	public static void main(String[] args) {
		String[] operations = {"I 7","I 5","I -5","D -1"};
		String[] operations2 = {"I 16","D 1"};
		System.out.println(Arrays.toString(getAnswer(operations2)));
	}

}
