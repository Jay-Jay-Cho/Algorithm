

# [섬 연결하기](https://programmers.co.kr/learn/courses/30/lessons/42861)
* **참고자료** : Prim - https://readystory.tistory.com/91
* **참고자료** : Kruskal - https://woongsin94.tistory.com/184
* **참고자료** : Union-Find - https://ssungkang.tistory.com/entry/Algorithm-%EC%9C%A0%EB%8B%88%EC%98%A8-%ED%8C%8C%EC%9D%B8%EB%93%9CUnion-Find
<br>

## &#10095;&#10095;&#10095; 전체 코드
### _ [Prim](https://readystory.tistory.com/91) 알고리즘
```java
import java.util.ArrayList;
class Solution {

  // Prim
  public int solution(int n, int[][] costs) {
        int answer = 0;

        boolean[] visit = new boolean[n];
        int[][] maps = new int[n][n];
        ArrayList<Integer> list = new  ArrayList<Integer>();
        for(int i=0;i<costs.length;i++) {
        	int from = costs[i][0];
        	int to = costs[i][1];
        	int cost = costs[i][2];
        	maps[from][to] = cost;
        	maps[to][from] = cost;
        }

        // initialize
        list.add(0);
        visit[0] = true;

        while(list.size()<n) {
        	int min = Integer.MAX_VALUE;
        	int min_idx = -1;

        	for(int i=0;i<list.size();i++) {
        		int v = list.get(i);
        		for(int j=0;j<n;j++) {
        			if(maps[v][j]!=0 && !visit[j] && min > maps[v][j]) {
        				min = maps[v][j];
        				min_idx = j;
        			}
        		}
        	}
        	list.add(min_idx);
        	visit[min_idx] = true;
        	answer+=min;
        }

        return answer;
    }
}
```

##### _ [Kruskal](https://woongsin94.tistory.com/184) &  [Union-Find](https://ssungkang.tistory.com/entry/Algorithm-%EC%9C%A0%EB%8B%88%EC%98%A8-%ED%8C%8C%EC%9D%B8%EB%93%9CUnion-Find) 알고리즘
```java
import java.util.Arrays;
import java.util.Comparator;
class Solution {
        static int[] parent;

  // 부모 노드 찾기
	static int find(int x) {
		if(x == parent[x]) return x;
		else {
			int y = find(parent[x]);
			parent[x] = y;
			return y;
		}
	}

  // 합집합 = 부모 노드를 하나로 통일
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x!=y) {
			if(x<y) {
				parent[y] = x;
			}else {
				parent[x] = y;
			}
		}
	}

  // 같은 집합인지 확인
	static boolean isSame(int x, int y) {
		return find(x) == find(y);
	}

      // Kruskal
    	public int solution(int n, int[][] costs) {
    		int answer = 0;

    		parent = new int[n];
    		for(int i=0;i<n;i++) parent[i] = i;

    		Arrays.sort(costs,new Comparator<int[]>() {
    			public int compare(int[] a, int[] b) {
    				if(a[2]<=b[2]) return -1;
    				else return 1;
    			}
    		});

    		boolean[] visit = new boolean[n];
    		int cnt = 0;
        int i = 0;
    		while(cnt<n-1) {
    			if(!isSame(costs[i][0],costs[i][1])) {
    				//visit[i] = true;
    				union(costs[i][0],costs[i][1]);
    				cnt++;
    				answer += costs[i][2];
    			}

    			i++;
    		}

    		return answer;
    	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* Greedy
* 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용s
  * 즉, MST를 구해라!!!
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 0. 처음에는 플로이드-워셜 알고리즘을 적용했다. 각 노드(v)간의 최소 비용은 구해지지만, 모든 노드를 연결하는 MST를 구하는 데는 부적합하기 때문에 포기.....
* 예를들어, v1에서 v2까지의 최소 비용은 구할 수 있지만, 이 과정에서 어느 노드(v)를 지나는지는 알 수 없음....

##### 1. Prim 알고리즘
* 노드(v) 기준 MST
* 방문하지 않은 노드들 중에서 최솟값 간선을 가진 노드와 연결
* 여기서 중요한 것은 방문 여부를 체크할 때, 마지막에 들어온 노드 기준이 아니라, 현재 list에 들어온 모든 노드들을 기준으로 탐색한다.


##### 2. Kruskal & Unidon-Find 알고리즘
* 간선(edge) 기준 MST
* Kruskal 알고리즘으로 구현한 MST에서는, `간선의 수 = 노드의 수 - 1`
* 일단 cost를 기준으로 오름차순 정렬
* index 0 부터 시작해서, 만약 두 섬(`costs[i][0]` , `costs[i][1]`)의 parent가 다르면 MST에 추가

<br><br>

## &#10095;&#10095;&#10095; 풀이
### - Prim
##### (1) 변수 선언
```java
boolean[] visit = new boolean[n]; // 노드 방문 여부 체크
int[][] maps = new int[n][n]; // 각 노드 간 cost 배열
ArrayList<Integer> list = new  ArrayList<Integer>();  // MST 리스트
```
##### (2) 2차원 배열에 노드, 간선 넣기
```java
for(int i=0;i<costs.length;i++) {
	int from = costs[i][0];
	int to = costs[i][1];
	int cost = costs[i][2];
	maps[from][to] = cost;
	maps[to][from] = cost;
}
```
##### (3) Prim 탐색
* 말했다시피, 마지막으로 연결된 노드가 아니라, MST 리스트에 존재하는 모든 노드들을 기준으로 가장 cost가 낮은 다음 노드를 선택.
  1. `maps[v][j]!=0` : 노드가 서로 연결돼있고,
  2. `!visit[j]` : MST 포함돼있지 않고,
  3. `min > maps[v][j]` : cost가 가장 낮은.
```java
list.add(0); // 아무 노드나 넣고, 초기화
visit[0] = true; // MST 리스트에 추가됐으므로, 방문 표시

