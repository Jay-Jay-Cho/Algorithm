package greedy;

public class MaximumNumber {
	
	static String solution(String number, int k) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        
        int idx = 0;
        int right = k;
        
        while(sb.length()<number.length()-k) {
        	int max = -1;
            int max_idx = 0;
        	for(int i=idx;i<=right;i++) {
            	if(number.charAt(i)-'0' > max) {
            		max = number.charAt(i)-'0';
            		max_idx = i;
            	}
            }
            sb.append(String.valueOf(max));
            idx = max_idx+1;
            right++;
        }
        
        
        return sb.toString();
    }
	
	static String solution2(String number, int k) {
        StringBuilder sb = new StringBuilder();
	    
		int cnt = number.length() - k;
        int left = 0;
        int right = number.length() - cnt;
        int max = -1;
        int idx = 0;
        
        while(cnt > 0) {
        	 max = -1;
             for(int j = left ; j <= right ; ++j){
                 int num = number.charAt(j) - '0';
                 if(num > max){
                     idx = j;
                     max = num;
                 }
             }
             sb.append(number.charAt(idx));
             left = idx + 1;
             right = number.length() - --cnt;
        }

        return sb.toString();
    }

	public static void main(String[] args) {
		String number = "4177252841";
		int k = 4;
		String answer = solution(number,k);
		System.out.println(answer);

	}

}
