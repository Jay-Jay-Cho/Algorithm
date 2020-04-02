# [다리를 지나는 트럭](https://programmers.co.kr/learn/courses/30/lessons/42583)
* **참고자료** : 엄슴..

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        int time = 0;
        int entry = 0;
        int temp_weight = 0;
        int complete = 0;
        int end = truck_weights.length-1;

        Queue<Truck> q = new LinkedList<Truck>();

        while(complete < truck_weights.length) {

        	//insert new truck
        	if(entry <= end && time > 0 && temp_weight + truck_weights[entry] <= weight) {
        		Truck truck = new Truck(truck_weights[entry],1); //
        		q.add(truck);
        		temp_weight += truck_weights[entry];
        		entry++;
        	}

        	// remove truck
        	if(time>0) {
        		Truck temp_truck = q.peek();
        		if(temp_truck.time >= bridge_length) {
        			complete++;
        			temp_weight -= temp_truck.weight;
        			answer = time+1; //
        			q.poll();
        		}
        	}

        	// time spend
        	int q_size = q.size();
        	for(int i=0;i<q_size;i++) {
        		Truck temp = q.poll();
        		q.add(new Truck(temp.weight,temp.time+1));
        	}

        	time++;
        }
        return answer;
    }

    static class Truck{
		int weight;
		int time;
		Truck(int w, int t){
			weight = w;
			time = t;
		}
	}
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 2
* Queue & Simulation
* 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### time 사이클마다 실행되는 로직을 3가지로 구분
* 새로운 트럭을 넣기 (단, 한 사이클마다 최대 한 대만 가능)
* 다리를 건넌 트럭 빼기 (마찬가지로, 한 사이클마다 최대 한 대만 가능)
* 그 외 현재 다리 위에 있는 트럭들의 시간 경과를 표시


<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 변수 및 객체 선언
```java
int time = 0; // 사이클마다 증가시킬 시간 변수
int entry = 0;  // 트럭 배열에서 어떤 트럭을 선택할지 선정하는 포인터 변수
int temp_weight = 0;  // 현재 다리 위에 올라와있는 트럭들의 무게의 총합
int complete = 0; // 다리를 지나간 트럭 갯수
int end = truck_weights.length-1; // 트럭 배열에서 마지막 트럭의 인덱스

Queue<Truck> q = new LinkedList<Truck>(); // 트럭을 담을 큐
```

##### 2. 모든 트럭이 지나갈 때 까지 반복
```java
while(complete < truck_weights.length) {

  // 새 트럭 넣기
  if(entry <= end && time > 0 && temp_weight + truck_weights[entry] <= weight) {
    Truck truck = new Truck(truck_weights[entry],1);
    q.add(truck);
    temp_weight += truck_weights[entry];
    entry++;
  }

  // 가장 선두의 트럭 제거
  if(time>0) {
    Truck temp_truck = q.peek();
    if(temp_truck.time >= bridge_length) {
      complete++;
      temp_weight -= temp_truck.weight;
      answer = time+1;
      q.poll();
    }
  }

  // 다리 위 트럭들의 시간경과 표시
  int q_size = q.size();
  for(int i=0;i<q_size;i++) {
    Truck temp = q.poll();
    q.add(new Truck(temp.weight,temp.time+1));
  }

  time++;
}
```


<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 새로운 트럭을 다리 위에 올릴 때의 조건 명시를 제대로 안함
* **트럭 배열의 특정 트럭을 가리키는 포인터(`entry`)가 유효한지**
  * 이 조건을 빼먹어서 `IndexOutofBound`에러가 떴음.
* 0초에는 트럭을 넣을 수 없으므로, 0초 이후
* 무게가 초과하지는 않는지
```java
// 실수
if(time > 0 && temp_weight + truck_weights[entry] <= weight) {
  ...
}

// 정답
if(entry <= end && time > 0 && temp_weight + truck_weights[entry] <= weight) {
  ...
}
```

##### 2. 새로운 트럭 객체가 들어갈 때, 해당 트럭의 시간 초기화를 잘못함
* 시간 초기화를 `time`변수로 해버리면, 트럭의 경과 여부와 상관없이 들어가자마자 큐에서 나가게 됨.
* 시간 초기화를 `0`으로 하면, 원래 경과 시간보다 1초가 더 걸림
  * 예를 들어, 다리 길이가 2라고 하면, 원래는 `1 -> 2 -> out` 이 되야 하는데,
  * `0 -> 1 -> 2 -> out` 이 됨.
```java
// 실수 1
Truck truck = new Truck(truck_weights[entry],time);
q.add(truck);

// 실수 2
Truck truck = new Truck(truck_weights[entry],0);
q.add(truck);

// 정답
Truck truck = new Truck(truck_weights[entry],1);
q.add(truck);
```

##### 3. 마지막 트럭이 지난 후의 경과시간을 뽑을 때, 1을 더해주지 않았음.
* 시작이 0초부터 시작한 것처럼, 모든 트럭이 빠져나간 직후에도 마지막에 1초만큼의 시간 버퍼가 더 필요함
```java
// 실수
if(temp_truck.time >= bridge_length) {
  ...
  answer = time;
}

// 정답
if(temp_truck.time >= bridge_length) {
  ...
  answer = time+1;
}
```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점





<br>
<br>
<br>
