

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

static void swap(String[] arr, int depth, int i) {
	String temp = arr[depth];
	arr[depth] = arr[i];
	arr[i] = temp;
}
```
여기서 가장 이해가 안됐었던 부분이 바로 마지막 백트래킹을 위한 swap 코드이다. 다시 말하자면 주석에 나와있듯이 swap을 두번 수행함으로써 처음 상태로 되돌린다는 의미로 이해했다. 즉, 만약 **A**BC에서 A는 고정된 상태인 depth 1을 고려해보자. B와 C를 바꾼 이후에(**A**BC --> **AC**B), B와 B를 바꾸려면(물론 값은 바뀌지않지만 알고리즘상) 다시 **A**BC로 돌아가야한다.(**AC**B --> **A**BC --> **AB**C). 트리순회이기때문에, **A**BC를 거치지 않고 **AB**C로 넘어가는 것은 불가능하다. 무조건 백트래킹을 사용해서 이전 단계를 거쳐야 한다. [참고동영상](https://youtu.be/GuTPwotSdYw?t=759)


2) 이후 순열을 통해 뽑힌 숫자들을 list에 넣은 후에, 이 숫자들을 대상으로 하나씩 완전탐색을 실행하며 소수일 경우(cnt==2)에만 answer++을 한다.
```JAVA
static int getAnswer(String numbers) {
	String[] arr = numbers.split("");
	for(int i=1;i<=arr.length;i++) {	// 1자리부터 ~ 최대 n자리까지 
		permutation(arr,0,i);
	}

	// 소수 찾기 
	int answer = 0;
	for(int num:list) {
		int cnt = 0;
		for(int i=1;i<=num;i++) {
			if(cnt>2) break;	// 몫이 2개보다 많으면 break
			if(num%i==0) cnt++;	// 몫이 나누어 떨어질 때마다 cnt++
		}
		if(cnt==2) answer++;
	}
	for(int a:list) System.out.print(a+" ");
	return answer;
}
```
<br>
<br>
<br>

## 유용한 함수 혹은 API
* StringBuilder.append(String str): String 이어 붙이기
```JAVA
StringBuilder sb = new StringBuilder();
for(int i=0;i<r;i++) sb.append(arr[i]);
```
String을 이어붙이려면 다른 여러가지 방법(e.g. '+'연산, String.concat(), StringBuffer)들이 있지만, 가장 빠르다고 함.

<br>
<br>
<br>

## 숙지해야할 점
1) 순열/조합을 백트래킹과 재귀를 이용해서 구현하는 방법을 숙지해야겠다....



<br>
<br>
<br>
