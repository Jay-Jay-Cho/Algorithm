package greedy;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
public class SpeedCamera {
	
	static int solution(int[][] routes) {
        int answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
        int[] arr = new int[60001];
        Arrays.sort(routes, new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		int o1_size = Math.abs(o1[0]-o1[1]);
        		int o2_size = Math.abs(o2[0]-o2[1]);
        		if(o1_size <= o2_size) return -1;
        		else return 1;
        	}
        });
        
        
        for(int i=0;i<routes.length;i++) {
        	int start = routes[i][0] + 30000;
        	int end = routes[i][1] + 30000;
        	int temp = i+1;
        	int min = temp;
        	for(int j=start;j<=end;j++) {
        		if(arr[j]==0) {
        			arr[j] = temp;
        		}else {
        			if(min > arr[j]) min = arr[j];
        		}
        	}
        	q.offer(min);
        }
        answer = q.peek();
        return answer;
    }
	
	
	static int solution2(int[][] routes) {
		int answer = 0;
		
		Arrays.sort(routes, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[1] <= b[1]) return -1;
				else return 1;
			}
		});
		
		int temp = Integer.MIN_VALUE;
		
		for(int i=0;i<routes.length;i++) {
			int start = routes[i][0];
			int end = routes[i][1];
			if(temp < start) {
				temp = end;
				answer++;
			}
		}
		
		return answer;
	}


	public static void main(String[] args) {
		int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
		int[][] routes2 = {{0,0},{0,0},{2,2}};
		
		
		
		int ans = solution(routes);
		System.out.println(ans);

	}

}
