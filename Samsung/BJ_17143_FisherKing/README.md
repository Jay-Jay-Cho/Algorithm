# [낚시왕](https://www.acmicpc.net/problem/17143)
* **참고자료** : https://yabmoons.tistory.com/288

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17143_FisherKing {

	static class Shark{
		int x;
		int y;
		int speed;
		int direction;
		int size;
		Shark(int x, int y, int speed, int direction, int size){
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}

	static int r,c,m;
	static Shark[][] map;
	static boolean flag = true;
	static int fisher = 0;
	static int weight = 0;
	static Queue<Shark> survived_sharks;
	static int[] move_x = {0,-1,1,0,0}; //
	static int[] move_y = {0,0,0,1,-1};
	static int stay_vertical;
	static int stay_horizontal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st1 = br.readLine().split(" ");
		r = Integer.parseInt(st1[0]);
		stay_vertical = r*2-2;
		c = Integer.parseInt(st1[1]);
		stay_horizontal = c*2-2;
		m = Integer.parseInt(st1[2]);
		if(m==0) flag = false;
		map = new Shark[r][c];
		PriorityQueue<Shark> temp_survived_sharks = new PriorityQueue<Shark>(new Comparator<Shark>() {
			@Override
			public int compare(Shark shark1, Shark shark2) {
				if(shark1.size > shark2.size) return -1;
				else return 1;
			}
		});
		survived_sharks = new LinkedList<Shark>();
		for(int i=0;i<m;i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st2.nextToken())-1; //
			int y = Integer.parseInt(st2.nextToken())-1; //
			int speed = Integer.parseInt(st2.nextToken());
			int direction = Integer.parseInt(st2.nextToken());
			int size = Integer.parseInt(st2.nextToken());
			Shark shark = new Shark(x,y,speed,direction,size);
			map[x][y] = shark;
			survived_sharks.add(shark);
		}

		while(flag && fisher<c && m>=0) {
			// catch shark
			for(int row=0;row<r;row++) {
				if(map[row][fisher]!=null) {
					weight+=map[row][fisher].size;
					map[row][fisher]=null;	// remove from array
					// remove from q
					while(true) {
						Shark shark = survived_sharks.poll();
						if(shark.x == row && shark.y == fisher) {
							break;
						}
						else survived_sharks.offer(shark);
					}
					m--;
					break; // mistake
				}
			}
			// sharks move
			int size = survived_sharks.size();
			for(int i=0;i<size;i++) {
				Shark shark = survived_sharks.poll();
				int x = shark.x;
				int y = shark.y;
				int direction = shark.direction;
				int speed;
				if(direction<=2) speed = shark.speed%stay_vertical;
				else speed = shark.speed%stay_horizontal;

				int shark_size = shark.size;
				for(int move=0;move<shark.speed;move++) {
					int dx = x+move_x[direction];
					int dy = y+move_y[direction];
					if(dx<0 || dx>=r || dy<0 || dy>=c) { // out the range
						direction = changeDirection(direction);
						x = x+move_x[direction]; // x = dx+move_x[direction];
						y = y+move_y[direction];
					}else {
						x = dx;
						y = dy;
					}
				}
				//survived_sharks.add(new Shark(x,y,speed,direction,shark_size));
				temp_survived_sharks.offer(new Shark(x,y,speed,direction,shark_size));
			}

			// empty the map
			for(int row=0;row<r;row++) {
				Arrays.fill(map[row], null);
			}
			// locate the shark
			int q_size = temp_survived_sharks.size();
			for(int i=0;i<q_size;i++) {
				Shark shark = temp_survived_sharks.poll();
				int x = shark.x;
				int y = shark.y;

				if(map[x][y]==null) {
					map[x][y] = shark;
					survived_sharks.offer(shark);
				}
				else m--;
			}

			fisher++;	// fisher moves
		}
		System.out.println(weight);
	}

	static int changeDirection(int num) {
		if(num==1) return 2;
		else if(num==2) return 1;
		else if(num==3) return 4;
		else return 3;
	}

}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 22%
* 시뮬레이션
* 낚시왕이 잡은 상어 크기의 합을 출력
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 낚시꾼 이동 전에 예외케이스인지 체크
* 상어가 아예 없는 경우(M==0)
#### 2. 반복문을 돌면서 계속 체크
* 종료조건
	1. 더 이상 상어가 없던가(M==0)
	2. 낚시꾼이 모든 열을 다 탐색했던가(fisher >= c)
##### 2-1. 상어잡기
##### 2-2. 상어이동
##### 2-3. 낚시꾼 이동
* 미리 낚시꾼의 위치를 반복문 시작 전에 한 칸 이동시킨 상태

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 반복문 전에 초기 예외케이스 체크
```java
if(m==0) flag = false;
```

#### 2. 초기 예외케이스가 아니면, 반복문 시작
* `Queue<Shark> survived_sharks` : **현재 살아있는 상어들을 저장하는 큐**
* `int stay_vertical` : **상/하로 이동하는 상어들이 제자리로 돌아올 때까지 걸리는 시간**
	* (행의 갯수 - 1) * 2
* `int stay_horizontal` : **좌/우로 이동하는 상어들이 제자리로 돌아올 때까지 걸리는 시간**
	* (열의 갯수 - 1) * 2
