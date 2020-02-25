package brute_force;

import java.util.ArrayList;
import java.util.Collections;

public class Programmers_MockTest {
	
	public int[] getAnswer(int[] answers){
        int[] first = {1,2,3,4,5};
        int[] second = {2,1,2,3,2,4,2,5};
        int[] third = {3,3,1,1,2,2,4,4,5,5};
        
        int first_score = 0;
        int second_score = 0;
        int third_score = 0;
        
        for(int i=0;i<answers.length;i++){
            int answer = answers[i];
            if(answer==first[i%first.length]) first_score++;
            if(answer==second[i%second.length]) second_score++;
            if(answer==third[i%third.length]) third_score++;
        }
        
        int max = Math.max(Math.max(first_score,second_score),third_score);
        ArrayList<String> list = new ArrayList<>();
        if(first_score==max) list.add("1");
        if(second_score==max) list.add("2");
        if(third_score==max) list.add("3");
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0;i<answer.length;i++){
            answer[i] = Integer.parseInt(list.get(i));
        }
        return answer;        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
