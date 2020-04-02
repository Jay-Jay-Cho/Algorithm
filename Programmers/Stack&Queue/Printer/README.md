# [프린터](https://programmers.co.kr/learn/courses/30/lessons/42587)
* **참고자료** : 엄슴..

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int n = priorities.length;
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0;i<n;i++) {
        	q.add(i);
        	heap.add(priorities[i]);
        }

        boolean flag = true;
        while(!heap.isEmpty() && flag) {
        	answer++;  // unreachable location
        	int max = heap.poll();
        	while(true) {
        		int temp_idx = q.peek();
        		int temp_priority = priorities[temp_idx];
        		if(temp_priority == max) {
        			q.poll();
        			if(temp_idx == location) {
        				flag = false;
        			}
    				break;
        		}else {
        			q.poll();
        			q.add(temp_idx);
        		}
        	}
        }

        return answer;
    }
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 2
* Queue & Simulation
* 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 최댓값들을 내림차순으로 정렬하는 `우선순위 큐`와 작업을 저장하는 `큐` 동시 활용
* 일단 우선순위 큐에서 최댓값을 poll 하고,
* 큐에서 해당 최댓값을 찾을 때까지 poll() 과 add() 반복
* 최댓값을 찾으면, 해당 최댓값을 poll 하고 다음 최댓값으로 넘어감

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 변수 및 객체 선언
```java
// 최댓값을 내림차순으로 정렬하는 Max Heap
PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
// 작업을 순서대로 저장할 큐
Queue<Integer> q = new LinkedList<Integer>();
```

##### 2. 우선순위 큐와 일반 큐에 값들을 넣어줌.
```java
for(int i=0;i<n;i++) {
  q.add(i);
  heap.add(priorities[i]);
}
```

##### 3. 인덱스가 location인 문서가 인쇄될 때까지 반복
```java
boolean flag = true;  // 인덱스가 location인 문서를 찾으면 false로 바뀜
while(!heap.isEmpty() && flag) {
  answer++;
  int max = heap.poll(); // 최댓값을 뽑고,
  while(true) {
    int temp_idx = q.peek();
    int temp_priority = priorities[temp_idx];

    // 최댓값이 큐의 head 값이면,
    // 해당 값을 poll 하고,
    // 인덱스가 location인지 확인
    if(temp_priority == max) {
      q.poll();
      if(temp_idx == location) {
        flag = false; // 인덱스가 location이면 outer while 문 정지
      }
    break; // 최댓값을 다시 뽑기 위해 inner while 문은 break
    }

    // 최댓값이 큐의 head 값이 아니면,
    // 해당 값을 poll해서 큐의 가장 끝으로 이동
    else {
      q.poll();
      q.add(temp_idx);
    }
  }
}
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. inner while문을 정지시키는 break의 위치를 잘못 넣음
* 해당 문서의 인덱스가 location이 아니어도, 최댓값은 찾은 것이므로 if문 밖에 위치해야 함.
```java
// 실수
if(temp_priority == max) {
  q.poll();
  if(temp_idx == location) {
    flag = false;
    break;
  }
}

// 정답
if(temp_priority == max) {
  q.poll();
  if(temp_idx == location) {
    flag = false;
  }
break;
}
```
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br>
<br>
<br>
