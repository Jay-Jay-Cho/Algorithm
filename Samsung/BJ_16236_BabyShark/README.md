# [아기 상어](https://www.acmicpc.net/problem/16236)
* **참고자료** : https://www.acmicpc.net/board/view/42021

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_16236_BabyShark {

	static class Shark{
		int x;
		int y;
		int size;
		int eat;
		Shark(int x, int y, int size, int eat){
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
		}
	}

	static class Fish{
		int x;
		int y;
		int size;
		int cnt;
		Fish(int x, int y, int size, int cnt){
			this.x = x;
			this.y = y;
			this.size = size;
			this.cnt = cnt;
		}
	}

	static int n;
	static int[][] map;
	static int fish_cnt = 0;
	static Shark shark;
	static boolean flag = true;
	static int[] move_x = {-1,1,0,0};
	static int[] move_y = {0,0,-1,1};
	static PriorityQueue<Fish> pq;
	static Queue<Fish> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num>0 && num<9) fish_cnt++;
				if(num==9) shark = new Shark(i,j,2,0);
			}
		}
		if(fish_cnt==0) flag = false;

		int time = 0;
		while(flag) {
			// find edible fishes
			find();
			if(pq.isEmpty()) break;	// mistake

			// shark moves & eat
			Fish fish = pq.poll();
			map[shark.x][shark.y] = 0; // mistake
			shark.x = fish.x;
			shark.y = fish.y;
			shark.eat++;
			time += fish.cnt;

			// update
			//map[fish.x][fish.y] = -1;
			map[fish.x][fish.y] = 0;
			if(shark.eat >= shark.size) {
				shark.eat = 0; //
				shark.size++;
			}
			fish_cnt--;

			if(fish_cnt==0) break;
			if(!isEdible()) break;
		}
		System.out.println(time);
	}

	static void find() {
		pq = new PriorityQueue<Fish>(new Comparator<Fish>() {
			@Override
			public int compare(Fish fish1, Fish fish2) {
				if(fish1.cnt < fish2.cnt) return -1;
				else if(fish1.cnt == fish2.cnt) {
					if(fish1.x < fish2.x) return -1;
					else if(fish1.x == fish2.x)
						if(fish1.y < fish2.y) return -1;
						else return 1;
					else return 1;
				}
				else return 1;
			}
		});
		boolean[][] visited = new boolean[n][n];
		q = new LinkedList<Fish>();
		q.add(new Fish(shark.x, shark.y, shark.size, 0));
		visited[shark.x][shark.y] = true; //
		int cnt = Integer.MAX_VALUE;
		//BFS
		while(!q.isEmpty()) {
			Fish fish  = q.poll();
			int x = fish.x;
			int y = fish.y;
			for(int i=0;i<4;i++) {
				int dx = x+move_x[i];
				int dy = y+move_y[i];
				int fish_cnt = fish.cnt + 1;
				if(dx>=0 && dx<n && dy>=0 && dy<n && map[dx][dy]>=0 && !visited[dx][dy] && map[dx][dy]<=shark.size && fish_cnt<=cnt) { // move
					int fish_size = map[dx][dy];
					q.add(new Fish(dx,dy,fish_size,fish_cnt));
					visited[dx][dy] = true;
					if(fish_size<shark.size && map[dx][dy]>0) {	// edible
						cnt = Math.min(fish_cnt, cnt);
						pq.add(new Fish(dx,dy,fish_size,fish_cnt));
					}
				}
			}
		}
	}

	static boolean isEdible() {
		boolean edible = false;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] < shark.size && map[i][j]>0) { // mistake
					edible = true;
					break;
				}
			}
			if(edible) break;
		}
		return edible;
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 37%
* BFS, 시뮬레이션
* 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 초기 예외케이스 체크
* 물고가기 한 마리도 없는 경우
#### 2. 가까운 거리에 있고, 먹을 수 있는 물고기들을 우선순위 큐에 담기
#### 3. 우선순위 큐의 head에 있는 물고기 위치로 이동해서 잡아먹기
* 우선순위 큐가 비어있으면, 잡아먹을 수 있는 물고기가 없는 것이므로 종료
* 이전 상어위치는 0으로 초기화
* 잡아먹기
	* 상어 위치 = 물고기 위치
	* shark.eat++
#### 4. 변수 업데이트
* 상어 크기 변화
* 전체 물고기 갯수 감소

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 반복문 전에 초기 예외케이스 체크
```java
if(fish_cnt==0) flag = false;
```

