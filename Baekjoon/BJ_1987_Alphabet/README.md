

### &#128526;
[문제 링크](https://www.acmicpc.net/problem/1987) 

<br>
<br>
<br>

## 설명
* 정답률: 31%
* Back Tracking (백트래킹)
* 2차원 String 배열에서 중복되지 않는 가장 긴 길이의 경로를 찾기 (경로길이 출력)


<br>
<br>
<br>

## 접근법
(실수) Stack을 통해서 접근을 했었으나, 계속 계속해서 위아래로 오고가야하는 백트래킹 문제에서는 **Stack보다 일반 재귀함수를 활용**하는 편으로 바꿈.<br>


1) 일단 첫번째 시작점 (arr[0][0])을 방문 & 비교를 위한 list<String>에 추가 후, 재귀를 위한 dfs메소드 호출
```JAVA
// main
list.add(arr[0][0]);
visited[0][0] = true;
getAnswer(arr,visited,0,0,1);
```
	
2) 주어진 입력값이 2차원 배열이기 때문에 상/하/좌/우를 이동할 수 있도록 dx[], dy[] 배열을 
```JAVA
// 볼 판별
static int getBall(String check, String num) {
	int cnt = 0;
	for(int i=0;i<3;i++) {
		// 숫자들은 포함하지만, 같은 자리는 아닐 때만 cnt++
		if(check.contains(String.valueOf(num.charAt(i))) && check.charAt(i)!=num.charAt(i)) {
			cnt++;
		}
	}
	return cnt;
}
```

2) 먼저 후보군이 될 수 있는 숫자들을 모두 생성해놓은 HashMap에 집어넣는다. (굳이 HashMap이 아니라 List도 괜찮을듯?????)
```JAVA
HashMap<String,Boolean> map = new HashMap<String,Boolean>();
    for(int i=1;i<10;i++) {
      for(int j=1;j<10;j++) {
        if(i==j) continue;
        for(int k=1;k<10;k++) {
          if(i==k || j==k) continue;
          String sub = String.valueOf(i).concat(String.valueOf(j)).concat(String.valueOf(k));
          map.put(sub, false);
        }
      }
    }
```


3) forEach문을 통해 keySet에서 key를 하나씩 추출한다.
```JAVA
for(String num:map.keySet())
```


4) 뽑은 key(임의의 숫자)값이 baseball배열의 아이템 수만큼 반복문을 돌며 해당 아이템과 조건이 일치할 때마다 cnt++을 해준다. 
```JAVA
for(int i=0;i<baseball.length;i++) {
  String check = String.valueOf(baseball[i][0]);
  int strike = baseball[i][1];
  int ball = baseball[i][2];
  if(strike==getStrike(check,num) && ball==getBall(check,num)) {
    cnt++;
  }
}
```


5) 즉, 결과적으로 부합하는 조건의 개수(cnt)가 **baseball의 모든 아이템과 조건이 부합할때만!!!**,(==baseball.length) answer++
```JAVA
if(cnt==baseball.length) answer++;
```


<br>
<br>
<br>

## 유용한 함수 혹은 API
* Char 변수를 String 변수로 바꾸는 법: String.valueOf(char a)
```JAVA
String.valueOf(num.charAt(i))
```

<br>
<br>
<br>

## 숙지해야할 점
1) 재귀를 활용해야 하는 문제에서는, 처음 케이스부터 재귀를 돌리는 것 보다는 main함수에서 첫번째 케이스를 처리하고 다음 케이스부터 재귀를 돌려야 할 듯 싶음.

```JAVA
// main함수 
list.add(arr[0][0]);
visited[0][0] = true;
getAnswer(arr,visited,0,0,1);
```



<br>
<br>
<br>
