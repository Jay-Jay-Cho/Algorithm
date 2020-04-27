# [이차원 배열과 연산](https://www.acmicpc.net/problem/17140)
* **참고자료** (**반례**) : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17140_2DimensionalArray {

	static class Node{
		int num;
		int cnt;
		Node(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
	}

	static int r,c,k;
	static int[][] map;
	static int[][] result_map;
	static int time = 0;
	static boolean flag = true;
	static int row_cnt = 3;
	static int col_cnt = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		map = new int[3][3];
		for(int i=0;i<3;i++) {
			if(i==0) r=Integer.parseInt(st1.nextToken())-1;
			else if(i==1) c=Integer.parseInt(st1.nextToken())-1;
			else k=Integer.parseInt(st1.nextToken());
		}
		for(int i=0;i<3;i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
				if(r<=2 && c<=2 && map[r][c] == k) flag=false;	// exception
			}
		}

		while(flag && time<=100) {
			time++;

			// R operation
			if(row_cnt >= col_cnt) {
				if(time==1) operation(true,map);
				else operation(true,result_map);
			}

			// C operation
			else {
				//result_map = operation(false,map);
				operation(false,result_map);
			}

			if(r<=row_cnt-1 && c<=col_cnt-1 && result_map[r][c]==k) {
				flag = false; // exception
			}
		}

		// could not find
		if(flag == true) time=-1;

		System.out.println(time);
	}

	// should not count the zero(0)

	static void operation(boolean isR, int[][] map) {
		PriorityQueue<Node> priority_q = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				if(node1.cnt < node2.cnt) return -1;
				else if(node1.cnt == node2.cnt) {
					if(node1.num <= node2.num) return -1;
					else return 1;
				}else return 1;
			}

		});
		HashMap<Integer,Integer> hashmap;
		Queue<int[]> q;
		if(isR) {
			q = new LinkedList<int[]>();
			int R_cnt = row_cnt;
			int max_col_cnt = Integer.MIN_VALUE;
			for(int i=0;i<R_cnt;i++) {
				hashmap = new HashMap<Integer,Integer>();
				for(int num:map[i]) {
					if(num==0) continue;
					if(hashmap.containsKey(num)) {
						int previous_value = hashmap.get(num);
						hashmap.replace(num, previous_value+1);
					}else {
						hashmap.put(num, 1);
					}
				}
				Iterator<Integer> iter = hashmap.keySet().iterator();
				while(iter.hasNext()) {
					if(priority_q.size() > 50) {
						break;
					}
					int num = (int) iter.next();
					int cnt = hashmap.get(num);
					priority_q.offer(new Node(num,cnt));
				}
				max_col_cnt = Math.max(priority_q.size()*2, max_col_cnt);
				int new_arr_length = priority_q.size()*2;
				int[] new_arr = new int[new_arr_length];
				int idx = 0;
				while(!priority_q.isEmpty()) {
					Node node = priority_q.poll();
					new_arr[idx] = node.num;
					idx++;
					new_arr[idx] = node.cnt;
					idx++;
				}
				q.offer(new_arr);
				//max_col_cnt = Math.max(priority_q.size()*2, max_col_cnt); //location

			}
			col_cnt = max_col_cnt;
			result_map = new int[row_cnt][col_cnt];
			for(int row=0;row<row_cnt;row++) {
				int[] temp_arr = q.poll();
				for(int col=0;col<temp_arr.length;col++) {
					result_map[row][col] = temp_arr[col];
				}
				//result_map[row] = temp_arr.clone();
			}
		}else {
			q = new LinkedList<int[]>();
			int C_cnt = col_cnt;
			int max_row_cnt = Integer.MIN_VALUE;
			for(int i=0;i<C_cnt;i++) {
				hashmap = new HashMap<Integer,Integer>();

				for(int row=0;row<row_cnt;row++) {
					int num = map[row][i];
					if(num==0) continue;
					if(hashmap.containsKey(num)) {
						int previous_value = hashmap.get(num);
						hashmap.replace(num, previous_value+1);
					}else {
						hashmap.put(num, 1);
					}
				}

				Iterator<Integer> iter = hashmap.keySet().iterator();
				while(iter.hasNext()) {
					if(priority_q.size() > 50) {
						break;
					}
					int num = (int) iter.next();
					int cnt = hashmap.get(num);
					priority_q.offer(new Node(num,cnt));
				}

				max_row_cnt = Math.max(priority_q.size()*2, max_row_cnt);
				int new_arr_length = priority_q.size()*2;
				int[] new_arr = new int[new_arr_length];
				int idx = 0;
				while(!priority_q.isEmpty()) {
					Node node = priority_q.poll();
					new_arr[idx] = node.num;
					idx++;
					new_arr[idx] = node.cnt;
					idx++;
				}
				q.offer(new_arr);
				//max_row_cnt = Math.max(priority_q.size()*2, max_row_cnt);
			}
			row_cnt = max_row_cnt;
			result_map = new int[row_cnt][col_cnt];
			for(int col=0;col<col_cnt;col++) {
				int[] temp_arr = q.poll();
				for(int row=0;row<temp_arr.length;row++) {
					result_map[row][col] = temp_arr[row];
				}
			}
		}
		//return result_map;
	}

}

