# [크레인 인형뽑기 게임](https://programmers.co.kr/learn/courses/30/lessons/64061)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {

  static Stack<Integer> result_stack;
	static HashMap<Integer,Stack<Doll>> stack_map;

	static class Doll{
		int x;
		int y;
		int type;
		Doll(int x, int y, int type){
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int doll_cnt = 0;

        stack_map = new HashMap<Integer,Stack<Doll>>();
        int n = board.length;
        for(int i=n-1;i>=0;i--) {
        	for(int j=n-1;j>=0;j--) {
        		if(board[i][j]!=0) {
        			if(stack_map.get(j)==null) {
        				Stack<Doll> stack = new Stack<Doll>();
	        			stack.push(new Doll(i,j,board[i][j]));
	        			stack_map.put(j, stack);
        			}else {
        				stack_map.get(j).push(new Doll(i,j,board[i][j]));
        			}
        			doll_cnt++;
        		}
        	}
        }

        boolean flag = true;
        if(doll_cnt==0) flag=false;

        result_stack = new Stack<Integer>();
        if(flag) {
        	for(int i=0;i<moves.length;i++) {
	        	int col=moves[i]-1;
	        	if(stack_map.get(col).isEmpty()) continue;
	        	else {
	        		Doll doll = stack_map.get(col).pop();
	        		board[doll.x][doll.y] = 0;
	        		if(!result_stack.isEmpty() && doll.type==result_stack.peek()) {
	        			answer += 2;
	        			result_stack.pop();
	        		}
	        		else result_stack.push(doll.type);
	        	}
	        }
        }

        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* 크레인을 모두 작동시킨 후 터트려져 사라진 인형의 개수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 변수 자료구조 선언
* `Stack<Integer> result_stack` : 뽑은 인형들의 타입(`int`)을 저장할 스택
  * 크레인으로 뽑은 인형 타입과 stack의 가장 윗 부분에 있는 타입이 같다면 pop
* `HashMap<Integer,Stack<Doll>> stack_map` : 열(col)별 스택을 담을 hashmap
* `Stack<Doll> stack` : 각 열(col)에 있는 인형들을 담을 스택
#### 2. 각 열 별 인형들을 stack에 담기
#### 3. 작동 반복문을 돌면서 갱신

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 각 열 별 인형들을 stack에 담기
* 바닥(n-1)부터 탐색
* 만약, 인형이 한 개도 없다면 반복문을 돌지않고 바로 0 반환
```java
for(int i=n-1;i>=0;i--) {
  for(int j=n-1;j>=0;j--) {
    if(board[i][j]!=0) { //인형이 있을 때만 담기
      if(stack_map.get(j)==null) {
        Stack<Doll> stack = new Stack<Doll>();
        stack.push(new Doll(i,j,board[i][j]));
        stack_map.put(j, stack);
      }else {
        stack_map.get(j).push(new Doll(i,j,board[i][j]));
      }
      doll_cnt++;
    }
  }
}
```

#### 2. 만약, 인형이 한 개도 없다면 반복문을 돌지않고 바로 0 반환
```java
boolean flag = true;
if(doll_cnt==0) flag=false;
```

#### 3. 크레인 작동 횟수만큼 반복문
```java
if(flag) {
  for(int i=0;i<moves.length;i++) {
    int col=moves[i]-1;
    // 해당 열에 인형이 없다면, 다음 작동으로 넘어감
    if(stack_map.get(col).isEmpty()) continue;
    else {
      // 열의 가장 위에 있는 인형을 뽑음
      Doll doll = stack_map.get(col).pop();
      board[doll.x][doll.y] = 0; // 인형의 위치는 뽑혔으므로, 0으로 갱신
      // 만약 뽑힌 인형의 타입과 바구니 가장 위에 있는 인형의 타입이 같다면
      if(!result_stack.isEmpty() && doll.type==result_stack.peek()) {
        answer += 2;  // 사라진 2개만큼 answer++
        result_stack.pop(); // 바구니 위에 있는 인형 타입을 제거
      }
      // 타입이 같지 않다면, 바구니에 뽑힌 인형의 타입을 추가
      else result_stack.push(doll.type);
    }
  }
}
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 열 별 인형을 쌓을 때, 위에서부터 탐색했기 때문에 stack에 값이 잘못들어감
* 인형을 위에서부터가 아니라, 바닥(`n-1`)에서부터 stack에 추가
```java
// 실수
for(int i=0;i<n;i++) {
  for(int j=0;j<n;j++) {
    ...
    stack_map.get(j).push(new Doll(i,j,board[i][j]));
    }
  }
}

// 정답
for(int i=n-1;i>=0;i--) {
  for(int j=n-1;j>=0;j--) {
    ...
    stack_map.get(j).push(new Doll(i,j,board[i][j]));
    }
  }
}
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