while(list.size()<n) {
	int min = Integer.MAX_VALUE;
	int min_idx = -1;

	for(int i=0;i<list.size();i++) {
		int v = list.get(i);
		for(int j=0;j<n;j++) {
			if(maps[v][j]!=0 && !visit[j] && min > maps[v][j]) {
				min = maps[v][j];
				min_idx = j;
			}
		}
	}

	list.add(min_idx); // 선별된 노드를 MST 리스트에 추가
	visit[min_idx] = true; // MST 리스트에 추가됐으므로, 방문 표시
	answer+=min;
}
```
<br>

### - Kruskal
##### (1) 간선의 MST 포함 여부를 체크하기위한 Union-Find 구현
* 노드 갯수만큼의 MST 배열을 선언
```java
int[] parent;
```
* `Union` : 합집합 만들기 = MST 추가
```java
static void union(int x, int y) {
	x = find(x); // x의 부모노드
	y = find(y); // y의 부모노드
	if(x!=y) { // 만약 둘의 부모노드가 다르다면 = 같은 집합이 아니면

                // 부모노드가 작은 쪽으로 합집합 만들기
		if(x<y) {
			parent[y] = x;
		}else {
			parent[x] = y;
		}
	}
}
```
* `Find` : 부모노드 찾기
```java
static int find(int x) {
  // 자기자신이 부모노드면 = 아직 MST 집합에 추가안됐으면
	if(x == parent[x]) return x;
	else {
		int y = find(parent[x]);  // 편향트리 방지
		parent[x] = y;
		return y;
	}
}
```
* `isSame` : 노드 2개가 같은 부모노드를 가지는지 = 간선의 MST 포함여부 판단
```java
static boolean isSame(int x, int y) {
	return find(x) == find(y);
}
```

##### (2) 선언해놓은 MST 배열의 각 부모노드들을 자기자신으로 초기화
```java
parent = new int[n];
for(int i=0;i<n;i++) parent[i] = i;
```

##### (3) cost를 기준으로 주어진 costs 배열을 오름차순으로 정렬
```java
Arrays.sort(costs,new Comparator<int[]>() {
	public int compare(int[] a, int[] b) {
		if(a[2]<=b[2]) return -1;
		else return 1;
	}
});
```

##### (4) Kruskal 탐색
```java
int cnt = 0;  // MST 간선 갯수
int i = 0;  // 탐색 포인트(현재 위치의 인덱스)
while(cnt<n-1) {  // MST에서 간선의 갯수 = 노드의 갯수 - 1
	if(!isSame(costs[i][0],costs[i][1])) { // MST에 포함이 아직 안된 간선이면,
		union(costs[i][0],costs[i][1]); // MST에 포함시키고,
		cnt++;
		answer += costs[i][2];  // 해당 간선의 weight만큼 answer++
	}
	i++; // 다음 인덱스로
}
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. Prim 알고리즘에서, 방문여부를 체크할 때 현재 list에 들어온 노드들이 아닌 가장 마지막에 들어온 노드를 기준으로 탐색했다.
```java
for(int i=0;i<list.size();i++) {
  int v = list.get(i);
  for(int j=0;j<n;j++) {
    if(maps[v][j]!=0 && !visit[j] && min > maps[v][j]) {
      min = maps[v][j];
      min_idx = j;
    }
  }
}
```

##### 2. Kruskal 알고리즘에서는 노드의 방문 여부(`boolean[] visit`)를 체크하면 안된다. 왜냐하면 간선(노드 2개로 구성)을 기준으로 MST에 추가되기 때문에, 하나의 노드만 중복될 경우에도 에러 발생..




<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 1. Prim 알고리즘에서는, MST 비교를 위한 List와 노드 판별을 위한 visit 배열이 필요하다.
* MST 완성 여부 판단은 `list.size() == n`
```java
ArrayList<Integer> list;
boolean[] visit = new visit[n];
```
##### 2. Kruskal 알고리즘에서는, MST 비교를 위한 Union-Find 배열이 필요하다.
* MST 완성 여부 판단은 `MST에 추가된 간선의 갯수 == n - 1`
```java
int[] parent = new int[n];
```



<br>
<br>
<br>
