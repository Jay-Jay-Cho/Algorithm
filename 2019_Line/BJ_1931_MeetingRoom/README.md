# [회의실 배정](https://www.acmicpc.net/problem/1931)
* **참고자료** : https://minwoo-kang.github.io/A_1931/
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_1931 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());


		int[][] meetings = new int[N][2];

		StringTokenizer st2;
		int max = -1;
		for(int i=0;i<N;i++) {
			st2 = new StringTokenizer(br.readLine());
			int start_time = Integer.parseInt(st2.nextToken());
			int end_time = Integer.parseInt(st2.nextToken());
			meetings[i][0] = start_time;
			meetings[i][1] = end_time;
			max = Math.max(max, end_time);
		}

		boolean[] start_arr = new boolean[max+1];
		boolean[] end_arr = new boolean[max+1];

		Arrays.sort(meetings, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[1]<b[1]) return -1;
				else if(a[1] == b[1]) {
					if(a[0]<=b[0]) return -1;
					else return 1;
				}else return 1;
			}
		});

		int cnt = 0;
		int pre_end = -1;
		for(int i=0;i<N;i++) {
			int start = meetings[i][0];
			int end = meetings[i][1];
			if(start >= pre_end) {
				cnt++;
				pre_end = end;
			}
		}

		System.out.println(cnt);

	}

}

```

<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 29%
* Greedy
* 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 0. 회의에 걸리는 시간(end-start)을 기준으로 오름차순 정렬 후, 순차적으로 cnt를 늘리는 방식으로 접근
* 그러나 이 경우, 아래와 같은 케이스에서 반례가 생김

![ex2](/assets/ex2.jpg)

##### 1. [참고자료](https://minwoo-kang.github.io/A_1931/)를 활용해서, 회의에 걸리는 시간이 아니라 종료시점을 기준으로 오름차순 정렬 후 순차적으로 채워넣기
![ex3](/assets/ex3.jpg)
* 즉, 회의 시간을 최대한 빨리 끝낼수록 남은 시간이 길기 때문에 많은 회의를 소화할 수 있다.
* 단, 종료시점을 기준으로 정렬하되 **시작시간도 함께 오름차순이 되도록 정렬** 해야됨.
<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. meeting 배열을 종료시점을 기준으로 오름차순 정렬
```java
Arrays.sort(meetings, new Comparator<int[]>() {
  public int compare(int[] a, int[] b) {
    if(a[1]<b[1]) return -1;
    else if(a[1] == b[1]) { // 종료시점이 같다면,
      if(a[0]<=b[0]) return -1; // 시작시간도 오름차순 정렬
      else return 1;
    }else return 1;
  }
});
```

##### 2. 반복문을 돌면서 갱신
```java
int cnt = 0;
int pre_end = -1;
for(int i=0;i<N;i++) {
  int start = meetings[i][0];
  int end = meetings[i][1];
  if(start >= pre_end) {  // 현재 회의의 시작시간이 지난 회의의 종료시간보다 같거나 크다면
    cnt++;
    pre_end = end;  // 종료시간 갱신
  }
}
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. 입력 받아오기
```java
// InputStreamReader --> BufferedReader
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

// BufferedReader --> StringTokenizer
StringTokenizer st = new StringTokenizer(br.readLine());
N = Integer.parseInt(st.nextToken());
```

##### 2. StringTokenizer
* 공백은 알아서 제거해줌
```java
String str = "aaa bb c";
StringTokenizer st = new StringTokenizer(str);
st.nextToken(); // aaa
st.nextToken(); // bb
st.nextToken(); // c
```

<br><br>


## &#10095;&#10095;&#10095; 실수
##### 0. [프로그래머스 단속카메라](https://programmers.co.kr/learn/courses/30/lessons/42884)에서 범한 것과 동일한 실수 반복
* 배열을 정렬 & greedy를 사용해서 최대/최솟값을 뽑는 문제

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점


<br>
<br>
<br>
