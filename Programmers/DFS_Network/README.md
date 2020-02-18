

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43162)
[참고 자료](https://saltae.tistory.com/12)
<br>
<br>
<br>

## 설명
* Level: 3
* DFS
* 주어진 인접행렬(computers)을 활용해서 총 네트워크수를 구하는 문제.


<br>
<br>
<br>

## 접근법
1) 직접이든 간접이든 연결된 컴퓨터들은 모두 간선으로 연결이 가능하기 때문에, DFS를 활용.
2) 인접행렬이 직접 주어졌기 때문에 곧바로 탐색에 활용이 가능하나, 반복 사이클을 방지하기 위해 visited 배열을 생성 후 활용. 굳이 2차원 배열이 아니어도, 해당 노드를 방문했는지만 체크하는 것이기 때문에 1차원 배열로 선언.
```java
boolean[] visited = new boolean[n];
```

3) 처음 시작은 0번째 컴퓨터부터. 새로운 네트워크를 구성하는 것이기 때문에 answer++. 또한 방문체크.
```java
Stack<Integer> stack = new Stack<>();
stack.push(0);
visited[0]=true;
answer++;
```

4) 컴퓨터의 숫자만큼 반복문을 돌면서 stack에 push. 
이미 방문한 노드라면 넘어가고, 새로운 노드라면 새로운 네트워크를 구성하기 때문에 answer++. 
전처리 작업이 끝난 이후에 DFS탐색 시작.
DFS탐색에서는 기존 문제들처럼 연결된 간선들을 조건에 맞게 체크하고 stack에 push.

```java
for(int i=0;i<n;i++) {	
  if(!visited[i]) {
    stack.push(i);
    visited[i]=true;
    answer++;
  }

  //탐색 시작 
  while(!stack.isEmpty()) {
    int temp = stack.pop();
    for(int j=0;j<n;j++) {
      if(temp==j) continue;
      if(computers[temp][j]==1 && !visited[j]) {
        stack.push(j);
        visited[j]=true;
      }
    }
  }
}
```


<br>
<br>
<br>

## 유용한 함수
* 해당 배열에 특정 문자열이 포함됐는지 확인: Arrays.asList(words).contains(target)

<br>
<br>
<br>

## 숙지해야할 점
1) 처음에는 Leaf 노드만 세면 되겠다 생각했는데 실수였음. Leaf 노드가 여러 개여도 같은 네트워크에 포함될 수도 있기 때문에. 따라서 Leaf 노드가 아니라 처음 stack에 들어갈 때 answer(네트워크 수)++
2) 사용해야하는 반복문이 여러 개일때, 탐색(while 부분)을 어느 라인부터 시작해야할지 잘 생각해야함.

<br>
<br>
<br>
