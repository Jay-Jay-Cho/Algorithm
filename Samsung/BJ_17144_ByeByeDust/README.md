# [미세먼지 안녕!](https://www.acmicpc.net/problem/17144)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17144_ByeByeDust {

	static int r,c,t;
	static int[][] map;
	static int[][] new_map;
	static int dust = 0;
	static boolean flag = true;
	static int row_cleaner_up = 0;
	static int row_cleaner_down = 0;
	static int[] spread_x = {-1,0,1,0};
	static int[] spread_y = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st1 = br.readLine().split(" ");
		r = Integer.parseInt(st1[0]);
		c = Integer.parseInt(st1[1]);
		t = Integer.parseInt(st1[2]);
		map = new int[r][c];
		for(int i=0;i<r;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==-1) {
					if(row_cleaner_up == 0) row_cleaner_up = i;
					else row_cleaner_down = i;
				}else {
					if(map[i][j]!=0) dust+=map[i][j];
				}
			}
		}
		if(dust==0) flag = false;
		int time = 1;
		while(flag && time<=t) {
			// spread
			boolean empty_check = spread();
			if(empty_check) {
				dust = 0;
				break;
			}

			// clean
			clean(true); //up
			clean(false);//down

			copyArray();
			time++;
		}
		System.out.println(dust);
	}
	static boolean spread() {
		new_map = new int[r][c];
		boolean isEmpty = true;
		for(int x=0;x<r;x++) {
			for(int y=0;y<c;y++) {
				int current_dust = map[x][y];
				if(current_dust == -1) new_map[x][y] = -1;	// mistake
				if(current_dust>0) {
					isEmpty = false;
					int cnt = 0;
					int spread_dust = current_dust/5;
					for(int i=0;i<4;i++) {
						int dx = x+spread_x[i];
						int dy = y+spread_y[i];
						if(dx<0 || dx>=r || dy<0 || dy>=c || map[dx][dy]==-1) continue;
						else {
							cnt++;
							new_map[dx][dy]+=spread_dust;
						}
					}
					//new_map[x][y] = current_dust - (spread_dust*cnt); //mistake
					new_map[x][y] += current_dust - (spread_dust*cnt);
				}
			}
		}
		return isEmpty;
	}

	static void clean(boolean up) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);

		int[] move_x;
		int[] move_y;
		int start_row;
		//up
		if(up) {
			start_row = row_cleaner_up;
			move_x = new int[]{0,-1,0,1};
			move_y = new int[]{1,0,-1,0};
		}
		//down
		else {
			start_row = row_cleaner_down;
			move_x = new int[]{0,1,0,-1};
			move_y = new int[]{1,0,-1,0};
		}

		int x = start_row;
		int y = 0;
		int idx = 0;
		while(true) {
			int dx = x+move_x[idx];
			int dy = y+move_y[idx];

			//if(new_map[dx][dy]==-1) break;

			if(dx<0 || dx>=r || dy<0 || dy>=c) {
				idx++;
				dx = x+move_x[idx];
				dy = y+move_y[idx];
			}

			if(new_map[dx][dy]==-1) break; // mistake

			int current = new_map[dx][dy];
			int prev = q.poll();
			q.offer(current);
			new_map[dx][dy] = prev;
			x = dx;
			y = dy;
		}
	}

	static void copyArray(){
		dust = 0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				int val = new_map[i][j];
				map[i][j] = val;
				if(val>0) dust+=val;
			}
		}
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 54%
* 시뮬레이션
* T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 초기 예외케이스 체크
* 먼지가 아예 없는 경우
#### 2. time만큼 반복문 돌기
##### 2-1. 미세먼지 확산
* map을 기준으로 계산해서,
* new_map에 값 채워넣기
##### 2-2. 공기청정기 가동
* 위/아래 분리
* 큐 활용해서 한 칸씩 이동&갱신 반복
##### 2-3. map 갱신
* new_map에 있는 값을 map으로 복사
* 미세먼지 양도 동시에 체크
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 반복문 전에 초기 예외케이스 체크
```java
if(map[i][j]!=0) dust+=map[i][j];
...
if(dust==0) flag = false;
```

#### 2. 반복문
```java
while(flag && time<=t) {
	// 미세먼지 확산
	boolean empty_check = spread();
	if(empty_check) {
		dust = 0;
		break;
	}
	// 공기청정기 가동
	clean(true); 	//위
	clean(false);	//아래

	// map 배열 갱신
	copyArray();
	time++;
}
```

