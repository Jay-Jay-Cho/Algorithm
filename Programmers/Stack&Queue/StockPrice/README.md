# [주식가격](https://programmers.co.kr/learn/courses/30/lessons/42584)
* **참고자료** : 엄슴..

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Stack;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        int n = prices.length;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for(int i=1;i<n;i++) {
        	int current_price = prices[i];
        	while(!stack.isEmpty()) {
        		int temp_idx = stack.peek();
        		int temp_price = prices[temp_idx];
        		if(temp_price > current_price) {
        			answer[temp_idx] = i - temp_idx;
        			stack.pop();
        		}else break;
        	}
        	stack.push(i);
        }
        if(stack.size() != 0) {
        	while(!stack.isEmpty()) {
        		int idx = stack.pop();
        		answer[idx] = (n-1) - idx;
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
* 가격이 떨어지지 않은 기간은 몇 초인지를 return
* [탑 문제](https://programmers.co.kr/learn/courses/30/lessons/42588)와 똑같음....
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### prices 배열의 값들을 하나씩 stack에 넣으면서 체크
* stack.peek()한 값이
  * 다음 값보다 작다면, 인덱스 차이만큼 answer 배열에 넣기
  * 다음 값보다 크다면, break
* 배열의 마지막 값까지 비교했는데 아직 stack에 남아있는 price들이 있다면,
  * (n-1)과 해당 인덱스 차이만큼 answer 배열에 넣기

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 스택을 선언하고, 첫번째 price의 인덱스를 넣어줌.
```java
Stack<Integer> stack = new Stack<Integer>();
stack.push(0);
```

##### 2. 마지막 인덱스까지 반복하면서 체크
```java
for(int i=1;i<n;i++) {
  int current_price = prices[i];
  while(!stack.isEmpty()) {
    // stack의 head
    int temp_idx = stack.peek();
    int temp_price = prices[temp_idx];

    // stack의 head 값이 현재 인덱스의 price보다 작다면
    if(temp_price > current_price) {
      answer[temp_idx] = i - temp_idx;  // 인덱스차이만큼 answer배열에 넣어주고
      stack.pop(); // 해당 값은 삭제
    }else break;  // stack의 head 값이 현재 인덱스의 price보다 크다면 break
  }
  stack.push(i);  // 현재 인덱스를 stack에 넣어줌
}
```

##### 3. 마지막 인덱스까지 체크했는데 아직 스택에 값이 남아있다면, 마지막 인덱스 - 해당 인덱스만큼을 answer 배열에 넣어줌 (가격이 떨어진 경우가 없기 때문에...)
```java
if(stack.size() != 0) {
  while(!stack.isEmpty()) {
    int idx = stack.pop();
    answer[idx] = (n-1) - idx;
  }
}
```
<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점





<br>
<br>
<br>
