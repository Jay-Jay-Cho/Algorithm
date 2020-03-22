# [저울](https://programmers.co.kr/learn/courses/30/lessons/42886)
* **참고자료** : 엄슴..

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.Arrays;
class Solution {
    public int solution(int[] weight) {
        int answer = 0;

        Arrays.sort(weight);

        int max = weight[0];

        for(int i=1;i<weight.length;i++) {
        	int curr = weight[i];
        	if(curr == max+1 || curr <= max) {
        		max += curr;
        	}else {
        		break;
        	}
            answer = max+1;
        }

        return answer;
    }
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* Greedy
* 주어진 추들로(`weight[]`) 측정할 수 없는 양의 정수 무게 중 최솟값을 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1. 처음에는 이전 배열의 아이템들을 현재 값과 전부 더해서 차례차례 비교하려고 했으나, 시간복잡도가 기하급수적으로 커지기 때문에 포기.

##### 2. 굳이 모든 아이템들을 더하지 않아도, 이전 배열까지 만들 수 있는 최댓값과 현재 인덱스의 값을 통해 조합할 수 있는 정수가 추출가능하다.
* 예를들어,
  * {1,1,3} 이면
  * 0번째 인덱스는 1
  * 1번째 인덱스는 이전 배열의 합인 1과, 현재 배열의 값인 1을 더해 최대 2까지 조합 가능
  * 2번째 인덱스는 이전 배열의 합인 2와, 현재 배열의 값인 3을 더해 최대 5까지 조합 가능

<br><br>

## &#10095;&#10095;&#10095; 풀이
##### 1. 작은 값부터 차례대로 비교하기 위해, 일단 주어진 weight 배열을 오름차순 정렬
```java
Arrays.sort(weight);
```

##### 2. 만들 수 있는 가장 작은 값은 정렬된 배열의 첫번째 값
```java
int max = weight[0];
```

##### 3. 2번째 인덱스부터 시작하여, 조합할 수 없는 정수를 만나면 answer로 리턴
```java
for(int i=1;i<weight.length;i++) {
  int curr = weight[i];

  // 현재 값이 이전 배열의 합 바로 다음 정수이거나, 그 값보다 작다면
  // 이전 배열의 합 + 현재 값만큼 조합이 가능하므로
  // 이전 max값에 현재 값을 더하여 갱신
  if(curr == max+1 || curr <= max) {
    max += curr;
  }

  // 그렇지 않다면, 더 이상 정수를 조합할 수 없으므로 반복문을 끝냄.
  else {
    break;
  }

  // 매 반복문이 끝날 때마다, 지금까지 만들 수 있는 최댓값 + 1 로 answer를 갱신
  answer = max+1;
}
```

##### 4. answer 반환
```java
return answer;
```
<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 배열이 끝까지 도달하기 전에 break 되는 경우의 수만을 생각했다. 정상적으로 인덱스 끝까지 탐색이 끝났을 경우에도 정상적으로 값을 반환할 수 있도록 신경써야함..
```java
// 실수
for(int i=1;i<weight.length;i++) {
  int curr = weight[i];
  if(curr == max+1 || curr <= max) {
    max += curr;
  }else {
    answer = max+1;
    break;
  }
}

// 정답
for(int i=1;i<weight.length;i++) {
  int curr = weight[i];
  if(curr == max+1 || curr <= max) {
    max += curr;
  }else {
    break;
  }
    answer = max+1;
}

```


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
##### 1. 반복문 설계시, 정상종료 경우와 에러발생 경우 모두를 고려해야함.
* 정상종료 : 배열의 끝까지 모두 탐색
* 에러발생 : 배열 중간에 break




<br>
<br>
<br>
