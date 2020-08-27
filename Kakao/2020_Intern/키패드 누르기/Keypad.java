package kakao_2020_intern;
import java.util.*;
public class Keypad {
	
	static HashMap<String,Integer> map = new HashMap<String,Integer>();
	static HashSet<Integer> leftSet = new HashSet<Integer>();
	static HashSet<Integer> rightSet = new HashSet<Integer>();
	static String leftPos = "*";
	static String rightPos = "#";
	
	static void init() {
		map.put("12", 1); map.put("15", 2); map.put("18", 3); map.put("10", 4);
		map.put("42", 2); map.put("45", 1); map.put("48", 2); map.put("40", 3);
		map.put("72", 3); map.put("75", 2); map.put("78", 1); map.put("70", 2);
		map.put("*2", 4); map.put("*5", 3); map.put("*8", 2); map.put("*0", 1);
		
		map.put("22", 0); map.put("25", 1); map.put("28", 2); map.put("20", 3);
		map.put("52", 1); map.put("55", 0); map.put("58", 1); map.put("50", 2);
		map.put("82", 2); map.put("85", 1); map.put("88", 0); map.put("80", 1);
		map.put("02", 3); map.put("05", 2); map.put("08", 1); map.put("00", 0);
		
		// mistake
		map.put("32", 1); map.put("35", 2); map.put("38", 3); map.put("30", 4);
		map.put("62", 2); map.put("65", 1); map.put("68", 2); map.put("60", 3);
		map.put("92", 3); map.put("95", 2); map.put("98", 1); map.put("90", 2);
		map.put("#2", 4); map.put("#5", 3); map.put("#8", 2); map.put("#0", 1);
		
		leftSet.add(1); leftSet.add(4); leftSet.add(7); 
		rightSet.add(3); rightSet.add(6); rightSet.add(9); 
	}
	
	static String solution(int[] numbers, String hand) {
       StringBuilder sb = new StringBuilder(numbers.length);
        
        init();
        for(int number:numbers) {
        	if(leftSet.contains(number)){
        		leftPos = Integer.toString(number);
        		sb.append("L");
        		continue;
        	}
        	
        	if(rightSet.contains(number)) {
        		rightPos = Integer.toString(number);
        		sb.append("R");
        		continue;
        	}
        	int leftD = map.get(leftPos.concat(Integer.toString(number)));
        	int rightD = map.get(rightPos.concat(Integer.toString(number)));
        	
        	if(leftD > rightD) {
        		rightPos = Integer.toString(number);
        		sb.append("R");
        	}else if(leftD < rightD) {
        		leftPos = Integer.toString(number);
        		sb.append("L");
        	}else {
        		if(hand.equals("left")) {
        			leftPos = Integer.toString(number);
            		sb.append("L");
        		}else {
        			rightPos = Integer.toString(number);
            		sb.append("R");
        		}
        	}
        	
        }
        
        return sb.toString();
    }

	public static void main(String[] args) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		String ans = solution(numbers,hand);
		System.out.println(ans);

	}

}
