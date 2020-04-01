package dfs_and_bfs;
import java.util.LinkedList;
import java.util.Queue;
public class HideAndSeek {
	
	static int solution(int Cony, int B) {
		int answer = 0;		
		
		Queue<Brown> q = new LinkedList<Brown>();
		q.add(new Brown(B,0));
		
		int time = 0;
		int current_C = Cony;
		Brown current_B;
		boolean flag = true;
		while(flag) {
			current_C += time;
			
			if(current_C > 20000) {
				answer = -1;
				break;
			}
			
			int q_size = q.size();
			for(int i=0;i<q_size;i++) {
				current_B = q.poll();
				
				if(current_C == current_B.p) {
					answer = time;
					flag=false;
				}
				
				if(current_B.p >= 1) q.add(new Brown(current_B.p-1,current_B.time+1));
				if(current_B.p +1 <= 200000) q.add(new Brown(current_B.p+1,current_B.time+1));
				if(2*current_B.p <= 200000) q.add(new Brown(2*current_B.p,current_B.time+1));
			}
			
			time++;
		}
		
		return answer;
	}
	
	static class Brown{
		int p;
		int time;
		Brown(int p, int time){
			this.p = p;
			this.time = time;
		}
	}
	

	public static void main(String[] args) {
		int answer = solution(6,6);
		System.out.println(answer);
	}

}
