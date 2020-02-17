

### &#128526;
[문제 링크](https://www.acmicpc.net/problem/1260)
[참고 자료](https://mygumi.tistory.com/102)

<br>
<br>
<br>

## 설명

가장 기본적인 DFS, BFS 문제.
* DFS = Stack or Recursive
* BFS = Queue


<br>
<br>
<br>

## 꿀팁
* Array 전체 초기화 method: Arrays.fill(array_name, value)

<br>
<br>
<br>


## 숙지해야할 점
1) 반복문 내에서의 break와 continue 사용법 숙지. 특히나 DFS 반복문에서 break를 거는 이유는 stack에서 꺼낸 노드의 자식노드로 이동하기 위해서. break를 쓰지 않으면, for문이 계속 돌기 때문에 자식노드가 아닌 인접노드를 stack에 계속 넣음.

<br>
<br>
<br>
