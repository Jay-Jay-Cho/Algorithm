# [쇠막대기](https://programmers.co.kr/learn/courses/30/lessons/42585)
* **참고자료** : https://medium.com/@nsh235482/java-coding-programmers-stack-queue-lv2-%EC%87%A0%EB%A7%89%EB%8C%80%EA%B8%B0-d3c482da3d98
* **참고자료** : https://developerdk.tistory.com/14

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static int solution(String arrangement) {
        int answer = 0;

        int n = arrangement.length();
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<n;i++) {
        	Character c = arrangement.charAt(i);
        	if(c == '(') stack.push(c);
        	else {
        		stack.pop();
        		Character prev = arrangement.charAt(i-1);
        		if(prev==')') {
        			answer++;
        		}else {
            		answer += stack.size();
        		}
        	}
        }

        return answer;
    }
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 2
* Stack
* 잘린 쇠막대기 조각의 총 개수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 도저히 아이디어를 생각해낼 수 없어서, [참고자료](https://medium.com/@nsh235482/java-coding-programmers-stack-queue-lv2-%EC%87%A0%EB%A7%89%EB%8C%80%EA%B8%B0-d3c482da3d98) 활용
##### 가장 중요한 것은, 막대기가 잘리는 시점을 종(vertiacl)으로 바라볼 것.
* 막대기 안에 레이저가 몇 개 있는지가 아니라,
* 당장 레이저가 위치한 시점에서 막대기가 몇 조각으로 잘리는지에 초점을 맞출 것
##### `(`를 만나면 stack에 push
##### `)`를 만나면 stack에 pop 하고,
* 이전 괄호가 `(` = 즉, 레이저면 stack안에 들어있는 **`(`갯수만큼** answer++
* 이전 괄호가 `)` = 즉, 막대기가 끝나는 지점이면 **1만큼만** answer++



<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 변수 및 객체 선언
```java
Stack<Character> stack = new Stack<Character>();
```

##### 2. arrangement 길이만큼 반복
```java
for(int i=0;i<n;i++) {
  Character c = arrangement.charAt(i);

  // `(`를 만나면 stack에 push
  if(c == '(') stack.push(c);

  // `)`를 만나면 pop
  else {
    stack.pop();
    Character prev = arrangement.charAt(i-1);
    if(prev==')') { // 막대기의 끝
      answer++;
    }else { // 레이저
        answer += stack.size();
    }
  }
}
```
<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 시점을 횡적으로만 보지 말고, 종적으로도 볼 줄 알아야 한다....
* 멀리 안보고, 현재에 집중해서 단편적으로 볼 수 있는 것도 중요....




<br>
<br>
<br>
