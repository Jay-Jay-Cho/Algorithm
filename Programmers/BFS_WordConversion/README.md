

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43163)

<br>
<br>
<br>

## 설명

* Level: 3
* BFS
* words 배열안의 단어변환을 통해 begin에서 target으로 치환


<br>
<br>
<br>

## 접근법
1) 최소 단계의 과정을 뽑아내는 것이기 때문에 BFS 활용.
2) 다른 문제들과는 달리 입력 값으로 주어진 words 배열을 곧바로 풀이에 활용할 수 없었기 때문에, 인접행렬/인접리스트를 별도로 구성하거나 Node클래스를 생성해서 사용.
```java
class Node{
		int count;  //몇 단계를 거쳤는지 누적
		String value; //실제 word
		Node(String value, int count){
			this.value = value;
			this.count = count;
		}
	}
```
3) 



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
