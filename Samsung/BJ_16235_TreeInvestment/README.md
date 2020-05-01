# [나무 재테크](https://www.acmicpc.net/problem/16235)
* **참고자료** : https://www.acmicpc.net/source/19268764

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_16235_TreeInvestment {

	static class Tree{
		int x;
		int y;
		int age;
		Tree(int x, int y, int age){
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}

	static int n,m,k;
	static int[][] current_soil;
	static int[][] add_soil;
	static List<Integer>[][] tree_map;
	static int tree_cnt = 0;
	static Queue<Tree> dead_tree;
	static int[] move_x = {-1,-1,0,1,1,1,0,-1};
	static int[] move_y = {0,1,1,1,0,-1,-1,-1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st1.nextToken());
		m = Integer.parseInt(st1.nextToken());
		k = Integer.parseInt(st1.nextToken());
		current_soil = new int[n][n];
		add_soil = new int[n][n];
		tree_map = new ArrayList[n][n];
		for(int i=0;i<n;i++) {
			Arrays.fill(current_soil[i], 5);
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				add_soil[i][j] = Integer.parseInt(st2.nextToken()); // null pointer
				tree_map[i][j] = new ArrayList<Integer>();
			}
		}
		for(int i=0;i<m;i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st3.nextToken())-1;
			int y = Integer.parseInt(st3.nextToken())-1;
			int age = Integer.parseInt(st3.nextToken());
			if(x<0 || x>=n || y<0 || y>=n) continue;
			tree_map[x][y].add(age);
			tree_cnt++;
		}

		int year = 0;
		while(year<k) { // mistake year<=k
			// spring
			spring();
			if(tree_cnt == 0) break;

			// summer
			summer();

			// fall
			fall();

			// winter
			winter();

			year++;
		}
		System.out.println(tree_cnt);
	}
	static void spring() {
		dead_tree = new LinkedList<Tree>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(tree_map[i][j].isEmpty()) continue;
				else {
					int last_idx = tree_map[i][j].size()-1;
					for(int idx=last_idx;idx>=0;idx--) {
						int tree_age = tree_map[i][j].get(idx);
						if(current_soil[i][j]>=tree_age) {
							current_soil[i][j] -= tree_age;
							tree_age++;
							tree_map[i][j].set(idx, tree_age);
						}else {
							dead_tree.add(new Tree(i,j,tree_age));
							tree_map[i][j].remove(idx);
						}
					}
				}
			}
		}
		tree_cnt -= dead_tree.size();
	}

	static void summer() {
		while(!dead_tree.isEmpty()) {
			Tree tree = dead_tree.poll();
			int soil = tree.age/2;
			current_soil[tree.x][tree.y] += soil;
		}
	}

	static void fall() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(tree_map[i][j].isEmpty()) continue;
				else {
					int q_size = tree_map[i][j].size();
					for(int r=0;r<q_size;r++) {
						int tree_age = tree_map[i][j].get(r);
						if(tree_age%5 == 0) {
							for(int idx=0;idx<8;idx++) {
								int dx = i + move_x[idx];
								int dy = j + move_y[idx];
								if(dx<0 || dx>=n || dy<0 || dy>=n) continue;
								else {
									tree_map[dx][dy].add(1);
									tree_cnt++;
								}
							}
						}
					}
				}
			}
		}
	}

	static void winter() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				current_soil[i][j] += add_soil[i][j];
			}
		}
	}

}

