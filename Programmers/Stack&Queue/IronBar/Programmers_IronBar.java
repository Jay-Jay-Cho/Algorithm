package stack_and_queue;
import java.util.Stack;
public class Programmers_IronBar {
	
	static int solution(String arrangement) {
        int answer = 0;
        
        int n = arrangement.length();
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<n;i++) {
        	Character c = arrangement.charAt(i);
        	if(c == '(') stack.push(c);
        	else {
        		stack.pop();
        		Character prev = arrangement.charAt(i-1);
        		if(prev==')') {
        			answer++;
        		}else {
            		answer += stack.size();
        		}
        	}
        }
        
        return answer;
    }

	public static void main(String[] args) {
		String arrangement = "()(((()())(())()))(())";
		int ans = solution(arrangement);
		System.out.println(ans);

	}

}
