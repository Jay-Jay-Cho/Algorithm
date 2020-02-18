package dfs_and_bfs;
import java.util.Stack;
public class Programmers_Network {
	
	public int dfs(int n, int[][] computers) {
		int answer=0;
		boolean[] visited = new boolean[n];
		
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		visited[0]=true;
		answer++;			
		
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				stack.push(i);
				visited[i]=true;
				answer++;
			}
			
			//탐색 시작 
			while(!stack.isEmpty()) {
				int temp = stack.pop();
				for(int j=0;j<n;j++) {
					if(temp==j) continue;
					if(computers[temp][j]==1 && !visited[j]) {
						stack.push(j);
						visited[j]=true;
					}
				}
			}
		}
		return answer;
	}
	

	public static void main(String[] args) {
		int n = 3;
		int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
		Programmers_Network a = new Programmers_Network();
		System.out.println(a.dfs(n, computers));
	}

}
