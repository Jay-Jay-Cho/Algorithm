package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
public class FarthestNode {
	
	static int solution(int n, int[][] edge) {
        int answer = 0;
        Queue<Integer> q=  new LinkedList<Integer>();
        int[] distance = new int[n+1];
        //int[][] map = new int[n+1][n+1];
        boolean[] visited = new boolean[n+1];
        
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
        for(int i=0;i<=n;i++) {
        	map.put(i, new ArrayList<Integer>());
        }
        for(int i=0;i<edge.length;i++) {
        	int from = edge[i][0];
        	int to = edge[i][1];
        	map.get(from).add(to);
        	map.get(to).add(from);
        }
        
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
        	int from = q.peek();
        		
        		for(int to:map.get(from)) {
        			if(!visited[to]) {
        				q.add(to);
        				distance[to]+=distance[from]+1; // 실수 
        				visited[to] = true;
        			}
        		}
        		
        		
//        		if(map[from][i]==1 && !visited[i]) {
//        			q.add(i);
//        			distance[i]+=distance[from]+1; // 실수 
//        			visited[i] = true;
//        		}
        		
        		
        		
        	
        	q.poll();
        }
        
        Arrays.sort(distance);
        int max = distance[distance.length-1];
        for(int i=distance.length-1;i>=0;i--) {
        	if(distance[i]<max) break;
        	answer++;
        }
        
        
        return answer;
    }
	
	
	
	

	public static void main(String[] args) {
		int n = 6;
		int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		int answer = solution(n,vertex);
		System.out.println(answer);
	}

}
