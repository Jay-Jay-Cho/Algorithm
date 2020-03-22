package greedy;

import java.util.Arrays;

public class Lifeboat {
	
	static int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);

        int start = 0;
        int cnt = people.length;
        boolean[] visit = new boolean[people.length];
        while(cnt>0) {
        	if(cnt == 1) {
        		answer++;
        		break;
        	}
        	int small = people[start];
        	visit[start] = true;
        	cnt--;
        	
        	for(int i=people.length-1;i>=start;i--) {
        		if(start==i) break;
        		else {
        			if(small + people[i] <= limit && !visit[i]) {
        				visit[i] = true;
        				cnt--;
        				break;
        			}
        		}
        	}
        	answer++;
        	start++;
        }
        
        return answer;
    }
	
	static int solution2(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int start = 0;
        
        for(int i=people.length-1;i>=start;i--){
            if(start==i){
                answer++;
                break;
            }
            if(people[start] + people[i] <= limit){
                answer++;
                start++;
            }else{
                answer++;
            }
        }
        
        return answer;
    }
	

	
	
	

	public static void main(String[] args) {
		int[] people = {70,80,50,50};
		int[] people2 = {70,80,50};
		int[] people3 = {40,110};
		int limit = 120;
		
		int temp = solution2(people3,limit);
		System.out.println(temp);

	}

}
