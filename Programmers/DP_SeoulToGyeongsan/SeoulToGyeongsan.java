package dp;

public class SeoulToGyeongsan {
	
	
	static int solution(int k, int[][] travel) {
		int answer = 0;
		
		int length = travel.length;
		int[][] dp = new int[length][k+1];
		dp[0][travel[0][0]] = travel[0][1];
		dp[0][travel[0][2]] = travel[0][3];
		
		for(int i=1;i<length;i++) {
			int walkingTime = travel[i][0];
			int walkingMoney = travel[i][1];
			int bikingTime = travel[i][2];
			int bikingMoney = travel[i][3];
			
			for(int j=0;j<=k;j++) {
				if(dp[i-1][j]==0) continue;
				
				//walking
				if(j+walkingTime <= k) {
					dp[i][j+walkingTime] = Math.max(dp[i][j+walkingTime], dp[i-1][j]+walkingMoney);
					answer = Math.max(dp[i][j+walkingTime], answer);
				}
				
				//biking
				if(j+bikingTime <= k) {
					dp[i][j+bikingTime] = Math.max(dp[i][j+bikingTime], dp[i-1][j]+bikingMoney);
					answer = Math.max(dp[i][j+bikingTime], answer);
				}
			}
		}
		
		
		return answer;
	}
	
	

	public static void main(String[] args) {
		int K = 3000;
		int[][] travel = {{1000, 2000, 300, 700}, {1100, 1900, 400, 900}, {900, 1800, 400, 700}, {1200, 2300, 500, 1200}};
		int K2 = 1650;
		int[][] travel2 = {{500, 200, 200, 100}, {800, 370, 300, 120}, {700, 250, 300, 90}};
		System.out.println(solution(K,travel));
	
	
	
	}
	
}
