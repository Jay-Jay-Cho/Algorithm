

# [큰 수 만들기](https://programmers.co.kr/learn/courses/30/lessons/42883)
* **참고자료** : https://geehye.github.io/programmers-greedy-02/#
* **참고자료** : https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%81%B0-%EC%88%98-%EB%A7%8C%EB%93%A4%EA%B8%B0-Java
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public String solution(String number, int k) {
        String answer = "";

        if(number.charAt(0) == '0') return "0";

        StringBuilder sb = new StringBuilder();

        int idx = 0;
        int right = k;

        while(sb.length()<number.length()-k) {
        	int max = -1;
            int max_idx = 0;
        	for(int i=idx;i<=right;i++) {
            	if(number.charAt(i)-'0' > max) {
            		max = number.charAt(i)-'0';
            		max_idx = i;
            	}
            }
            sb.append(String.valueOf(max));
            idx = max_idx+1;
            right++;
        }

        return sb.toString();
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 2
* Greedy
* number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1. 주어진 범위 (idx = 0 ~ k) 내에서 최댓값을 찾아 시작 인덱스로 지정.
##### 2. k의 범위를 1씩 늘려가며, 계속해서 최댓값을 찾음.
* k가 한 개씩 선택될 때마다, 자릿수가 밀리기 때문에...
##### 3. 시간 절약을 위해 문자열 붙이기는 StringBuilder 이용.


<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 시작 인덱스 범위 내에서 최댓값을 찾기 위한 조건 지정.
  ```java
  int idx = 0;
  int right = k;
  ```
<br>

##### 2. 반환 문자열의 길이가 length - k 가 될 때까지 반복하며, 해당 반복문에서 최댓값을 계속 이어붙임.
* 해당 회차에서 최댓값을 찾았으면, 다음 반복문의 시작 인덱스는 최댓값 인덱스 + 1
* k 개 중에서 한 개가 결정되었으므로, 제한 조건도 + 1
  ```java
  while(sb.length()<number.length()-k) {
    int max = -1;
    int max_idx = 0;
    for(int i=idx;i<=right;i++) {
        if(number.charAt(i)-'0' > max) {
          max = number.charAt(i)-'0';
          max_idx = i;
        }
      }
      sb.append(String.valueOf(max));
      idx = max_idx+1;
      right++;
  }
  ```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. [Char 형을 Int 형으로 변환](https://m.blog.naver.com/PostView.nhn?blogId=zxy826&logNo=220806191917&proxyReferer=https%3A%2F%2Fwww.google.com%2F)
* char 뒤에 `- '0'`을 붙여준다.
* `input.charAt(i) - '0'`



<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 최솟값 처리를 0으로 했다. 이 경우, 문자열 중간에 `0`이 껴있을 경우에 예외 케이스가 생길 수 있다.
```java
// 실수
char max = '0';
if(number.charAt(i) > max) {
  ...
}
// 정답
int max = -1;
if(number.charAt(i)-'0' > max) {
  ...
}
```
<br>

##### 2. 반복문의 제한 조건이 헷갈렸다.
* 실수
  * length만큼의 길이를 가진 문자열에서 k개를 제외하려면 적어도 마지막 문자 인덱스 뒤에는 k개가 있고, 이 조건을 만족하는 값들 중에서 최댓값인 인덱스가 시작점이다.
  * 하지만 이 조건은 틀렸다. 왜냐하면 시작 인덱스의 범위를 0부터 length-k까지 놓는다는 것은, k개를 선택하는 조건이기 때문이다.
* 정답
  * k를 제외하는 조건이 되려면, 오히려 시작 인덱스의 범위를 0부터 k까지 주어야 한다.
  * 예를들어 length = 10, k = 3 이라고 해보자. 이 경우, 정답 문자열은 7(length-k)의 길이를 가져야하기 때문에 시작 인덱스는 0 ~ 7 이 아니라, 0 ~ 3에서 찾아야 한다.

<br>





<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 1. 반복문의 제한 조건 명확히 할 것.
* 테스트 케이스 여러 개를 대입해보면서 확인.





<br>
<br>
<br>
