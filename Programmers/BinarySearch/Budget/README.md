

# [예산](https://programmers.co.kr/learn/courses/30/lessons/43237)
* **참고자료** : https://jee-goo.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4%EC%9D%B4%EB%B6%84%ED%83%90%EC%83%89%EC%98%88%EC%82%B0
* **참고자료** : https://iamheesoo.github.io/blog/algo-prog43237
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Arrays;
class Solution {
    public int solution(int[] budgets, int M) {
        int answer = 0;
        Arrays.sort(budgets);
        int length = budgets.length;
        long sum = 0;
        for(int val:budgets){
            sum+=val;
        }
        if(sum<=M) return budgets[length-1];

        int max = budgets[length-1];
        int min = 0;
        int mid = 0;

        while(min<=max){
            sum = 0;
            mid = (max+min)/2;

            for(int i=0;i<length;i++){
                if(budgets[i]<mid){
                    sum += budgets[i];
                }else
                    sum += mid;
            }
	        if(sum>M){
	            max = mid-1;
	        }else{
	        	answer = Math.max(answer, mid);
	            min = mid+1;
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
* 주어진 총 예산 내에서 최댓값을 가질 수 있도록 하는 상한액의 최댓값을 찾아라.
  * 예산(budgets[i]) <= 상한액 = 예산(budgets[i])
  * 예산(budgets[i]) > 상한액 = 상한액
<br><br>

## &#10095;&#10095;&#10095; 이분탐색 풀이법
#### 1. Objective, 활용가능한 변수를 찾는다.
#### 2. 반환 기준을 정한다.
* 조건을 만족하는 값들중에서 최댓값인지 혹은 최솟값인지.
#### 3. 이분탐색 반복문 로직을 구성한다.
* min,max,mid 변수
* 공통 부분
  ```Java
  while(min<=max){

    if(condition...){
      // 활용가능한 변수용 조건문(e.g. cnt++, sum++)
    }


    if(condition < limit){
      max = mid-1;
    }else{
      // answer = Math.max 또는 Math.min(answer,mid);
      min = mid+1;
    }
  }
  ```
<br><br>

## &#10095;&#10095;&#10095; 접근법  

##### - Logic
* Objective : 상한액의 최댓값
* 활용할 수 있는 것 : M
* 만족하는 조건(sum<=M) 중에서 최댓값을 구하는 것.
  ```Java
  if(sum>M){
    max = mid-1;
  }else{
    answer = Math.max(answer,mid);
    min = mid+1;
  }
  ```



##### - 처음에는 이분탐색의 개념을 명확히 몰랐기 때문에, 이분탐색에서 제공하는 인덱스 API(`Arrays.binarySearch(arr,value)`)를 활용해서 문제를 풀려고 했다. 그러나 이 경우, 초과 금액을 어떻게 처리할 지에 대한 모호한 점들이 있어 포기했다...

##### - 그래서 [참고자료](https://iamheesoo.github.io/blog/algo-prog43237)를 활용하여, 이분탐색의 개념자체를 적용하여 문제를 접근했다.

##### - 이분탐색 자체가 특정 값의 위치를 찾는 것이다. 이 문제에 적용해본다면 찾는 특정 값은 `상한액`이라 할 수 있다. 그렇기 때문에
1. 처음 상한액은 일단 평균값(mid)으로 한다. ((최댓값+최솟값)/2)
2. 상한액을 기준으로 예산 총액(sum)을 계산했을 때,
  * 예산이 주어진 값(M)보다 크면, 최댓값을 상한액으로 재설정하여 다시 계산한다.
  * 예산이 주어진 값(M)보다 작으면, 최솟값을 상한액으로 재설정하여 다시 계산한다.
3. 이전 상한액을 임시 변수로 저장해놓고, 매 사이클마다 현재 상한액과 비교하여 상한액 값이 바뀌지 않으면, 해당 금액이 최적의 해이므로 반환한다.

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 이분탐색을 위해 일단 주어진 배열을 정렬시킨다.
```java
Arrays.sort(budgets);
```
<br>

##### 2. 주어진 예산(M)의 상한액은 10억이지만, sum을 도출하는 과정에서 sum값은 int의 상한선인 21억을 초과할 수 있으므로, sum의 변수 타입은 long으로 선언한다.
```java
long sum = 0;
```
<br>

##### 3. 도시별 예산의 총액이 주어진 예산보다 작으면, 굳이 이분탐색을 할 필요가 없으므로, 최댓값을 반환한다.
```java
for(int val:budgets){
    sum+=val;
}
if(sum<=M) return budgets[length-1];
```
<br>

##### 4. 3번 케이스가 아닐 시에는, 이분탐색을 해야하므로 이를 위해 max,min,mid, 그리고 최댓값 비교를 위한 pre_mid를 선언한다.
```java
int max = budgets[length-1];
int min = 0;
int mid = 0;
int pre_mid = 0;
```
<br>

##### 5. 최적의 상한액을 찾을 때까지 반복문을 진행한다.
```java
while(mid<=max){
    sum = 0;

    // 상한액은 최댓값과 최솟값의 평균.
    mid = (max+min)/2;

    // sum 계산.
    for(int i=0;i<length;i++){
        if(budgets[i]<mid){
            sum += budgets[i];
        }else
            sum += mid;
    }


    if(sum>M){  // 계산된 총액이 주어진 예산보다 크면,
        max = mid-1;  // 최댓값을 현재 상한액으로 갱신하고, 다시 이분탐색  
    }else{  // 계산된 총액이 주어진 예산보다 작으면,
        answer = Math.max(answer,mid);
        min = mid+1; // 최솟값을 현재 상한액으로 갱신하고, 다시 이분탐색  
    }

}
```
<br><br>

## &#10095;&#10095;&#10095; 꿀팁
* `Arrays.binarySearch(arr,value)` : value가 index를 반환
  * arr내에 value가 존재할 시 : value의 index반환
  * arr내에 value가 존재하지 않을 시 : -(value의 위치 index) + 1
  * [참고자료](https://code0xff.tistory.com/68)

<br><br>


## &#10095;&#10095;&#10095; 실수
* sum 변수의 타입을 long이 아니라, int로 선언한 것
  ```java
  // 실수
  int sum = 0;
  // 정답
  long sum = 0;
  ```
* min 변수의 값을 0이 아니라, budget[0]으로 선언한 것
  ```java
  // 실수
  int min = budgets[0];
  // 정답
  int min = 0;
  ```
  * 테스트케이스 9번이 계속 틀린 이유다.
  * `budgets = [9, 8, 5, 6, 7]`, `M = 5`
  * 이 경우, 배열의 최솟값이 5이기 때문에 상한액이 5보다 작을 수 없도록 로직이 구성된다.
  * 따라서, 이러한 반례를 방지하기 위해서는 min값을 0으로 설정해줘야 한다.


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
* 이분탐색을 쓸 때는 항상 맨 처음에 정렬을 시킨 후에 사용할 것.
* 이분 탐색을 직관적으로 이해하자!!!!
![hhiR6QU](/assets/hhiR6QU_0m8odqmhl.png)






<br>
<br>
<br>
