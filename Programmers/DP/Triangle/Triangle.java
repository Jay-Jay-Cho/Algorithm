package dp;

import java.util.Collections;
import java.util.PriorityQueue;

public class Triangle {

	public int getAnswer(int[][] triangle){
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        for(int i=1;i<n;i++){
            dp[i][0] = triangle[i][0] + dp[i-1][0];
            dp[i][i] = triangle[i][i] + dp[i-1][i-1];
        }
        
        for(int i=2;i<n;i++){
            for(int j=1;j<i;j++){
                dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1],dp[i-1][j]);
                if(i==n-1){
                    q.add(dp[i][j]);
                }
            }
        }
        return q.poll();
        
    }
	
	

	public static void main(String[] args) {
		int[][] a = {{2,2},{2,2},{2,2}};
		System.out.println(a[1].length);

	}

}
