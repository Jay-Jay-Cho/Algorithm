

# [조이스틱](https://programmers.co.kr/learn/courses/30/lessons/42860)
* **참고자료** : https://geehye.github.io/programmers-greedy-02/#
* **참고자료** : https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%81%B0-%EC%88%98-%EB%A7%8C%EB%93%A4%EA%B8%B0-Java
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public int solution(String name) {
        int answer = 0;

        answer += Math.abs(String.valueOf(name.charAt(0)).compareTo("A"));

        int cnt = 0;
        boolean[] shouldVisit = new boolean[name.length()];
        int[] distance = new int[name.length()];
        int idx = 0;

        for(int i=1;i<name.length();i++) {
        	if(name.charAt(i) != 'A') {
        		shouldVisit[i] = true;
        		distance[i] = Math.min(i, name.length()-i);
        		cnt++;
        		int temp = Math.abs(String.valueOf(name.charAt(i)).compareTo("A"));
        		answer += Math.min(temp, 26-temp);

        	}else {
        		shouldVisit[i] = false;
        	}
        }

        while(cnt>0) {
        	int min = Integer.MAX_VALUE;
        	int min_idx = 0;
        	for(int i=1;i<name.length();i++) {
        		if(shouldVisit[i] && min>=distance[i]) {
        			min = distance[i];
        			min_idx = i;
        		}
        	}
        	int move = Math.abs(min_idx-idx);
        	answer+=Math.min(move, name.length()-move);
        	shouldVisit[min_idx] = false;
        	idx = min_idx;
        	cnt--;
        	for(int i=1;i<name.length();i++) {
        		if(shouldVisit[i]) {
        			int temp = Math.abs(i-idx);
        			distance[i] = Math.min(temp, name.length()-temp);
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
* Greedy
* number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1.



<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 첫번째 인덱스(0)는 무조건 바꾸고 시작하므로, `A`와 차이만큼 answer++
  ```java
  answer += Math.abs(String.valueOf(name.charAt(0)).compareTo("A"));
  ```
<br>

##### 2. 변수 선언.
  ```java
  // 이동이 필요한 부분 갯수
  int cnt = 0;
  // 이동이 필요한 부분 표시
  boolean[] shouldVisit = new boolean[name.length()];
  // 현재 인덱스에서 각 인덱스까지의 거리 저장
  int[] distance = new int[name.length()];
  ```
<br>

##### 3. name의 길이만큼 돌면서, A가 아닌 부분이 있다면,
```java
for(int i=1;i<name.length();i++) {
  if(name.charAt(i) != 'A') {
    shouldVisit[i] = true;
    distance[i] = Math.min(i, name.length()-i);
    cnt++;
    int temp = Math.abs(String.valueOf(name.charAt(i)).compareTo("A"));
    answer += Math.min(temp, 26-temp);

  }else {
    shouldVisit[i] = false;
  }
}
```
* 이동이 필요한 부분임을 표시
  * `shouldVisit[i] = true`
* 현재 인덱스(0)에서 해당 인덱스까지의 최소 거리 표시 (좌/우측 이동거리 중에서)
  * `Math.min(i, name.length()-i);`
* 이동이 필요한 갯수 추가
  * `cnt++`
* 알파벳 변환 횟수만큼 answer++
  * `answer += Math.min(temp, 26-temp);`

<br>

##### 4. 이동이 필요한 부분을 모두 방문할 때까지, 현재 위치에서 가장 거리가 가까운 위치로 이동하며 인덱스 간 거리(`distance[i]`) 업데이트
```java
while(cnt>0) {
  int min = Integer.MAX_VALUE;
  int min_idx = 0;
  for(int i=1;i<name.length();i++) {
    if(shouldVisit[i] && min>distance[i]) {
      min = distance[i];
      min_idx = i;
    }
  }
  int move = Math.abs(min_idx-idx);
  answer+=Math.min(move, name.length()-move);
  shouldVisit[min_idx] = false;
  idx = min_idx;
  cnt--;
  for(int i=1;i<name.length();i++) {
    if(shouldVisit[i]) {
      int temp = Math.abs(i-idx);
      distance[i] = Math.min(temp, name.length()-temp);
    }
  }
}
```
* 현재 위치에서 가장 가까운 거리의 인덱스로 이동
  * `min_idx = i`
* 이동한 거리만큼 answer ++
  * `answer+=Math.min(move, name.length()-move)`
* 방문했으므로 체크
  * `shouldVisit[min_idx] = false;`
* 현재 위치 업데이트
  * `idx = min_idx`
* 이동이 필요한 부분 차감
  * `cnt--`
* 인덱스 간 거리 업데이트
  ```java
  for(int i=1;i<name.length();i++) {
    if(shouldVisit[i]) {
      int temp = Math.abs(i-idx);
      distance[i] = Math.min(temp, name.length()-temp);
    }
  }
  ```
<br>

##### 5. answer 반환
  ```java
  return answer;
  ```
<br>



<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. [Char 형을 Int 형으로 변환](https://m.blog.naver.com/PostView.nhn?blogId=zxy826&logNo=220806191917&proxyReferer=https%3A%2F%2Fwww.google.com%2F)
* char 뒤에 `- '0'`을 붙여준다.
* `input.charAt(i) - '0'`

##### 2. Character 순서 비교
* 굳이 compareTo 메소드를 안써도, 연산자로 비교 가능
  ```java
  // 기존(String)
  String A = String.valueOf('A');
  String B = String.valueOf('B');
  A.compareTo(B);
  // Character
  'A' - 'B'
  ```






<br><br>


## &#10095;&#10095;&#10095; 실수
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점




<br>
<br>
<br>
