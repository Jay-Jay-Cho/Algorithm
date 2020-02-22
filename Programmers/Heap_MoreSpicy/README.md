

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42626)
<br>
<br>
<br>

## 설명
* Level: 2
* Heap
* 주어진 scoville 배열 안의 모든 스코빌 지수가 k를 넘도록 만드는....


<br>
<br>
<br>

## 접근법
1) 일단은 scoville 배열을 PriorityQueue안에 넣어서 자동으로 오름차순(Min Heap) 정렬. 
2) 배열 길이가 1 or 2일때의 경우를 제외하고,
3) 첫번째 뽑는 음식이 k 이상일 때까지 반복문 돌리기.
3-1) 첫번째 뽑은 음식이 k 이상일 때는, 바로 cnt 반환
3-2) 첫번째 뽑은 음식이 k 미만인데, 더 이상 뽑을 음식이 없을 때 = 더 이상 k이상인 음식을 만들지 못함. -1 반환.

<br>
<br>
<br>

## 유용한 함수 혹은 API
* Priority Queue: 굳이 collections.sort를 하지 않아도, 기본적으로 Min Heap을 지원하기 때문에 자동으로 오름차순 정렬.
* 만약 Priority Queue를 내림차순 정렬하고 싶다면, Collections.reverseOrder() 활용.
```JAVA
PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
```

<br>
<br>
<br>

## 숙지해야할 점
1) 문제에서 최댓값 혹은 최솟값을 계속 확인해야 한다면, HashMap 보다는 Heap을 활용하자!!! 왜냐하면, Heap 같은 경우는 기본적으로 큐이기 때문에 offer / poll 메소드를 통해 자동으로 사이즈가 조절되지만, HashMap의 경우 삽입/삭제/탐색이 O(1)이어도 공간복잡도가 안좋기 때문에????
2) while문이 끝나고 -1을 반환하면 예외케이스가 잡힐 줄 알았는데 아니었음.
```JAVA
int first = q.poll();
if(first >= k) return cnt;
else if(first < k && q.size()==0) return -1;
```
마지막이 아니라, 첫번째 음식을 뽑을 때마다 체크를 해줬어야함. 왜냐!!! while문의 탈출조건이 !q.isEmpty()이기 때문에, 결과적으로 k 미만이더라도 반복문은 끝나게 되고, -1이 아니라 cnt를 반환하기 때문에 마지막줄 -1로 갈 수 없는거였음...
<br>
<br>
<br>
