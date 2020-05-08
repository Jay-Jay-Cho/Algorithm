# [불량 사용자](https://programmers.co.kr/learn/courses/30/lessons/64064)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static boolean[] used;
	static int badUserCnt;
	static String[] banned_id_arr;
	static ArrayList<ArrayList<Integer>> answer_list;

	public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        banned_id_arr = banned_id;
        badUserCnt = 0;
        used = new boolean[user_id.length];
        answer_list = new ArrayList<ArrayList<Integer>>();

        dfs(user_id,0,0);
        answer = badUserCnt;

        return answer;
    }

	static void dfs(String[] user_id, int banned_id_index, int cnt) {

		if(cnt==banned_id_arr.length) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<used.length;i++) {
				if(used[i]) list.add(i);
			}

			if(!answer_list.isEmpty()) {
				boolean flag = true;
				for(int i=0;i<answer_list.size();i++) {
					ArrayList<Integer> exist_list = answer_list.get(i);
					if(list.containsAll(exist_list)) {
						flag=false;
						break;
					}
				}
				if(flag) {
					answer_list.add(list);
					badUserCnt++;
				}
			}else {
				answer_list.add(list);
				badUserCnt++;
			}
			return;
		}

		if(banned_id_index>=banned_id_arr.length) return;

		String badUserId = banned_id_arr[banned_id_index];

    	int star_cnt = 0;
		for(int i=0;i<badUserId.length();i++) {
			if(badUserId.charAt(i)=='*') star_cnt++;
		}

		for(int i=0;i<user_id.length;i++) {
    		String userId = user_id[i];
    		if(userId.length() == badUserId.length() && !used[i]) {
    			int same_cnt = 0;
    			for(int j=0;j<userId.length();j++) {
    				if(userId.charAt(j)==badUserId.charAt(j)) same_cnt++;
    			}
    			if(same_cnt+star_cnt==badUserId.length()) {
    				used[i] = true;
    				dfs(user_id,banned_id_index+1,cnt+1);
    				used[i] = false;
    			}
    		}
    	}
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 3
* DFS(백트래킹), 시뮬레이션
* 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한 지 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 백트래킹
##### 1. 제재된 아이디 배열을 순회
##### 2. 인덱스에 해당하는 제재 아이디의 조건에 만족하는 응모자 아이디를 찾으면, 재귀함수 실행
* used = true
* cnt++ (제재 아이디 조건을 만족)
* index++ (제대 아이디 배열을 위한)
##### 3. 만약 제재된 아이디를 만족하는 응모 아이디를 모두 찾았으면, 응모자 아이디의 인덱스들로 list를 생성
##### 4. 만약 이 list들을 담는 answer_list가
* 비어있으면, 바로 추가
* 비어있지 않으면, answer_list에 있는 다른 list들과 겹치지 않는지 확인하여 추가
##### 5. answer_list에 추가되는 경우, badUserCnt++

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. Main함수에서 DFS 실행
```java
dfs(user_id,0,0);
```

#### 2. DFS
```java
static void dfs(String[] user_id, int banned_id_index, int cnt) {

  // 제재 아이디에 해당하는 모든 응모 아이디를 찾았으면
  if(cnt==banned_id_arr.length) {
    // 해당 응모 아이디들의 인덱스를 list에 저장
    ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i=0;i<used.length;i++) {
      if(used[i]) list.add(i);
    }

    // answer_list가 비어있지 않으면, 중복여부 검사
    if(!answer_list.isEmpty()) {
      boolean flag = true;
      // 기존 answer_list 내에 있는 다른 list들과 겹치는지
      for(int i=0;i<answer_list.size();i++) {
        ArrayList<Integer> exist_list = answer_list.get(i);
        if(list.containsAll(exist_list)) { // 하나라도 겹치면, 중복되므로 flag = false
          flag=false;
          break;
        }
      }
      if(flag) { // 중복되지 않는 list만 answer_list에 추가하고, 경우의 수++
        answer_list.add(list);
        badUserCnt++;
      }
    }
    // answer_list가 비어있으면, 바로 추가하고 경우의 수++
    else {
      answer_list.add(list);
      badUserCnt++;
    }
    return;
  }

  if(banned_id_index>=banned_id_arr.length) return;

  String badUserId = banned_id_arr[banned_id_index];

  int star_cnt = 0;
  for(int i=0;i<badUserId.length();i++) {
    if(badUserId.charAt(i)=='*') star_cnt++;
  }

  // 제재 아이디에 해당하는 응모아이디를 찾기
  for(int i=0;i<user_id.length;i++) {
      String userId = user_id[i];
      if(userId.length() == badUserId.length() && !used[i]) {
        int same_cnt = 0;
        for(int j=0;j<userId.length();j++) {
          if(userId.charAt(j)==badUserId.charAt(j)) same_cnt++;
        }
        // 조건에 해당하는 응모아이디를 찾았으면, DFS 시작
        if(same_cnt+star_cnt==badUserId.length()) {
          used[i] = true;
          dfs(user_id,banned_id_index+1,cnt+1);
          used[i] = false; // 백트래킹
        }
      }
    }
}
```

#### 3. 경우의 수 반환
```java
answer = badUserCnt;
return answer;
```

<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 중복되는 경우의 수 고려 X
* 응모 아이디의 인덱스로 구성된 list를 answer_list에 추가할 때, 중복여부 검사 추가
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
* 백트래킹 문제에서는, 트리 형태로 로직을 상상해보기
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
