package binary_search;

import java.util.Arrays;

public class Immigration {
	
	static long solution(int n, int[] times) {
		long answer = Long.MAX_VALUE;
		Arrays.sort(times);
		int length = times.length;
		long max = (long)times[length-1]*n;
		long min = 0;
		long mid = (max+min)/2;
		long sum;
		
		while(min<=max) {
			sum = 0;
			mid = (max+min)/2;
			for(int time:times) {
				sum+=mid/time;
			}
			
			if(sum<n) {
				min = mid+1;
			}else {
				answer = Math.min(answer, mid);
				max = mid-1;
			}
		}
		return answer;
	}
	
	public static void main(String[] args) {
		int n = 6;
		int[] times = {7,10};
		System.out.println(solution(n,times));
	}

}
