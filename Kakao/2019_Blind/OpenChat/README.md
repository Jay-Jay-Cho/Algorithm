# [오픈채팅방](https://programmers.co.kr/learn/courses/30/lessons/42888)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static class Message{
		int action;
		String id;
		Message(int action, String id){
			this.action = action;
			this.id = id;
		}
	}

    public String[] solution(String[] record) {
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
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 로직을 구성할 변수 자료구조 설정
* `HashMap<String,String> info`: 유저정보를 저장할 HashMap
	* **key** : userId
	* **value** : 닉네임
* `Queue<Message> msg_box`: 사용자들의 Enter, Leave 내역을 저장
	* **Message** : action & userId
#### 2. record 길이만큼 돌면서 반복문 수행
* userId
	* 이미 HashMap에 존재하면, 닉네임이 변경됐을 때만 갱신
	* HashMap에 없으면, 새로 생성
* action
	* Enter 또는 Leave일 경우에만 msg_box에 추가
#### 3. 큐의 갯수만큼 출력

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 변수 자료구조 설정
```java
HashMap<String,String> info = new HashMap<String,String>();
Queue<Message> msg_box = new LinkedList<Message>();
```

#### 2. 반복문 돌면서 큐에 추가
```java
for(int idx=0;idx<record.length;idx++) {
	int action_num = -1;	// action 구분을 위한 변수
	String[] str = record[idx].split(" ");
	String action = str[0];
	String userId = str[1];

	// Enter
	if(action.equals("Enter")) {
		String Nickname = str[2];
		if(!info.containsKey(userId)) { // 새로운 유저일 경우, 추가
				info.put(userId, Nickname);
			}else { // 기존 유저이고, 닉네임기 바꼈을 때는 갱신
				if(!Nickname.equals(info.get(userId))) {
					info.replace(userId, Nickname);
				}
			}
		action_num = 0;
	}

	// Leave
	else if(action.equals("Leave")) {
		action_num = 1;
	}

	// Change
	else {
		String Nickname = str[2];
		if(!Nickname.equals(info.get(userId))) {
			info.replace(userId, Nickname);
		}
	}

	// Enter 혹은 Leave인 경우에만 큐에 추가
	if(action_num>=0) msg_box.add(new Message(action_num,userId));
}
```
#### 3. 출력
```java
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
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. record의 길이가 다 같을 것이라고 착각했음.
> Leave일 경우, indexOutofBoundException 발생

* **Enter와 Change** : action + userId + 닉네임
* **Leave** : action + userId
* 따라서, 닉네임 여부는 action에 따라 분기 후에 적용
```java
//실수
String[] str = record[idx].split(" ");
String action = str[0];
String userId = str[1];
String Nickname = str[2];

//정답
String[] str = record[idx].split(" ");
String action = str[0];
String userId = str[1];
if(action.equals("Enter")) {
	String Nickname = str[2];
	...
}else if(action.equals("Leave")) {
	action_num = 1;
}else {
	String Nickname = str[2];
	...
}
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
