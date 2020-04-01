# [탑](https://www.acmicpc.net/problem/2493)
* **참고자료** : https://wellohorld.tistory.com/61?category=875002
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2493_Tower {

	// PriorityQueue
	static void solution(int N, int[] arr) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int[] answer = new int[N];
		heap.add(N-1);
		answer[0] = 0;
		if(N!=1) {
			for(int i=N-2;i>=0;i--) {
				int target_height = arr[i];
				while(!heap.isEmpty()) {
					int temp = heap.peek();
					if(target_height >= arr[temp]) {
						answer[temp] = i+1;
						heap.poll();
					}else break;
				}
				heap.add(i);
			}
			while(!heap.isEmpty()) {
				answer[heap.poll()] = 0;
			}
		}

		for(int i=0;i<N;i++) {
			System.out.print(answer[i]+" ");
		}
	}

	// Stack
	static void solution2(int N, int[] arr) {
		Stack<Integer> stack = new Stack<Integer>();
		int[] answer = new int[N];
		stack.push(N-1);
		if(N!=1) {
			for(int i=N-2;i>=0;i--) {
				int target_height = arr[i];
				while(!stack.isEmpty()) {
					int item = arr[stack.peek()];
					if(target_height >= item) {
						answer[stack.peek()] = i+1;
						stack.pop();
					}else break;
				}
				stack.push(i);
			}
		}
		while(!stack.isEmpty()) {
			answer[stack.pop()] = 0;
		}
		for(int i=0;i<N;i++) {
			System.out.print(answer[i]+" ");
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}

		solution(N,arr);

	}

}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 35%
* Stack or PriorityQueue
* 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1. 배열의 마지막 인덱스의 값을 우선순위큐(내림차순)에 넣고,
##### 2. 0번째 인덱스까지 반복문을 돌며, 우선순위큐 내부의 값들과 비교
* 현재 인덱스의 값(높이)이 우선순위큐의 첫번째 값(가장 작은)보다 크거나 같다면 `poll()`
* 크다면, break하고 현재 인덱스를 우선수위큐에 추가
##### 3. 0번째 인덱스까지 반복한 후에 남은 값들은 모두 값을 0으로 넣어줌.
<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 우선수위큐 선언 후, 마지막 인덱스를 값으로 넣어줌.
```java
PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
heap.add(N-1);
```
##### 2. 마지막 2번째 인덱스부터 첫번째 인덱스까지 반복문을 돌며 우선순위큐 내부 값들과 비교
```java
for(int i=N-2;i>=0;i--) {
	int target_height = arr[i];
	while(!heap.isEmpty()) {
		int temp = heap.peek();	// 일단 확인을 위해 poll 대신 peek
		if(target_height >= arr[temp]) {
			answer[temp] = i+1;	// 현재 인덱스+1을 값으로 넣어줌
			heap.poll();	// 확인되면 poll
		}else break;
	}
	heap.add(i);
}
```

##### 3. 마지막까지 남은 인덱스들은 값을 0으로 선언
```java
while(!heap.isEmpty()) {
	answer[heap.poll()] = 0;
}
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 0. 우선순위내부의 값을 `peek`으로 먼저 확인 후에 `poll`을 했어야했는데, 바로 `poll`을 해서 값이 제대로 들어가지 않았음
```java
// 실수
while(!heap.isEmpty()) {
	int temp = heap.poll();
	if(target_height >= arr[temp]) {
		answer[temp] = i+1;
	}else break;
}

// 정답
while(!heap.isEmpty()) {
	int temp = heap.peek();
	if(target_height >= arr[temp]) {
		answer[temp] = i+1;
		heap.poll();
	}else break;
}
```
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점


<br>
<br>
<br>
