package brute_force;

import java.util.HashMap;

public class Programmers_BullsAndCows {
	
	static int getStrike(String check, String num) {
		int cnt = 0;
		for(int i=0;i<3;i++) {
			if(check.charAt(i)==num.charAt(i)) {
				cnt++;
			}
		}
		return cnt;
	}
	
	static int getBall(String check, String num) {
		int cnt = 0;
		for(int i=0;i<3;i++) {
			if(check.contains(String.valueOf(num.charAt(i))) && check.charAt(i)!=num.charAt(i)) {
				cnt++;
			}
		}
		return cnt;
	}
	
	static int getAnswer(int[][] baseball) {
		int answer = 0;
		//get substitutes
		HashMap<String,Boolean> map = new HashMap<String,Boolean>();
		for(int i=1;i<10;i++) {
			for(int j=1;j<10;j++) {
				if(i==j) continue;
				for(int k=1;k<10;k++) {
					if(i==k || j==k) continue;
					String sub = String.valueOf(i).concat(String.valueOf(j)).concat(String.valueOf(k));
					map.put(sub, false);
				}
			}
		}
		
		
			
			for(String num:map.keySet()) {
				int cnt = 0;
				for(int i=0;i<baseball.length;i++) {
					String check = String.valueOf(baseball[i][0]);
					int strike = baseball[i][1];
					int ball = baseball[i][2];
					if(strike==getStrike(check,num) && ball==getBall(check,num)) {
						cnt++;
					}
				}
				if(cnt==baseball.length) answer++;
			}
		
		
		
		
		return answer;
	}

	public static void main(String[] args) {
		int[][] baseball = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
		//System.out.println(getBall("328","489"));
		System.out.println(getAnswer(baseball));
	}

}
