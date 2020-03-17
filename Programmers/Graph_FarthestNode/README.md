

# [가장 먼 노드](https://programmers.co.kr/learn/courses/30/lessons/43236)
* **참고자료** : https://lkhlkh23.tistory.com/110
* **참고자료** : https://heedipro.tistory.com/233
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.HashMap;
class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        Queue<Integer> q=  new LinkedList<Integer>();
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];

        HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
        for(int i=0;i<=n;i++) {
        	map.put(i, new ArrayList<Integer>());
        }
        for(int i=0;i<edge.length;i++) {
        	int from = edge[i][0];
        	int to = edge[i][1];
        	map.get(from).add(to);
        	map.get(to).add(from);
        }

        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
        	int from = q.peek();
            for(int to:map.get(from)) {
                if(!visited[to]) {
                    q.add(to);
                    distance[to]+=distance[from]+1; // 실수
                    visited[to] = true;
                }
            }  	
        	q.poll();
        }

        Arrays.sort(distance);
        int max = distance[distance.length-1];
        for(int i=distance.length-1;i>=0;i--) {
        	if(distance[i]<max) break;
        	answer++;
        }


        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* Graph
* 노드 1에서부터 가장 멀리 떨어진 노드의 갯수 찾기
	* 가장 멀리 떨어졌다 = 최단거리에 포함된 간선(edge)의 수가 가장 많다.
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### - 그래프 최단경로 탐색에 가능한 알고리즘이 뭐가 있을까 생각해봤는데 BFS를 제외한 다른 알고리즘들은 모두 간선의 가중치가 있어야 활용이 가능하므로 그냥 BFS를 활용했다.

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. BFS 탐색을 위한 변수들 선언.
```java
// BFS Queue
Queue<Integer> q=  new LinkedList<Integer>();

// 1번 노드에서부터 각 노드까지의 거리를 담는 배열
int[] distance = new int[n+1];

// 탐색 중 이전 노드를 재방문하지않토록 하는 booelan 배열
boolean[] visited = new boolean[n+1];

// 노드에 연결된 간선들을 표현하기위한 HashMap
HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
```
<br>

##### 2. 간선이 양방향이기 때문에 양쪽 노드 모두 HashMap에 넣는다.
```java
for(int i=0;i<edge.length;i++) {
	int from = edge[i][0];
	int to = edge[i][1];
	map.get(from).add(to);
	map.get(to).add(from);
}
```
<br>

##### 3. BFS 탐색
```java
q.add(1);
visited[1] = true;
while(!q.isEmpty()) {
	int from = q.peek();
	for(int to:map.get(from)) {
			if(!visited[to]) {
					q.add(to);
					distance[to]+=distance[from]+1; // 거리 누적
					visited[to] = true;
			}
	}  	
	q.poll();
}
```
<br>

##### 4. 가장 거리가 먼 노드들의 갯수 파악
```java
Arrays.sort(distance);
int max = distance[distance.length-1];
for(int i=distance.length-1;i>=0;i--) {
	if(distance[i]<max) break;
	answer++;
}
```
<br>

##### 5. 결과값 반환
```java
return answer;
```

<br><br>

## &#10095;&#10095;&#10095; 꿀팁

<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 처음에는 노드와 연결된 간선을 표현하기 위해 2차원 배열을 사용했으나, 공간복잡도가 굉장히 비효율적이라 테스트케이스 8,9번을 통과못했다.
* 해결책으로 2차원 배열 대신 HashMap과 ArrayList 활용.
	```java
	HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
	```
<br>

##### 2. 1번 노드로부터의 거리를 누적시키기 위해 사용한 distance 배열에서, 이전 노드의 distance 뿐만 아니라, 1을 동시에 더해줘야했다.
```java
// 실수
distance[to]++;

// 정답
distance[to]+=distance[from]+1;
```
<br>


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 1. HashMap이나 HashSet 같은 자료구조 안에 ArrayList를 넣으려면 반복문을 통해 ArrayList 객체를 생성후에 사용해야 한다.
```java
for(int i=0;i<=n;i++) {
	map.put(i, new ArrayList<Integer>());
}
```
<br>


<br>
<br>
<br>
