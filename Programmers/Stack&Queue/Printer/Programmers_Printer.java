package stack_and_queue;
import java.util.*;
public class Programmers_Printer {
	
	static int solution(int[] priorities, int location) {
        int answer = 0;
        int n = priorities.length;
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder()); //priority
        Queue<Integer> q = new LinkedList<Integer>();	//index
        for(int i=0;i<n;i++) {
        	q.add(i);
        	heap.add(priorities[i]);
        }
        
        boolean flag = true;
        while(!heap.isEmpty() && flag) {
        	answer++;
        	int max = heap.poll();
        	while(true) {
        		int temp_idx = q.peek();
        		int temp_priority = priorities[temp_idx];
        		if(temp_priority == max) {
        			q.poll();
        			if(temp_idx == location) {
        				flag = false;
        			}
    				break;
        		}else {
        			q.poll();
        			q.add(temp_idx);
        		}
        	}
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int[] priorities = {2,1,3,2};
		int location = 2;
		int ans = solution(priorities,location);
		System.out.println(ans);

	}

}