```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 43%
* 시뮬레이션
* A[r][c]에 들어있는 값이 k가 되기 위한 연산의 최소 시간을 출력. 이 값이 100을 넘어가는 경우에는 -1을 출력
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 연산 전에 처음 배열에서 A[r][c]==k 인지 && r과 c가 배열 범위 내인지 확인
#### 2. 찾지 못했으면 time=1 부터 time=100까지 R/C연산 시작
* 단, 처음은 무조건 R 연산이다..!!! (왜냐면 처음 배열은 항상 3x3 이니까)
#### 3. 연산이 끝나면, A[r][c]==k 인지 && r과 c가 배열 범위 내인지 확인
* 찾았으면 flag=false
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 연산 전에 처음 배열 체크
```java
if(r<=2 && c<=2 && map[r][c] == k) flag=false;
```

#### 2. 찾지 못했으면 time=1 부터 time=100까지 R/C연산 시작
```java
while(flag && time<=100) {
	time++;

	// R operation
	if(row_cnt >= col_cnt) {
		if(time==1) operation(true,map);	// 처음에는 초기 배열로
		else operation(true,result_map);	// 이후에는 갱신된 새로운 배열로
	}

	// C operation
	else operation(false,result_map);

	// 연산이 끝나면, A[r][c]==k 인지 && r과 c가 배열 범위 내인지 확인
	if(r<=row_cnt-1 && c<=col_cnt-1 && result_map[r][c]==k) {
		flag = false;
	}
}
```

#### 3. R/C 연산
* `PriorityQueue<Node> priority_q` : **배열을 제시된 정렬 기준으로 추출시킬 우선순위큐**
	* 횟수 오름차순
	* 횟수가 같다면, 숫자 오름차순
* `HashMap<Integer,Integer> hashmap` : **각 행/열의 숫자와 횟수를 세기위한 해시맵**
	* key = 숫자
	* value = 횟수
* `Queue<int[]> q` : **연산이 끝난 행/열을 저장시킬 큐**
	* 모든 행/열에 대한 연산이 끝나면 하나씩 꺼내면서 2차원 배열 갱신
* `R 연산` : row_cnt는 그대로, col_cnt가 바뀜
* `C 연산` : col_cnt는 그대로, row_cnt가 바뀜



```java
static void operation(boolean isR, int[][] map) {
	PriorityQueue<Node> priority_q = new PriorityQueue<Node>(new Comparator<Node>() {
		@Override
		public int compare(Node node1, Node node2) {
			if(node1.cnt < node2.cnt) return -1; // 횟수 오름차순
			else if(node1.cnt == node2.cnt) { // 횟수가 같으면,
				if(node1.num <= node2.num) return -1; // 숫자 오름차순
				else return 1;
			}else return 1;
		}
	});

	HashMap<Integer,Integer> hashmap;
	Queue<int[]> q;

	if(isR) {	// R 연산
		q = new LinkedList<int[]>();
		int R_cnt = row_cnt;
		int max_col_cnt = Integer.MIN_VALUE;

		// 각 행마다 실행
		for(int i=0;i<R_cnt;i++) {
			hashmap = new HashMap<Integer,Integer>();
			for(int num:map[i]) {
				if(num==0) continue;	// 숫자 0은 무시
				if(hashmap.containsKey(num)) {
					int previous_value = hashmap.get(num);
					hashmap.replace(num, previous_value+1);
				}else {
					hashmap.put(num, 1);
				}
			}
			Iterator<Integer> iter = hashmap.keySet().iterator();
			while(iter.hasNext()) {
				if(priority_q.size() > 50) {	// 사이즈가 100을 넘어가면
					break;	// 뒤에 오는 값들은 무의미
				}
				int num = (int) iter.next();
				int cnt = hashmap.get(num);
				priority_q.offer(new Node(num,cnt));
			}
			max_col_cnt = Math.max(priority_q.size()*2, max_col_cnt);
			int new_arr_length = priority_q.size()*2;
			int[] new_arr = new int[new_arr_length];
			int idx = 0;
			while(!priority_q.isEmpty()) {
				Node node = priority_q.poll();
				new_arr[idx] = node.num;
				idx++;
				new_arr[idx] = node.cnt;
				idx++;
			}
			q.offer(new_arr);
		}

		// 모든 행에 대한 연산이 끝났으면, 새로운 2차원 배열을 생성후 갱신
		col_cnt = max_col_cnt;
		result_map = new int[row_cnt][col_cnt];
		for(int row=0;row<row_cnt;row++) {
			int[] temp_arr = q.poll();
			for(int col=0;col<temp_arr.length;col++) {
				result_map[row][col] = temp_arr[col];
			}
		}
	}else {	// C 연산
		q = new LinkedList<int[]>();
		int C_cnt = col_cnt;
		int max_row_cnt = Integer.MIN_VALUE;
		for(int i=0;i<C_cnt;i++) {
			hashmap = new HashMap<Integer,Integer>();

			for(int row=0;row<row_cnt;row++) {
				int num = map[row][i];
				if(num==0) continue;
				if(hashmap.containsKey(num)) {
					int previous_value = hashmap.get(num);
					hashmap.replace(num, previous_value+1);
				}else {
					hashmap.put(num, 1);
				}
			}

			Iterator<Integer> iter = hashmap.keySet().iterator();
			while(iter.hasNext()) {
				if(priority_q.size() > 50) {
					break;
				}
				int num = (int) iter.next();
				int cnt = hashmap.get(num);
				priority_q.offer(new Node(num,cnt));
			}

			max_row_cnt = Math.max(priority_q.size()*2, max_row_cnt);
			int new_arr_length = priority_q.size()*2;
			int[] new_arr = new int[new_arr_length];
			int idx = 0;
			while(!priority_q.isEmpty()) {
				Node node = priority_q.poll();
				new_arr[idx] = node.num;
				idx++;
				new_arr[idx] = node.cnt;
				idx++;
			}
			q.offer(new_arr);
		}
		row_cnt = max_row_cnt;
		result_map = new int[row_cnt][col_cnt];
		for(int col=0;col<col_cnt;col++) {
			int[] temp_arr = q.poll();
			for(int row=0;row<temp_arr.length;row++) {
				result_map[row][col] = temp_arr[row];
			}
		}
	}
}
```

#### 4. time이 100을 넘었음에도 flag가 false면, 즉 A[r][c]!=k면 -1 출력
```java
if(flag == true) time=-1;
System.out.println(time);
```
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 배열 값이 0인 숫자도 횟수를 셈
* 아래와 같은 경우에서 에러가 발생함
	* 이전 배열
		> 2 1 1 2 0 0
			1 1 2 1 3 1
			3 3 0 0 0 0

	* **실수** : 0 포함
		> 1 3 0 0 3 1
			1 1 1 1 1 1
			2 1 1 1 0 0
			1 2 1 1 2 2
			3 0 2 2 0 0
			1 0 1 1 0 0

	* **정답** : 0 미포함
		> 1 3 1 1 3 1
			1 1 1 1 1 1
			2 1 2 2 0 0
			1 2 1 1 0 0
			3 0 0 0 0 0
			1 0 0 0 0 0

* 따라서, 숫자/횟수를 해시맵에 넣는 부분에서 **숫자가 0인 값은 무시**
```java
if(num==0) continue;
```

#### 2. (`반례`) r,c값이 배열 사이즈보다 큰 경우를 생각안함.
* 아래와 같은 경우에서 에러가 발생함
	> 9 9 9
		1 1 2
		3 3 1
		1 3 1

* 따라서, r과 c가 **배열 범위 내인지 확인하는 과정** 추가
```java
if(map[r][c] == k) flag=false; // 실수
if(r<=row_cnt-1 && c<=col_cnt && map[r][c] == k) flag=false;	// 정답
```

#### 3. 연산에서 col_cnt / row_cnt 갱신 && 2차원 배열 선언을 매 행/열마다 수행함.
* before
	1. 매 행/열을 돌면서 cnt 갱신
	2. 이렇게 되면, max_cnt는 갱신되지만 마지막 2차원 배열선언 후 값을 입력하면 마지막 행/열 값만 들어감 (**나머지 배열은 모두 초기화값인 0이 들어감**)
* after
	1. 매 행/열을 돌면서 cnt 갱신 & 해당 1차원 배열을 q에 집어넣음
	2. 모든 갱신이 끝난 후, max_cnt를 기준으로 2차원 배열 생성
	3. 큐에 있는 배열 값들을 2차원 배열에 집어넣음


```java
// 실수
for(int i=0;i<R_cnt;i++) {
	//1. 정렬
	//2. max_cnt 갱신
	//3. 2차원 배열 선언 후 배열 갱신
}

// 정답
for(int i=0;i<R_cnt;i++) {
	//1. 정렬
	//2. max_cnt 갱신
	//3. 큐에 정렬시킨 배열을 넣음
}
// 4. max_cnt를 기준으로 2차원 배열 선언
// 5. 큐에서 하나씩 뽑으면서 2차원 배열 갱신

```

#### 4. 연산 함수에서 배열을 반환
* 초기화 에러가 발생해서, 반환값을 **배열** 에서 **void** 로 바꿈
```java
static int[][] operation(boolean isR, int[][] map) { return map; } // 실수
static void operation(boolean isR, int[][] map) { } // 정답
```

<br><br>


## &#10095;&#10095;&#10095; 꿀팁
#### 1. 우선수위큐 정렬 조건을 커스터마이징
* 생성자 안에서 `new Comparator<Object>` 사용
```java
PriorityQueue<Node> priority_q = new PriorityQueue<Node>(new Comparator<Node>() {
	@Override
	public int compare(Node node1, Node node2) {
		...
	}
});
```
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
#### 1. 반례 조심...
* 2차원 배열의 좌표값을 기준으로 체크할 때
* 배열의 범위

<br>
<br>
<br>
