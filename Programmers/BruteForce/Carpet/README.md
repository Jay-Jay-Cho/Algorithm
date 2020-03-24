

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42842) <br>
[참고 자료](https://swjeong.tistory.com/74)

<br>
<br>
<br>

## 설명
* Level: 2
* Brute Force (완전탐색)
* Brown 타일과 Red 타일의 갯수가 주어졌을 때, 만족하는 카펫의 크기 [width, height]를 반환


<br>
<br>
<br>

## 접근법
(실수) 숫자야구때랑 같은 실수 반복... 완전탐색문제인데 너무 조건이나 규칙을 찾는데에 매달렸던 것 같다.. 포커스를 카펫의 **모양**이 아니라 카펫의 **크기**에 둬야 했다.

결국 구글링을 통해 찾은 [접근법](https://swjeong.tistory.com/74)을 통해 구현했다. 중요한 것은, 카펫의 모양에 상관없이 <br>
1. 카펫의 가로, 세로 길이는 언제나 3 이상이며 (Red타일은 항상 Brown타일에 둘러쌓여있으므로)
2. 따라서, (가로-2) * (세로-2) = Red 타일의 갯수 라는 것이다. 이를 토대로 구현하면 <br>



1) 일단 모든 타일의 갯수를 구한 뒤에,
```JAVA
int sum = brown + red;
```
2) 반복문을 돌면서 가로, 세로 길이를 구하고 --> Red 타일 갯수의 조건과 맞는 케이스를 반환.
```JAVA
for(int i=3;i<sum/3;i++) {
  if(sum%i==0) {
    int row = Math.max(sum/i, i); // 가로길이
    int col = Math.min(sum/i, i); // 세로길이
    if((row-2)*(col-2)==red) {  // (가로길이-2) * (세로길이-2) = Red 타일의 갯수
      answer = new int[]{row,col};
      break;
    }
  }
}
```
<br>
<br>
<br>

## 유용한 함수 혹은 API

<br>
<br>
<br>

## 숙지해야할 점
1) 완전탐색(Brute Force)문제일지라도 최대한 반복횟수는 줄이는 편이 낫다.
```JAVA
for(int i=3;i<sum/3;i++)
```
가로/세로길이를 구하는 반복문이기 때문에, 가장 짧은 3부터 시작해서 최댓값은 모든 타일 갯수(sum)을 3으로 나눈 몫 된다.


<br>
<br>
<br>
