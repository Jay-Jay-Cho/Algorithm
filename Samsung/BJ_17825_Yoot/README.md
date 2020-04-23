# [주사위 윷놀이](https://www.acmicpc.net/problem/17825)
* **참고자료** : https://velog.io/@hyeon930/BOJ-17825-%EC%A3%BC%EC%82%AC%EC%9C%84-%EC%9C%B7%EB%86%80%EC%9D%B4-Java

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
package samsung;
import java.io.*;
import java.util.*;
public class BJ_17825_Yoot {

	static Node start;
	static Node[] yoot_arr;
	static int answer;
	static int[] dice;

	static class Node{
		int value;
		boolean isLocated;
		boolean isEnd;
		Node next;
		Node blue;

		Node(int value){
			this.value = value;
			isLocated = false;
			isEnd = false;
			next = null;
			blue = null;
		}

		Node addNext(int value) {
			Node nextNode = new Node(value);
			this.next = nextNode;
			return this.next;
		}

		static Node getNode(int value) {
			Node temp = start;
			while(true) {
				if(temp==null) return null;
				if(temp.value==value) return temp;
				temp = temp.next;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		dice = new int[10];
		for(int i=0;i<10;i++) {
			dice[i] = Integer.parseInt(input[i]);
		}


		init();
		answer = Integer.MIN_VALUE;
		dfs(0,0);

		System.out.println(answer);
	}

	static void dfs(int depth, int score) {
		if(depth == 10) {
			answer = Math.max(answer, score);
			return;
		}

		for(int i=0;i<4;i++) {
			int move = dice[depth];
			Node current = yoot_arr[i];


			Node temp = current;
			for(int j=0;j<move;j++) {
				if(j==0 && temp.blue!=null) temp = temp.blue;
				else {
					temp = temp.next;
				}
			}
			if(temp.isLocated && !temp.isEnd) {
				continue;
			}else {
				current.isLocated = false;
				temp.isLocated = true;
				score += temp.value;
				yoot_arr[i] = temp;

				dfs(depth+1,score);

				yoot_arr[i] = current;
				score -= temp.value;
				temp.isLocated = false;
				current.isLocated = true;
			}


		}
	}

	static void init() {
		start = new Node(0);
		Node end = null;
		Node center = new Node(25);

		yoot_arr = new Node[4];
		for(int i=0;i<4;i++) {
			yoot_arr[i] = start;
		}

		Node temp = start;
		for(int i=2;i<=40;i+=2) {
			temp = temp.addNext(i);
		}

		// end
		end = temp.addNext(0); // mistake
		end.next = end;
		end.isEnd = true;

		// first blue
		Node n = Node.getNode(10);
		n = n.blue = new Node(13);
		n = n.addNext(16);
		n = n.addNext(19);
		n.next = center;

		// second blue
		Node n2 = Node.getNode(20);
		n2 = n2.blue = new Node(22);
		n2 = n2.addNext(24);
		n2.next = center;

		// third blue
		Node n3 = Node.getNode(30);
		n3 = n3.blue = new Node(28);
		n3 = n3.addNext(27);
		n3 = n3.addNext(26);
		n3.next = center;

		// center
		center = center.addNext(30);
		center = center.addNext(35);
		center.next = Node.getNode(40);
	}

}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* 정답률 : 32%
* DFS(백트래킹) & 시뮬레이션
* 주사위에서 나올 수 10개를 미리 알고 있을 때, 얻을 수 있는 점수의 **최댓값**
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1. 윷판을 LinkedList로 표현
* 라이브러리 활용이 아니라, 직접 구현하여 사용

##### 2. 백트래킹 활용해서, 경우의 수 구하기
* 도착지점이 아닌데도, 해당 지점에 이미 말이 있는 경우는 skip (`continue`)
* 예외 경우가 아니라면, 백트래킹


<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. LinkedList를 구성할 Node 선언
```java
static class Node{
  int value;  // 점수
  boolean isLocated;  // 해당 노드에 말이 있는지
  boolean isEnd;  // 도착 지점인지
  Node next;
  Node blue;  // 경유 경로

  Node(int value){
    this.value = value;
    isLocated = false;
    isEnd = false;
    next = null;
    blue = null;
  }

  Node addNext(int value) {
    Node nextNode = new Node(value);
    this.next = nextNode;
    return this.next;
  }

  static Node getNode(int value) {
    Node temp = start;
    while(true) {
      if(temp==null) return null;
      if(temp.value==value) return temp;
      temp = temp.next;
    }
  }
}
```

##### 2. 윷판을 LinkedList로 표현 (초기화)
```java
static void init() {
  start = new Node(0);
  Node end = null;
  Node center = new Node(25);

  yoot_arr = new Node[4];
  for(int i=0;i<4;i++) {
    yoot_arr[i] = start;
  }

  Node temp = start;
  for(int i=2;i<=40;i+=2) {
    temp = temp.addNext(i);
  }

  // end
  end = temp.addNext(0);
  end.next = end;
  end.isEnd = true;

  // first blue
  Node n = Node.getNode(10);
  n = n.blue = new Node(13);
  n = n.addNext(16);
  n = n.addNext(19);
  n.next = center;

  // second blue
  Node n2 = Node.getNode(20);
  n2 = n2.blue = new Node(22);
  n2 = n2.addNext(24);
  n2.next = center;

  // third blue
  Node n3 = Node.getNode(30);
  n3 = n3.blue = new Node(28);
  n3 = n3.addNext(27);
  n3 = n3.addNext(26);
  n3.next = center;

  // center
  center = center.addNext(30);
  center = center.addNext(35);
  center.next = Node.getNode(40);
}
```

##### 3. DFS 진행
```java
static void dfs(int depth, int score) {

  // 주사위 10번을 다 썼으면
  if(depth == 10) {
    answer = Math.max(answer, score); // 갱신
    return;
  }

  // 말 4개를 놓는 모든 경우의 수
  for(int i=0;i<4;i++) {
    int move = dice[depth];
    Node current = yoot_arr[i];

    Node temp = current;
    for(int j=0;j<move;j++) {
      // 경유지가 있으면, 우선순위
      if(j==0 && temp.blue!=null) temp = temp.blue;
      else {
        temp = temp.next;
      }
    }
    // 도착지점이 아닌데, 해당 지점에 말이 있으면 skip
    if(temp.isLocated && !temp.isEnd) {
      continue;
    }else {
      current.isLocated = false;
      temp.isLocated = true;
      score += temp.value;
      yoot_arr[i] = temp;

      dfs(depth+1,score);

      // 백트래킹
      yoot_arr[i] = current;
      score -= temp.value;
      temp.isLocated = false;
      current.isLocated = true;
    }
  }
}
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 도착 노드를 생성할 때, 값으로 -1을 넣어서 score 계산시 오류 발생
* 원래대로라면, 예제 4번의 답이 130이 나와야되는데 -1 * 3 = -3 만큼 오차 발생
```java
// 실수
end = temp.addNext(-1);
end.next = end;
end.isEnd = true;

// 정답
end = temp.addNext(0);
end.next = end;
end.isEnd = true;
```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점





<br>
<br>
<br>
