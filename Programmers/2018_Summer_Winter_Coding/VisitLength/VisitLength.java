package summer_winter_coding_2018;
import java.util.*;
public class VisitLength {
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	
	static int solution(String dirs) {
        int answer = 0;
        
        //int[][] map = new int[11][11];
        HashSet<String> set = new HashSet<String>();
        int x = 5;
        int y = 5;
        for(int i=0;i<dirs.length();i++) {
        	Character c = dirs.charAt(i);
        	int direction;
        	if(c=='U') direction = 0;
        	else if(c=='D') direction = 1;
        	else if(c=='R') direction = 2;
        	else direction = 3;
        	int xx = x+dx[direction];
        	int yy = y+dy[direction];
        	
        	if(xx<0 || xx>=11 || yy<0 || yy>=11) continue;
        	else {
        		StringBuilder sb = new StringBuilder();
        		sb.append(x).append(y).append(xx).append(yy);
        		String route = sb.toString();
        		
        		/* mistake */
        		StringBuilder sb2 = new StringBuilder();
        		sb2.append(xx).append(yy).append(x).append(y);
        		String route2 = sb2.toString();
        		/* ------- */
        		
        		if(!set.contains(route) && !set.contains(route2)) {
        			set.add(route2);
        			set.add(route);
        			answer++;
        		}
        		x = xx;
            	y = yy;
        	}
        }
        
        
        return answer;
    }

	public static void main(String[] args) {
		String dirs = "LULLLLLLU";
		int ans = solution(dirs);
		System.out.println(ans);

	}

}
