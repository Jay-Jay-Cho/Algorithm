

# [징검다리](https://programmers.co.kr/learn/courses/30/lessons/43236)
* **참고자료** : https://dokrsky.tistory.com/81
* **참고자료** : https://geehye.github.io/programmers-binary-search-01
* **참고자료** : https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC-Java
* **참고자료** : https://webfirewood.tistory.com/108
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Arrays;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
            int answer = 0;
            Arrays.sort(rocks);
            int length = rocks.length;
            int[] new_rocks = new int[length+1];
            new_rocks[0] = rocks[0];
            new_rocks[length] = distance - rocks[length-1];
            for(int i=1;i<length;i++){
                new_rocks[i] = rocks[i]-rocks[i-1];
            }
            int min = 1;
            int max = distance;
            int mid;
            int cnt;
            int temp;
            while(min<=max){
                cnt = 0;
                temp = 0;
                mid = (max+min)/2;
                for(int rock:new_rocks){
                    if(rock+temp<mid){
                        cnt++;
                        temp += rock;
                    }else{
                        temp = 0;
                    }
                }

                if(cnt>n){
                    max = mid-1;
                }else{
                    answer = Math.max(answer,mid);
                    min = mid+1;
                }

            }
        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 4
* Binary Search
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### - Objective : 두 지점 사이의 거리의 최솟값
* 출발점 ~ 바위
* 바위 ~ 바위
* 바위 ~ 도착점
##### - 활용할 수 있는 것 : n (제거할 수 있는 바위의 갯수)


<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 이분탐색을 위해 일단 주어진 배열을 정렬시킨다.
```java
Arrays.sort(rocks);
```
<br>

##### 2. 출발점, 바위, 도착점 사이의 거리를 저장할 새로운 배열을 만들어 초기화한다.
```java
int length = rocks.length;
int[] new_rocks = new int[length+1];
new_rocks[0] = rocks[0];
new_rocks[length] = distance - rocks[length-1];
for(int i=1;i<length;i++){
    new_rocks[i] = rocks[i]-rocks[i-1];
}
```
<br>

##### 3. 이분탐색을 위한 min,max,mid값을 선언한다.
```java
int min = 1;
int max = distance;
int mid;
int cnt;
int temp;
```
<br>

##### 4. 이분탐색을 위한 로직을 구성한다.
```java
while(min<=max){
    cnt = 0;
    temp = 0;
    mid = (max+min)/2;
    for(int rock:new_rocks){
        if(rock+temp<mid){
            cnt++; // 제거 
            temp += rock;
        }else{
            temp = 0;
        }
    }

    if(cnt>n){
        max = mid-1;
    }else{
        answer = Math.max(answer,mid);
        min = mid+1;
    }

}
```
<br><br>

## &#10095;&#10095;&#10095; 꿀팁

<br><br>


## &#10095;&#10095;&#10095; 실수

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점

<br>
<br>
<br>
