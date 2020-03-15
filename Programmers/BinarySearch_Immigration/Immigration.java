package binary_search;

import java.util.Arrays;

public class Immigration {
	
	static long solution(int n, int[] times) {
		long answer = 0;
		Arrays.sort(times);
		int length = times.length;
		long turn = (long)Math.ceil(n/length+n%length);
		long max = times[length-1]*turn;
		long min = 0;
		long avg = (long)Math.ceil((max+min)/2+(max+min)%2);
		long sum;
		long temp_avg = 0;
		
		while(true) {
			sum = 0;
			avg = (long)Math.ceil((max+min)/2+(max+min)%2);
			for(int time:times) {
				sum+=avg/time;
			}
			
			if(temp_avg==avg) {
				answer = avg;
				break;
			}
			
			if(sum<n) {
				min = avg;
			}else {
				max = avg;
			}
			temp_avg = avg;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		int n = 6;
		int[] times = {7,10};
		System.out.println(solution(n,times));

	}

}
