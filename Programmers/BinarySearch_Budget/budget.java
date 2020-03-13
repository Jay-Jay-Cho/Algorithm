package binary_search;

import java.util.Arrays;

public class budget {
	
	static int solution(int[] budgets, int M) {
        int answer = 0;
        
        Arrays.sort(budgets);
        
        long sum = 0;
        for(int budget: budgets) 
        	sum += budget;
        
        if(sum <= M) return budgets[budgets.length-1];
        
        // 가장 큰 예산요청을 최대값으로 설정
        int max = budgets[budgets.length-1];
        int min = budgets[0];
        int mid = 0;
        int compareMid = 0;
        
        while(true){
        	mid = (int) Math.ceil((max+min) / 2);
        	sum = 0;
        	
        	for(int budget: budgets) {
				if(budget > mid) {
        			sum += mid;
        		}else {
        			sum += budget;
        		}
        	}
            // 더 이상 상한값의 변화가 없다면 종료
    		if(mid == compareMid){
    			answer = mid;
    			break;
    		}
        	
        	if(sum > M){
        		max = mid;
        	}else {
        		min = mid;
        	}
        	
        	compareMid = mid;
        }
        
        return answer;
    }
	
	

	public static void main(String[] args) {
		int[] budget = {120, 110, 140, 150};
		int[] budget2 = {1,1,1,99,99};
		int M2 = 200;
		int M = 485;
		System.out.println(solution(budget,M));
	}

}
