package kakao_2020;

import java.lang.StringBuilder;

public class BracketConversion {
	
	static String solution(String p) {
        String answer = recursive(p);
        return answer;
    }
	
	static String recursive(String str) {
		
		if(str.length() == 0) return "";
		else {
			StringBuilder sb = new StringBuilder();
			int cnt = 0;
			int idx = -1;
			boolean isRight = true;
			for(int i=0;i<str.length();i++) {
				char c = str.charAt(i);
				
				if(c == '(') {
					cnt++;
					isRight = false;
				}
				else {
					cnt--;
					isRight = true;
				}
				
				sb.append(c);
				
				if(cnt == 0) {
					idx = i;
					break;
				}
			}
			
			String u = sb.toString();
			String v = str.substring(idx+1,str.length());
			StringBuilder sb2 = new StringBuilder();
			if(isRight) {
				return sb2.append(u).append(recursive(v)).toString();
			}else {
				sb2.append('(');
				sb2.append(recursive(v));
				sb2.append(')');
				String[] temp = u.substring(1,u.length()-1).split("");
				for(String str_temp:temp) {
					if(str_temp.equals("(")) {
						str_temp = ")";
					}else if(str_temp.equals(")")) {	// 실수  
						str_temp = "(";
					}
					sb2.append(str_temp);
				}
				//sb2.append(temp.toString());
				return sb2.toString();
			}
		}
		
	}
	
	

	public static void main(String[] args) {
		
		String p = "()))((()";
		String ans = solution(p);
		System.out.println(ans);
		

	}

}
