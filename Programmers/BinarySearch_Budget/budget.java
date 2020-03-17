package binary_search;

import java.util.Arrays;

public class budget {
	
	static int solution(int[] budgets, int M) {
        int answer = 0;
        Arrays.sort(budgets);
        int length = budgets.length;
        long sum = 0;
        for(int val:budgets){
            sum+=val;
        }
        if(sum<=M) return budgets[length-1];
        
        int max = budgets[length-1];
        int min = 0;
        int mid = 0;
        
        while(min<=max){
            sum = 0;
            mid = (max+min)/2;
            
            for(int i=0;i<length;i++){
                if(budgets[i]<mid){
                    sum += budgets[i];
                }else
                    sum += mid;
            }
	        if(sum>M){
	            max = mid-1;
	        }else{
	        	answer = Math.max(answer, mid);
	            min = mid+1;
	        }
            
        }
        
        
        
        return answer;
    }
	
	

	public static void main(String[] args) {
		int[] budget = {120, 110, 140, 150};
		int M = 485;
		System.out.println(solution(budget,M));
	}

}
