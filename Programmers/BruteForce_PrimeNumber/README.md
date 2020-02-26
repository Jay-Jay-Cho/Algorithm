

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42839)
[참고 자료](https://bcp0109.tistory.com/14)

<br>
<br>
<br>

## 설명
* Level: 2
* Brute Force (완전탐색)
* 주어진 문자열로 만들 수 있는 소수의 갯수 찾기


<br>
<br>
<br>

## 접근법
일단, 이 문제를 시작하려면 순열/조합을 구현해야 했기 때문에 이와 관련한 [공부](https://bcp0109.tistory.com/14)가 필요했음..... 


1) 주어진 문자열, 순열트리의 깊이(depth), 몇 개를 뽑을 건지(r)을 입력으로 하는 순열 메소드 작성.
```JAVA
static void permutation(String[] arr, int depth, int r) {
	if(r==depth && !arr[0].equals("0")) {	//depth가 계속 증가해서, 뽑는 갯수(r)와 같아지면 출력.
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<r;i++) sb.append(arr[i]);
		int value = Integer.parseInt(sb.toString());
		if(!list.contains(value))list.add(value);
		return;
	}
	for(int i=depth;i<arr.length;i++) {
		swap(arr,depth,i);	// 인덱스가 depth인 아이템과, i인 아이템을 서로 교환. 고정시킨다고 보면 됨.
		permutation(arr,depth+1,r);	//아이템이 고정된 상태에서, depth+1 후에 다음 재귀단계로 넘어감. 
		swap(arr,depth,i);	// 백트래킹을 위해, 고정된 아이템을 다시 풂.
	}
}
```
여기서 가장 이해가 안됐었던 부분이 바로 마지막 백트래킹을 위한 swap 코드이다. 다시 말하자면 주석에 나와있듯이 swap을 두번 수행함으로써 처음 상태로 되돌린다는 의미로 이해했다. 즉, 만약 __**A**__ BC에서 A는 고정된 상태인 depth 1을 고려해보자. B와 C를 바꾼 이후에(**A**BC --> **AC**B), B와 B를 바꾸려면(물론 값은 바뀌지않지만 알고리즘상) 다시 **A**BC로 돌아가야한다.(**AC**B --> **A**BC --> **AB**C). 트리순회이기때문에, **A**BC를 거치지 않고 **AB**C로 넘어가는 것은 불가능하다. 무조건 백트래킹을 사용해서 이전 단계를 거쳐야 한다.

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
* StringBuilder
```JAVA
String.valueOf(num.charAt(i))
```

<br>
<br>
<br>

## 숙지해야할 점
1) 완전탐색(Brute Force)문제에서는, 조건을 분석하거나 if문으로 세세하게 모든 경우의 수를 판별하기보다는 그냥 간단하게 생각하는 연습을 해야겠다......



<br>
<br>
<br>
