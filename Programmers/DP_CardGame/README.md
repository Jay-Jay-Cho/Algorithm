

# [카드 게임](https://programmers.co.kr/learn/courses/30/lessons/42896)
* **참고자료** : https://webfirewood.tistory.com/92
* **참고자료** : https://velog.io/@ptm0304/%EC%B9%B4%EB%93%9C%EA%B2%8C%EC%9E%84
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public int solution(int[] left, int[] right) {
		int answer = 0;
		int length = left.length;
		int[][] dp = new int[length+1][length+1];

		dp[0][0] = 0;

		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(left[i]>right[j]) {
					dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j]+right[j]);
					answer = Math.max(answer, dp[i][j+1]);
				}else {
					dp[i+1][j+1] = Math.max(dp[i+1][j+1],dp[i][j]);
					dp[i+1][j] = Math.max(dp[i+1][j],dp[i][j]);
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
* 규칙에 따라 게임을 진행해서 얻을 수 있는 점수의 최댓값을 구하라.
  * 왼쪽 <= 오른쪽 : 왼쪽만 버리거나, 둘 다 버리거나
  * 왼쪽 > 오른쪽 : 오른쪽만 버리고, 점수++
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### - 일단 처음에는 DP 2차원 배열을 선언하기보다, 각 경우의 수에 따라 점수를 계산해서 한 쪽 더미 끝이 인덱스 끝에 다다르면 끝나도록 로직을 구성하려고 했다. 그러나 이런 로직은 오른쪽 카드를 버릴 때는 점수가 계산되지만, 왼쪽 혹은 모든 카드를 버릴 때는 경우의 수에 포함이 되지 않는다는 문제점이 있다.

##### - 그래서 [참고자료](https://webfirewood.tistory.com/92)를 활용하여, 2차원 DP 배열을 선언하고 각 경우의 수에 따라 분기를 해서 dp 값이 최댓값으로 유지되도록 로직을 짰다. 또한 점수의 경우, 오른쪽 카드를 버릴 때만 업데이트가 되므로, 이 경우에만 값을 업데이트 시켰다.
* 오른쪽 카드만 버릴 경우
  ```java
  dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j]+right[j]);
  answer = Math.max(answer, dp[i][j+1]);
  ```
* 왼쪽 카드만 버릴 경우 : 왼쪽 카드만 버린 경우는 점수가 갱신되지 않기 때문에, 이전 경우의 점수가 그대로 내려온다.
  ```java
  dp[i+1][j] = Math.max(dp[i+1][j],dp[i][j]);
  ```
* 둘 다 버릴 경우 : 둘 다 버린 경우도 점수가 갱신되지 않기 때문에, 이전 경우의 점수가 그대로 내려온다.
  ```java
  dp[i+1][j+1] = Math.max(dp[i+1][j+1],dp[i][j]);
  ```
<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. dp배열을 선언하고, 시작점의 초기 값을 넣는다.
```java
int[][] dp = new int[length+1][length+1];
dp[0][0] = 0;
```
<br>

##### 2. 두 더미의 크기만큼 중첩반복문을 진행한다. (Top --> Down)
* 행(`i`)에는 left배열을, 열(`j`)에는 right배열을 넣는다.
  ```java
  for(int i=0;i<length;i++) {
    for(int j=0;j<length;j++) {

      // 오른쪽 카드를 버리고, 점수를 갱신
      if(left[i]>right[j]) {
        dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j]+right[j]);
        answer = Math.max(answer, dp[i][j+1]);
      }

      else {
        // 둘 다 버릴 경우
        dp[i+1][j+1] = Math.max(dp[i+1][j+1],dp[i][j]);
        // 왼쪽만 버릴 경우
        dp[i+1][j] = Math.max(dp[i+1][j],dp[i][j]);
      }
    }
  }
  ```
<br>

##### 3. 최댓값이 저장된 answer 전역변수를 반환한다.
```java
return answer;
```

<br><br>




## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
* 왼쪽 혹은 둘 다 버리는 경우, 각 경우에 따라 분기해서 dp배열을 갱신해야했지만 처음에는 두 경우를 합쳐서 생각했다... 경우의 수에 따라서는 무조건 분기를 할 것... 물론 [이 경우](https://velog.io/@ptm0304/%EC%B9%B4%EB%93%9C%EA%B2%8C%EC%9E%84)처럼 인덱스가 끝(`length`)에서부터 처음(`0`)으로 거슬러오는(`Bottom-Up`) 경우에는 가능할 수도???
  ```java
  //실수
  dp[i][j] = Math.max(dp[i+1][j+1], dp[i+1][j]);
  //정답
  dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
  dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
  ```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
* dp배열의 값을 업데이트하는 과정에서는 무조건 값을 넣는게 아니라, 항상 이전 값과 비교하여 갱신할것. 최댓값이든 최솟값이든....
  ```java
  dp[i][j+1] = Math.max(dp[i][j+1], dp[i][j]+right[j]);
  ```

<br>
<br>
<br>
