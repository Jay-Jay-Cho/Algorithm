

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
3-1) 첫번째 

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
