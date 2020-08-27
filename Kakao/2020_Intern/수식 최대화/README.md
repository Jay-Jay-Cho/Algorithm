# [튜플](https://programmers.co.kr/learn/courses/30/lessons/64065)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
  static Queue<Integer> q;
	static ArrayList<ArrayList<Integer>> answer_list;

	public int[] solution(String s) {

        answer_list = new ArrayList<ArrayList<Integer>>();
        String new_s = s.substring(1,s.length()-1);

        for(int i=0;i<new_s.length();i++) {
        	char c = new_s.charAt(i);
        	if(c=='{') {
        		q = new LinkedList<Integer>();
        	}else if(c=='}') {
        		ArrayList<Integer> list = new ArrayList<Integer>();
        		while(!q.isEmpty()){
        			list.add(q.poll());
        		}
        		answer_list.add(list);
        	}else if(c==','){
        		continue;
        	}else {//number
        		StringBuilder sb = new StringBuilder();
        		sb.append(c);

        		int idx = i+1;
        		while(idx<new_s.length() && Character.isDigit(new_s.charAt(idx))) {
        			sb.append(new_s.charAt(idx));
        			idx++;
        		}

        		q.offer(Integer.parseInt(sb.toString()));
        		i = idx-1;
        	}
        }

        Collections.sort(answer_list, new Comparator<ArrayList<Integer>>() {
			public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
				if(list1.size() < list2.size()) return -1;
				else return 1;
			}
        });

        Queue<Integer> answer_q = new LinkedList<Integer>();

        for(int i=0;i<answer_list.size();i++) {
        	ArrayList<Integer> list = answer_list.get(i);
        	for(int j=0;j<list.size();j++) {
        		if(answer_q.contains(list.get(j))) continue;
        		else answer_q.add(list.get(j));
        	}
        }

        int[] answer = new int[answer_q.size()];
        for(int i=0;i<answer.length;i++) {
        	answer[i] = answer_q.poll();
        }
        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* s가 표현하는 튜플을 배열에 담아 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. String s를 집합(`{}`로 구성)들로 구분해서 answer_list에 넣기
#### 2. answer_list를 크기별 오름차순으로 정렬
#### 3. 기존 answer와 중복되지않는 새로운 원소를 순서대로 answer에 넣기

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 가장 앞/뒤에 있는 괄호(`{}`)를 잘라서, 집합들만 처리할 수 있도록 만듦.
```java
String new_s = s.substring(1,s.length()-1);
```

#### 2. 전처리된 S를 끝까지 탐색하면서 각 집합들을 list에 넣기
```java
for(int i=0;i<new_s.length();i++) {
  char c = new_s.charAt(i);
  // '{' = 새로운 집합 : 해당 집합의 원소들을 담을 큐를 선언
  if(c=='{') {
    q = new LinkedList<Integer>();
  }
  // '}' = 집합이 끝남 : 집합의 원소들을 list에 담아서, 이 list를 answer_list에 추가
  else if(c=='}') {
    ArrayList<Integer> list = new ArrayList<Integer>();
    while(!q.isEmpty()){
      list.add(q.poll());
    }
    answer_list.add(list);
  }
  // ',' = 스킵
  else if(c==','){
    continue;
  }
  // 그 외 = 집합의 원소(숫자)
  else {
    StringBuilder sb = new StringBuilder();
    sb.append(c);
    // 숫자는 100,000 이하의 자연수이므로 s의 다음 char가 숫자면 이어붙임
    int idx = i+1;
    while(idx<new_s.length() && Character.isDigit(new_s.charAt(idx))) {
      sb.append(new_s.charAt(idx));
      idx++;
    }
    // 만들어진 숫자를 큐에 추가
    q.offer(Integer.parseInt(sb.toString()));
    i = idx-1; // 숫자 다음 인덱스부터 다시 시작할 수 있도록 반복문 인덱스를 갱신
  }
}
```

#### 3. answer_list를 집합의 길이별 오름차순으로 정렬
```java
Collections.sort(answer_list, new Comparator<ArrayList<Integer>>() {
    public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
    if(list1.size() < list2.size()) return -1;
    else return 1;
}
});
```

#### 4. 기존 answer와 중복되지않는 새로운 원소를 순서대로 answer에 넣기
```java
Queue<Integer> answer_q = new LinkedList<Integer>();

for(int i=0;i<answer_list.size();i++) {
  ArrayList<Integer> list = answer_list.get(i);
  for(int j=0;j<list.size();j++) {
    if(answer_q.contains(list.get(j))) continue;
    else answer_q.add(list.get(j)); // 새로운 원소들만 추가
  }
}
// 큐에 있는 원소들을 반환 배열에 넣기
int[] answer = new int[answer_q.size()];
for(int i=0;i<answer.length;i++) {
  answer[i] = answer_q.poll();
}
return answer;
```

<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 문제 자체를 이해 못했음..
* 인형을 위에서부터가 아니라, 바닥(`n-1`)에서부터 stack에 추가
  * 추가되는 원소와 집합의 길이에 포커싱하자.
  * 집합의 길이가 늘어난다 = 새로운 원소가 추가된다.

#### 2. 숫자가 한 자릿수 일거라고만 생각했음
* 원소의 크기는 1~200,000까지이므로 최대 6자리까지 가능
* 다음 char가 숫자인지 아닌지 고려를 했었어야함
* 반복문의 다음 인덱스가 숫자가 끝나는 지점으로 갈 수 있도록 인덱스도 갱신
```java
// 실수
q.offer(c-'0');

// 정답
StringBuilder sb = new StringBuilder();
sb.append(c);
int idx = i+1;
while(idx<new_s.length() && Character.isDigit(new_s.charAt(idx))) {
  sb.append(new_s.charAt(idx));
  idx++;
}
q.offer(Integer.parseInt(sb.toString()));
i = idx-1;
```

<br><br>


## &#10095;&#10095;&#10095; 꿀팁
* char를 int로 변형
  ```java
  char c = '4';
  int numeric_c = c-'0';
  ```
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
* 문제에서 char/String값을 int값으로 변환할 때에는 숫자의 길이를 고려해야 하는지 반드시 미리 확인할 것.
<br><br>
