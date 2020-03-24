

# [입국심사](https://programmers.co.kr/learn/courses/30/lessons/43238)
* **참고자료** : https://iamheesoo.github.io/blog/algo-prog43238
* **참고자료** : https://antananarivo.tistory.com/141
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
		long answer = Long.MAX_VALUE;
		Arrays.sort(times);
		int length = times.length;
		long max = (long)times[length-1]*n;
		long min = 0;
		long mid = (max+min)/2;
		long sum;

		while(min<=max) {
			sum = 0;
			mid = (max+min)/2;
			for(int time:times) {
				sum+=mid/time;
			}

			if(sum<n) {
				min = mid+1;
			}else {
				answer = Math.min(answer, mid);
				max = mid-1;
			}
		}
		return answer;
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* Binary Search
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### - Logic
* Objective : 총 심사시간의 최솟값
* 활용할 수 있는 것 : n
* 만족하는 조건(sum<=M) 중에서 최솟값(Math.min)을 구하는 것.
  ```Java
  if(sum<n){
    min = mid+1;
  }else{
    answer = Math.min(answer,mid);
    max = mid-1;
  }
  ```


<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 이분탐색을 위해 일단 주어진 배열을 정렬시킨다.
```java
Arrays.sort(times);
```
<br>

##### 2. min,max, 그리고 mid값을 계산한다.
```java
long max = times[length-1]*n;
long min = 0;
long mid = (max+min)/2;
```
<br>

##### 3. 주어진 mid시간을 기준으로 이분탐색을 진행한다.
* sum = mid시간동안 처리할 수 있는 사람의 수.
  * 만약 mid=30, times=[7,10]이면, 30/7 + 30/10 = 7명이다.
* sum > n : 처리할 수 있는 사람이 제약조건보다 많으므로, 최솟값을 구하기 위해 max를 mid로 갱신한다.
* sum <= n : 처리할 수 있는 사람이 제약조건보다 적으므로, 최솟값을 구하기 위해 min을 mid로 갱신한다.
```java
while(min<=max) {
  sum = 0;
  mid = (max+min)/2;

  // avg타임동안 처리할 수 있는 사람의 수 구하기
  for(int time:times) {
    sum+=mid/time;
  }

  // 처리한 사람의 수가 제약조건보다 적으면 (=시간이 부족하면)
  if(sum>n) {
    max = mid-1;
  }
  // 처리한 사람의 수가 제약조건보다 많으면 (=시간이 남으면)
  else {
    answer = Math.min(answer,mid);
    min = mid+1;
  }
}
```
<br><br>

## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
* 이분탐색을 위한 while문의 조건으로 처음에는 temp_mid와 mid변수가 같아질 때 멈추도록 로직을 구성했었다. 하지만 이렇게되면 mid 선언함에 있어 몫과 나머지를 더하는 귀찮은 과정 뿐만 아니라, 반환되는 answer값에 오차가 생겼다. 이를 위해 while문의 break조건을, mid값 비교가 아닌 min과 max 비교로 대체했다.
  ```java
  // 기존
  while(true) {
    if(temp_mid==mid){
      answer = mid;
      break;
    }
  }

  // 변경
  while(min<=max) {

  }
  ```
* 또한 기존에는 min,max을 값의 갱신을 mid로 했는데 이를 기존 mid보다 1만큼의 오차가 날 수 있도록 조정했다.
  ```java
  // 기존
  while(true) {
    if(sum>=n){
      max = mid;
    }else{
      min = mid;
    }
  }

  // 변경
  while(true) {
    if(sum>=n){
      answer = Math.min(answer,mid);
      max = mid - 1;
    }else{
      min = mid + 1;
    }
  }
  ```
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
* 문제의 반환 조건을 명심할 것 + answer를 계속해서 갱신할 것
  * 조건을 만족하는 값들 중에서 최댓값을 뽑는 것인지
  * 조건을 만족하는 값들 중에서 최솟값을 뽑는 것인지

<br>
<br>
<br>
