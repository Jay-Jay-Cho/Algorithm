

# [구명보트](https://programmers.co.kr/learn/courses/30/lessons/42883)
* **참고자료** : https://udud0510.tistory.com/42
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);

        int start = 0;

        for(int i=people.length-1;i>=start;i--){
            if(start==i){
                answer++;
                break;
            }
            if(people[start] + people[i] <= limit){
                answer++;
                start++;
            }else{
                answer++;
            }
        }

        return answer;
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
##### 1. 처음에는 배열을 오름차순으로 정렬하고, 첫번째 인덱스부터 순차적으로 탐색했으나, 이러한 방법은 최소한의 구명보트는 아님.
* 몸무게가 적은 사람부터 차례대로.... 그러나 이런 로직은 문제점이 있음.
* 예를들어 `[40,40,80,80]` , `limit : 120` 의 경우, `[40,40] , [80] , [80]` 총 3번이 필요함.

##### 2. 따라서, 구명보트에 채워넣는 로직은 `[몸무게가 가장 적게 나가는 사람, 몸무게가 가장 많이 나가는 사람]` 쌍으로 가야함.
* 두 몸무게의 합이 limit보다 크면, 몸무게가 가장 많이 나가는 사람만 태워서 보냄.
* 시간 복잡도를 따져서 단 한 번의 반복문으로 해결해야 함.
  * `LinkedList`와 `while & for 반복문`은 시간효율성 측면에서 제고됨.


<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 배열을 오름차순으로 정렬
  ```java
  Arrays.sort(people);
  ```
<br>

##### 2. 단 한 번의 반복문을 돌며 경우에 따라 구명보트 갯수를 추가시킴.
* 가장 가벼운 사람(start)와 가장 무거운 사람(i)의 몸무게가 limit보다 클 경우, 몸무게가 무거운 사람만 보트에 태워서 보낸다. 왜냐하면 어차피 가장 가벼운 사람과의 합이 limit보다 크기 때문에, **어느 사람과 타도 limit을 넘기 때문**.
  ```java
  // 시작 인덱스
  int start = 0;

  // 마지막 인덱스부터 이동
  for(int i=people.length-1;i>=start;i--){

      // 한 사람만 남았을 경우
      if(start==i){
          answer++;
          break;
      }

      // 보트에 두 사람을 태우는 경우
      if(people[start] + people[i] <= limit){
          answer++;
          start++;
      }

      // 가장 무거운 사람만 태우는 경우
      else{
          answer++;
      }
  }
  ```
<br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. 배열을 List에 넣어서 정렬할 때, 데이터 삽입 후 Collection 정렬하는 것보다는 데이터 정렬 후에 삽입을 하는게 더 빠르다.
```java
// 기존
int[] arr;
LinkedList list;
for(int i:arr) list.add(i);
Collections.sort(list);

// 변경
int[] arr;
LinkedList list;
Arrays.sort(arr);
for(int i:arr) list.add(i);
```




<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 한 명 남았을 경우, break를 걸어줘야 하는데 안걸어줬다.
```java
// 실수
if(start==i){
    answer++;
}

// 변경
if(start==i){
    answer++;
    break;
}
```




<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 1. 시간효율성 고려....





<br>
<br>
<br>
