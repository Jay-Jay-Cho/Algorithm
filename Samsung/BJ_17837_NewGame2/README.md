# [새로운 게임 2](https://www.acmicpc.net/problem/17837)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17837_NewGame2 {

	static class Horse{
		int num;
		int x;
		int y;
		int direction;
		Horse(int num, int x, int y, int direction){
			this.num = num;
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

	}

	static int N, K;
	static int[][] map;
	static Stack<Integer>[][] real_map;
	static int answer = -1;
	static int[] move_x = {0,0,-1,1};
	static int[] move_y = {1,-1,0,0};
	static HashMap<Integer,Horse> location;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st1.nextToken());
		map = new int[N+1][N+1];
		real_map = new Stack[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				real_map[i][j] = new Stack<Integer>();
			}
		}

		for(int i=0;i<=N;i++) {
			for(int j=0;j<=N;j++) {
				if(i==0 || j==0) map[i][j]=-1;
			}
		}

		K = Integer.parseInt(st1.nextToken());
		for(int i=1;i<=N;i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}

		location = new HashMap<Integer,Horse>();
		for(int i=0;i<K;i++) {
			String[] pos_info = br.readLine().split(" ");
			int x = Integer.parseInt(pos_info[0]);
			int y = Integer.parseInt(pos_info[1]);
			int direction = Integer.parseInt(pos_info[2]);
			location.put(i+1, new Horse(i+1,x,y,direction));
			real_map[x][y].push(i+1);
		}


		int cnt=1;
		boolean flag = true;
		while(cnt<1000 && flag) {
			for(int turn=1;turn<=K;turn++) {
				Horse horse = location.get(turn);
				int x = horse.x;
				int y = horse.y;
				int direction = horse.direction;


				// extract
				Queue<Integer> q = new LinkedList<Integer>();
				if(real_map[x][y].size()!=1) {
					while(real_map[x][y].peek()!=horse.num) {	//everyday mistake!!!!!!
						q.offer(real_map[x][y].pop());
					}
					q.offer(real_map[x][y].pop());
					int size = q.size();
					Stack<Integer> stack = new Stack<Integer>();
					for(int i=0;i<size;i++) {
						stack.push(q.poll());
					}
					for(int i=0;i<size;i++) {
						q.offer(stack.pop());
					}

				}else {
					int num = real_map[x][y].pop();
					q.offer(num);
				}

				// move
				int dx = x+move_x[direction-1];
				int dy = y+move_y[direction-1];
				if(dx>N || dx<1 || dy>N || dy<1 || map[dx][dy]==2 ) { //blue
					// change direction
					int new_direction = changeDirection(direction);
					int new_dx = x+move_x[new_direction-1];
					int new_dy = y+move_y[new_direction-1];

					if(new_dx>N || new_dx<1 || new_dy>N || new_dy<1 || map[new_dx][new_dy]==2) { //blue
						int size = q.size();
						for(int i=0;i<size;i++) {
							int input = q.poll();
							if(i==0) {
								real_map[x][y].push(input);
								location.replace(input, new Horse(input,x,y,new_direction));
							}else {
								real_map[x][y].push(input);
								Horse orgin_horse = location.get(input);
								location.replace(input, new Horse(input,x,y,orgin_horse.direction));
							}
						}
						location.replace(turn, new Horse(turn,x,y,new_direction));
					}else if(map[new_dx][new_dy]==0) { //white
						moveWhite(q,new_dx,new_dy,new_direction);
					}
					else { //red
						moveRed(q,new_dx,new_dy,new_direction);
					}
				}
				else if(map[dx][dy]==0) { //white
					moveWhite(q,dx,dy);
				}
				//red
				else {
					moveRed(q,dx,dy);
				}

				for(int i=1;i<=N;i++) {
					for(int j=1;j<=N;j++) {
						if(real_map[i][j].size()>=4) {
							answer = cnt;
							flag=false;
						}
					}
				}
			}
			cnt++;
		}


		System.out.println(answer);
	}

	static int changeDirection(int x) {
		if(x==1) return 2;
		else if(x==2) return 1;
		else if(x==3) return 4;
		else return 3;
	}

	static void moveRed(Queue<Integer> q, int dx, int dy) {
		Stack<Integer> stack = new Stack<Integer>();
		int size = q.size();
		for(int i=0;i<size;i++) {
			stack.push(q.poll());
		}
		for(int i=0;i<size;i++) {
			int input = stack.pop();
			real_map[dx][dy].push(input);
			Horse orgin_horse = location.get(input);
			location.replace(input, new Horse(input,dx,dy,orgin_horse.direction));			
		}
	}

	// blue -> red
	static void moveRed(Queue<Integer> q, int dx, int dy,int new_direction) {
		int size = q.size();
		int change_num = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<size;i++) {
			if(i==0) {
				change_num = q.poll();
				stack.push(change_num);
			}else {
				stack.push(q.poll());
			}

		}
		for(int i=0;i<size;i++) {
			int input = stack.pop();
			real_map[dx][dy].push(input);
			if(input==change_num) {
				location.replace(input, new Horse(input,dx,dy,new_direction));
			}else {
				Horse h = location.get(input);
				location.replace(input, new Horse(input,dx,dy,h.direction));
			}

		}
	}

	static void moveWhite(Queue<Integer> q, int dx, int dy) {
		int size = q.size();
		for(int i=0;i<size;i++) {
			int input = q.poll();
			real_map[dx][dy].push(input);
			Horse orgin_horse = location.get(input);
			location.replace(input, new Horse(input,dx,dy,orgin_horse.direction));
		}
	}

	// blue -> white
	static void moveWhite(Queue<Integer> q, int dx, int dy, int new_direction) {
		int size = q.size();
		for(int i=0;i<size;i++) {
			int input = q.poll();
			real_map[dx][dy].push(input);
			if(i==0) {
				location.replace(input, new Horse(input,dx,dy,new_direction));
			}else {
				Horse h = location.get(input);
				location.replace(input, new Horse(input,dx,dy,h.direction));
			}
		}
	}


}