#### 2. 반복문을 돌면서 계속 체크 & 변수 업데이트
```java
int time = 0;
while(flag) {
	find(); // 먹을 수 있는 물고기들을 찾아서 PriorityQueue에 넣기
	if(pq.isEmpty()) break;	// 먹을 수 있는 물고기들이 없으면, 종료

	// 먹을 수 있는 물고기의 위치로 이동해서 잡아먹기
	Fish fish = pq.poll();
	map[shark.x][shark.y] = 0; // 상어의 현재위치는 0으로 초기화
	// 물고기 위치로 상어 이동
	shark.x = fish.x;
	shark.y = fish.y;
	// 잡아먹기
	map[fish.x][fish.y] = 0;
	shark.eat++;

	// 변수 업데이트
	time += fish.cnt;	// 상어 이동거리만큼 time++
	// 상어 크기 업데이트
	if(shark.eat >= shark.size) {
		shark.eat = 0;
		shark.size++;
	}
	fish_cnt--;	// 전체 물고기 마릿수 감소

	if(fish_cnt==0) break;	// 물고기가 한 마리도 안남으면, 종료
	if(!isEdible()) break;	// 먹을 수 있는 물고기가 더이상 없으면, 종료
}
```

##### 2-1. 먹을 수 있는 물고기를 찾아서 PriorityQueue에 넣기 (`잡아먹기 전`)
* `PriorityQueue<Fish> pq` : **물고기를 제시된 정렬 기준으로 추출시킬 우선순위큐**
	* 거리(cnt) 오름차순
	* 거리가 같다면, 가장 위에 위치한 순서대로
	* 가장 위에 위치한 물고기들이 많다면, 가장 좌측에 위치한 순서대로


```java
static void find() {
	pq = new PriorityQueue<Fish>(new Comparator<Fish>() {
		@Override
		public int compare(Fish fish1, Fish fish2) {
			if(fish1.cnt < fish2.cnt) return -1;
			else if(fish1.cnt == fish2.cnt) {
				if(fish1.x < fish2.x) return -1;
				else if(fish1.x == fish2.x)
					if(fish1.y < fish2.y) return -1;
					else return 1;
				else return 1;
			}
			else return 1;
		}
	});
	boolean[][] visited = new boolean[n][n];
	q = new LinkedList<Fish>();
	q.add(new Fish(shark.x, shark.y, shark.size, 0));
	visited[shark.x][shark.y] = true;
	int cnt = Integer.MAX_VALUE;

	//BFS
	while(!q.isEmpty()) {
		Fish fish  = q.poll();
		int x = fish.x;
		int y = fish.y;
		for(int i=0;i<4;i++) {
			int dx = x+move_x[i];
			int dy = y+move_y[i];
			int fish_cnt = fish.cnt + 1;
			// 해당 방향으로 움직일 수 있는지
			if(dx>=0 && dx<n && dy>=0 && dy<n && map[dx][dy]>=0 && !visited[dx][dy] && map[dx][dy]<=shark.size && fish_cnt<=cnt) {
				int fish_size = map[dx][dy];
				q.add(new Fish(dx,dy,fish_size,fish_cnt));
				visited[dx][dy] = true;
				// 해당 위치 물고리를 먹을 수 있는지
				if(fish_size<shark.size && map[dx][dy]>0) {
					cnt = Math.min(fish_cnt, cnt);	// 최단거리 갱신
					pq.add(new Fish(dx,dy,fish_size,fish_cnt));
				}
			}
		}
	}
}
```

##### 2-2. 먹을 수 있는 물고기가 있는지 찾기 (`잡아먹은 후`)
```java
static boolean isEdible() {
	boolean edible = false;
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			// 먹을 수 있는 물고기를 한 마리라도 찾으면, 종료
			if(map[i][j] < shark.size && map[i][j]>0) {
				edible = true;
				break;
			}
		}
		if(edible) break;
	}
	return edible;
}
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 물고기들은 존재하지만, 먹을 수 없는(?) 반례를 무시해서 `NullPointerException`.
* `find()`함수 이후에 `pq`가 비어있는, 즉 먹을 수 있는 물고기들이 없는 경우를 추가  
```java
//실수
find();

//정답
find();
if(pq.isEmpty()) break;
```

#### 2. 상어의 이전 위치 초기화를 안해줬음.
* 이전 위치를 0으로 초기화
```java
map[shark.x][shark.y] = 0;
```

#### 3. 이동 가능한 위치를 BFS 방문 시, 상어의 크기를 비교하는 조건이 없었음.
```java
//실수
if(dx>=0 && dx<n && dy>=0 && dy<n && !visited[dx][dy] && fish_cnt<=cnt) { ... }

//정답
if(dx>=0 && dx<n && dy>=0 && dy<n && map[dx][dy]>=0 && !visited[dx][dy] && map[dx][dy]<=shark.size && fish_cnt<=cnt) { ... }
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
#### 1. BFS 쓸거면 무조건 visited 배열은 활용하자!!!!
#### 2. 배열에서 객체 이동시에는 이동 후 + `이동 전 위치 초기화`까지 신경쓰자!!!!

<br>
<br>
<br>
