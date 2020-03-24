

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42576)
<br>
<br>
<br>

## 설명
* Level: 1
* Hash
* participant 배열과 completion 배열을 비교해서 없는 문자열(완주하지 못한 선수) 찾기.


<br>
<br>
<br>

## 접근법
1) 맨 처음에는 이중for문을 생각했는데, 효율성(time complexity)부분에서 걸려서 out...
2) 두 배열 모두 정렬한 후에, for문을 동시에 돌리면서 체크. 동일한 인덱스에 다른 값이 있다면, participant 해당 인덱스가 완주하지 못한 것. for문을 한번만 돌기 때문에 시간복잡도는 O(n)
3) completion배열 끝까지 다 갔는데 조건이 만족하지 않다면, participant의 마지막 인덱스 문자열이 완주하지 못한 것.

<br>
<br>
<br>

## 유용한 함수
* 배열 정렬(알파벳 사전 순서대로): Arrays.sort(participant) 혹은 Arrays.parallelSort(participant)

<br>
<br>
<br>

## 숙지해야할 점
1) 자바에서 제공하는 Arrays.sort()는 내부적으로 Primitive Type의 경우 Dual-Pivot Quick Sort(피봇이 2개!!!!), non-primitive type의 경우 Tim Sort(Merge Sort+Insertion Sort)를 사용. Tim Sort의 경우, 최악의 시간복잡도는 O(nlogn)
[참고자료](https://defacto-standard.tistory.com/38)
2) 갯수가 적을 때는 sort, 갯수가 많을 때는 parallelSort 사용
3) 일단 문자열 문제가 나오면, 정렬 여부부터 확인!!!!

<br>
<br>
<br>
