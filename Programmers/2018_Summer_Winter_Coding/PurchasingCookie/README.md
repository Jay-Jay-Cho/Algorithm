# [배달](https://programmers.co.kr/learn/courses/30/lessons/12978)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int infinite = Integer.MAX_VALUE/3;
        int[][] map = new int[N+1][N+1];
        for(int i=1;i<map.length;i++) {
        	for(int j=1;j<map.length;j++) {
        		if(i==j) map[i][j] = 0;
        		else map[i][j] = infinite;
        	}
        }

        for(int i=0;i<road.length;i++) {
        	int from = road[i][0];
        	int to = road[i][1];
        	int cost = road[i][2];
        	if(map[from][to]!= 0 || map[from][to]!= infinite) {
        		if(map[from][to]<cost) continue;
        		else {
        			map[from][to] = cost;
                	map[to][from] = cost;
        		}
        	}
        }

        for(int k=1;k<=N;k++){	// 중간 경유
            for(int i=1;i<=N;i++) {	// 시작
            	for(int j=1;j<=N;j++) {	// 도착
            		if(map[i][j] > map[i][k]+map[k][j]) {
            			map[i][j] = map[i][k]+map[k][j];
            		}
            	}
            }
        }

        for(int i=1;i<=N;i++) {
        	if(map[1][i]<=K) answer++;
        }

        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 3
* 최단경로(플로이드-워셜 or 다익스트라)
* 음식 주문을 받을 수 있는 마을의 개수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. cost 배열 초기화
* 자기 자신은 0
* 그 외의 경로는 infinite
#### 2. 주어진 road 배열 길이만큼 반복하며, cost 갱신
* road가 중복될 경우, 최솟값으로 계속해서 갱신
#### 3. 플로이드-워셜 알고리즘을 통해 각 마을 간 최단경로 구하기
#### 4. 1번 마을 배열만 뽑아서 answer 계산
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. cost 배열 초기화
```java
int infinite = Integer.MAX_VALUE/3;
int[][] map = new int[N+1][N+1];
for(int i=1;i<map.length;i++) {
  for(int j=1;j<map.length;j++) {
    if(i==j) map[i][j] = 0;
    else map[i][j] = infinite;
  }
}
```
#### 2. 주어진 road 배열 길이만큼 반복하며, cost 갱신
```java
for(int i=0;i<road.length;i++) {
  int from = road[i][0];
  int to = road[i][1];
  int cost = road[i][2];
  if(map[from][to]!= 0 || map[from][to]!= infinite) {
    if(map[from][to]<cost) continue;
    else {
      map[from][to] = cost;
          map[to][from] = cost;
    }
  }
}
```
#### 3. 플로이드-워셜 알고리즘을 통해 각 마을 간 최단경로 구하기
```java
for(int k=1;k<=N;k++){	// 중간 경유
    for(int i=1;i<=N;i++) {	// 시작
      for(int j=1;j<=N;j++) {	// 도착
        if(map[i][j] > map[i][k]+map[k][j]) {
          map[i][j] = map[i][k]+map[k][j];
        }
      }
    }
}
```
#### 4. 1번 마을 배열만 뽑아서 answer 계산
```java
for(int i=1;i<=N;i++) {
  if(map[1][i]<=K) answer++;
}
```

<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 마을과 마을을 연결하는 도로의 갯수가 여러 개인 경우를 생각못함
* 따라서, cost가 최소인 경우로 계속해서 갱신
```java
// 실수
for(int i=0;i<road.length;i++) {
  int from = road[i][0];
  int to = road[i][1];
  int cost = road[i][2];
  map[from][to] = cost;
  map[to][from] = cost;
}

// 정답
for(int i=0;i<road.length;i++) {
  int from = road[i][0];
  int to = road[i][1];
  int cost = road[i][2];
  if(map[from][to]!= 0 || map[from][to]!= infinite) {
    if(map[from][to]<cost) continue;
    else {
      map[from][to] = cost;
          map[to][from] = cost;
    }
  }
}
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
#### 각 노드 간 최단 경로를 계산하는 문제에는 `다익스트라` 활용 가능
* 이 문제의 경우, n이 50이라 플로이드-워셜이 가능하지만, 보통 시간복잡도가 `n^3`이기 때문에
* 다익스트라의 경우, 시간복잡도는 보통 `E*logE`
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
