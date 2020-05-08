package kakao_2019_winter_intern;
import java.util.*;
public class Tuple {
	
	static Queue<Integer> q;
	static ArrayList<ArrayList<Integer>> answer_list;
	
	static int[] solution(String s) {
        
        answer_list = new ArrayList<ArrayList<Integer>>();
        String new_s = s.substring(1,s.length()-1);
        
        for(int i=0;i<new_s.length();i++) {
        	char c = new_s.charAt(i);
        	if(c=='{') {
        		q = new LinkedList<Integer>();
        	}else if(c=='}') {
        		ArrayList<Integer> list = new ArrayList<Integer>();
        		while(!q.isEmpty()){
        			list.add(q.poll());
        		}
        		answer_list.add(list);
        	}else if(c==','){
        		continue;
        	}else {//number
        		StringBuilder sb = new StringBuilder();
        		sb.append(c);
        		
        		int idx = i+1;
        		while(idx<new_s.length() && Character.isDigit(new_s.charAt(idx))) {
        			sb.append(new_s.charAt(idx));
        			idx++;
        		}
        		
        		q.offer(Integer.parseInt(sb.toString()));
        		i = idx-1;
        	}
        }
        
        Collections.sort(answer_list, new Comparator<ArrayList<Integer>>() {
			public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
				if(list1.size() < list2.size()) return -1;
				else return 1;
			}
        });
       
        Queue<Integer> answer_q = new LinkedList<Integer>();
        
        for(int i=0;i<answer_list.size();i++) {
        	ArrayList<Integer> list = answer_list.get(i);
        	for(int j=0;j<list.size();j++) {
        		if(answer_q.contains(list.get(j))) continue;
        		else answer_q.add(list.get(j));
        	}
        }
        
        int[] answer = new int[answer_q.size()];
        for(int i=0;i<answer.length;i++) {
        	answer[i] = answer_q.poll();
        }
        return answer;
    }

	public static void main(String[] args) {
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		int[] answer = solution(s);
		for(int val:answer) System.out.print(val+" ");
	}

}
