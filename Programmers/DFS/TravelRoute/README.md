# [여행경로](https://programmers.co.kr/learn/courses/30/lessons/43164)
* **참고자료** : https://geehye.github.io/programmers-dfs-bfs-04

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;

class Solution {

  static boolean[] visit;
	static ArrayList<String> list = new ArrayList<String>();

    public String[] solution(String[][] tickets) {
        for(int i=0;i<tickets.length;i++) {
          if(tickets[i][0].equals("ICN")) {
            visit = new boolean[tickets.length];
            visit[i] = true;
            StringBuilder path = new StringBuilder("ICN").append(",").append(tickets[i][1]);
            dfs(tickets,tickets[i][1],path,1);
          }
        }
        Collections.sort(list);
        String[] answer = list.get(0).split(",");

        return answer;
    }

    public void dfs(String[][] tickets, String dest, StringBuilder path, int cnt) {
		if(cnt == visit.length) {
			list.add(path.toString());
			return;
		}else {
			for(int i=0;i<visit.length;i++) {

				if(tickets[i][0].equals(dest) && !visit[i]) {
					visit[i] = true;
					path.append(",").append(tickets[i][1]);
					dfs(tickets,tickets[i][1],path,cnt+1);
					path.delete(path.length()-4, path.length());	// 실수  
					visit[i] = false;
				}
			}
		}
	}
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* DFS 백트래킹
* 모든 항공권을 사용하여 방문하는 공항 경로를 배열에 담아 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1. DFS 문제라는 것을 인지하고, 처음에는 Stack으로 구현했다. 그러나, 백트래킹을 위해서는 Stack이 아니라 재귀함수로 구현해야함을 깨닫고 로직을 변경했다.
* 백트래킹 문제를 Stack으로 구현이 불가능한 이유
  * 한 경로만 계속해서 쭉~ 파는게 아니라, 가능한 경로를 Stack에 넣고 하나씩 pop 하면서 경로를 찾기 때문에,
  * 예를들어 방문여부를 체크하는 visit 배열 사용시 다음 pop되는 노드에 영향을 미침...

##### 2. 재귀호출을 활용한 백트래킹
* main 함수
  * 반복문(`0 ~ length`)을 돌면서, 출발지(`tickets[i][0]`)가 `INC`이면
  * DFS 시작
* DFS 재귀 함수
  * 항공권을 모두 사용했으면, 반환
  * 다시 반복문(`0 ~ length`)을 돌면서, 현재 항공권의 도착지가 출발지인 항공권을 찾으면
  * 항공권을 사용했다고 표시 & 누적 경로에 항공권의 도착지를 추가
  * 다음 DFS 호출
  * 백트래킹을 위해, 항공권 사용 표시와 누적 경로를 이전으로 초기화

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 정적 변수 선언
```java
static boolean[] visit; // 항공권 사용 여부 체크
static ArrayList<String> list = new ArrayList<String>();  // 경로 후보군 저장
```

##### 2. Main(Solution) 함수
```java
public String[] solution(String[][] tickets) {
    for(int i=0;i<tickets.length;i++) { // 0 ~ length 반복
      if(tickets[i][0].equals("ICN")) {
        visit = new boolean[tickets.length];
        visit[i] = true;  // 항공권 사용 표시
        // path 추가
        StringBuilder path = new StringBuilder("ICN").append(",").append(tickets[i][1]);
        dfs(tickets,tickets[i][1],path,1);  // dfs 호출
      }
    }
    Collections.sort(list); // 경로 후보군들을 알파벳순으로 정렬
    String[] answer = list.get(0).split(",");

    return answer;
}
```

##### 3. DFS 함수
```java
public void dfs(String[][] tickets, String dest, StringBuilder path, int cnt) {

// 항공권을 모두 사용했으면, 반환  
if(cnt == visit.length) {
  list.add(path.toString());
  return;
}

else {
  for(int i=0;i<visit.length;i++) {
    // 이전 항공권의 도착지가 출발지인 항공권을 찾으면 && 사용하지 않은 항공권이면
    if(tickets[i][0].equals(dest) && !visit[i]) {
      visit[i] = true;  // 항공권 사용 표시
      path.append(",").append(tickets[i][1]); // 경로 갱신
      dfs(tickets,tickets[i][1],path,cnt+1);

      //백트래킹
      path.delete(path.length()-4, path.length()); // 이전 경로로 회귀
      visit[i] = false; // 미사용 항공권으로 되돌림
    }
  }
}
}
```


<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. 순회/탐색시 누적경로를 저장하기 위해서는 객체 내부에 StringBuilder 선언해서 활용.



<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. String 비교를 equals로 해야하는데, 그냥 `==`연산자로 했다...
```java
// 실수
if(tickets[i][0] == "ICN") {
  ...
}

// 정답
if(tickets[i][0].equals("ICN")) {
  ...
}
```
##### 2. 백트래킹에서는, 방문한 배열 초기화뿐 아니라 path도 이전 값으로 되돌려야한다...
```java
// 살수
if(tickets[i][0].equals(dest) && !visit[i]) {
  visit[i] = true;
  dfs(tickets,tickets[i][1],path,cnt+1);
  visit[i] = false;
}

// 정답
if(tickets[i][0].equals(dest) && !visit[i]) {
  visit[i] = true;
  path.append(",").append(tickets[i][1]);
  dfs(tickets,tickets[i][1],path,cnt+1);
  path.delete(path.length()-4, path.length());
  visit[i] = false;
}
```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 1. visit배열을 활용하는, 즉 백트래킹을 위한 DFS 사용시에는 Stack 말고 재귀함수 사용
##### 2. String 비교시에는 `==`연산자가 아닌, `equals()` 메소드 사용




<br>
<br>
<br>
