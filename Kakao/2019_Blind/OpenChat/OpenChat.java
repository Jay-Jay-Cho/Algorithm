package kakao_2019;
import java.util.*;
public class OpenChat {
	
	static class Message{
		int action;
		String id;
		Message(int action, String id){
			this.action = action;
			this.id = id;
		}
	}
	
	static String[] solution(String[] record) {
        HashMap<String,String> info = new HashMap<String,String>();
        Queue<Message> msg_box = new LinkedList<Message>();
        
        
        for(int idx=0;idx<record.length;idx++) {
        	int action_num = -1;
        	String[] str = record[idx].split(" ");
        	String action = str[0];
        	String userId = str[1];
        	
        	if(action.equals("Enter")) {
        		String Nickname = str[2];
        		if(!info.containsKey(userId)) {
            		info.put(userId, Nickname);
            	}else { //change
            		if(!Nickname.equals(info.get(userId))) {
            			info.replace(userId, Nickname);
            		}
            	}
        		action_num = 0;
        	}else if(action.equals("Leave")) {
        		action_num = 1;
        	}else {
        		String Nickname = str[2];
        		if(!Nickname.equals(info.get(userId))) {
        			info.replace(userId, Nickname);
        		}
        	}
        	if(action_num>=0) msg_box.add(new Message(action_num,userId));
        }
        
        String[] answer = new String[msg_box.size()];
        int index = 0;
        while(!msg_box.isEmpty()) {
        	Message msg = msg_box.poll();
        	StringBuilder sb = new StringBuilder();
        	sb.append(info.get(msg.id)).append("님이 ");
        	if(msg.action==0) sb.append("들어왔습니다.");
        	else sb.append("나갔습니다.");
        	answer[index] = sb.toString();
        	index++;
        }
        return answer;
    }

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] answer = solution(record);
		for(int i=0;i<answer.length;i++) {
			System.out.println(answer[i]);
		}
	}

}
