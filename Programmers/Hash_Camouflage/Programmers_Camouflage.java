package hash;

import java.util.HashMap;

public class Programmers_Camouflage {
	
	static int getAnswer(String[][] clothes) {
		HashMap<String,Integer> map = new HashMap<>();
		for(int i=0;i<clothes.length;i++) {
			if(!map.containsKey(clothes[i][1])) {
				map.put(clothes[i][1], 1);
			}else {
				int temp = map.get(clothes[i][1])+1;
				map.put(clothes[i][1], temp);
			}
		}
		int answer=1;
		for(int value : map.values()) {
			answer *= (value+1);
		}
		return answer-1;
	}
	
	
	public static void main(String[] args) {
		
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		System.out.println(getAnswer(clothes));

	}

}
