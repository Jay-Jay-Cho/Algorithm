

### &#128526;
[문제 링크](https://www.acmicpc.net/problem/1987)

<br>
<br>
<br>

## 설명
* 정답률: 31%
* Back Tracking (백트래킹)
* 2차원 String 배열에서 중복되지 않는 가장 긴 길이의 경로를 찾기 (경로길이 출력)


<br>
<br>
<br>

## 접근법
(실수) Stack을 통해서 접근을 했었으나, 계속 계속해서 위아래로 오고가야하는 백트래킹 문제에서는 **Stack보다 일반 재귀함수를 활용**하는 편으로 바꿈.<br>


1) 일단 첫번째 시작점 (arr[0][0])을 방문 & 비교를 위한 list<String>에 추가 후, 재귀를 위한 dfs메소드 호출
```java
// main
list.add(arr[0][0]);
visited[0][0] = true;
getAnswer(arr,visited,0,0,1);
```

2) 시작점을 기준으로 상/하/좌/우를 돌며 계속해서 유망한(Promising) 자식들을 탐색.
```java
for(int i=0;i<4;i++) {
  	int xx = x+dx[i];  
  	int	yy = y+dy[i];

    // xx,yy 좌표가 유효하고 && 방문한 적이 없으며 && 유효한 알파벳이면
  	if(xx>=0 && xx<r && yy>=0 && yy<c && !visited[xx][yy] && !list.contains(arr[xx][yy])) {
  		list.add(arr[xx][yy]);  // 해당 자식을 리스트에 추가하고,
  		visited[xx][yy] = true; // 해당 자식을 방문했다고 표시
  		getAnswer(arr,visited,xx,yy,cnt+1); // 재귀호출:자식 하나가 추가됐으므로 cnt+1
  		list.remove(arr[xx][yy]); // 백트래킹(1) 리스트에서 제거
  		visited[xx][yy] = false;  // 백트래킹(2) 방문 초기화
  	}
}
```
여기서 이해하기 어려웠던 것이 바로 가장 밑에 있는 백트래킹 코드다.... 
일단 직관적으로 해석하자면, 어찌됐든 재귀함수이기 때문에 자식이 함수에 들어갈때까지는 유효하지만, 해당 자식으로부터 비롯되는 모든 경로를 탐색한 이후에는 다시 부모 노드로 거슬러올라가
다음 경우를 찾아야하므로 초기화를 시켜줘야 한다는 것 같다.... **확실히 트리 그림**을 그려가면서 해야 이해가 잘 되는듯...



<br>
<br>
<br>

## 유용한 함수 혹은 API
* Char 변수를 String 변수로 바꾸는 법: String.valueOf(char a)
```JAVA
String.valueOf(num.charAt(i))
```

<br>
<br>
<br>

## 숙지해야할 점
1) 재귀를 활용해야 하는 문제에서는, 처음 케이스부터 재귀를 돌리는 것 보다는 main함수에서 첫번째 케이스를 처리하고 다음 케이스부터 재귀를 돌려야 할 듯 싶음.

```JAVA
// main함수
list.add(arr[0][0]);
visited[0][0] = true;
getAnswer(arr,visited,0,0,1);
```



<br>
<br>
<br>
