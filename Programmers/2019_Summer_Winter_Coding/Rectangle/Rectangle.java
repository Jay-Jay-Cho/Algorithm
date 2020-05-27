package summer_winter_coding_2019;
import java.util.*;
public class Rectangle {
	
	static long solution(int w, int h) {
        long answer = 0;
        
        double radian = (double)w/(double)h;
        for(int i=0;i<h;i++) {
        	long temp = (long)Math.floor(radian*i);
        	answer += temp;
        }
        
        return answer*2;
    }
	

	public static void main(String[] args) {
		long answer = solution(8,12);
		System.out.println(answer);
	}

}
