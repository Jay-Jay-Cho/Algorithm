

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
예를들어,
```JAVA
int stock = 3;
int[] dates = {1,2,3,4,5};
int[] supplies = {1,1,9999,1,1};
int k = 6;
```
라고 하자. <br>
그냥 for문으로 풀게되면, 1일/2일/3일 모두 공급을 받게되니 return값은 3이된다. 그러나 사실 최소횟수를 구하려면 1일/2일은 공급받지않고, 3일째만 공급을 받아 return값은 1이된다. 즉, Max Heap으로 접근해야 한다는 뜻!!!! 어떻게? 아래처럼.
<br>
<br>

1) 첫째날부터 k번째날까지 for문으로 돌리면서
```JAVA
for(int day=0;day<k;day++)
```
2) 만약 해당 날짜에 공급을 받을 수 있다면, Max Heap에 일단 넣는다. (공급 X)
```JAVA
if(idx<dates.length && day==dates[idx]) {
  q.offer(supplies[idx]);
  idx++;
}
```
3) stock이 0이라면 저장해놓은 Max Heap에서 최댓값을 공급(stock+=q.poll())받고 answer++, 아니라면 그냥 넘어간다.
```JAVA
if(stock==0) {
  answer++;
  stock+=q.poll();
}
```
4) 하루가 지났으므로, stock--
```JAVA
stock--;
```


<br>
<br>
<br>

## 유용한 함수 혹은 API
* 내림차순 정렬: Collections.reverseOrder()
```JAVA
PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());
```

<br>
<br>
<br>

## 숙지해야할 점
1) stock--를 for문 시작할 때 vs. 끝날 때 고민을 했는데, **끝날 때** 해주는 게 맞다. 왜냐하면, 쓰기도 전에 차감을 해주면 null pointer exception이 뜬다.
2) i를 기준으로 for문을 돌면서, dates[] 배열의 인덱스는 어떻게 다뤄야하나 고민을 했는데, 조건을 2개 주어줘야 했다.
```JAVA
if(idx<dates.length && day==dates[idx]) {
  q.offer(supplies[idx]);
  idx++;
}
```
즉, dates[idx]가 day임과 동시에, **배열 길이보다 작아야 할것**...!!
또한, if의 조건문이 2개라면 먼저 명시된 조건이 통과가 돼야 두번째 조건을 비교한다. 


<br>
<br>
<br>
