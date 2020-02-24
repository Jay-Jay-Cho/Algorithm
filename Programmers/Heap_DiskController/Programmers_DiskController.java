package heap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
public class Programmers_DiskController {
	
	static int getAnswer(int[][] jobs) {

		
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]<o2[1]) return -1;
				else if(o1[1]==o2[1]) {
					if(o1[0]<o2[0] || o1[0]==o2[0]) return -1;
					else return 1;
				}else return 1;
			}
		});
		
		for(int i=0;i<jobs.length;i++) {
			q.offer(jobs[i]);
		}
		
		ArrayList<int[]> list = new ArrayList<int[]>();
		for(int i=0;i<jobs.length;i++) {
			list.add(q.poll());
		}
		
		int answer = 0;
		int time = 0;
		
		while(!list.isEmpty()) {
			
			for(int i=0;i<list.size();i++) {
				if(time>=list.get(i)[0]) {
					int[] temp = list.get(i);
					int startTime = temp[0];
					int workTime = temp[1];
					answer += workTime+time-startTime;
					time += workTime;
					list.remove(i);
					break;
				}
				if(i==list.size()-1) time++;
			}
		}
		return answer/jobs.length;
	}

	public static void main(String[] args) {
		int[][] jobs = {{0, 3},{1, 9},{500, 6}};
		System.out.println(getAnswer(jobs));
		

	}

}
