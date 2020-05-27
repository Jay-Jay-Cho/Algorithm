# [종이접기](https://programmers.co.kr/learn/courses/30/lessons/62049)
* **참고자료** : https://soobarkbar.tistory.com/191

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    public int[] solution(int n) {
		int size = (int) (Math.pow(2, n)-1);
		int[] answer = new int[size];
		StringBuilder sb = new StringBuilder("0");
		int cnt = n;
		while(cnt>1) {
			String str = sb.toString();
			sb.append(0);
			for(int i=str.length()-1;i>=0;i--) {
				if(str.charAt(i)=='0') {
					sb.append(1);
				}else {
					sb.append(0);
				}
			}
			cnt--;
		}
		String[] str_arr = sb.toString().split("");
		for(int i=0;i<size;i++) answer[i] = Integer.parseInt(str_arr[i]);

		return answer;
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 3
* 시뮬레이션
* 종이를 절반씩 n번 접은 후 모두 펼쳤을 때 생기는 접힌 부분의 모양을 배열에 담아 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 착안점은 중앙 인덱스를 기준으로 좌/우가 대칭을 이룬다는 것 + 이전 케이스 재활용
* n=1 : [0,0,1]
* n=2 : [0,0,1,0,1,1,0]
#### 2. 따라서, n번을 반복하면서 이전 배열을 거꾸로 이어붙임

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. n번만큼 반복하면서 이전 배열의 대칭값들을 이어붙여서 갱신
```java
StringBuilder sb = new StringBuilder("0"); // n=1일 때
int cnt = n;
while(cnt>1) { // n=1일 때 제외
  String str = sb.toString(); // 매번 이전 값으로 갱신
  sb.append(0); // 중간지점은 0
  for(int i=str.length()-1;i>=0;i--) { // 대칭되는 값들을 입력
    if(str.charAt(i)=='0') {
      sb.append(1);
    }else {
      sb.append(0);
    }
  }
  cnt--;
}
```

#### 2. StringBuilder 값들을 배열로 변환
```java
String[] str_arr = sb.toString().split("");
for(int i=0;i<size;i++) answer[i] = Integer.parseInt(str_arr[i]);
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 규칙을 찾지 않고, 이진트리 활용 시도
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
