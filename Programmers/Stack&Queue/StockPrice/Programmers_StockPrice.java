package stack_and_queue;
import java.util.Stack;
public class Programmers_StockPrice {
	
	static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        int n = prices.length;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for(int i=1;i<n;i++) {
        	int current_price = prices[i];
        	while(!stack.isEmpty()) {
        		int temp_idx = stack.peek();
        		int temp_price = prices[temp_idx];
        		if(temp_price > current_price) {
        			answer[temp_idx] = i - temp_idx;
        			stack.pop();
        		}else break;
        	}
        	stack.push(i);
        }
        if(stack.size() != 0) {
        	while(!stack.isEmpty()) {
        		int idx = stack.pop();
        		answer[idx] = (n-1) - idx;
        	}
        }
       
        return answer;
    }

	public static void main(String[] args) {
		int[] prices = {2,2,2,2,3};
		int[] ans = solution(prices);
		for(int val:ans) {
			System.out.print(val+" ");
		}

	}

}
