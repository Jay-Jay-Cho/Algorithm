# [게리맨더링 2](https://www.acmicpc.net/problem/17779)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17779_Gerrymandering2 {

	static int N;
	static int[][] map;
	static int[][] copy_map;
	static int answer = Integer.MAX_VALUE;
	static int d1;
	static int d2;

	static class Pair{
		int d1;
		int d2;
		Pair(int d1, int d2){
			this.d1 = d1;
			this.d2 = d2;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// mistake
		ArrayList<Pair> list = new ArrayList<Pair>();
		for(int i=1;i<=N-1;i++) {
			for(int j=1;j<=N-1;j++) {
				if(i+j<=N-1) list.add(new Pair(i,j));
			}
		}

		// find x,y,d1,d2
		for(int x=0;x<N;x++) {
			for(int y=0;y<N;y++) {
				for(int list_idx=0;list_idx<list.size();list_idx++) {
					Pair pair = list.get(list_idx);
					d1 = pair.d1;
					d2 = pair.d2;

					if(0<=x && x<x+d1+d2 && x+d1+d2<=N-1) {
						if(0<=y-d1 && y-d1<y && y<y+d2 && y+d2<=N-1) {
							int[] count_region = new int[6];

							copy_map = new int[N][N];
							int[] x_arr = {x,x,x+d1,x+d2};
							int[] y_arr = {y,y,y-d1,y+d2};
							int[] move = {d1,d2,d2,d1};
							for(int i=0;i<4;i++) {
								int cnt = 0;
								int temp_x = x_arr[i];
								int temp_y = y_arr[i];
								while(cnt<=move[i]) { // mistake
									if(i==0 || i==3) {
										int target_x = temp_x+cnt;
										int target_y = temp_y-cnt;
										if(copy_map[target_x][target_y]!=5) { // mistake
											copy_map[temp_x+cnt][temp_y-cnt] = 5;
											count_region[5] += map[temp_x+cnt][temp_y-cnt];
										}
									}
									else{
										int target_x = temp_x+cnt;
										int target_y = temp_y+cnt;
										if(copy_map[target_x][target_y]!=5) {
											copy_map[temp_x+cnt][temp_y+cnt] = 5;
											count_region[5] += map[temp_x+cnt][temp_y+cnt];
										}
									}
									cnt++;
								}
							}
							for(int i=0;i<N;i++) {
								Queue<Integer> q = new LinkedList<Integer>();
								for(int j=0;j<N;j++) {
									if(copy_map[i][j] == 5) q.offer(j);
								}
								if(q.size()<=1) continue;
								else {
									int from = q.poll();
									int to = q.poll();
									for(int idx=from+1;idx<to;idx++) {
										copy_map[i][idx] = 5;
										count_region[5] += map[i][idx];
									}
								}
							}

							// fill 1~4
							for(int i=0;i<N;i++) {
								for(int j=0;j<N;j++) {
									if(copy_map[i][j]==5) continue;
									else {
										int region = getRegion(i,j,x,y,d1,d2);
										copy_map[i][j] = region;
										count_region[region] += map[i][j];
									}
								}
							}
							// calculate sum
							int max = Integer.MIN_VALUE;
							int min = Integer.MAX_VALUE;
							for(int i=1;i<=5;i++) {
								max = Math.max(max, count_region[i]);
								min = Math.min(min, count_region[i]);
							}

							answer = Math.min(answer, Math.abs(max-min));
						}
					}
				}
			}
		}
		System.out.println(answer);
	}

	static int getRegion(int r, int c, int x, int y, int d1, int d2) {
		if(0<=r && r<x+d1 && 0<=c && c<=y) return 1;
		else if(0<=r && r<=x+d2 && y<c && c<=N+1) return 2;
		else if(x+d1<=r && r<=N+1 && 0<=c && c<y-d1+d2) return 3;
		else return 4;
	}

}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 52%
* 시뮬레이션
* 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. x,y,d1,d2를 정한다.
#### 2. 경계선들을 찾아서 5로 바꾸고, 그 내부도 5로 바꾼다.
#### 3. 1~4 지역구들을 채워넣는다.
#### 4. 각 지역구 별 합과, (최댓값-최솟값)으로 answer와 비교하여 갱신한다.

<br><br>

## &#10095;&#10095;&#10095; 풀이
#### 1. x,y를 찾기에 앞서 d1과 d2 후보군들을 찾아서 list에 넣는다.
```java
ArrayList<Pair> list = new ArrayList<Pair>();
for(int i=1;i<=N-1;i++) {
	for(int j=1;j<=N-1;j++) {
		if(i+j<=N-1) list.add(new Pair(i,j));
	}
}
```

