package kakao_2019_winter_intern;

import java.util.*;

public class HotelRoom_Answer {
	static HashMap<Long, Long> parent = new HashMap<>();
	
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
//        for(long i=1;i<=k;i++) {
//        	parent.put(i, i);
//        }
        
        for(int i=0; i<room_number.length; i++) {
        	long roomNumber = room_number[i];
        	long allocateNumber = find(roomNumber);
        	answer[i] = allocateNumber;
        	union(allocateNumber,allocateNumber+1);
        }
        return answer;
    }
    
    static long find(long x) {
    	if(!parent.containsKey(x) || parent.get(x)==x) {
    		parent.put(x, x);
			return x;			
		}else {
			long y = find(parent.get(x));
			parent.put(x, y);
			return y;
		}
	}
    static void union(long x, long y) {
		x = find(x);
		y = find(y);
		if(x<y) parent.put(x, y);
		else parent.put(y, x);
	}
}
