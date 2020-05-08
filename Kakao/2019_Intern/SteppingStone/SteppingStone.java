package kakao_2019_winter_intern;
import java.util.*;
public class SteppingStone {

	static int solution(int[] stones, int k) {
        int answer = 0;
        
        int min = 1;
        int max = 200000000;
        int mid;
        
        while(min<=max) { //mistake
        	mid = (max+min)/2;
        	int[] copy_stones = stones.clone();
        	for(int i=0;i<stones.length;i++) {
        		copy_stones[i] -= mid;
        	}
        	int check = 0;
        	boolean flag = true;
        	for(int i=0;i<stones.length;i++) {
        		if(copy_stones[i]<0) check++;
        		else check = 0; //mistake
        		
        		if(check==k) {
        			flag = false;
        			break;
        		}
        	}
        	
        	//mistake
        	if(flag) {
        		answer = Math.max(answer, mid);
        		min = mid+1;
        	}else {
        		max = mid-1;
        	}
        }
        
        
        return answer;
    }
	
	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		System.out.println(solution(stones,k));
	}

}