#### 2. 전체 배열을 순회하며, 조건에 맞는 x,y,d1,d2를 발견하면 계속 진행한다.

#### 3. 경계선들을 찾아 5로 바꾸고, 해당 인원만큼 지역구 배열 ++
```java
copy_map = new int[N][N];
int[] x_arr = {x,x,x+d1,x+d2};
int[] y_arr = {y,y,y-d1,y+d2};
int[] move = {d1,d2,d2,d1};
for(int i=0;i<4;i++) {
	int cnt = 0;
	int temp_x = x_arr[i];
	int temp_y = y_arr[i];

	// 0부터 경계선 길이(d1,d2)만큼 + 또는 -
	while(cnt<=move[i]) {
		if(i==0 || i==3) {
			int target_x = temp_x+cnt;
			int target_y = temp_y-cnt;
			// 지역구가 선정되지 않았을 때만 들어가서 5로 바꿔준다.
			// 왜냐하면 인원이 중복으로 더해지기 때문.
			if(copy_map[target_x][target_y]!=5) {
				copy_map[temp_x+cnt][temp_y-cnt] = 5;
				count_region[5] += map[temp_x+cnt][temp_y-cnt];
			}
		}
		else{
			int target_x = temp_x+cnt;
			int target_y = temp_y+cnt;
			if(copy_map[target_x][target_y]!=5) {
				copy_map[temp_x+cnt][temp_y+cnt] = 5;
				count_region[5] += map[temp_x+cnt][temp_y+cnt];
			}
		}
		cnt++;
	}
}
```

#### 4. 경계선 내부에 있는 값들을 5로 바꿔준다.
```java
for(int i=0;i<N;i++) {
	Queue<Integer> q = new LinkedList<Integer>();
	for(int j=0;j<N;j++) {
		if(copy_map[i][j] == 5) q.offer(j);
	}
	if(q.size()<=1) continue;
	else {
		int from = q.poll();
		int to = q.poll();
		for(int idx=from+1;idx<to;idx++) {
			copy_map[i][idx] = 5;
			count_region[5] += map[i][idx];
		}
	}
}
```

#### 5. 1~4번 지역구를 각 조건에 맞게 넣는다.
```java
for(int i=0;i<N;i++) {
	for(int j=0;j<N;j++) {
		if(copy_map[i][j]==5) continue;
		else {
			int region = getRegion(i,j,x,y,d1,d2);
			copy_map[i][j] = region;
			count_region[region] += map[i][j];
		}
	}
}
```

#### 6. 지역구의 최대/최소값을 구해서 answer를 갱신
```java
int max = Integer.MIN_VALUE;
int min = Integer.MAX_VALUE;
for(int i=1;i<=5;i++) {
	max = Math.max(max, count_region[i]);
	min = Math.min(min, count_region[i]);
}

answer = Math.min(answer, Math.abs(max-min));
```
<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
#### 1. 처음에 경계선 길이(d1,d2)값의 경우를 고려안했음.
```java
// 실수
int d1 = 1;
int d2 = 1;

// 정답
ArrayList<Pair> list = new ArrayList<Pair>();
for(int i=1;i<=N-1;i++) {
	for(int j=1;j<=N-1;j++) {
		if(i+j<=N-1) list.add(new Pair(i,j));
	}
}
```
#### 2. 경계선 부분을 5로 채울 때, while 조건을 잘못줘서 마지막 부분이 채워지지 않음.
* 등호(`=`)를 빼먹었음...
```java
int cnt = 0;
int temp_x = x_arr[i];
int temp_y = y_arr[i];

// 실수
while(cnt<move[i]) { ... }

// 정답
while(cnt<=move[i]) { ... }
```

#### 3. 경계선 부분의 값을 지역구에 추가할 때, 인원수가 중복으로 더해짐.
* 이미 5로 채워진 부분은 건너뛰도록 수정함.
```java
// 실수
copy_map[temp_x+cnt][temp_y-cnt] = 5;
count_region[5] += map[temp_x+cnt][temp_y-cnt];

// 정답
int target_x = temp_x+cnt;
int target_y = temp_y-cnt;
if(copy_map[target_x][target_y]!=5) { // 5로 채워지지 않은 경우만, 추가
	copy_map[temp_x+cnt][temp_y-cnt] = 5;
	count_region[5] += map[temp_x+cnt][temp_y-cnt];
}
```


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점

<br>
<br>
<br>
