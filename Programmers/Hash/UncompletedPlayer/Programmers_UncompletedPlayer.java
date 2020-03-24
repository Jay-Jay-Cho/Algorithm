package hash;

import java.util.Arrays;

public class Programmers_UncompletedPlayer {
	
	public String getAnswer(String[] participant,String[] completion) {
		Arrays.sort(participant);
		Arrays.sort(completion);
		for(int i=0;i<participant.length-1;i++) {
			if(participant[i].equals(completion[i])) continue;
			else return participant[i];
		}
		
		
		return participant[participant.length-1];
	}

	public static void main(String[] args) {
		String[] participant = {"leo","kiki","eden"};
		String[] completion  = {"kiki","eden"};
		Programmers_UncompletedPlayer a = new Programmers_UncompletedPlayer();
		System.out.println(a.getAnswer(participant, completion));
	}

}
