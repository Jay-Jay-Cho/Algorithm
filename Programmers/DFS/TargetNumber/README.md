

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
* Stack안에 들어갈 node를 별도로 class로 선언후에 사용
* depth: 시작점은 -1이고 자식노드로 이동할때마다 1씩 추가. Leaf node의 depth = numbers.length-1)
* value: 노드를 지날때마다 누적되는 계산값

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

* 아직 leaf node에 도착하지 않았을 때에는, 새로운 node를 생성하여 stack에 삽입
```java
stack.push(new Node(temp.depth+1,temp.value+numbers[temp.depth+1]));
stack.push(new Node(temp.depth+1,temp.value-numbers[temp.depth+1]));
```

<br>
<br>
<br>


## 숙지해야할 점
1) DFS문제인데 입력 값이 인접행렬(2차원 배열)형식이 아닐 경우, node 클래스를 생성하여 접근할 것.
2) depth와 비교할 때는 입력 값의 length에서 1을 빼야 함(케바케겠지만 시작 depth를 -1로 줬고, 새로운 node를 stack에 삽입시 직접 입력 배열의 인덱스로 value를 접근하기 )

<br>
<br>
<br>