```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 43%
* 시뮬레이션
* 체스판의 크기와 말의 위치, 이동 방향이 모두 주어졌을 때, 게임이 종료되는 턴의 번호
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 1~K번 말까지 순서대로 선택
* 말들을 저장하는 `HashMap<Integer,Horse> location`에서 순서대로 추출
#### 2. 해당 말이 위치한 지점에서, 해당 말~중첩된 말까지 추출해서 큐에 담는다.
* 말들의 위치는 `Stack[][] real_map`에 저장
* 해당 말이 선택될 때까지 stack에서 pop한 값을 큐에 담는다.
* 순서를 유지하기 위해, 큐에 있는 값들을 거꾸로(새로운 stack활용)
#### 3. 현재 위치(x,y) /  다음 위치(dx,dy)라 할 때 조건에 맞게 이동
##### 3-1. 다음 위치가 범위에서 벗어나거나, `blue`일 때
* 현재 위치의 방향을 역전(**direction -> new_direction**)
* 재설정한 방향의 다음 위치(new_dx,new_dy)를 기준으로 다시 조건에 맞게 분기
	* 다음 위치가 범위에서 벗어나거나, `blue`일 때
		* 위치는 그대로 = **(x, y)**
		*	큐의 head부분의 방향을 **new_direction** 으로 변경하고 다시 **real_map** 에 push
		* 큐의 나머지 부분들은 그대로 **real_map** 에 다시 push
		* **location** 업데이트
	* 다음 위치가 `white`일 때
		* 위치는 변경 = **(new_dx, new_dy)**
		*	큐의 head부분의 방향을 **new_direction** 으로 변경하고 다시 **real_map** 에 push
		* 큐의 나머지 부분들은 그대로 **real_map** 에 다시 push
		* **location** 업데이트
	* 다음 위치가 `red`일 때
		* 위치는 변경 = **(new_dx, new_dy)**
		* 색깔이 `red`이므로 큐를 다시 stack에 담음(순서 거꾸로)
		*	큐의 head부분의 방향을 **new_direction** 으로 변경하고 다시 **real_map** 에 push
		* 큐의 나머지 부분들은 그대로 **real_map** 에 다시 push
		* **location** 업데이트
##### 3-2. 다음 위치가 `white`일 때
* 이동하고
* **real_map** 에 push
* **location** 업데이트
##### 3-3. 다음 위치가 `red`일 때
* 이동하고
* 순서를 바꾼 뒤, **real_map** 에 push
* **location** 업데이트
#### 4. 전체 `real_map`을 탐색하면서 중첩된 말의 갯수가 4개 이상인 부분이 있는지 체크
* 있으면, break



<br><br>

## &#10095;&#10095;&#10095; 풀이
<br><br>

## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 해당 위치에서 특정 말부터 중첩된 말들까지 추출하는 부분에서 오류 발생
* **real_map** 의 타입을 큐에서 스택으로 바꿔서 해결.

#### 2. 행 혹은 열이 0인 부분을 초기화 안해줬음
```java
// 해결책
for(int i=0;i<=N;i++) {
	for(int j=0;j<=N;j++) {
		if(i==0 || j==0) map[i][j]=-1;
	}
}
```

#### 3. `Blue` 조건에서, 범위초과 조건을 뒤에 써서 `indexOutofBoundException`발생
```java
// 실수
if(map[dx][dy]==2 || dx>N || dx<1 || dy>N || dy<1 ){ ... }

// 정답
if(dx>N || dx<1 || dy>N || dy<1 || map[dx][dy]==2 ){ ... }
```

#### 4. `Blue`에서 다시 다음 위치로 갈 때, 큐의 `head`부분은 direction을 변경해줬어야 함.
```java
// 실수
location.replace(input, new Horse(input,dx,dy,h.direction));

// 정답
if(i==0) {
	location.replace(input, new Horse(input,dx,dy,new_direction));
}else {
	Horse h = location.get(input);
	location.replace(input, new Horse(input,dx,dy,h.direction));
}
```
#### 5. 반복문 탈출조건을 cnt가 끝날 때 체크를 함.
* 원래는 매 turn이 끝날 때마다 체크를 했어야 함.

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점





<br>
<br>
<br>
