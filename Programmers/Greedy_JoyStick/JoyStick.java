package greedy;

public class JoyStick {
	
	static int solution(String name) {
        int answer = 0;
        
        answer += Math.abs(String.valueOf(name.charAt(0)).compareTo("A"));
        int cnt = 0;
        boolean[] shouldVisit = new boolean[name.length()];
        int[] distance = new int[name.length()];
        int idx = 0;
        for(int i=1;i<name.length();i++) {
        	if(name.charAt(i) != 'A') {
        		shouldVisit[i] = true;
        		distance[i] = Math.min(i, name.length()-i);
        		cnt++;
        		int temp = Math.abs(String.valueOf(name.charAt(i)).compareTo("A"));
        		answer += Math.min(temp, 26-temp);
        	}else {
        		shouldVisit[i] = false;
        	}
        }
        
        while(cnt>0) {
        	int min = Integer.MAX_VALUE;
        	int min_idx = 0;
        	for(int i=1;i<name.length();i++) {
        		if(shouldVisit[i] && min>=distance[i]) {
        			if(distance[i]==min) {
        				int prev_sum = sum(min_idx,name.length(),shouldVisit);
        				int current_sum = sum(i,name.length(),shouldVisit);
        				if(prev_sum>current_sum) {
        					min = distance[i];
                			min_idx = i;
        				}
        			}else {
        				min = distance[i];
            			min_idx = i;
        			}
        		}
        	}
        	int a = Math.abs(min_idx-idx);
        	answer+=Math.min(a, name.length()-a);
        	shouldVisit[min_idx] = false;
        	idx = min_idx;
        	cnt--;
        	for(int i=1;i<name.length();i++) {
        		if(shouldVisit[i]) {
        			int temp = Math.abs(i-idx);
        			distance[i] = Math.min(temp, name.length()-temp);
        		}
        	}
        }
        
        
        return answer;
    }
	
	static int sum(int idx, int length, boolean[] shouldVisit) {
		int ans = 0;
		for(int i=1;i<length;i++) {
			if(i==idx) continue;
    		if(shouldVisit[i]) {
    			int temp = Math.abs(idx-i);
    			ans += Math.min(temp, length-temp);
    		}
    	}
		return ans;
	}
	
	
	
	
	public static void main(String[] args) {
		String name = "JJAJAAAAAAAAAJ";
		int answer = solution(name);
		System.out.println(answer);
	}

}
