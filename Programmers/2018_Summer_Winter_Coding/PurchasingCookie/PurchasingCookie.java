package summer_winter_coding_2018;
import java.util.*;
public class PurchasingCookie {
	
	static int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;
        int[] sum = new int[n+1];
        for(int i=0;i<n;i++) {
        	sum[i+1] = sum[i]+cookie[i];
        }
        
        for(int m=1;m<n;m++) {
        	int child_1 = sum[m];
        	for(int r=m+1;r<=n;r++) {
        		int child_2 = sum[r]-child_1;
        		if(answer > child_2 || child_2 > child_1) continue;
        		for(int l=0;l<m;l++) {
    				if(child_2 == child_1 - sum[l]) {
        				answer = Math.max(answer, child_2);
        				break;
        			}
        		}
        	}
        }
        
        
        return answer;
    }

	public static void main(String[] args) {
		int[] cookie = {1,1,2,3};
		int ans = solution(cookie);
		System.out.println(ans);
	}

}
