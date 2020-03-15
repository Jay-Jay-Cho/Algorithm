package binary_search;
import java.util.Arrays;
public class SteppingStone {
	
	static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int length = rocks.length;
        Arrays.sort(rocks);

        int[] new_rocks = new int[length+1];
        new_rocks[0] = rocks[0];
        new_rocks[length] = distance-rocks[length-1];
        for(int i=1;i<length;i++) {
        	new_rocks[i] = rocks[i]-rocks[i-1];
        }
        int min = 1;
        int max = distance;
        int mid = (int)Math.ceil((min+max)/2+(min+max)%2);
        int cnt;
        int temp_mid = 0;
        int temp_rock = 0;
        while(true) {
        	cnt = 0;
        	temp_rock = 0;
        	mid = (int)Math.ceil((min+max)/2+(min+max)%2);
        	
        	for(int i=0;i<=length;i++) {
        		if(new_rocks[i]+temp_rock < mid) {
        			//System.out.println("smaller : "+(new_rocks[i]+temp_rock));
        			cnt++;
        			temp_rock+=new_rocks[i];
        		}else {
        			//System.out.println("bigger : "+(new_rocks[i]+temp_rock));
        			temp_rock = 0;
        		}
        		
        	}
        
        	System.out.println();
        	if(temp_mid==mid) {
        		answer = mid;
        		break;
        	}
        	
        	if(cnt>=n) {
        		max = mid;
        	}else {
        		min = mid;
        	}
        	temp_mid = mid;
        	
        	
        }
        
        
        return answer;
    }

	
	static int solution2(int distance, int[] rocks, int n){
		Arrays.sort(rocks);
        long ans = 0;
        long left = 1, right = distance, mid = 0;
    
        while(left <= right){
            int cnt = 0;
            int prev = 0;
            mid = (left + right) / 2;
            
            for(int i = 0 ; i < rocks.length ; ++i){
            	int gap = rocks[i] - prev;
                if(gap < mid){
                	// mid보다 작은 값이 존재한다는 뜻으로
                    // 해당 돌을 제거한다.
                	System.out.println("smaller : "+gap);
                    cnt++;
                } else {
                	System.out.println("bigger : "+gap);
           			// mid보다 크거나 같은 값이 존재하므로
                    // prev를 현재 돌로 초기화한다.
                    prev = rocks[i];
                }
            }
            
            // 마지막 돌과 도착점 사이의 거리도 확인한다.
            if(distance - prev < mid) {
            	System.out.println(distance-prev);
            	cnt++;
            }
            System.out.println();
            if(cnt <= n){
            	// 주어진 n 보다 작거나 같은 만큼 돌을 없애서
                // 최솟값 x를 만들 수 있다.
                ans = mid > ans ? mid : ans;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) ans;
    }

	public static void main(String[] args) {
		int distance = 25;
		int[] rocks = {2, 14, 11, 21, 17};
		int n =2;
		System.out.println(solution(distance,rocks,n));

	}

}
