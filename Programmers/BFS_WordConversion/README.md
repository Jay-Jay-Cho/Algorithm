

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
3) 다음 노드를 큐에 넣을 때에 비교를 통해 해당 조건에 만족하는 단어들만 넣음. 즉, 한번도 방문하지 않았던 노드임과 동시에 다음 노드의 word와 현재 노드의 word의 글자수 차이가 1개 이하일 때만 큐에 넣음(isNext함수를 통해). 왜냐하면 무조건 한 글자만 변환이 가능하니까.
```java
for(int i=0;i<arr_length;i++) {
	if(!visited[i] && isNext(temp.value,words[i],target_length)) {
		q.add(new Node(words[i],temp.count+1));
		visited[i] = true;
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
1) 문자열 비교 처리 함수 숙지해야할 것. (반복문을 활용한 charAt 등)
2) 무한 사이클 방지를 위해 flag(boolean[] visited)를 활용할 것.

<br>
<br>
<br>
