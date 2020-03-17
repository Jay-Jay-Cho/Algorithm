package graph;

import java.util.HashSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
public class Rank {
	
	static int solution(int n, int[][] results) {
        int infinite = Integer.MAX_VALUE/3;	// 실
        int answer = 0;
        int[][] map = new int[n+1][n+1];
        
       
        
        for(int i=1;i<map.length;i++) {
        	for(int j=1;j<map.length;j++) {
        		if(i==j) map[i][j] = 0;
        		else map[i][j] = infinite;
        	}
        }
        
        for(int i=0;i<results.length;i++){
            int win = results[i][0];
            int lose = results[i][1];
            map[win][lose]=1;
        }  
        
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++) {
            	for(int j=1;j<=n;j++) {
            		if(map[i][j] > map[i][k]+map[k][j]) {
            			map[i][j] = map[i][k]+map[k][j];
            		}
            	}
            }
        }
        
        boolean[] flag = new boolean[n+1];
        Arrays.fill(flag, true);
        
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
        		if(i==j) continue;
        		if(map[i][j]==infinite && map[j][i]==infinite) {
        			flag[i]=false;
        			break;
        		}
        	}
        }
        
        for(int i=1;i<flag.length;i++) {
        	if(flag[i]) answer++;
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int n = 8;
		int[][] results = {{1, 2}, {2, 3}, {3, 4}, {5, 6}, {6, 7}, {7, 8}};
		int answer = solution(n,results);
		System.out.println(answer);

	}

}
