package dfs_and_bfs;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;

public class Programmers_TravelRoute {
	
	static boolean[] visit;
	static ArrayList<String> list = new ArrayList<String>();

	static String[] solution(String[][] tickets) {
        String[] answer;
        
        
        for(int i=0;i<tickets.length;i++) {
        	if(tickets[i][0] == "ICN") {
        		visit = new boolean[tickets.length];
        		
        		visit[i] = true;
        		StringBuilder path = new StringBuilder("ICN").append(",").append(tickets[i][1]);
        		dfs(tickets,tickets[i][1],path,1);
        	}
        }
        
        Collections.sort(list);
        answer = list.get(0).split(",");
        
        return answer;
    }
	
	static void dfs(String[][] tickets, String dest, StringBuilder path, int cnt) {
		if(cnt == visit.length) {
			list.add(path.toString());
			return;
		}else {
			for(int i=0;i<visit.length;i++) {
				if(tickets[i][0].equals(dest) && !visit[i]) {
					visit[i] = true;
					path.append(",").append(tickets[i][1]);
					dfs(tickets,tickets[i][1],path,cnt+1);
					path.delete(path.length()-4, path.length());	// 실수  
					visit[i] = false;	
				}
			}
		}
	}


	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
		String[][] tickets3 = {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}, {"BOO", "DOO"}};
		String[] answer = solution(tickets2);
		System.out.println(answer);
	}

}
