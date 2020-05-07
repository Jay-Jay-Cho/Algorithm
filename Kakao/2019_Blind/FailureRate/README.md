# [실패율](https://programmers.co.kr/learn/courses/30/lessons/42889)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static class Node{
		int stage;
		double rate;
		Node(int stage, double rate){
			this.stage = stage;
			this.rate = rate;
		}
	}

    public int[] solution(int N, int[] stages) {
		int[] answer = new int[N];

		int[] people = new int[N+1];
    for(int i=0;i<stages.length;i++) {
			if(stages[i]==N+1) continue;
			people[stages[i]]++;
		}

		double[] rate_arr  = new double[N+1];
		int total = stages.length;
		for(int i=1;i<=N;i++) {
			if(people[i]==0) rate_arr[i]=0;
			else {
				double rate = (double)people[i]/total;
				rate_arr[i] = rate;
				total -= people[i];
			}
		}

		ArrayList<Node> list = new ArrayList<Node>();
    for(int i=1;i<=N;i++) list.add(new Node(i,rate_arr[i]));
    Collections.sort(list, new Comparator<Node>() {
    	public int compare(Node n1, Node n2) {
    		if(n1.rate > n2.rate) return -1;
    		else if(n1.rate == n2.rate) {
    			if(n1.stage < n2.stage) return -1;
    			else return 1;
    		}else return 1;
    	}
    });
    for(int i=0;i<list.size();i++) {
    	answer[i] = list.get(i).stage;
    }

		return answer;
	}
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return
  * `실패율` = `스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수` / `스테이지에 도달한 플레이어 수`

<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 해당 스테이지에 현재 머물고있는 사람들의 수를 배열에 저장
#### 2. 해당 배열의 처음부터 끝까지 순회하며, 실패율을 구해서 다른 배열에 저장
#### 3. 정렬


<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 스테이지별 머물고 있는 사람들의 수를 저장
```java
int[] people = new int[N+1];
for(int i=0;i<stages.length;i++) {
  if(stages[i]==N+1) continue; // 모든 스테이지를 클리어한 사람의 경우 건너뜀.
  people[stages[i]]++;
}
```

#### 2. people 배열을 처음부터 끝까지 순회하며, 실패율을 구해서 다른 배열(`rate_arr`)에 저장
```java
double[] rate_arr  = new double[N+1]; // stage는 1부터 시작
int total = stages.length; // 전체 사람의 수
for(int i=1;i<=N;i++) {
  if(people[i]==0) rate_arr[i]=0;
  else {
    double rate = (double)people[i]/total;
    rate_arr[i] = rate;
    total -= people[i]; // 해당 스테이지에 있는 사람의 수만큼 총 사람의 수에서 감소
  }
}
```
#### 3. 정렬 후 반환
```java
ArrayList<Node> list = new ArrayList<Node>();
for(int i=1;i<=N;i++) list.add(new Node(i,rate_arr[i]));
Collections.sort(list, new Comparator<Node>() {
  public int compare(Node n1, Node n2) {
    if(n1.rate > n2.rate) return -1;
    else if(n1.rate == n2.rate) {
      if(n1.stage < n2.stage) return -1;
      else return 1;
    }else return 1;
  }
});

for(int i=0;i<list.size();i++) {
  answer[i] = list.get(i).stage;
}

return answer;
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 로직을 너무 어렵게 생각했었다. 스테이지별 사람 수와 해당 스테이지의 실패율을 한 번에 구하려니 오류가 발생해서 그냥 따로 따로 수행.
* 어차피 시간복잡도는 2N이기 때문에 딱히 상관이 없을 것 같긴 하다..

#### 2. 실패율 구할 시, double 캐스팅 실수해서 0.0이 들어감
```java
int people[i];
int total;

// 실수 --> 이미 int값 끼리 계산이 된 0이 double로 캐스팅 되기 때문에 0.0이 들어감
double rate = (double)(people[i]/total);
// 정답
double rate = (double)people[i]/total;
double rate = (double)people[i]/(double)total;
```
<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
