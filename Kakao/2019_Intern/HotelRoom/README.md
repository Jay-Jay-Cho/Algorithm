# [호텔 방 배정](https://programmers.co.kr/learn/courses/30/lessons/640653)
* **참고자료** : https://sangwoo0727.github.io/algorithm/Algorithm-Kakao2019_4/

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static HashMap<Long, Long> parent = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for(int i=0; i<room_number.length; i++) {
        	long roomNumber = room_number[i];
        	long allocateNumber = find(roomNumber);
        	answer[i] = allocateNumber;
        	union(allocateNumber,allocateNumber+1);
        }
        return answer;
    }

    static long find(long x) {
    	if(!parent.containsKey(x) || parent.get(x)==x) {
            parent.put(x,x);
			return x;			
		}else {
			long y = find(parent.get(x));
			parent.put(x, y);
			return y;
		}
	}
    static void union(long x, long y) {
		x = find(x);
		y = find(y);
		if(x<y) parent.put(x, y);
		else parent.put(y, x);
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 4
* 유니온파인드
* 각 고객에게 배정되는 방 번호를 순서대로 배열에 담아 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. room_number 배열의 길이만큼 반복문 수행
#### 2. 해당 방이 비어있든, 비어있지 않든 원하는 번호의 부모값(`allocateNumber`)을 할당
#### 3. 할당된 값을 +1한 값과 합집합하여 다음 경우를 대비
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 유니온파인드 구현
```java
// 부모 찾기
static long find(long x) {
  // 찾고자 하는 값이 없거나, 부모가 자기 자신이면 자기 자신을 반환
  if(!parent.containsKey(x) || parent.get(x)==x) {
        parent.put(x,x);
        return x;			
  }
  // 부모가 자기 자신이 아니면
  else {
        long y = find(parent.get(x)); // 부모를 찾아서
        parent.put(x, y);
        return y; // 반환
  }
}

// 합집합 만들기
static void union(long x, long y) {
  x = find(x);
  y = find(y);
  // 작은 값의 부모를, 큰 값의 부모로 변경
  if(x<y) parent.put(x, y);
  else parent.put(y, x);
}
```

#### 2. 방 번호의 부모를 저장할 HashMap 생성
* 원래는 배열로 하려고 했으나, 배열의 경우 인덱스 값이 무조건 int여야 하므로 불가
  ```java
  static HashMap<Long, Long> parent = new HashMap<>();
  ```


#### 3. 반복문을 돌면서 방 배정
```java
for(int i=0; i<room_number.length; i++) {
  long roomNumber = room_number[i]; // 원하는 방 번호를 찾기
  long allocateNumber = find(roomNumber); // 해당 값의 부모를 찾기
  answer[i] = allocateNumber; // 배정된 방 번호를 answer에 넣고,
  union(allocateNumber,allocateNumber+1); // 다음 경우를 대비하며, 합집합
}
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 지문에서 방을 배정받지 못하는 경우는 없다고 했지만, 만약 끝 방 번호를 배정받은 경우 함수에 의해 배정된 방 번호+1과 합집합을 하게 된다. 이 경우, 에러가 발생하므로 HashMap을 처음부터 선언해서 사용할 때에는 배열의 길이+1의 경우도 추가를 해줘야한다.
```java
// 실수
for(int i=1;i<=room_number.length;i++){
  parent.put(i,i);
}

// 정답 (but, 시간초과)
for(int i=1;i<=room_number.length+1;i++){
  parent.put(i,i);
}
```

#### 2. (`시간초과`) 미리 parent를 처음부터 선언해서 사용.
* room_number배열에 불필요한 방 번호들까지 생성되므로, 부모를 찾을 때 없는 경우의 조건도 넣어서 해당 경우에만 추가.
```java
// 실수
for(int i=1;i<=room_number.length+1;i++){
  parent.put(i,i);
}

// 정답
static long find(long x) {
  if(!parent.containsKey(x) || parent.get(x)==x) {
        parent.put(x,x);
        return x;			
  }
  ...
}
```

<br><br>


## &#10095;&#10095;&#10095; 꿀팁
* for 반복문의 인덱스는 `long`타입이 불가능하다.
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
* 로직을 짤 때, 항상 엣지 케이스를 고려
  * **끝 방 번호 배정 = 끝 방 번호 + 1까지 map에 넣어야 함**
<br><br>
