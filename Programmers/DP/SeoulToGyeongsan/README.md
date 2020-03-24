

# [서울에서 경산까지](https://programmers.co.kr/learn/courses/30/lessons/42899)
* **참고자료** : https://ckddn9496.tistory.com/15
* **참고자료** : https://webfirewood.tistory.com/97
* **참고자료** : https://brad903.tistory.com/entry/Todays-Algorithm20190122
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public int solution(int k, int[][] travel) {
		int answer = 0;

		int length = travel.length;
		int[][] dp = new int[length][k+1];
		dp[0][travel[0][0]] = travel[0][1];
		dp[0][travel[0][2]] = travel[0][3];

		for(int i=1;i<length;i++) {
			int walkingTime = travel[i][0];
			int walkingMoney = travel[i][1];
			int bikingTime = travel[i][2];
			int bikingMoney = travel[i][3];

			for(int j=0;j<=k;j++) {
				if(dp[i-1][j]==0) continue;

				//walking
				if(j+walkingTime <= k) {
					dp[i][j+walkingTime] = Math.max(dp[i][j+walkingTime], dp[i-1][j]+walkingMoney);
					answer = Math.max(dp[i][j+walkingTime], answer);
				}

				//biking
				if(j+bikingTime <= k) {
					dp[i][j+bikingTime] = Math.max(dp[i][j+bikingTime], dp[i-1][j]+bikingMoney);
					answer = Math.max(dp[i][j+bikingTime], answer);
				}
			}
		}


		return answer;
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 4
* DP
* 출발지에서 목적지까지 가는 과정 중
  1) 도보 또는 자전거를 활용하여
  2) 정해진 시간(K)안에
  3) 최대의 모금액을 모을 것.
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### - 지금까지 풀어온 DP문제들과는 접근 자체가 달라서 많이 헤맸다. 직관적으로 문제에서 주어진 배열들을 활용해서 DP용 2차원 배열 만드는게 아니라, 직접 점화식을 만들어야 했다....
##### - 즉, **도보와 자전거와 같이 대상이 되는 데이터가 dp배열의 정의로 들어가는 것이 아니라 해당 문제의 조건이 정의로 존재할 수 있다는 것.** [(참고자료)](https://brad903.tistory.com/entry/Todays-Algorithm20190122)
##### - 참고자료를 활용하여, DP 배열은 아래와 같이 정의했다.
* `dp[travel.length][K+1]`
* 행(i)에는 방문 도시
* 열(j)에는 시간
* 즉, 해당 도시를 방문했을 때 도보/자전거 여부에 상관없이 모금액의 최대치만을 저장하도록.
  * dp[도시][시간] = 모금액   
##### - 주의할 점은
* 도보와 자전거, 총 2개의 경우에 따라 dp배열이 갱신되기 때문에, 값을 넣을 때 항상 현재 저장된 모금액과 비교해서 최댓값을 넣어야 한다.
  * `dp[i][j+walkingTime] = Math.max(dp[i][j+walkingTime], dp[i-1][j]+walkingMoney);`




<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. dp배열을 선언하고, 시작점의 초기 값을 넣는다.
* 도보와 자전거 둘 다 시간대가 다르기 때문에, 모두 넣는다.
* 해석하자면,
  * **시작점**(`dp[0]`)에서 **도보 시간만큼**(`travel[0][0]`) 모인 **모금액**(`travel[0][1]`)
  * **시작점**(`dp[0]`)에서 **자전거를 이용한 시간만큼**(`travel[0][2]`) 모인 **모금액**(`travel[0][3]`)

```java
int[][] dp = new int[length][k+1];
dp[0][travel[0][0]] = travel[0][1];
dp[0][travel[0][2]] = travel[0][3];
```
<br>

##### 2. i번째 도시까지 & j시간까지의 중첩반복문을 돌린다.
* 초기 값을 제외한 나머지 dp배열들은 모두 0이다. 따라서, 이 부분들은 건너뛰기 위해 이전 도시에서 **모금액이 0인 시간대들은 건너뛴다**.
  ```java
  if(dp[i-1][j]==0) continue;
  ```
* 시간 반복문(j)과 도보시간(walkingTime)의 합이 문제에서 주어진 총 시간(k)보다 작으면, 현재 도시와 해당 시간을 나타내는 dp배열의 값을 업데이트한다. 단, 여기서 주의할 점은 바로 이전 dp 누적 값 + 현재 모금액으로 바로 업데이트를 하면 안되고, **기존 값과 비교를 해서** 값을 넣어야한다.
  ```java
  //walking
  if(j+walkingTime <= k) {
    dp[i][j+walkingTime] = Math.max(dp[i][j+walkingTime], dp[i-1][j]+walkingMoney);
    answer = Math.max(dp[i][j+walkingTime], answer);
  }
  ```
  * 예를들어, 이전에 자전거를 이용한 경우가(i=1, **k=200**) 먼저 업데이트되고, 이어서 도보를 이용한 값이 들어오게 되는데 (i=1, **k=500**) 비교를 하지 않으면 먼저 조건에 들어온 값으로 저장되기 때문에, **도보/자전거 상관없이 최댓값을 저장하기 위해서는 값이 들어올 때마다 비교를 해줘야 한다**.
  * 이후 전역 변수인 answer와 비교하여, 큰 값을 전역변수로 다시 업데이트 한다.
* 도보와 마찬가지로, 자전거를 이용한 경우도 동일하게 진행한다.
  ```java
  //walking
  if(j+bikingTime <= k) {
    dp[i][j+bikingTime] = Math.max(dp[i][j+bikingTime], dp[i-1][j]+bikingMoney);
    answer = Math.max(dp[i][j+bikingTime], answer);
  }
  ```
  <br>

##### 3. 최댓값이 저장된 answer 전역변수를 반환한다.
```java
return answer;
```

<br><br>




## &#10095;&#10095;&#10095; 꿀팁
* 배열을 처음 선언만 해주면, 모두 0으로 초기화된다.
<br><br>


## &#10095;&#10095;&#10095; 실수
* 안쪽 반복문(j) continue를 하는 조건에서, 이전 도시(`i-1`)에서 모인 모금액을 비교해야 하는데 현재 도시(`i`)의 모금액을 비교해서 초반에 오류가 났다. 유의할 것.
  ```java
  //실수
  if(dp[i][j]==0) continue;
  //정답
  if(dp[i-1][j]==0) continue;
  ```
* dp배열을 업데이트 하는 과정에서, 점화식에 대한 이해가 부족해 비교를 안하고 바로 값을 집어넣었다. 경우의 수가 2가지고(`도보`&`자전거`), 따라서 시간대도 2가지(`도보시간`&`자전거시간`) 모두 저장되기 때문에 반드시 조건을 명확히 할 것. **디버깅을 통해 파악함 (i=1일 때 k=200, k=500)**.
  ```java
  //실수
  dp[i][j+walkingTime] = dp[i-1][j]+walkingMoney;
  //정답
  dp[i][j+walkingTime] = Math.max(dp[i][j+walkingTime], dp[i-1][j]+walkingMoney);
  ```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
* 점화식을 세우는 데에 있어 직접적으로 주어진 값이 아니라, 이를 이용해서 도출해야 한다는 점.
* 갱신 여부 파악 명확히.

<br>
<br>
<br>
