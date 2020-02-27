package back_tracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6603_Lotto {
	
	static void comb(int[] arr, boolean[] visited, int depth, int r) {
		if(r==0) {
			for(int i=0;i<arr.length;i++) {
				if(visited[i]) System.out.print(arr[i]+" ");
			}
			System.out.println();
		}
		for(int i=depth;i<arr.length;i++) {
			visited[i]=true;
			comb(arr,visited,i+1,r-1);
			visited[i]=false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean check = true;
		while(check) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			String N = st.nextToken();
			if(N.equals("0")) {
				check = false;
				break;
			}else {
				int K = Integer.parseInt(N);
				int[] S = new int[K];
				boolean[] visited = new boolean[K];
				for(int i=0;i<K;i++) {
					S[i] = Integer.parseInt(st.nextToken());
				}
				comb(S,visited,0,6);
				System.out.println();
			}
			
		}
	}

}
