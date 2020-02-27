

### &#128526;
[문제 링크](https://www.acmicpc.net/problem/6603)

<br>
<br>
<br>

## 설명
* 정답률: 53%
* Back Tracking (백트래킹)
* 길이가 K인 S배열에서, 중복되지않는 6개의 숫자 찾기(+사전순배열)


<br>
<br>
<br>

## 접근법
문제를 보자마자 조합을 쓰면 될 것이라 생각했다.. 
  1) 조합은 자동적으로 사전순 배열
  2) 중복되지않는 숫자후보군
<br>


1) 길이가 K인 배열 S를 받고, 또한 길이가 K인 boolean배열을 생성.
```java
int K = Integer.parseInt(N);
int[] S = new int[K];
boolean[] visited = new boolean[K];
for(int i=0;i<K;i++) {
  S[i] = Integer.parseInt(st.nextToken());
}
comb(S,visited,0,6);
```
여기서 visited배열은 탐색을 위한 것이라기보다는, 조건을 만족시켰을때 visited된 아이템들을 출력하기 위한 장치라고 보면될 것 같다.



2) 재귀함수(조합)를 돌며, 조건이 만족할 때마다(r==0 --> 6개를 모두 선별함) 출력.
```java
static void comb(int[] arr, boolean[] visited, int depth, int r) {
		if(r==0) {  // 6개가 모두 선별이 되면,
			for(int i=0;i<arr.length;i++) {
				if(visited[i]) System.out.print(arr[i]+" ");  //visited, 즉 선택된 아이템들을 출력 
			}
			System.out.println();
		}
		for(int i=depth;i<arr.length;i++) {
			visited[i]=true;
			comb(arr,visited,i+1,r-1);
			visited[i]=false; //백트래킹
		}
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
1) 순열엣ㄴ

// main에
// main함수

<br>
<br>
<br>
