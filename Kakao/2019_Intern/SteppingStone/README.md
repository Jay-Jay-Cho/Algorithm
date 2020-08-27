# [징검다리 건너기](https://programmers.co.kr/learn/courses/30/lessons/64062)
* **참고자료** : https://makeupthebed.tistory.com/30

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int min = 1;
        int max = 200000000;
        int mid;

        while(min<=max) {
        	mid = (max+min)/2;
        	int[] copy_stones = stones.clone();
        	for(int i=0;i<stones.length;i++) {
        		copy_stones[i] -= mid;
        	}
        	int check = 0;
        	boolean flag = true;
        	for(int i=0;i<stones.length;i++) {
        		if(copy_stones[i]<0) check++;
        		else check = 0;

        		if(check==k) {
        			flag = false;
        			break;
        		}
        	}

        	if(flag) {
        		answer = Math.max(answer, mid);
        		min = mid+1;
        	}else {
        		max = mid-1;
        	}
        }


        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 3
* 이분탐색
* 최대 몇 명까지 징검다리를 건널 수 있는지 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### Objective : 건널 수 있는 사람들의 최댓값
#### 활용할 수 있는 것
* `min` : 건널 수 있는 사람들의 최솟값 = 1
* `max` : 건널 수 있는 사람들의 최솟값 = 200,000
* `mid` & `k` : mid, max 조절 여부를 판단하는 기준에 사용
#### 1. Min, Max 설정
#### 2. Mid값 만큼을 각 돌다리의 숫자들에서 뺌
#### 3. 숫자가 0 미만인 연속된 돌다리들이 k보다 큰 경우, mid 만큼의 사람이 건널 수 없으므로 max를 감소
* mid를 줄이려면, max를 줄여야 됨.
#### 4. 숫자가 0 미만인 연속된 돌다리들이 k보다 큰 경우가 없이, mid 만큼의 사람이 건널 수 있다면 더 많은 사람들이 건널 수 있으므로 mid를 증가
* mid를 늘리려면, min을 늘려야 됨.
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. Min, Max 설정
```java
int min = 1;
int max = 200000000;
```

#### 2. 최적값을 찾을 때까지 반복문 (최적값 = min이 max값 이상이 되는 경우)
```java
while(min<=max) {
  mid = (max+min)/2;
  // mid값만큼 각 돌다리의 숫자를 감소
  int[] copy_stones = stones.clone();
  for(int i=0;i<stones.length;i++) {
    copy_stones[i] -= mid;
  }

  // 0 미만인 돌다리가 k개 이상이면, mid만큼의 사람이 건널 수 없으므로 정지
  int check = 0;
  boolean flag = true;
  for(int i=0;i<stones.length;i++) {
    if(copy_stones[i]<0) check++;
    else check = 0;
    if(check==k) {
      flag = false;
      break;
    }
  }

  // mid만큼의 사람이 건널 수 있으면, 더 많은 사람도 건널 수 있으므로
  if(flag) {
    answer = Math.max(answer, mid);
    min = mid+1; // mid를 증가시키기위해, min을 증가
  }else { // mid만큼의 사람도 건널 수 없으면, 더 적은 사람의 수를 구하기 위해
    max = mid-1; // mid를 감소시키기위해, max를 감소
  }
}
```
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 이분탐색을 활용해야겠다는 감을 못찾음.
* 이분탐색의 경우, mid와 주어진 조건들을 활용하여 min,max를 갱신시켜 최적값을 찾는 것.
* 무엇을 활용할 수 있는지, 어떻게 활요하면 mid값이 변화하는지 생각.
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
* 무엇을 활용할 수 있는지, 어떻게 활요하면 mid값이 변화하는지 생각.
<br><br>
