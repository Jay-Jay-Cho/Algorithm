

# [순위](https://programmers.co.kr/learn/courses/30/lessons/49191)
* **참고자료** : https://iamheesoo.github.io/blog/algo-prog49191
* **참고자료** : https://manzoo.tistory.com/11
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Arrays;
class Solution {
    public int solution(int n, int[][] results) {
        int infinite = Integer.MAX_VALUE/3;	// 실
        int answer = 0;
        int[][] map = new int[n+1][n+1];

        for(int i=1;i<map.length;i++) {
        	for(int j=1;j<map.length;j++) {
        		if(i==j) map[i][j] = 0;
        		else map[i][j] = infinite;
        	}
        }

        for(int i=0;i<results.length;i++){
            int win = results[i][0];
            int lose = results[i][1];
            map[win][lose]=1;
        }  

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++) {
            	for(int j=1;j<=n;j++) {
            		if(map[i][j] > map[i][k]+map[k][j]) {
            			map[i][j] = map[i][k]+map[k][j];
            		}
            	}
            }
        }

        boolean[] flag = new boolean[n+1];
        Arrays.fill(flag, true);

        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
        		if(i==j) continue;
        		if(map[i][j]==infinite && map[j][i]==infinite) {
        			flag[i]=false;
        			break;
        		}
        	}
        }

        for(int i=1;i<flag.length;i++) {
        	if(flag[i]) answer++;
        }

        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* Graph
* 주어진 경기결과(results)에 따라 순위를 판별할 수 있는 권투선수의 수 찾기.
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### - 처음에는 BFS탐색을 활용하려 했으나, 시간초과가 나서 `플로이드-워셜`방식을 활용했다. 아마 BFS탐색 자체는 괜찮지만,
1. n명만큼 탐색을 반복하고,
2. 이전 노드의 결과를 이후 노드까지 누적시켜야하는 부분에서 복잡도가 증가한 것 같다.
<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 플로이드-워셜 구현을 위해 2차원 배열 초기화.
* 자기 자신은 0, 나머지는 무한대(infinite)으로...
  ```java
  int infinite = Integer.MAX_VALUE/3;

  int[][] map = new int[n+1][n+1];
  for(int i=1;i<map.length;i++) {
    for(int j=1;j<map.length;j++) {
      // 자기자신
      if(i==j) map[i][j] = 0;
      // 나머지
      else map[i][j] = infinite;  
    }
  }
  ```
<br>

##### 2. 경기결과 배열을 받아와 두 선수(좌표) 간 연결관계 표시
* 가중치는 1로 동일하다.
  ```java
  for(int i=0;i<results.length;i++){
      int win = results[i][0];
      int lose = results[i][1];
      map[win][lose]=1;
  }
  ```
<br>

##### 3. 3중반복문을 통해 각 선수별 최단거리 업데이트
* 가중치는 1로 동일하다.
  ```java
  for(int k=1;k<=n;k++){
      for(int i=1;i<=n;i++) {
        for(int j=1;j<=n;j++) {
          if(map[i][j] > map[i][k]+map[k][j]) {
            map[i][j] = map[i][k]+map[k][j];
          }
        }
      }
  }
  ```
<br>

##### 4. 승패를 알 수 있는 선수 찾기.
* 양 쪽 모두 infinite
  * 두 선수 간 경기를 한 적이 없음.
  * 다른 선수의 경기를 통해 알 수도 없음.
  ```java
  boolean[] flag = new boolean[n+1];
  Arrays.fill(flag, true);

  for(int i=1;i<=n;i++) {
    for(int j=1;j<=n;j++) {
      if(i==j) continue;
      if(map[i][j]==infinite && map[j][i]==infinite) {
        flag[i]=false;
        break;
      }
    }
  }
  ```
<br>




<br><br>



## &#10095;&#10095;&#10095; 꿀팁

<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 플로이드-워셜 방식을 위해 2차원 배열을 초기화할 당시, 자기자신을 제외한 좌표를 `무한대`로 표현한다는 부분에서 `Integer.MAX_VALUE`를 사용했는데, 이 경우 오버플로우로 음수가 발생한다.
```java
// 실수
int infinite = Integer.MAX_VALUE;
// 정답
int infinite = Integer.MAX_VALUE/3;
```
<br>

##### 2. 반복문에서 값 비교를 할 때, 행열 입력 실수
```java
// 실수
if(map[i][j]==infinite && map[i][j]==infinite) {
  ...
}
// 정답
if(map[i][j]==infinite && map[j][i]==infinite) {
  ...
}
```
<br>

##### 3. 반복문의 제한조건으로 length를 입력할 때 주의할 것. 지금까지는 지역변수로 length를 따로 빼서 관리했는데, 이 때문에 여러 개의 반복문을 돌 때 헷갈림...







<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
### [플로이드-워셜방식](https://manzoo.tistory.com/11)

##### - 특징
- 모든 꼭짓점 사이의 최단경로를 구하는 방식
- 순환이 없는 그래프에서 사용
- 음수 가중치를 갖는 그래프에서도 사용이 가능.
<br>

##### - 3중 반복문을 돌기 때문에, 시간복잡도는 O(n^3)
1. 가장 바깥 반복문은 거쳐가는 꼭짓점(node)
2. 중간 반복문은 출발하는 꼭짓점
3. 안쪽 반복문은 도착하는 꼭짓점
```java
for (int k = 0; k < 4; k++) {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (d[i][j] > d[i][k] + d[k][j]) {
                d[i][j] = d[i][k] + d[k][j];
            }
        }
    }
}
```





<br>
<br>
<br>
