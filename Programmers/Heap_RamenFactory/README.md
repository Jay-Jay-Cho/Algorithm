

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42629)
[참고 자료](https://udud0510.tistory.com/38)

<br>
<br>
<br>

## 설명
* Level: 2
* Heap (PriorityQueue)
* stock(현재 가지고있는 밀가루), dates[](밀가루 공급일), supplies[](밀가루 공급량), k(원상복구일)을 활용해서 밀가루 해외수입을 최소화!!


<br>
<br>
<br>

## 접근법
(실수) 처음에는 굳이 Heap을 써야되나 싶어서, 단순 for문으로 **(stock + supplies[i]) >= k** 구현했다. 즉, 그냥 공급받을 수 있는 밀가루를 0번째부터 다~ 더해서 k보다 커지면 break하도록. 물론 전부 fail......<br>
왜냐!! 문제에서는 최소 횟수를 구해야했기 때문에, 만약에 위처럼 단순 for문으로 풀면 최소 횟수가 아니기 때문.

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
