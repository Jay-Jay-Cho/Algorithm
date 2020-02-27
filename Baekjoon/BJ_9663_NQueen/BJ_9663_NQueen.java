package back_tracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BJ_9663_NQueen {
	
	static int ans = 0;
	static int n = 0;
	static int[] col;
	
	static void dfs(int row) {
		
		if(row==n-1) {
			ans++;
		}
		for(int i=0;i<n;i++) {
			col[row+1]=i;
			if(isPossible(row+1)) {
				dfs(row+1);
			}
		}
		//col[row] = 0;
	}
	
	static boolean isPossible(int row) {
		
		for(int i=0;i<row;i++) {
			if(col[i]==col[row]) {
				return false;
			}
			if(Math.abs(col[i]-col[row])==Math.abs(i-row)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			col = new int [n+1];
			col[0] = i;
			dfs(0);
		}
		System.out.println(ans);
		long end = System.currentTimeMillis();

		System.out.println( "실행 시간 : " + ( end - start ) );



	}

}
