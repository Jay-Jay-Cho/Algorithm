

### &#128526;
[문제 링크](https://www.acmicpc.net/problem/9663) <br>
[참고 자료](https://mygumi.tistory.com/199) <br>
[참고 동영상](https://youtu.be/-xlSysSwG7w) 

<br>
<br>
<br>

## 설명
* 정답률: 56% (이게????? 이렇게 높다고???? 겁나 어려웠는데????)
* Back Tracking (백트래킹)
* 넓이가 K * K 인 2차원 배열에 K개의 퀸을 서로 잡아먹지 않도록 하는 경우의 수를 출력.


<br>
<br>
<br>

## 접근법
(실수) 맨 처음에는 그냥 직관적으로 체스말을 놓을 2차원 배열과 visited 2차원 배열을 놓고 하나씩 모든 경우의 수를 찾는 DFS로 생각했으나, 시간복잡도에서 오버헤드가 날 것 같아서 참고자료들을 보고 구현했다... 나는 생각지도 못한 방법들이었는데
  1) 2차원 배열 대신에 1차원 배열로 탐색: col[0] = 1 ---> 0행 1열
  2) main함수에서 직접 반복문을 돌려가며(첫 번째 퀸의 위치) 모든 케이스 체크
<br>


1) Main함수에서 직접 시작 케이스들을 선별하여 탐색을 시작.
```java
for(int i=0;i<n;i++) {  // 시작 퀸의 위치가 1열부터 n-1열까지 반복
      col = new int [n+1];  
      col[0] = i;   // 0행의 i열에 퀸을 배치
      dfs(0); // 0행은 배치가 끝났으니, 다음 행부터 탐색 시작
}
```
0행으로 고정된 이유는, 하나의 행만 고정돼도 다른 퀸의 위치에 따라 모든 경우의 수를 나열할 수 있기 때문.... 또한 i가 0부터 n-1까지 반복되기때문에 0행1열, 0행2열, 0행3열, ... 즉 0행의 모든 경우의 수를 헤아릴 수 있다.
<br>
<br>


2) 고정된 행의 갯수를 parameter로 받아, 다음 탐색을 진행하는 메소드 구현.
```java
static void dfs(int row) {
	if(row==n-1) ans++;   // 만약 퀸이 모두 배치가 되면 ans++

	for(int i=0;i<n;i++) {
		col[row+1]=i;     //고정된 행(row)의 다음 행(row+1)의 열이 i라고 할 때,
		if(isPossible(row+1)) { // 만약 이 행과 열이 가능하다면, 
			dfs(row+1);     // 고정시킨후에, 다음 탐색을 진행.
		}
	}
	//col[row] = 0;
}
  
  
static boolean isPossible(int row) {

  // 해당 행과 열이(row), 누적된(<row) 행렬들과 충돌하지 않는지 체크
  for(int i=0;i<row;i++) {
  
    // 같은 열에 있지는 않는지,,
    if(col[i]==col[row]) {
      return false;
    }
    
    // 대각선 위치상에 있는지,,
    if(Math.abs(col[i]-col[row])==Math.abs(i-row)) {
      return false;
    }
  }
  return true;
}
```

여기서 중요한 것은, 
  1) comb(arr,visited,**depth**+1,r-1)가 아닌, comb(arr,visited,**i**+1,r-1) 
  2) 백트래킹은 다른 유사문제들과 마찬가지로, 재귀함수에 들어간 이후에 호출할 것.

<br>
<br>
<br>

## 유용한 함수 혹은 API


<br>
<br>
<br>

## 숙지해야할 점
1) 순열에서는 재귀함수 안의 parameter가 depth지만, 조합에서는 depth가 아니라 i라는 것을 명심!!!
```java
// Permutation
	if(depth==r) return;
for Loop
	permutation(arr,depth+1,r)
	
// Combination
	if(r==0) return;
for Loop
	combination(arr,visited,i+1,r-1)
```

<br>
<br>
<br>
