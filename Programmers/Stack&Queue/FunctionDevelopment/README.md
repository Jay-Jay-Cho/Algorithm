# [기능개발](https://programmers.co.kr/learn/courses/30/lessons/42586)
* **참고자료** : 엄슴..

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        int n = progresses.length;

        for(int i=0;i<n;i++) {
        	int day = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        	q.add(day);
        }

        int criteria = 0;
        int cnt = 0;
        while(!q.isEmpty()) {
        	if(cnt == 0) {
        		criteria = q.poll();
        		cnt++;  //
        	}
        	else {
        		int temp = q.peek();
        		if(criteria >= temp) {
        			cnt++;
        			q.poll();
        		}else {
        			list.add(cnt);
        			cnt = 0; //
        			//break;
        		}
        	}
        }

        if(cnt!=0) list.add(cnt);

        answer = new int[list.size()];
        for(int i=0;i<list.size();i++) answer[i] = list.get(i);

        return answer;
    }
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 2
* Queue & Simulation
* 각 배포마다 몇 개의 기능이 배포되는지를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 문제에 명시된 `입출력 예 설명`에서 아이디어를 얻음.
* 그래서 일단 모든 작업이 완료될 때까지 걸리는 시간을 계산해서 차례대로 큐에 넣음.
* 큐에서 하나씩 꺼내면서, 가장 앞에 있는 작업의 완료일보다 빨리 끝나면 pop, 아니면 break를 하고 해당 작업으로 초기화하고 다시 시작.
<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 변수 및 객체 선언
```java
// 배포될 때마다, 몇 개가 배포되는지 저장할 임시 answer 리스트
ArrayList<Integer> list = new ArrayList<Integer>();
// 각 작업별 완료시점을 저장할 큐
Queue<Integer> q = new LinkedList<Integer>();
int n = progresses.length;
```

##### 2. 각 작업별 완료 시점을 계산해서 큐에 넣어줌
```java
for(int i=0;i<n;i++) {
  int day = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]); //
  q.add(day);
}
```


##### 3. 큐가 완전히 빌 때까지 반복하면서 각 배포일마다 list에 넣기
```java
int criteria = 0;
int cnt = 0;
while(!q.isEmpty()) {

  // 새로운 배포일의 기준
  if(cnt == 0) {
    criteria = q.poll();
    cnt++;
  }
  else {
    int temp = q.peek();
    if(criteria >= temp) { // 배포일 기준보다 작업완료일이 작으면
      cnt++;
      q.poll();
    }else {  // 배포일 기준보다 작업완료일이 크면
      list.add(cnt); // 기존 cnt를 리스트에 넣고
      cnt = 0; // cnt 초기화 (새로운 배포일을 넣을 수 있도록)
    }
  }
}

if(cnt!=0) list.add(cnt);
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 반올림,올림,내림 사용시 인자값 안에 `double`로 캐스팅을 해주면 값이 정확하게 나옴.
```java
// 2
int day = (int) Math.ceil((100 - progresses[i]) / speeds[i]);

// 3
int day = (int) Math.ceil((double) 100 - progresses[i] / speeds[i]);
```



<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 완료 시점 계산시 괄호를 안 씌움.
```java
// 실수
int day = (int) Math.ceil((double) 100 - progresses[i] / speeds[i]); //

// 정답
int day = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
```

##### 2. 처음 배포가 되는 작업의 경우에도 cnt를 증가시켜줬어야 함.
```java
// 실수
if(cnt == 0) {
  criteria = q.poll();
}

// 정답
if(cnt == 0) {
  criteria = q.poll();
  cnt++;  //
}
```



<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점





<br>
<br>
<br>
