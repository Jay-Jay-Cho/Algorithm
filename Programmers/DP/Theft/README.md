

# [도둑질](https://programmers.co.kr/learn/courses/30/lessons/42897)
* **참고자료** : https://doohong.github.io/2019/03/14/Algorithm-%20thievery/
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public int solution(int[] money) {
      	int answer = 0;

      	int[] dp = new int[money.length-1];
      	dp[0] = money[0];
      	dp[1] = money[0];
      	for(int i=2;i<money.length-1;i++) {
      		dp[i] = Math.max(dp[i-2]+money[i], dp[i-1]);
      	}

        int[] dp2 = new int[money.length];
        dp2[0] = 0;
        dp2[1] = money[1];
        for(int i=2;i<money.length;i++) {
      		dp2[i] = Math.max(dp2[i-2]+money[i], dp2[i-1]);
      	}
      	return Math.max(dp[money.length-2], dp2[money.length-1]);
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 4
* DP
* 인접한 두 집을 털지 않으면서, 도둑이 훔칠 수 있는 돈의 최댓값 구하기.
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1. [[ 백준 2579. 계단오르기 ]](https://www.acmicpc.net/problem/2579) 문제와 유사하다고 생각했다.
##### 2. 즉, n번째 집을 털 때의 경우의 수는 다음과 같다.
* n-1번째 집을 털었을 경우 ==> 훔치지 않는다. (연속 2번째니까)
* n-2번째 집을 털었을 경우 ==> 훔친다.
##### 3. 따라서 점화식은 아래와 같다.
* `dp[n] = Math.max( dp[n-1] , money[n]+dp[n-2] )`
##### 4. 단, 이 문제에서 주의할 점은 **집의 배치가 원형이기 때문에 첫번째 집과 마지막 집은 연속돼서는 안된다** 는 것이다.
* 이를 위해, 총 2번의 반복문을 따로 돌게된다. (중첩 X)
  * 첫번째 집을 훔쳤을 경우
  * 첫번째 집을 훔치지 않았을 경우
<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 첫번째 집을 훔쳤을 경우의 DP
* 첫번째 집을 훔치기 때문에, 두번째 집은 첫번째 집을 훔쳤을 때의 돈과 같다. (훔치지 못하니까!!!)
* 자동적으로 마지막 집은 훔칠 수 없기 때문에, **반복문은 length-1까지**
```java
int[] dp = new int[money.length-1];
dp[0] = money[0];
dp[1] = money[0]; // 두번째 집은 훔치지 못함.
for(int i=2;i<money.length-1;i++) {
  dp[i] = Math.max(dp[i-2]+money[i], dp[i-1]);
}
```
<br>

##### 2. 첫번째 집을 훔치지 않았을 경우의 DP
* 첫번째 집을 훔치지 않았기 때문에, 두번째 집은 무조건 훔친다.
* 마지막 집의 도둑질 여부는, 정해지지 않았기 때문에 **반복문은 length까지**
```java
int[] dp2 = new int[money.length];
dp2[0] = 0;
dp2[1] = money[1];  // 두번째 집은 무조건 훔침.
for(int i=2;i<money.length;i++) {
  dp2[i] = Math.max(dp2[i-2]+money[i], dp2[i-1]);
}
```
<br>

##### 3. 각 경우의 수 중 큰 값을 반환.
* 첫번째 집을 훔치지 않았기 때문에, 두번째 집은 무조건 훔친다.
* 마지막 집의 도둑질 여부는, 정해지지 않았기 때문에 **반복문은 length까지**
```java
return Math.max(dp[money.length-2], dp2[money.length-1]);
```
<br><br>




## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 숙지해야할 점 & 실수
* 경우의 수에 따라 반복문을 따로 진행할 시에, 조건을 명확히 구분할 것.
  * 처음 문제를 풀 때는, 반복문의 조건을 똑같이 줘서 틀렸다.....


<br>
<br>
<br>
