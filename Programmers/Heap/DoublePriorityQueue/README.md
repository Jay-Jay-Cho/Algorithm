

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42628)<br>

<br>
<br>
<br>

## 설명
* Level: 3
* Heap (PriorityQueue)
* 정해진 command를 따라 실행한 후에, 비어있으면 [0,0] / 비어있지 않으면 [최댓값,최솟값]을 반환하는 이중 우선수위큐를 구현하시오.


<br>
<br>
<br>

## 접근법
음... 다른 Level 3 문제들과는 다르게 생각보다 쉬워서 놀랐다.
맨 처음에는 우선순위큐와 ArrayList 두 개를 만들어서 command마다 삽입/삭제를 해야하나 고민했는데, 이 경우 시간복잡도가 우선순위큐 2개를 돌리는 것보다 안좋을것 같아서 Max_Heap / Min_Heap 우선순위큐 2개를 돌리는 방향으로 진행했다.
<br>
<br>

1) operations 배열 길이만큼 반복하면서
```JAVA
for(int i=0;i<operations.length;i++)
```
2) command를 공백 기준으로 자른다. ( 예시 = [I 6"] --> [I,6] )
```JAVA
String[] cmd = operations[i].split(" ");
```
3) 만약 Insert 명령어면, 우선순위큐 2개에 모두 넣는다.
```JAVA
if(cmd[0].equals("I")) { //insert
  int value = Integer.parseInt(cmd[1]);
  min_heap.offer(value);
  max_heap.offer(value);
}
```
4) 만약 Delete 명령어면, 아래와 같이 진행한다. 
```JAVA
else { //delete
  if(min_heap.isEmpty()) {  // 우선순위큐가 비어있으면, continue를 통해 다음 command 진행
    continue;
  }else {
    if(cmd[1].equals("-1")) { // 최솟값 삭제 명령어라면, 
      int min_value = min_heap.peek();
      max_heap.remove(min_value);
      min_heap.poll();
    }else {	// 최댓값 삭제 명령어라면, 
      int max_value = max_heap.peek();
      min_heap.remove(max_value);
      max_heap.poll();
    }
  }
}
```
5) For문을 돌때마다 index를 체크하여, 마지막 index일 경우에는 케이스에 맞게 각각 값을 반환한다.
```JAVA
if(i==operations.length-1) {
  if(min_heap.isEmpty()) answer =  new int[] {0,0};
  else {
    answer =  new int[] {max_heap.peek(),min_heap.peek()};
  }
}
```
<br>
<br>
<br>

## 유용한 함수 혹은 API
* 우선순위큐에서 특정 값 삭제(index를 통한 삭제는 불가능하니.....)
```JAVA
int min_value = min_heap.peek();
max_heap.remove(min_value);
min_heap.poll();
```

<br>
<br>
<br>

## 숙지해야할 점

<br>
<br>
<br>