##### 2-1. 미세먼지 확산
```java
static boolean spread() {
	new_map = new int[r][c];// 확산 후 변화된 미세먼지 새로 저장할 map
	boolean isEmpty = true;	// 미세먼지 아예 없는지 체크

	for(int x=0;x<r;x++) {
		for(int y=0;y<c;y++) {
			int current_dust = map[x][y];
			if(current_dust == -1) new_map[x][y] = -1;	// 공기청정기는 그대로 유지
			if(current_dust>0) {
				isEmpty = false;
				int cnt = 0;
				int spread_dust = current_dust/5;	// 확산되는 양
				for(int i=0;i<4;i++) {
					int dx = x+spread_x[i];
					int dy = y+spread_y[i];
					if(dx<0 || dx>=r || dy<0 || dy>=c || map[dx][dy]==-1) continue;
					else {
						cnt++;	// 확산 방향 ++
						new_map[dx][dy]+=spread_dust;	// 새로운 map에 추가
					}
				}
				// 기존 미세먼지 양도 갱신해서 새로운 map에 추가
				new_map[x][y] += current_dust - (spread_dust*cnt);
			}
		}
	}
	return isEmpty;	// 미세먼지가 하나라도 있으면 false 반환
}
```

##### 2-2. 공기청정기 가동
```java
static void clean(boolean up) {
	// 앞으로 진행하면서 미세먼지를 갱신해줄 큐
	Queue<Integer> q = new LinkedList<Integer>();
	q.offer(0);

	int[] move_x;
	int[] move_y;
	int start_row;
	//위
	if(up) {
		start_row = row_cleaner_up;
		// 오른쪽 -> 위 -> 왼쪽 -> 아래
		move_x = new int[]{0,-1,0,1};
		move_y = new int[]{1,0,-1,0};
	}
	//아래
	else {
		// 오른쪽 -> 아래 -> 왼쪽 -> 위
		start_row = row_cleaner_down;
		move_x = new int[]{0,1,0,-1};
		move_y = new int[]{1,0,-1,0};
	}

	int x = start_row;
	int y = 0;
	int idx = 0;
	while(true) {
		int dx = x+move_x[idx];
		int dy = y+move_y[idx];

		// 다음 값이 범위를 초과하면
		if(dx<0 || dx>=r || dy<0 || dy>=c) {
			idx++;	// 방향을 바꾸고, 다시 진행
			dx = x+move_x[idx];
			dy = y+move_y[idx];
		}

		// 다음 값이 공기청정기면, 멈춤
		if(new_map[dx][dy]==-1) break;

		int current = new_map[dx][dy];
		int prev = q.poll();
		q.offer(current);	// 현재 값을 큐에 넣어주고
		new_map[dx][dy] = prev;	// 큐에 저장된 이전 값으로 현재 값을 갱신

		// 다음 위치로 이동
		x = dx;
		y = dy;
	}
}
```

##### 2-3. map 갱신
```java
static void copyArray(){
	dust = 0;
	for(int i=0;i<r;i++) {
		for(int j=0;j<c;j++) {
			int val = new_map[i][j];
			map[i][j] = val;
			if(val>0) dust+=val;
		}
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 미세먼지 확산시, 공기청정기 위치를 무시해서 `무한루프`.
* 공기청정기가 있으면, new_map에 그대로 복사
```java
//실수
for(int x=0;x<r;x++) {
	for(int y=0;y<c;y++) {
		if(current_dust>0) { ... }
	}
}

//정답
for(int x=0;x<r;x++) {
	for(int y=0;y<c;y++) {
		if(current_dust == -1) new_map[x][y] = -1;
		if(current_dust>0) { ... }
	}
}
```

#### 2. 미세먼지 확산시, new_map에 값을 더해줘야 하는데, 갱신해서 잘못된 값 들어감.
```java
//실수
new_map[x][y] == current_dust - (spread_dust*cnt);
//정답
new_map[x][y] += current_dust - (spread_dust*cnt);
```

#### 3. 공기청정기 가동시, `indexOutofBoundException` 발생
* 범위 초과 조건이 먼저와야되는데, 공기청정기 체크를 먼저 함.
* 순서를 바꿔줘야...

```java
//실수
while(true) {
	// 공기청정기 도착
	if(new_map[dx][dy]==-1) break;
	// 범위초과
	if(dx<0 || dx>=r || dy<0 || dy>=c) {
		idx++;
		dx = x+move_x[idx];
		dy = y+move_y[idx];
	}
	...
}

//정답
while(true) {
	// 범위초과
	if(dx<0 || dx>=r || dy<0 || dy>=c) {
		idx++;
		dx = x+move_x[idx];
		dy = y+move_y[idx];
	}
	// 공기청정기 도착
	if(new_map[dx][dy]==-1) break;

	...
}
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
#### 1. 새로운 배열을 갱신해서 값을 채워나갈때, `=`인지 `+=`인지 정확하게 파악
#### 2. 조건문에서는 범위초과 체크를 가장 앞에......

<br>
<br>
<br>
