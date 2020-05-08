package kakao_2019_winter_intern;
import java.util.*;
public class BadUser {
	
	static boolean[] used;
	static int badUserCnt;
	static String[] banned_id_arr;
	static ArrayList<ArrayList<Integer>> answer_list;
	
	static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        banned_id_arr = banned_id;
        badUserCnt = 0;
        used = new boolean[user_id.length];
        answer_list = new ArrayList<ArrayList<Integer>>();
        
        dfs(user_id,0,0);
        answer = badUserCnt;
        
        return answer;
    }
	
	static void dfs(String[] user_id, int banned_id_index, int cnt) {
		
		if(cnt==banned_id_arr.length) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<used.length;i++) {
				if(used[i]) list.add(i);
			}
			
			if(!answer_list.isEmpty()) {
				boolean flag = true;
				for(int i=0;i<answer_list.size();i++) {
					ArrayList<Integer> exist_list = answer_list.get(i);
					if(list.containsAll(exist_list)) {
						flag=false;
						break;
					}
				}
				if(flag) {
					answer_list.add(list);
					badUserCnt++;
				}
			}else {
				answer_list.add(list);
				badUserCnt++;
			}
			
			
			//badUserCnt++;
			return;
		}
		
		if(banned_id_index>=banned_id_arr.length) return;
		
		String badUserId = banned_id_arr[banned_id_index];
		
    	int star_cnt = 0;
		for(int i=0;i<badUserId.length();i++) {
			if(badUserId.charAt(i)=='*') star_cnt++;
		}
		
		for(int i=0;i<user_id.length;i++) {
    		String userId = user_id[i];
    		if(userId.length() == badUserId.length() && !used[i]) {
    			int same_cnt = 0;
    			for(int j=0;j<userId.length();j++) {
    				if(userId.charAt(j)==badUserId.charAt(j)) same_cnt++;
    			}
    			if(same_cnt+star_cnt==badUserId.length()) {
    				used[i] = true;
    				dfs(user_id,banned_id_index+1,cnt+1);
    				used[i] = false;
    			}
    		}
    	}
	}

	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"}; //2
		String[] banned_id2 = {"*rodo", "*rodo", "******"}; //3
		String[] banned_id3 = {"fr*d*", "*rodo", "******", "******"}; //3
		System.out.println(solution(user_id,banned_id));
	}

}
