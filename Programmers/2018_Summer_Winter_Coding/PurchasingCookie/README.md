# [쿠키 구입](https://programmers.co.kr/learn/courses/30/lessons/49995)
* **참고자료** : https://kyounju.tistory.com/6

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;
        int[] sum = new int[n+1];
        for(int i=0;i<n;i++) {
        	sum[i+1] = sum[i]+cookie[i];
        }

        for(int m=1;m<n;m++) {
        	int child_1 = sum[m];
        	for(int r=m+1;r<=n;r++) {
        		int child_2 = sum[r]-child_1;
        		if(answer > child_2 || child_2 > child_1) continue;
        		for(int l=0;l<m;l++) {
    				if(child_2 == child_1 - sum[l]) {
        				answer = Math.max(answer, child_2);
        				break;
        			}
        		}
        	}
        }


        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 4
* 시뮬레이션
* 한 명의 아들에게 줄 수 있는 가장 많은 과자 수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 각 바구니의 누적 합 배열 만들기
#### 2. 3중 반복문을 통해 answer의 최댓값을 계속해서 갱신
* 첫번째 반복문 = `m` = 첫째 아들
* 두번째 반복문 = `r` = 둘째 아들
* 세번째 반복문 = `l`
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 각 바구니의 누적 합 배열 만들기
```java
int[] sum = new int[n+1];
for(int i=0;i<n;i++) {
  sum[i+1] = sum[i]+cookie[i];
}
```
#### 2. 3중 반복문을 통해 answer의 최댓값을 계속해서 갱신
```java
for(int m=1;m<n;m++) {
  int child_1 = sum[m]; // 첫째 아들의 쿠키 갯수 (1~m)
  for(int r=m+1;r<=n;r++) {
    int child_2 = sum[r]-child_1; // 둘째 아들의 쿠키 갯수 (m+1~r)
    // 기존 answer이 크거나, 이미 둘째 아들의 쿠키 갯수가 첫째 아들보다 많다면 의미X
    if(answer > child_2 || child_2 > child_1) continue;

    // 첫째 아들의 쿠키 갯수를 1~m에서 l~m으로 바꿔주기
    for(int l=0;l<m;l++) {
    if(child_2 == child_1 - sum[l]) {
        answer = Math.max(answer, child_2);
        break;
      }
    }
  }
}
```

<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 둘째 아들이 받을 수 있는 과자바구니의 갯수가 하나일 거라고 생각함
* 첫째 아들은 l~m, 둘째 아들은 m+1~r이기 때문에 실수..
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
