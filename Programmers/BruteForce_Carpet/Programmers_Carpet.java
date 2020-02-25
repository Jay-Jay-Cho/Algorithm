package brute_force;

import java.util.Arrays;

public class Programmers_Carpet {
	
	static int[] getAnswer(int brown, int red){
		int[] answer = new int[2];
		int sum = brown + red;
		for(int i=3;i<sum/3;i++) {
			if(sum%i==0) {
				int row = Math.max(sum/i, i);
				int col = Math.min(sum/i, i);
				if((row-2)*(col-2)==red) {
					answer = new int[]{row,col};
					break;
				}
			}
		}
		return answer;
    }

	public static void main(String[] args) {
		int brown = 8;
		int red = 1;
		System.out.println(Arrays.toString(getAnswer(brown,red)));

	}

}
