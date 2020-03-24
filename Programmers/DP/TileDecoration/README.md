

# [타일 장식물](https://programmers.co.kr/learn/courses/30/lessons/43104)
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public long solution(int N) {
        long answer = getAnswer(N);
        return answer;
    }

    public long getAnswer(int N){
        long[] arr = new long[N];
        arr[0] = 1;
        arr[1] = 1;
        for(int i=2;i<N;i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return 2*(2*arr[N-1]+arr[N-2]);
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* 가장 기본적인 DP 문제  
* 피보나치의 규칙을 찾아서, n번째의 직사각형 둘레를 구하기
<br><br>


## &#10095;&#10095;&#10095; 접근법   
* 일단 가장 먼저, 배열 내 아이템들의 규칙을 찾는 데에 집중했다.
  * n번째 아이템의 점화식은
`arr[n] = arr[n-1]+arr[n-2]` 이고,
  * n개의 타일로 둘러쌓인 직사각형의 둘레 길이는
  `4*arr[n] + 2*arr[n-1]`이다.
* 이에 따라 **Bottom-Up** 방식으로 코드를 구성하면 아래와 같다.
  ```java
  long[] arr = new long[N];
  arr[0] = 1;
  arr[1] = 1;

  for(int i=2;i<N;i++){
      arr[i] = arr[i-1]+arr[i-2];
  }

  return 2*(2*arr[N-1]+arr[N-2]);
  ```

<br><br>

## &#10095;&#10095;&#10095; 꿀팁
* 음.... 딱히???

<br><br>


## &#10095;&#10095;&#10095; 숙지해야할 점
* 피보나치 수열이기 때문에, 시간복잡도는 아이템 갯수가 많아질수록 기하급수적으로 커진다. 따라서, 배열을 int(32비트)가 아닌 long타입(64비트)으로 해야한다.
  * `int의 범위` : -2147483648 ~ 2147483647 (**약 21억**)
  * `long의 범위` : -9223372036854775808 ~ 9223372036854775807


<br>
<br>
<br>
