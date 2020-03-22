package greedy;
import java.util.Arrays;

public class Scale {
	
	static int solution(int[] weight) {
        int answer = 0;
        
        Arrays.sort(weight);
        
        int max = weight[0];
        
        for(int i=1;i<weight.length;i++) {
        	int curr = weight[i];
        	if(curr == max+1 || curr <= max) {
        		max += curr;
        	}else {
        		answer = max+1;
        		break;
        	}
        	answer = max+1;	// 실
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int[] weight = {3, 1, 6, 2, 7, 30, 1};
		int[] weight2 = {1,1,3};
		int answer = solution(weight2);
		System.out.println(answer);

	}

}
