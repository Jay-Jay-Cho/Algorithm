# [연구소 3](https://www.acmicpc.net/problem/17142)
* **참고자료** (**반례**) : https://www.acmicpc.net/board/view/43303
* **참고자료** (**반례**) : https://mygumi.tistory.com/352

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17142_Lab3 {

	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static int[][] map;
	static ArrayList<Pos> list;
	static int[] sub_virus;
	static Stack<String> stack;
	static int[] cnt_arr = new int[4];
	static int cnt_sum = 0;
	static int answer = Integer.MAX_VALUE;
	static int[] move_x = {-1,0,1,0};
	static int[] move_y = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first_line = br.readLine().split(" ");
		N = Integer.parseInt(first_line[0]);
		map = new int[N][N];
		M = Integer.parseInt(first_line[1]);
		list = new ArrayList<Pos>();



		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				cnt_arr[value]++;
				if(value==2) list.add(new Pos(i,j));
			}
		}

		// Exceptional Case = no virus
		for(int i=0;i<3;i++) {
			cnt_sum+=cnt_arr[i];
		}
		if(cnt_sum == cnt_arr[0] || cnt_sum == cnt_arr[1]) {
			answer = -2;
		}

		int size = list.size();
		sub_virus = new int[size];
		boolean[] used = new boolean[size];
		for(int i=0;i<size;i++) sub_virus[i]=i;
		stack = new Stack<String>();
		combination(sub_virus,used,0,M);

		while(!stack.isEmpty() && answer!=-2) { //mistake
			int empty_cnt = cnt_arr[0];
			String initial_virus = stack.pop();

			// copy the map
			int[][] copy_map = new int[N][N];
			for(int i=0;i<N;i++) {
				copy_map[i] = map[i].clone();
			}
			boolean[][] visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				Arrays.fill(visited[i],false);
			}

			Queue<Pos> q = new LinkedList<Pos>();

			boolean flag = true;
			int time = 1;
			while(flag) {
				boolean change = false; // mistake
				if(time==1) {
					for(int idx=0;idx<M;idx++) {
						Pos pos = list.get(initial_virus.charAt(idx)-'0');
						int x = pos.x;
						int y = pos.y;
						visited[x][y] = true;
						for(int i=0;i<4;i++) {
							int dx = x+move_x[i];
							int dy = y+move_y[i];
							if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) {
								if(copy_map[dx][dy]==0) {
									empty_cnt--;
									q.offer(new Pos(dx,dy));
									copy_map[dx][dy] = 2;
								}else {
									q.offer(new Pos(dx,dy));
								}
								change = true;
								visited[dx][dy] = true;
							}
						}
					}
				}else {
					int q_size = q.size();
					for(int k=0;k<q_size;k++) {
						Pos pos = q.poll();
						int x = pos.x;
						int y = pos.y;
						for(int i=0;i<4;i++) {
							int dx = x+move_x[i];
							int dy = y+move_y[i];
							if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) {
								if(copy_map[dx][dy]==0) {
									empty_cnt--;
									q.offer(new Pos(dx,dy));
									copy_map[dx][dy] = 2;
								}else {
									q.offer(new Pos(dx,dy));
								}
								visited[dx][dy] = true;
								change = true;
							}
						}
						//q.offer(pos); -----> 시간초과 원흉  
					}
				}
				if(time>=answer) flag=false;
				if(empty_cnt<=0) {
					if(time==1 && cnt_arr[0]==0) {
						answer = 0; //mistake
					}
					else answer =  Math.min(answer, time);
					flag = false;
				}
				if(!change) {
					flag = false;
				}
				time++;
			}
		}
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}

	static void combination(int[] arr, boolean[] visited, int depth, int r) {
		if(r==0) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<arr.length;i++) {
				if(visited[i]) {
					sb.append(arr[i]);
				}
			}
			stack.push(sb.toString());
			return;
		}

		for(int i=depth;i<arr.length;i++) {
			visited[i] = true;
			combination(arr,visited,i+1,r-1);
			visited[i] = false; // backtracking
		}
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 25%
* DFS(백트래킹),BFS,시뮬레이션
* 연구소의 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. `DFS`(백트래킹)를 활용한 조합(Combination)을 통해, 초기 후보군이 될 수 있는 바이러스 위치를 스택에 저장
#### 2. while문을 통해, 매 시간마다 바이러스를 확산시킴(`BFS`)
* 정상 종료
	1) 빈 공간이 전부 바이러스로 채워졌다면 answer를 time으로 갱신하고 종료
