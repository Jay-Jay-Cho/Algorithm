package kakao_2019;
import java.util.*;
public class CandidateKey {
	
	static Queue<ArrayList<Integer>> q;
	
	static void combination(int[] arr, boolean[] visited, int depth, int r) {
		if(r==0) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<arr.length;i++) {
				if(visited[i]) list.add(arr[i]);
			}
			q.offer(list);
			return;
		}
		
		for(int i=depth;i<arr.length;i++) {
			visited[i] = true;
			combination(arr,visited,i+1,r-1);
			visited[i] = false; // backtracking
		}
	}
	
	static int solution(String[][] relation) {
        ArrayList<ArrayList<Integer>> answer_list = new ArrayList<ArrayList<Integer>>();
        int row_cnt = relation.length;
        int col_cnt = relation[0].length;
        int[] arr = new int[col_cnt];
        for(int i=0;i<col_cnt;i++) arr[i] = i;
        boolean[] visited = new boolean[arr.length];
        q = new LinkedList<ArrayList<Integer>>();
        for(int i=1;i<=col_cnt;i++) {
        	combination(arr,visited,0,i);
        }
        
        while(!q.isEmpty()) {
        	ArrayList<Integer> list = q.poll();
        	HashSet<String> set = new HashSet<String>();
        	boolean check = true;
        	
        	if(list.size()==1) {
        		int key_idx = list.get(0);
        		for(int i=0;i<relation.length;i++) {
        			String[] row = relation[i];
        			String value = row[key_idx];
        			if(set.contains(value)) {
        				check = false;
        				break;
        			}else {
        				set.add(value);
        			}
        		}
        	}else {
        		//check minimality
        		boolean isMinimal = true;
        		for(int i=0;i<answer_list.size();i++) {
        			ArrayList<Integer> exist_list = answer_list.get(i);
        			for(int k=0;k<exist_list.size();k++) {
        				
        			}
        			
        			
        			if(list.containsAll(exist_list)) {
        				isMinimal = false;
        				break;
        			}
        		}
        		if(!isMinimal) continue;
        		
        		
        		for(int i=0;i<relation.length;i++) {
        			String[] row = relation[i];
        			StringBuilder sb = new StringBuilder();
        			for(int key_idx=0;key_idx<list.size();key_idx++) {
        				sb.append(row[list.get(key_idx)]);
        			}
        			String value = sb.toString();
        			if(set.contains(value)) {
        				check = false;
        				break;
        			}else {
        				set.add(value);
        			}
        		}
        	}
        	
        	if(check && set.size()==row_cnt) {
        		answer_list.add(list);
        	}
        }
        
        int answer = answer_list.size();
        return answer;
    }

	public static void main(String[] args) {
		String[][] relation = {{"100","ryan","music","2"},
			                    {"200","apeach","math","2"},
			                    {"300","tube","computer","3"},
			                    {"400","con","computer","4"},
			                    {"500","muzi","music","3"},
			                    {"600","apeach","music","2"}};
		
		String[][] relation2 = {{"a","b","c"},
				                {"1","b","c"},
				                {"a","b","4"},
				                {"a","5","c"}};
		
		String[][] relation3 = {{"b","2","a","a","b"},
								{"b","2","7","1","b"},
								{"1","0","a","a","8"},
								{"7","5","a","a","9"},
								{"3","0","a","f","9"}};
		
		String[][] relation4 = {{"ab", "c"}, {"a", "bc"}, {"x", "yz"}, {"x", "c"}};
		
		System.out.println(solution(relation3));
	}

}
