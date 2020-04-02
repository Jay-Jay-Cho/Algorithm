package stack_and_queue;
import java.util.*;
public class Programmers_FunctionDevelopment {
	
	static int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        int n = progresses.length;
        for(int i=0;i<n;i++) {
        	int day = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        	q.add(day);
        }
        
        int criteria = 0;
        int cnt = 0;
        while(!q.isEmpty()) {
        	if(cnt == 0) {
        		criteria = q.poll();
        		cnt++;
        	}
        	else {
        		int temp = q.peek();
        		if(criteria >= temp) {
        			cnt++;
        			q.poll();
        		}else {
        			list.add(cnt);
        			cnt = 0;
        			//break;
        		}
        	}
        }
        if(cnt!=0) list.add(cnt); 
        
        answer = new int[list.size()];
        for(int i=0;i<list.size();i++) answer[i] = list.get(i);
        
        return answer;
    }

	public static void main(String[] args) {
		int[] progresses = {93,30,55};
		int[] speeds = {1,30,5};
		int[] ans = solution(progresses,speeds);
		for(int val:ans) System.out.print(val+" ");
		

	}

}
