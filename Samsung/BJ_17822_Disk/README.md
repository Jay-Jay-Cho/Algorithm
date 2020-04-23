# [원판 돌리기](https://www.acmicpc.net/problem/17822)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17822_Disk {

	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, T;
	static int x,d,k;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first_line = br.readLine().split(" ");
		N = Integer.parseInt(first_line[0]);
		M = Integer.parseInt(first_line[1]);
		T = Integer.parseInt(first_line[2]);
		map = new int[N+1][M];

		for(int row=1;row<=N;row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		for(int t=0;t<T;t++) {
			boolean change = false;
			Queue<Pos> q = new LinkedList<Pos>();
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st2.nextToken());	// which disk
			d = Integer.parseInt(st2.nextToken());	// 0 = clock, 1 = reverse_clock
			k = Integer.parseInt(st2.nextToken());	// k = turn count

			// rotate
			for(int i=1;i<=N;i++) {
				if(i%x == 0) {
					int[] copy_arr = map[i].clone();
					for(int idx=0;idx<M;idx++) {
						int new_idx;
						if(d==1) new_idx = (idx+k) % M;	// reverse_clock
						else new_idx = (M-k+idx) % M;	// clock
						map[i][idx] = copy_arr[new_idx];
					}
				}
			}

			// remove
			// inner
			for(int i=1;i<=N;i++) {
				for(int j=0;j<M-1;j++) {
					if(map[i][j] != 9999 && map[i][j+1] != 9999 && map[i][j] == map[i][j+1]) {
						change = true;
						q.offer(new Pos(i,j));
						q.offer(new Pos(i,j+1));
					}
				}
				if(map[i][0] != 9999 && map[i][M-1] != 9999 && map[i][0] == map[i][M-1]) {
					change = true;
					q.offer(new Pos(i,0));
					q.offer(new Pos(i,M-1));
				}
			}

			// outer
			for(int j=0;j<M;j++) {
				for(int i=1;i<=N-1;i++) {
					if(map[i][j] != 9999 && map[i+1][j] != 9999 && map[i][j]==map[i+1][j]) {
						change = true;
						q.offer(new Pos(i,j));
						q.offer(new Pos(i+1,j));
					}
				}
			}

			if(change) {
				while(!q.isEmpty()){
					Pos pos = q.poll();
					map[pos.x][pos.y] = 9999;
				}
			}else {
				int sum = 0;
				int cnt = 0;
				for(int i=1;i<=N;i++) {
					for(int j=0;j<M;j++) {
						if(map[i][j]!=9999) {
							sum+=map[i][j];
							cnt++;
						}
					}
				}
				if(cnt == 0) continue; // mistake

				double avg = (double)sum/cnt;	// mistake
				for(int i=1;i<=N;i++) {
					for(int j=0;j<M;j++) {
						if(map[i][j]!=9999) {
							if(map[i][j]==avg) continue;
							else if(map[i][j] > avg){
								map[i][j] = map[i][j]-1;
							}else {
								map[i][j] = map[i][j]+1;
							}
						}
					}
				}
			}

		}

		int sum = 0;
		for(int i=1;i<=N;i++) {
			for(int val:map[i]) {
				if(val!=9999) sum += val;
			}
		}
		System.out.println(sum);
	}

}

```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 30%
* 시뮬레이션
* 원판을 T번 회전시킨 후 원판에 적힌 수의 합
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1. 조건에 부합하는 원판을 회전
##### 2-1. 원판 `내부`에서 인접하는 수가 동일한 경우, 해당 쌍을 큐에 넣음.
* 바로 제거 x, 일단 큐에 넣은 후 마지막에 한꺼번에 제거
##### 2-2. 원판 `외부`에서 인접하는 수가 동일한 경우, 해당 쌍을 큐에 넣음.
##### 3. 삭제해야할 인접한 수들이 있으면, 삭제 진행
* 변수값을 9999로 변경
##### 4. 삭제할 인접한 수들이 없으면, 이후 처리 진행
* 원판 전체의 평균을 구하고,
* 평균보다 작으면 +1, 평균보다 크면 -1
##### 5. 모든 원판의 합 출력

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 조건에 부합하는 원판 회전
```java
for(int i=1;i<=N;i++) {
	if(i%x == 0) {	// i번째 원판이 x의 배수이면
		int[] copy_arr = map[i].clone();
		for(int idx=0;idx<M;idx++) {
			int new_idx;
			if(d==1) new_idx = (idx+k) % M;	// 반시계방향
			else new_idx = (M-k+idx) % M;	// 시계방향
			map[i][idx] = copy_arr[new_idx];
		}
	}
}
```

##### 2. 제거해야할 인접한 수들이 있으면, 큐에 넣는다.
```java
// 원판 내부
for(int i=1;i<=N;i++) {
	for(int j=0;j<M-1;j++) {
		if(map[i][j] != 9999 && map[i][j+1] != 9999 && map[i][j] == map[i][j+1]) {
			change = true;
			q.offer(new Pos(i,j));
			q.offer(new Pos(i,j+1));
		}
	}
	if(map[i][0] != 9999 && map[i][M-1] != 9999 && map[i][0] == map[i][M-1]) {
		change = true;
		q.offer(new Pos(i,0));
		q.offer(new Pos(i,M-1));
	}
}

