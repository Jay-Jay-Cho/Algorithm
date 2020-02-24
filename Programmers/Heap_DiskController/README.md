

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42627)<br>
[참고 자료](https://developerdk.tistory.com/22)

<br>
<br>
<br>

## 설명
* Level: 3
* Heap (PriorityQueue)
* OS에서 지원하는 작업 스케쥴링처럼, SJF(Shortest Job First)를 Heap으로 구현하기...


<br>
<br>
<br>

## 접근법
(실수) 처음에는 Comparator를 활용해서, (1)입력시간 오름차순(먼저 온 순서대로), (2)입력시간이 같다면 workTime이 짧은 순으로 정렬 후에 풀려고했다. 그런데 정렬을 이렇게 해버리면, 테스트케이스는 통과하지만 나머지 모든 경우에서 에러가 발생했다. 아마 이후 조건문에서 에러가 발생했을듯. 그래서 방법은 이전에 풀었던 라면공장처럼 들어온 순서대로가 아니라 <br>
1) workTime이 짧은 순서대로 먼저 정렬하고, 그 다음
2) 들어온 순서대로 정렬<br>
하는 것이다. 이렇게 되면 이후 조건탐색에서 index가 작은 것부터 차례대로 비교할 수 있으니까. <br>

즉 정리하자면, <br>
1) workTime이 짧고 and 그 다음 먼저 온 순서대로 정렬하고, then jobs배열에 있는 들을 우선순위큐에 넣는다.
```JAVA
PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
  @Override
  public int compare(int[] o1, int[] o2) {
    if(o1[1]<o2[1]) return -1;
    else if(o1[1]==o2[1]) {
      if(o1[0]<o2[0] || o1[0]==o2[0]) return -1;
      else return 1;
    }else return 1;
  }
});

for(int i=0;i<jobs.length;i++) {
  q.offer(jobs[i]);
}
```
2) 우선순위큐는 index를 활용한 조건탐색이 불가능하기에 이렇게 정렬된 아이템들을 다시 ArrayList에 넣는다. 
```JAVA
ArrayList<int[]> list = new ArrayList<int[]>();
for(int i=0;i<jobs.length;i++) {
  list.add(q.poll());
}
```
3) list에 있는 모든 작업들을 처리할때까지(처리->제거) 반복한다.
```JAVA
while(!list.isEmpty()) {
			
	for(int i=0;i<list.size();i++) {
		if(time>=list.get(i)[0]) {  //만약 뽑힌 작업의 시작시간이 현재시간(time)보다 이르거나 같다면, 해당 작업을 수행한다
			int[] temp = list.get(i);
			int startTime = temp[0];
			int workTime = temp[1];
			answer += workTime+time-startTime;  //총 작업시간 업데이트: 누적된 작업시간 + 현재 작업시간 + 기다린 시간(=현재시간-원래 시작시간)
			time += workTime; //현재시간 업데이트: 현재시간 + 현재 작업시간
			list.remove(i); //작업 처리가 끝났으므로, 작업을 지운다.
			break; //작업은 하나씩만 처리하므로, 작업 하나가 끝난 후 break
		}
		if(i==list.size()-1) time++;
	}
}
```
4) 여기서 가장 이해가 안갔던것은 바로 가장 밑에 있는 코드다.
```JAVA
if(i==list.size()-1) time++;
```
그냥 time++이 아니라, 왜 굳이 조건을 추가로 걸어줘야했을까??? 이유는 아래와 같다.
```JAVA
int[][] jobs = {{0, 3},{1, 9},{500, 6}};
```
위와 같은 경우, 첫번째 작업을 처리하고 for문은 break되고 다시 index는 0부터 시작한다. 그런데 정렬은 우선적으로 workTime이 짧은 작업이 우선순위를 갖기 때문에 [1,9] 보다 [500,6]이 먼저 저장돼있다. 여기서 만약 조건을 안 걸어주고 time++만 하게되면, 작업시간에 오차가 발생한다. 
1) 첫번째 작업[0,3]이 끝난 시각은 3
2) 두번째 작업[500,6]의 시작시간은 현재 시각보다 크므로, 조건없이 time++을 하게되면 시각은 4가 된다.
3) 세번째 작업[1,9]은 조건에 부합하기 때문에 작업을 시작하지만, 위에서 발생한 1초의 시간오차만큼 차이가 생기게된다. 즉, 원래는 3+9=12초에 끝나야 하지만, 4+9=13초에 끝난다.<br>
요약하자면, 일단은 time++없이 list를 탐색해야하므로, list의 끝까지 탐색을 해보고 그때가서 없으면 time++을 한다는 것이다. 

<br>
<br>
<br>

## 유용한 함수 혹은 API


<br>
<br>
<br>

## 숙지해야할 점
1) 최댓값 혹은 최솟값만을 바로바로 뽑아서 비교할 수 없는 경우에는, 우선순위큐로 정렬한 후에 ArrayList같이 index를 통해 직접적으로 조작할 수 있는 자료구조에 담은 후에 처리한다.
2) 라면공장문제ㅇ

2) 라면


<br>
<br>
<br>
