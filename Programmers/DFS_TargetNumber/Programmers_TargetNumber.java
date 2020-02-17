package dfs_and_bfs;
import java.util.*;

public class Programmers_TargetNumber {
	
	class Node{
		int depth;
		int value;
		Node(int depth, int value){
			this.depth = depth;
			this.value = value;
		}
	}
	


	public int dfs(int[] numbers, int target, int answer) {
		
		int length = numbers.length-1;
		Stack<Node> stack = new Stack<Node>();
		stack.push(new Node(-1,0));
		
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			if(node.depth==length && node.value==target) {
				answer+=1;
			}
			
			if(node.depth<length) {
				stack.push(new Node(node.depth+1, node.value+numbers[node.depth+1]));
				stack.push(new Node(node.depth+1, node.value-numbers[node.depth+1]));
			}
			
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		Programmers_TargetNumber t = new Programmers_TargetNumber();
		int numbers[] = {1,1,1,1,1};
		int target = 3;
		int answer = 0;
		System.out.println(t.dfs(numbers, target, answer));
	}
	
}