* 비정상 종료
	1) 이전 값보다 시간이 더 많이 경과하면 무의미하므로 종료
	2) 확산시킬 바이러스가 더 이상 없다면, 즉 큐가 비어있다면 종료
#### 3. 모든 케이스를 다 확인했음에도, answer의 초기값(`MAX_NUM`)이 변하지 않았다면 해당 케이스는 -1을 반환. 아니면 갱신된 answer를 반환.



<br><br>

## &#10095;&#10095;&#10095; 풀이
#### 1. 탐색이 불가능한 경우를 먼저 예외처리
```java
// 연구소에 바이러스가 전혀 없거나, 모두 벽으로 둘러쌓였을 경우
if(cnt_sum == cnt_arr[0] || cnt_sum == cnt_arr[1]) {
	answer = -2;
}
```
#### 2. 조합을 통해 가능한 초기 바이러스 위치들을 저장
```java
int size = list.size();	// list = 모든 바이러스의 위치가 담긴 ArrayList
sub_virus = new int[size];
boolean[] used = new boolean[size];
for(int i=0;i<size;i++) sub_virus[i]=i;
stack = new Stack<String>();
combination(sub_virus,used,0,M); // 조합된 바이러스의 list 인덱스를 String으로 저장
```

#### 3. while 반복문을 돌면서 바이러스 확산
```java
// 초기 예외 경우가 아니고(answer=-2), 가능한 바이러스 조합을 하나씩 확인
while(!stack.isEmpty() && answer!=-2) {
	int empty_cnt = cnt_arr[0];	// 빈 공간의 갯수
	String initial_virus = stack.pop();

	int[][] copy_map = new int[N][N]; // 다음 조합을 위해 map 배열을 복사해서 사용
	for(int i=0;i<N;i++) {
		copy_map[i] = map[i].clone();
	}
	boolean[][] visited = new boolean[N][N];
	for(int i=0;i<N;i++) {
		Arrays.fill(visited[i],false);
	}

	Queue<Pos> q = new LinkedList<Pos>();	// 다음 time때 확산시킬 바이러스를 담을 큐

	boolean flag = true;
	int time = 1;
	while(flag) {
		boolean change = false; // 확산시킬 바이러스가 존재하는지 체크
		if(time==1) {
			// 초기 바이러스의 경우
			for(int idx=0;idx<M;idx++) {
				Pos pos = list.get(initial_virus.charAt(idx)-'0');
				int x = pos.x;
				int y = pos.y;
				visited[x][y] = true;
				for(int i=0;i<4;i++) {
					int dx = x+move_x[i];
					int dy = y+move_y[i];
					if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) {
						if(copy_map[dx][dy]==0) {
							empty_cnt--;
							q.offer(new Pos(dx,dy));
							copy_map[dx][dy] = 2;
						}else {
							q.offer(new Pos(dx,dy));
						}
						change = true;
						visited[dx][dy] = true;
					}
				}
			}
		}
		// 초기 바이러스가 아닌 경우
		else {
			int q_size = q.size();
			for(int k=0;k<q_size;k++) {
				Pos pos = q.poll();
				int x = pos.x;
				int y = pos.y;
				for(int i=0;i<4;i++) {
					int dx = x+move_x[i];
					int dy = y+move_y[i];
					if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) {
						if(copy_map[dx][dy]==0) {
							empty_cnt--;
							q.offer(new Pos(dx,dy));
							copy_map[dx][dy] = 2;
						}else {
							q.offer(new Pos(dx,dy));
						}
						visited[dx][dy] = true;
						change = true;
					}
				}
			}
		}
		// 모든 빈 공간이 바이러스로 채워졌으면
		if(empty_cnt<=0) {
			if(time==1 && cnt_arr[0]==0) {	// 원래부터 빈 공간이 없었으면 time=0
				answer = 0;
			}
			else answer =  Math.min(answer, time);	// 아니면 answer 갱신
			flag = false;
		}
		if(time>=answer || !change) flag=false;

		time++;	// 시간 경과
	}
}
```

