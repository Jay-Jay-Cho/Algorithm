package kakao_2020;
import java.lang.StringBuilder;
public class StringCompression {
	
	
	static int solution(String s) {
        int answer = s.length();
        
        int max_range = s.length()/2;
        for(int i=1;i<=max_range;i++) {
        	String prev_str = "";
        	int prev_idx = 0;
        	int curr_idx = 0;
        	String[] str_arr = new String[s.length()/i + s.length()%i];
        	int[] cnt_arr = new int[s.length()/i + s.length()%i];
        	for(int j=0;j<s.length();j+=i) {
        		String str;
        		if(j+i<s.length()) {
        			str = s.substring(j,j+i);
        		}else {
        			str = s.substring(j,s.length());
        		}
        		str_arr[curr_idx] = str;
        		if(prev_str.equals(str)) {
        			cnt_arr[prev_idx]++;
        		}else {
        			prev_str = str;
        			prev_idx = curr_idx;
        			cnt_arr[curr_idx]++;
        		}
        		curr_idx++;
        	}
        	
        	
        	StringBuilder sb = new StringBuilder();
        	for(int k=0;k<str_arr.length;k++) {
        		if(cnt_arr[k] == 0) {
        			continue;
        		}else if(cnt_arr[k] == 1) {
        			sb.append(str_arr[k]);
        		}else {
        			sb.append(String.valueOf(cnt_arr[k]));
        			sb.append(str_arr[k]);
        		}
        	}
        	int temp_ans = sb.toString().length();
        	answer = Math.min(answer, temp_ans);
        }
        
        
        return answer;
    }
	
	
	
	

	public static void main(String[] args) {
		String s = "xababcdcdababcdcd";
		int ans = solution(s);
		System.out.println(ans);
	}

}
