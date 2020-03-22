package greedy;

import java.util.ArrayList;
import java.util.List;

public class Island_Prim {
	
	// Prim
	static int solution(int n, int[][] costs) {
        int answer = 0;
        
        boolean[] visit = new boolean[n];
        int[][] maps = new int[n][n];
        ArrayList<Integer> list = new  ArrayList<Integer>();
        for(int i=0;i<costs.length;i++) {
        	int from = costs[i][0];
        	int to = costs[i][1];
        	int cost = costs[i][2];
        	maps[from][to] = cost;
        	maps[to][from] = cost;
        }
        
        // initialize
        list.add(0);
        visit[0] = true;
        
        while(list.size()<n) {
        	int min = Integer.MAX_VALUE;
        	int min_idx = -1;
        	
        	for(int i=0;i<list.size();i++) {
        		int v = list.get(i);
        		for(int j=0;j<n;j++) {
        			if(maps[v][j]!=0 && !visit[j] && min > maps[v][j]) {
        				min = maps[v][j];
        				min_idx = j;
        			}
        		}
        	}
        	list.add(min_idx);
        	visit[min_idx] = true;
        	answer+=min;
        }
        
        return answer;
    }
	
	

	
	
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		int[][] costs2 = {{0,1,1},{0,2,2},{1,2,5},{1,3,3},{2,3,8},{3,4,1}};
		int n2 = 5;
		int ans = solution(n2,costs2);
		System.out.println(ans);

	}

}