<br><br>

## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 초기 예외상황을 생각안하고 진행했음.
```java
// 실수
while(!stack.isEmpty()) { ... }
// 정답
while(!stack.isEmpty() && answer!=-2) { ... }
```

#### 2. 비활성화된 바이러스는, 바이러스를 만나면 활성화되는데 이 경우를 생각안함. 즉, 그냥 빈 공간을 만날 때만 체크했음.
```java
// 실수
if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]==0) {
		empty_cnt--;
		q.offer(new Pos(dx,dy));
		copy_map[dx][dy] = 2;
}

// 정답
// 비활성화 바이러스 조건 추가 && 방문확인
if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) {
	if(copy_map[dx][dy]==0) {
		empty_cnt--;
		q.offer(new Pos(dx,dy));
		copy_map[dx][dy] = 2; // mistake
	}else {
		q.offer(new Pos(dx,dy));
	}
	visited[dx][dy] = true;
	change = true;
}
```

#### 3. 바이러스 확산시, 이미 바이러스가 있는 곳은 중복방문함. 이 때문에 `시간초과` 발생
* BFS이기 때문에, 방문을 표시할 2차원 배열을 만들어서 해결
```java
// 실수
if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1) { ... }
// 정답
if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) { ... }
```

#### 4. (`종료조건 실수`) 더 이상 진행할 수 없는 경우를, 빈 공간의 갯수 변동 여부로 체크했음.
* 3이 아니라, -1이 반환됨.
```java
// 실수
if(previous_empty_cnt == empty_cnt) flag = false;

// 정답
if(!change) flag = false;
```
* 아래와 같은 경우에서 에러가 발생함
	* time이 1일 때
		> 1 1 1 1 1
			1 1 1 1 1
			1 1 1 1 1
			0 2 2 2 2
			1 1 1 1 1

	* time이 2일 때: 여기서, 큐에 바이러스(`3,1`)가 존재하므로 다음 time때 제거가 가능하지만 **빈 공간의 갯수에는 변화가 없기 때문에** 종료됨.
		> 1 1 1 1 1
			1 1 1 1 1
			1 1 1 1 1
			0 2 2 2 2
			1 1 1 1 1


#### 5. 초기 바이러스 확산 한번에 모든 공간이 바이러스로 채워지는 경우를 빼먹음.
* 1이 아니라, 0이 반환됨.
* while문이 time이 1일 때부터 돌아가기 때문에, 반례 즉 아예 처음부터 0이 없는 경우(`cnt_arr[0]==0`)를 조건에 추가해줬어야 함.

```java
// 실수
if(empty_cnt<=0) {
	if(time==1) answer = 0;
	else answer =  Math.min(answer, time);
	flag = false;
}

// 정답
if(empty_cnt<=0) {
	if(time==1 && cnt_arr[0]==0) {
		answer = 0; //mistake
	}
	else answer =  Math.min(answer, time);
	flag = false;
}
```
* 아래와 같은 경우에서 에러가 발생함
	* time이 0일 때
		> 2 2 2 1 1
			2 1 1 1 1
			2 1 1 1 1
			2 1 1 1 1
			2 2 2 2 0

	* time이 1일 때
		> 2 2 2 1 1
			2 1 1 1 1
			2 1 1 1 1
			2 1 1 1 1
			2 2 2 2 1

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
#### 1. BFS를 사용하는 경우에는 반드시 visited 배열을 통해 중복을 방지할 것!!!
#### 2. 반례 조심... 특히나 시간 경과를 기준으로 반복문의 종료가 결정될 때...
#### 3. 문제 잘 읽을 것!!!
* 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변한다.





<br>
<br>
<br>