```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 21%
* 시뮬레이션
* K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 출력
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 변수에 어떤 자료구조를 사용할지 선정
* `int[][] add_soil` : 겨울에 추가할 양분을 저장
* `int[][] current_soil` : 실시간 양분 상태를 저장
* `List<Integer>[][] tree_map` : 좌표에 위치한 나무들의 나이를 저장
* `Queue<Tree> dead_tree` : 좌표에 위치한 나무들을 저장
#### 2. k년이 경과할 때까지 사계절 반복
##### 2-1. 봄 : 나이가 어린 나무부터 양분을 먹고, 나이를 먹음
* 양분이 충분치 않아 죽는 나무들을 `dead_tree`에 추가
##### 2-2. 여름 : `dead_tree`에 있는 나무들을 하나씩 꺼내서 해당 양만큼 양분 추가
##### 2-3. 가을 : 번식
##### 2-4. 겨울 : `current_soil`에 `add_soil` 추가
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. k년이 지날 때까지 반복문 돌기
```java
int year = 0;
while(year<k) {
	spring();
	if(tree_cnt == 0) break;

	summer();

	fall();

	winter();

	year++;
}
```
##### 1-1. 봄
* `tree_map[i][j]`에서 마지막 인덱스부터 뽑는게 가능한 이유
	* 어차피 처음 초기화시 추가된 나무들의 나이에 상관없이, 이후 추가되는 나무들을 모두 나이가 1살부터 시작한다. 즉, 인덱스는 자연스레 **내림차순** 정렬이 된 것.

```java
static void spring() {
	dead_tree = new LinkedList<Tree>();
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			if(tree_map[i][j].isEmpty()) continue;
			else {	// 좌표에 나무들이 있으면
				int last_idx = tree_map[i][j].size()-1;
				for(int idx=last_idx;idx>=0;idx--) {
					int tree_age = tree_map[i][j].get(idx);
					// 양분을 먹을 수 있으면
					if(current_soil[i][j]>=tree_age) {
						current_soil[i][j] -= tree_age; // 양분 감소
						tree_age++; // 나무는 나이를 먹고
						tree_map[i][j].set(idx, tree_age); // 나무 업데이트
					}
					// 양분을 먹을 수 있으면 dead_tree에 추가
					else {
						dead_tree.add(new Tree(i,j,tree_age));
						tree_map[i][j].remove(idx);
					}
				}
			}
		}
	}
	tree_cnt -= dead_tree.size(); // 전체 나무 개수에서 죽은 나무의 개수를 빼기
}
```

##### 1-2. 여름
```java
static void summer() {
	while(!dead_tree.isEmpty()) {
		Tree tree = dead_tree.poll();
		int soil = tree.age/2;
		current_soil[tree.x][tree.y] += soil;
	}
}
```

##### 1-3. 가을
```java
static void fall() {
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			if(tree_map[i][j].isEmpty()) continue;
			else {
				int q_size = tree_map[i][j].size();
				for(int r=0;r<q_size;r++) {
					int tree_age = tree_map[i][j].get(r);
					if(tree_age%5 == 0) {
						for(int idx=0;idx<8;idx++) {
							int dx = i + move_x[idx];
							int dy = j + move_y[idx];
							if(dx<0 || dx>=n || dy<0 || dy>=n) continue;
							else {
								tree_map[dx][dy].add(1);
								tree_cnt++;
							}
						}
					}
				}
			}
		}
	}
}
```

##### 1-4. 겨울
```java
static void winter() {
	for(int i=0;i<n;i++) {
		for(int j=0;j<n;j++) {
			current_soil[i][j] += add_soil[i][j];
		}
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. `시간초과`. 나무의 위치를 저장하는 배열의 객체타입을 우선순위큐로 선언했음.
* 우선순위 큐는 삽입할 때마다 sorting이 발생하므로, 시간이 더 걸림
* 따라서, 객체타입을 ArrayList로 바꿔서 인덱스로 바로 접근이 가능하도록 변경
```java
//실수
static PriorityQueue<Tree>[][] tree_map;

//정답
static List<Integer>[][] tree_map;
```

#### 2. 조건을 k년 미만이 아니라, k년 이하로 잡아서 답이 안나왔음.
* year가 0년부터 시작했기 때문에, 문제에서 제시한 k년이 지난 후는 k년 미만이 되어야 함.
```java
//실수
while(year<=k) { ... }

//정답
while(year<k) { ... }
```

<br><br>


## &#10095;&#10095;&#10095; 꿀팁
* `list.set(index, value)` : List계열에서, 값을 바로 업데이트할 수 있는 메소드.
* static으로 선언된 2차원 배열의 자료타입이 자료구조인 경우, **제네릭** 생략
	```java
	static List<Integer>[][] tree_map;
	tree_map = new ArrayList[n][n];
	```
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
#### 1. 우선순위 큐 사용시, 단일이 아닌 여러 개를 사용하는 경우는 되도록 지양.
#### 2. 시간복잡도를 줄이는 방법
* 반복문은 되도록 적게 사용
* 데이터타입은 되도록 기본 타입으로(Integer)

<br>
<br>
<br>
