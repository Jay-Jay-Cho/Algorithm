# [숨바꼭질](https://engineering.linecorp.com/ko/blog/2019-firsthalf-line-internship-recruit-coding-test/)
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
static int solution(int Cony, int Brown) {
	int answer = 0;

	boolean[][] visit = new boolean[200001][2];

	Queue<Integer> q = new LinkedList<Integer>();
	q.add(Brown);
	visit[Brown][0] = true;

	boolean flag = true;
	int time = 0;
	while(flag) {
		Cony += time;
		int time_check = time%2;
		if(Cony > 200000) {
			answer = -1;
			flag = false;
		}
		if(visit[Cony][time_check]) {
			answer = time;
			flag = false;
		}

		int size = q.size();
		int[] sub = new int[3];
		for(int i=0;i<size;i++) {
			int current = q.poll();
			int newTime = (time + 1)%2; //

			sub[0] = current - 1;
			sub[1] = current + 1;
			sub[2] = current * 2;
			for(int j=0;j<3;j++) {
				int newPosition = sub[j];

				if(newPosition>=0 && newPosition<=200000 && !visit[newPosition][newTime]) {
					visit[sub[j]][newTime] = true;
					q.add(sub[j]);
				}
			}
		}
		time++;
	}

	return answer;
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* 브라운이 코니를 잡을 수 있는 최소시간 N초를 표준 출력
	* 코니 : 이전 거리 + 1 만큼 이동 (피보나치)
	* 브라운 : (현재위치 - 1) || (현재위치 + 1) || (현재위치 * 2)
<br><br>


## &#10095;&#10095;&#10095; 접근법   
### 가장 중요한 것!
* 코니의 위치가 고정이 아니기 때문에, `특정 좌표에서 코니와 브라운이 만나냐`가 아니라, `코니의 위치가 매 시간 증가하는 브라운의 범위내로 들어오느냐`이다.
##### 0. 원래는 무식한 BFS로 탐색을 했는데, 답은 맞으나 굉장히 비효율적이라 코드를 수정함
##### 1. 일단 가장 중요한 개념은, 시간이 지날수록 브라운이 갈 수 있는 경로들을 체크해서 코니가 해당 좌표를 방문한다면 time을 return하는 것임. 여기서 짝수/홀수 개념이 등장함.
* 브라운의 이동 가능 경로를 p라 할 때, 다음 경로는 p-1 || p+1 || p*2임.
* 짝수/홀수로 나누는 것은 이전 경로를 포함시키기 위해서이다.
* 즉, 2초 단위로 현재 경로를 재방문할 수 있다는 것 = 브라운이 커버할 수 있는 범위가 넓어짐
	* 예를 들어, 4초일 때의 브라운이 갈 수 있는 경로 = `0초일 때의 위치` + `2초일 때의 위치` + `4초일 때의 위치`

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 짝수/홀수별 브라운의 커버범위를 정하는 배열 선언
```java
// 0일때는 짝수, 1일때는 홀수
boolean[][] visit = new boolean[200001][2];
```

##### 2. 처음 브라운의 위치를 큐와 짝수 배열에 넣어줌
```java
Queue<Integer> q = new LinkedList<Integer>();
q.add(Brown);
visit[Brown][0] = true;
```

##### 3. 반복문을 돌며, 코니의 위치가 브라운의 커버 범위 안쪽이면 멈춤.
```java
while(flag) {
	Cony += time;	// 코니의 위치는 피보나치
	int time_check = time%2;	// 현재 시간이 짝수인지 홀수인지 체크
	// 만약 코니의 위치가 지정범위를 넘어가면
	if(Cony > 200000) {
		answer = -1;
		flag = false;
	}

	// 만약 코니의 위치가 브라운의 커버범위 안쪽이면
	if(visit[Cony][time_check]) {
		answer = time;
		flag = false;
	}

	// 이전 시간에서 들어온 브라운의 위치 갯수(q.size)만큼 반복
	int size = q.size();
	int[] sub = new int[3];
	for(int i=0;i<size;i++) {
		int current = q.poll();
		// 현재 시간이 짝수면, 다음 시간은 홀수
		// 현재 시간이 홀수면, 다음 시간은 짝수
		int newTime = (time + 1)%2;

		sub[0] = current - 1;
		sub[1] = current + 1;
		sub[2] = current * 2;
		for(int j=0;j<3;j++) {
			int newPosition = sub[j];
			if(newPosition>=0 && newPosition<=200000 && !visit[newPosition][newTime]) {
				visit[sub[j]][newTime] = true;
				q.add(sub[j]);
			}
		}
	}
	time++;
}
```



<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. BFS에서 특정 레벨 단위로 큐 내부의 아이템들을 체크하고 싶다면, `큐 사이즈`를 활용할 것.
* 즉, 이전 레벨에서 큐에 들어간 아이템 갯수만큼 현재 레벨에서 반복문을 돌면 각 레벨별 체크가 가능하다.
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 시간이 t일 때의 브라운의 위치를 모두 확인 & 다음 위치를 큐에 넣고, t++을 해줬어야 했는데, 하나의 위치만 확인하고 t++을 해줘서 오류가 남.
* 가장 중요한 것은, `큐의 사이즈`를 활용하는 것.
* 즉, 이전 시간에서 큐에 들어간 브라운 위치가 6개면 현재 시간에서는 반복문을 6번만 체크하면 되는 것.
```java
//실수
while(true) {

	...

	time++;
}

// 정답
while(flag) {

	...

	// 이전 레벨에서 들어온 브라운의 위치를 모두 체크한 후에
	for(int i=0;i<q_size;i++) {

	}

	...

	time++;
}
```

##### 2. 다음 레벨의 시간이 아니라, 현재 시간을 기준으로 짝수/홀수를 판단함
```java
//실수
for(int i=0;i<size;i++) {
	int newTime = time%2;
	...
}

//정답
for(int i=0;i<size;i++) {
	int newTime = (time + 1)%2;
	...
}
```
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점


<br>
<br>
<br>
