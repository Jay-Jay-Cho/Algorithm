package binary_search;
import java.util.Arrays;
public class SteppingStone {
	
	static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        int length = rocks.length;
        int[] new_rocks = new int[length+1];
        new_rocks[0] = rocks[0];
        new_rocks[length] = distance - rocks[length-1];
        for(int i=1;i<length;i++){
            new_rocks[i] = rocks[i]-rocks[i-1];
        }
        
        int min = 1;
        int max = distance;
        int mid;
        int cnt;
        int temp;
        while(min<=max) {
        	cnt = 0;
        	temp = 0;
        	mid = (max+min)/2;
        	for(int rock:new_rocks) {
        		if(rock+temp<mid) {
        			cnt++;
        			temp+=rock;
        		}else {
        			temp=0;
        		}
        	}
        	if(cnt<=n) {
        		min = mid+1;
        		answer = Math.max(answer, mid);
        	}else {
        		max = mid-1;
        	}
        }
        return answer;
    }


	public static void main(String[] args) {
		int distance = 25;
		int[] rocks = {2, 14, 11, 21, 17};
		int n =2;
		System.out.println(solution(distance,rocks,n));

	}

}
