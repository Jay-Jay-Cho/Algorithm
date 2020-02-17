

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43165)

<br>
<br>
<br>

## 설명

* Level: 2
* DFS
* 입력값으로 받는 numbers 배열을 활용해서 target을 완성시키고, 만족하는 경우의 수 만큼 answer++


<br>
<br>
<br>

## 접근법
* numbers배열의 값들이 각각 +numbers[i] 혹은 -numbers[i] 두 개의 경우만을 가지기 때문에 DFS를 활용해서 모든 경우의 수의 끝(leaf node)이 target과 맞는지 체크.
<br>

```java
class Node{
        int depth;
        int value;
        Node(int depth, int value){
            this.depth = depth;
            this.value = value;
        }
    }
```

<br>
<br>
<br>


## 숙지해야할 점
1) 반복문 내에서의 break와 continue 사용법 숙지. 특히나 DFS 반복문에서 break를 거는 이유는 stack에서 꺼낸 노드의 자식노드로 이동하기 위해서. break를 쓰지 않으면, for문이 계속 돌기 때문에 자식노드가 아닌 인접노드를 stack에 계속 넣음.

<br>
<br>
<br>
