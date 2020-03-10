

# [정수 삼각형](https://programmers.co.kr/learn/courses/30/lessons/43104)
* **참고자료** : https://web2eye.tistory.com/163
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Collections;
import java.util.PriorityQueue;
class Solution {
    public int solution(int[][] triangle) {
        int answer = getAnswer(triangle);
        return answer;
    }

    public int getAnswer(int[][] triangle){
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];

        for(int i=1;i<n;i++){
            dp[i][0] = triangle[i][0] + dp[i-1][0];
            dp[i][i] = triangle[i][i] + dp[i-1][i-1];
        }

        for(int i=2;i<n;i++){
            for(int j=1;j<i;j++){
                dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1],dp[i-1][j]);
                if(i==n-1){
                    q.add(dp[i][j]);
                }
            }
        }
        return q.poll();
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* DP
* 정해진 규칙에 따라, 가장 큰 결과값 찾기
<br><br>


## &#10095;&#10095;&#10095; 접근법   
* 가장 먼저 `모든 경로`, 그리고 `최댓값`이라는 단어에 꽂혀서 DFS로 완전탐색을 하려고 했으나 구글링을 해보니 효율성(시간복잡도)면에서 통과를 못한다고 카더라.... 하긴 삼각형 최대 높이가 500이면 완전탐색을 하기에는 너무 큰 것 같기는 하다... (참고로 트리의 깊이가 30이상 된다면 DP를 써야한다는 [댓글](https://programmers.co.kr/learn/questions/7298)이 있었다..)
* 일단 이 문제를 관통하는 가장 큰 점화식은 `현재 아이템의 누적 값 = 현재 아이템의 값 + Max(좌측 누적 값, 우측 누적 값)`이다.
[참고자료](https://web2eye.tistory.com/163)를 인용하자면,
  ```
  위에서 부터 나올 수 있는 경우의 수 중 최대값을 비교해서 복사된 배열에 수를 각각 누적해 준다.
  그러면 맨 마지막배열에 나온 수들이 각각의 최대값들을 저장하게 되므로
  맨 마지막 배열의 값 중 MAX 값을 찾으면 문제가 풀린다.
  ```
  인 것이다.
  즉, **맨 위에서부터 아래로 값을 누적시켜 마지막 depth에서 최댓값을 찾는 것**!!
<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 맨 마지막 depth의 누적값들을 넣어, 최댓값을 한 번에 뽑을 수 있는 Max Heap 선언.
```java
PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
```
<br>

##### 2. 누적 값들을 저장할 2차원 배열 선언. (크기는 주어진 triangle과 같음)
```java
int n = triangle.length;
int[][] dp = new int[n][n];
```
<br>

##### 3. 점화식을 위하여 depth 0, depth 1 및 양 끝 값들을 미리 초기화.
* 양 끝 값들의 경우, 점화식을 거칠 필요 없이 경우의 수가 1가지이기 때문에 미리 dp배열에 넣어놓는다. [(참고)](https://web2eye.tistory.com/163)
* 여기서 주목할 점은, 각 **depth 배열의 마지막 인덱스는 해당 depth와 똑같다** 는 것이다.
  * depth 1의 마지막 인덱스는 1
  * depth 2의 마지막 인덱스는 2
  ...

```java
// depth 0
dp[0][0] = triangle[0][0];

// depth 1 + 양 끝 값
for(int i=1;i<n;i++){
    dp[i][0] = triangle[i][0] + dp[i-1][0]; // 좌측 끝
    dp[i][i] = triangle[i][i] + dp[i-1][i-1]; // 우측 끝
}
```
<br>

##### 4. 점화식에 따라, 누적 값들을 dp배열에 넣기.
* `triangle[i][j]` = 누적되지 않은 현재 값
* `dp[i-1][j-1]` = 이전 depth(i-1)의 좌측(j-1)
* `dp[i-1][j]` = 이전 depth(i-1)의 우측(j-1)
```java
for(int i=2;i<n;i++){ // depth 0,1은 이미 채웠으니까 2부터 시작.
    for(int j=1;j<i;j++){ // 양 끝 값(0,i)들은 제외
        dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1],dp[i-1][j]);

        // 마지막 depth면, Max Heap에 넣기
        if(i==n-1){
            q.add(dp[i][j]);
        }
    }
}
```
<br>

##### 5. 마지막 누적 값들 중 최댓값 뽑기.
```java
return q.poll();
```
<br><br>




## &#10095;&#10095;&#10095; 꿀팁
* PriorityQueue의 **default** 는 **MinHeap**(오름차순)
* 따라서, 최댓값(내림차순)을 위해서는 **Collections.reverseOrder()** 를 통해 기준을 바꿔줌.

<br><br>


## &#10095;&#10095;&#10095; 숙지해야할 점 & 실수
* 트리가 아닌, 일반 삼각형 형태의 2차원 배열은 각 depth의 마지막 인덱스가 해당 depth와 똑같다는 점.
* 깊이가 30 이상이면, 완전탐색이 아닌 dp를 이용해야 한다는 점.


<br>
<br>
<br>
