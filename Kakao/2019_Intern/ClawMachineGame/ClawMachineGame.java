package kakao_2019_winter_intern;
import java.util.*;
public class ClawMachineGame {
	
	static Stack<Integer> result_stack;
	static HashMap<Integer,Stack<Doll>> stack_map;
	
	static class Doll{
		int x;
		int y;
		int type;
		Doll(int x, int y, int type){
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	static int solution(int[][] board, int[] moves) {
        int answer = 0;
        int doll_cnt = 0;
        
        stack_map = new HashMap<Integer,Stack<Doll>>();
        int n = board.length;
        for(int i=n-1;i>=0;i--) {
        	for(int j=n-1;j>=0;j--) {
        		if(board[i][j]!=0) {
        			if(stack_map.get(j)==null) {
        				Stack<Doll> stack = new Stack<Doll>();
	        			stack.push(new Doll(i,j,board[i][j]));
	        			stack_map.put(j, stack);
        			}else {
        				stack_map.get(j).push(new Doll(i,j,board[i][j]));
        			}
        			doll_cnt++;
        		}
        	}
        }
        
        boolean flag = true;
        if(doll_cnt==0) flag=false;
        
        result_stack = new Stack<Integer>();
        if(flag) {
        	for(int i=0;i<moves.length;i++) {
	        	int col=moves[i]-1;
	        	if(stack_map.get(col).isEmpty()) continue;
	        	else {
	        		Doll doll = stack_map.get(col).pop();
	        		board[doll.x][doll.y] = 0;
	        		if(!result_stack.isEmpty() && doll.type==result_stack.peek()) {
	        			answer += 2;
	        			result_stack.pop();
	        		}
	        		else result_stack.push(doll.type);
	        	}
	        }
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		System.out.print(solution(board,moves));

	}

}
