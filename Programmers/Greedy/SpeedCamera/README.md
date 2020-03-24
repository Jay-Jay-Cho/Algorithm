

# [단속카메라](https://programmers.co.kr/learn/courses/30/lessons/42884)
* **참고자료** : https://leveloper.tistory.com/36
* **참고자료** : https://velog.io/@ptm0304/%EB%8B%A8%EC%86%8D%EC%B9%B4%EB%A9%94%EB%9D%BC-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Lv.3

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Comparator;
import java.util.Arrays;
class Solution {
    public int solution(int[][] routes) {
		int answer = 0;

		Arrays.sort(routes, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[1] <= b[1]) return -1;
				else return 1;
			}
		});

		int temp = Integer.MIN_VALUE;

		for(int i=0;i<routes.length;i++) {
			int start = routes[i][0];
			int end = routes[i][1];
			if(temp < start) {
				temp = end;
				answer++;
			}
		}

		return answer;
	}
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* Greedy
* 모든 차량이 한 번은 단속용 카메라를 만나도록 하는 최소 카메라 갯수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 0. 처음에는 주어진 routes 배열을 진출시점(`routes[i][1]`)이 아닌, 이동거리(`routes[i][0]-routes[i][1]`)순으로 생각했다.
* 진입 ~ 진출이 -30000 ~ 30000이므로 총 60000사이즈의 배열을 선언
* 차례대로 해당 이동거리의 배열값을 (index+1)로 채움
  * for문을 돌다가 배열값이 0이 아닌 부분을 만난다면, 그 부분은 이미 카메라가 설치돼있으므로 해당 값은 넘어감.
  * 이동거리 내의 Min 값을 Priority Queue에 넣음
* Priority Queue 의 최댓값 = answer
* 그러나, 아래와 같은 반례를 해결하지 못함
  * (0,4) , (4,6) , (6,13)
  * 오름차순 정렬하면 ---> (4,6) , (0,4) , (6,13)
  * 결과값이 2가 아니라 1이 나옴...

##### 1. [참고자료](https://leveloper.tistory.com/36)를 활용해서, 주어진 routes 배열을 진출시점순으로 오름차순 정렬해서 사용.
* 진출 시점순으로 오름차순 정렬
* routes 배열의 크기만큼 반복문을 돌면서 체크
  * 처음 temp 값은 Integer.MIN_VALUE
  * 만약 i번째 자동차의 진입시점(`routes[i][0]`)이 temp보다 크다면, 즉 우측에 있다면 카메라를 추가해야하므로 answer++
  * temp를 현재 자동차의 진출시점(`routes[i][1]`)으로 갱신

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. routes배열값들을 진출시점기준으로 오름차순 정렬
```java
boolean[] visit = new boolean[n]; // 노드 방문 여부 체크
int[][] maps = new int[n][n]; // 각 노드 간 cost 배열
ArrayList<Integer> list = new  ArrayList<Integer>();  // MST 리스트
```

##### 2. 반복문을 돌면서 갱신
```java
int temp = Integer.MIN_VALUE;

for(int i=0;i<routes.length;i++) {
  int start = routes[i][0]; // i번째 자동차의 진입시점
  int end = routes[i][1]; // i번째 자동차의 진출시점
  if(temp < start) {  // 이전 자동차의 진출시점 < 현재 자동차의 진입시점
    temp = end; // 현재 자동차의 진출시점으로 갱신
    answer++; // 카메라 수 추가
  }
}
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 코드 자체의 구현은 어렵지 않지만, 아이디어 자체를 발상해내기 쉽지 않았음....

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점


<br>
<br>
<br>
