package dp;

public class CardGame {
	
	static int solution(int[] left, int[] right) {
		int answer = 0;
		int length = left.length;
		int[][] dp = new int[length+1][length+1];
			
		dp[0][0] = 0;
		
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(left[i]>right[j]) {
					dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j]+right[j]);
					answer = Math.max(answer, dp[i][j+1]);
				}else {
					dp[i+1][j+1] = Math.max(dp[i+1][j+1],dp[i][j]);
					dp[i+1][j] = Math.max(dp[i+1][j],dp[i][j]);
				}
			}
		}
		
		return answer;
	}
	
	
	
	public static void main(String[] args) {
		int[] left = {1,1,1,1,3};
		int[] right = {2,3,1,1,1};
		
		System.out.println(solution(left, right));
	}

}