// 원판 외부
for(int j=0;j<M;j++) {
	for(int i=1;i<=N-1;i++) {
		if(map[i][j] != 9999 && map[i+1][j] != 9999 && map[i][j]==map[i+1][j]) {
			change = true;
			q.offer(new Pos(i,j));
			q.offer(new Pos(i+1,j));
		}
	}
}
```

##### 3. 제거해야할 인접한 수들이 있으면(change==true), 제거
```java
if(change) {
	while(!q.isEmpty()){
		Pos pos = q.poll();
		map[pos.x][pos.y] = 9999;	// 제거 = 값을 9999로 갱신
	}
}
```

##### 4. 제거해야할 인접한 수들이 없으면(change==false), 값 갱신
```java
else {
	// 원판 전체 합 구하기
	int sum = 0;
	int cnt = 0;
	for(int i=1;i<=N;i++) {
		for(int j=0;j<M;j++) {
			if(map[i][j]!=9999) {
				sum+=map[i][j];
				cnt++;
			}
		}
	}
	if(cnt == 0) continue; // 모두 제거됐으면, skip
	double avg = (double)sum/cnt;	// 원판 전체 평균 구하기

	for(int i=1;i<=N;i++) {
		for(int j=0;j<M;j++) {
			if(map[i][j]!=9999) {
				if(map[i][j]==avg) continue;
				else if(map[i][j] > avg){	// 배열값이 평균보다 크면 +1
					map[i][j] = map[i][j]-1;
				}else { // 배열값이 평균보다 작으면 -1
					map[i][j] = map[i][j]+1;
				}
			}
		}
	}
}
```

##### 5. 모든 원판의 합 출력
```java
int sum = 0;
for(int i=1;i<=N;i++) {
	for(int val:map[i]) {
		if(val!=9999) sum += val;
	}
}
System.out.println(sum);
```
<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. 1차원배열 시계/반시계 회전
```java
int[] arr;
int size = arr.length;
int[] copy_arr = arr.clone();
for(int idx=0;idx<size;idx++) {
	int new_idx;
	if(d==1) new_idx = (idx+k) % size;	// 반시계방향
	else new_idx = (idx+(size-k)) % size;	// 시계방향
	arr[idx] = copy_arr[new_idx];
}
```
##### 2. 평균 계산하기
```java
int sum;
int n;
double avg = (double)sum/n;
```


<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 문제 해석 오류
* 평균을 원판마다 계산하는게 아니라, 전체 원판을 대상으로 계산하는 거였음.
##### 2. 평균을 구할 때, double이 아니라 int형으로 선언한 오류
```java
// 실수
int avg = sum/cnt;

// 정답
double avg = (double)sum/cnt;
```
##### 3. 시계방향, 반시계방향 인덱스 이동 혼동.
* **시계방향**
	* 인덱스들이 왼쪽에서 오른쪽으로 이동
	* 즉, 인덱스들의 값이 작아지는 것
	* 따라서, k만큼 감소 = (전체크기-k)만큼 증가
	* [0,1,2,3] --> [3,0,1,2]
	```java
	if(d==0) new_idx = (idx+(M-k)) % M;
	```
* **반시계방향**
	* 인덱스들이 오른쪽에서 왼쪽으로 이동
	* 즉, 인덱스 관점에서 본다면 값이 커지는 것
	* 따라서, k만큼 증가  
	* [0,1,2,3] --> [1,2,3,0]
	```java
	if(d==1) new_idx = (idx+k) % M;
	```
##### 4. 인접한 수들을 제거할 때, 제거할 수 있는 숫자의 갯수가 0이면 평균을 구할 때 `/ by zero`에러가 발생하므로 예외처리를 해줬어야 함.
```java
if(cnt == 0) continue;
```
##### 5. 평균과 비교하여 배열값을 갱신할 때, 분기처리를 제대로 해주지 않았음.
* 이 경우, 갱신이 한 번만 돼야하는데 연속으로 2번 갱신되는 경우가 발생.
```java
// 실수
if(map[i][j]!=9999) {
	if(map[i][j] > avg) map[i][j] = map[i][j]-1;
	if(map[i][j] < avg) map[i][j] = map[i][j]+1;
}

// 정답
if(map[i][j]!=9999) {
	if(map[i][j]==avg) continue;
	else if(map[i][j] > avg){
		map[i][j] = map[i][j]-1;
	}else {
		map[i][j] = map[i][j]+1;
	}
}

```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 1. 소숫점 계산 시, int 끼리의 계산이라도 double로 캐스팅을 소수점까지 출력됨.
```java
double avg = (double)sum/cnt;
```



<br>
<br>
<br>
