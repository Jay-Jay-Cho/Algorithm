

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42578)
[참고 자료](https://developerdk.tistory.com/12)

<br>
<br>
<br>

## 설명

* 기본적인 Hash문제...
* 전체 조합의 수를 구해야했기 때문에 DFS를 통해 조합의 수를 구해야되나 했는데, 알고보니 수학공식 하나로 풀림....

* 의상의 종류가 A, B, C 3개일 때 총 조합의 수 = A + B + C + AB + AC + BC + ABC = (A+1)(B+1)(C+1) - 1
(의상+1)에서 **+1** 은 선택하지 않는 경우, 그리고 맨 마지막 **-1** 은 전체 다 선택하지 경우

* 조합의 수를 구하는 방법만 빼고는 쉬웠다.

1) HashMap을 이용, clothes 종류(key)마다 갯수(value)를 입력해서
```JAVA
HashMap<String,Integer> map = new HashMap<>();

for(int i=0;i<clothes.length;i++) {
  if(!map.containsKey(clothes[i][1])) {
    map.put(clothes[i][1], 1);
  }else {
    int temp = map.get(clothes[i][1])+1;
    map.put(clothes[i][1], temp);
  }
}
```

2) 모든 조합을 구하는 공식에 따라 계산
```JAVA
int answer=1;
for(int value : map.values()) {
  answer *= (value+1);
}
```

<br>
<br>
<br>

## 꿀팁
* 모든 조합의 수를 계산하는 공식: (A,B,C,...) = (A+1) * (B+1) * (C+1) * ... - 1

* forEach문을 통한 HashMap의 value추출

```JAVA
    for(int value : map.values()) {
			answer *= (value+1);
		}
```

<br>
<br>
<br>


## 숙지해야할 점
1) 조합의 수를 구하는 건지, 모든 조합의 경우의 수를 나열해야하는건지 구분해야 할듯.
2) 배열탐색에서는 HashMap을 이용하는게 빠르다. ---> 시간복잡도: O(1)

<br>
<br>
<br>
