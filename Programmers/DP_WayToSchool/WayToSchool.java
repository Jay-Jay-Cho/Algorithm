package dp;

import java.util.Arrays;

public class WayToSchool {

	static int getAnswer(int m, int n, int[][] puddles){
        int[][] dp = new int[n+1][m+1];
       
        int answer = 0;
        dp[1][1] = 1;
        for(int i=0;i<puddles.length;i++){
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(dp[i][j]==-1) continue;
                //from left
                if(dp[i-1][j]!=-1 && dp[i][j]!=-1) {
                	dp[i][j] += dp[i-1][j];
                }
                //from up	
                if(dp[i][j-1]!=-1 && dp[i][j]!=-1) {
                	dp[i][j] += dp[i][j-1];
                }
                
                
            }
        }
        
        return dp[n][m]%1000000007;
    }
	
	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int[][] puddles = {{2,2}};
		System.out.println(getAnswer(4,3,puddles));

	}

}