* `PriorityQueue<Shark> temp_survived_sharks` : **크기(size)순으로 상어들을 내림차순시키는 우선순위큐**
	* 상어들의 이동 후, 지도에 재배치 시킬 때 사용

```java
while(flag && fisher<c && m>=0) {
	// 상어 잡기
	for(int row=0;row<r;row++) {
		if(map[row][fisher]!=null) {	// 해당 칸에 상어가 있으면
			weight+=map[row][fisher].size;
			map[row][fisher]=null;	// 지도에서 상어 제거
			while(true) {	// 큐에서 상어 제거
				Shark shark = survived_sharks.poll();
				if(shark.x == row && shark.y == fisher) {
					break;
				}
				else survived_sharks.offer(shark);
			}
			m--;
			break; // 찾았으니까 탐색 강제 종료
		}
	}

	// 상어 이동
	int size = survived_sharks.size();
	for(int i=0;i<size;i++) {
		Shark shark = survived_sharks.poll();	// 큐에 있는 상어들을 모두 poll
		int x = shark.x;
		int y = shark.y;
		int direction = shark.direction;
		int speed;
		// 상,하로 움직이는 상어의 speed 갱신
		if(direction<=2) speed = shark.speed%stay_vertical;
		// 좌,우로 움직이는 상어의 speed 갱신
		else speed = shark.speed%stay_horizontal;

		int shark_size = shark.size;
		for(int move=0;move<shark.speed;move++) {
			int dx = x+move_x[direction];
			int dy = y+move_y[direction];
			if(dx<0 || dx>=r || dy<0 || dy>=c) { // 벽에 부딪히면
				direction = changeDirection(direction);	// 방향을 바꾸고 한번 더 이동
				x = x+move_x[direction];
				y = y+move_y[direction];
			}else {
				x = dx;
				y = dy;
			}
		}
		// 크기를 내림차순으로 정렬시키는 우선순위큐에 추가
		temp_survived_sharks.offer(new Shark(x,y,speed,direction,shark_size));
	}

	// 현재 지도를 모두 null로 초기화
	for(int row=0;row<r;row++) Arrays.fill(map[row], null);

	// 우선수위큐에서 상어를 하나씩 poll()하면서 지도에 배치
	int q_size = temp_survived_sharks.size();
	for(int i=0;i<q_size;i++) {
		Shark shark = temp_survived_sharks.poll();
		int x = shark.x;
		int y = shark.y;
		if(map[x][y]==null) {	// 지도가 아직 비어있으면
			map[x][y] = shark;	// 배치
			survived_sharks.offer(shark);	// 큐에 상어 추가
		}
		// 지도에 이미 상어가 있으면, 어차피 현재 상어는 기존 상어보다 크기가 작으므로
		// 큐에 상어를 추가하지는 않고, 상어의 마릿수만 감소
		else m--;
	}

	fisher++;	// 낚시꾼 이동
}
```

<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 시간초과
* 상어 이동시, 정직하게 한칸씩 이동했는데 이렇게되면 **시간복잡도가 10^9까지 상승**..
	* 상어는 최대 10,000 마리
	* 상어의 최대 스피드는 1,000
	* 낚시꾼의 최대 이동거리 100
* 따라서, 상어의 스피드를 제자리로 돌아오는 시간으로 나눈 **나머지로 갱신** 해서 이동시킴
	* 만약 스피드가 **997** 이고, 제자리로 돌아오는 시간이 **10** 이면, 남은 **7** 번만 이동시키면 됨

```java
if(direction<=2) speed = shark.speed%stay_vertical;
else speed = shark.speed%stay_horizontal;
```

#### 2. 상어를 잡을 때, Break를 빼먹음
* 가장 위에 있는 상어만 잡을 수 있기 때문에, 한 마리를 잡았으면 정지
```java
//
for(int row=0;row<r;row++) {
	if(map[row][fisher]!=null) {
		...
	}
}

// 정답
for(int row=0;row<r;row++) {
	if(map[row][fisher]!=null) {
		...
		break;
	}
}
```

#### 3. 벽에 부딪혔을 때, 상어 이동경로 실수
* 벽에 부딪히면, **다음(dx, dy) 이동경로** 에서 한번 이동하는게 아니라 **현재(x, y) 이동경로** 에서 이동하는 것임

```java
// 실수
for(int move=0;move<shark.speed;move++) {
	int dx = x+move_x[direction];
	int dy = y+move_y[direction];
	if(dx<0 || dx>=r || dy<0 || dy>=c) {
		direction = changeDirection(direction);
		x = dx+move_x[direction];
		y = dy+move_y[direction];
	}
	...
}

// 정답
for(int move=0;move<shark.speed;move++) {
	int dx = x+move_x[direction];
	int dy = y+move_y[direction];
	if(dx<0 || dx>=r || dy<0 || dy>=c) {
		direction = changeDirection(direction);
		x = x+move_x[direction];
		y = y+move_y[direction];
	}
	...
}
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
#### 1. 시간복잡도 고려
* 문제를 풀기 전에, 최악의 경우 때 시간복잡도를 고려해봐야됨...

<br>
<br>
<br>
