package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_1931 {
	
	static int N;
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
	
		int[][] meetings = new int[N][2];
		
		StringTokenizer st2;
		int max = -1;
		for(int i=0;i<N;i++) {
			st2 = new StringTokenizer(br.readLine());
			int start_time = Integer.parseInt(st2.nextToken());
			int end_time = Integer.parseInt(st2.nextToken());
			meetings[i][0] = start_time;
			meetings[i][1] = end_time;
			max = Math.max(max, end_time);
		}
		
		boolean[] start_arr = new boolean[max+1];
		boolean[] end_arr = new boolean[max+1];
		
		Arrays.sort(meetings, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[1]<b[1]) return -1;
				else if(a[1] == b[1]) {
					if(a[0]<=b[0]) return -1;
					else return 1;
				}else return 1;
			}
		});
		
		int cnt = 0;
		int pre_end = -1;
		for(int i=0;i<N;i++) {
			int start = meetings[i][0];
			int end = meetings[i][1];
			if(start >= pre_end) {
				cnt++;
				pre_end = end;
			}
		}
		
		System.out.println(cnt);
		
	}

}
