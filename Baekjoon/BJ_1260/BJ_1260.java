package dfs_and_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOError;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class BJ_1260 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int a[][] = new int[n+1][n+1];	// 인접행렬 
		boolean visited[] = new boolean[n+1];	// 방문체크 
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			a[node1][node2] = 1;
			a[node2][node1] = 1;
		}
		
		dfs_r(a,visited,v);
		System.out.println();
		Arrays.fill(visited, false);
		//bfs(a,visited,v);
	
	}
	
	static void dfs(int[][] a, boolean[] visited, int startNode) {
		Stack<Integer> stack = new Stack<Integer>();
		int n = a.length-1;	// index로 계산하기위해 length에서 1을 빼줌.
		
		stack.push(startNode);
		visited[startNode] = true;
		System.out.print(startNode+" ");
		
		while(!stack.isEmpty()) {
			int temp = stack.pop();
			
			for(int i=0; i<=n; i++	) {
				if(a[temp][i]==1 && !visited[i]) {
					visited[i] = true;
					stack.push(i);
					System.out.print(i+" ");
					break; // for문을 아예 벗어남 
				}
			}
			
		}
	}
	
	static void dfs_r(int[][] a, boolean[] visited, int startNode) {
		int n = a.length-1;
		visited[startNode] = true;
		System.out.print(startNode+" ");
		for(int i=0;i<=n;i++) {
			if(a[startNode][i]==1 && !visited[i]) {
				dfs_r(a,visited,i);
			}
		}
	}
	
	static void bfs(int[][] a, boolean[] visited, int startNode) {
		Queue<Integer> q = new LinkedList<>();
		int n = a.length-1;	// index로 계산하기위해 length에서 1을 빼줌.
		
		q.add(startNode);
		visited[startNode] = true;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp+" ");
			for(int i=0;i<=n;i++) {
				if(a[temp][i]==1 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
			
		}
	}


}
