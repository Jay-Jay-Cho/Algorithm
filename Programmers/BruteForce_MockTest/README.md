

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42840)<br>

<br>
<br>
<br>

## 설명
* Level: 1
* Brute Force (완전탐색)
* 수포자 삼인방의 찍기 점수들 중 가장 높은 것 고르기


<br>
<br>
<br>

## 접근법
음... Level 1 문제라서 그런지 굉장히 직관적이어서 별도의 코드 설명은 안해도 괜찮을 듯 싶다...


1) 삼인방의 찍기 규칙을 찾아서, 
2) 입력값인 answers와 비교하면서 각각 count를 한 뒤에
3) 가장 높은 점수를 얻은 친구 혹은 친구들이 담긴 배열을 반환...

<br>
그나마 고민을 좀 했던 것은, answers배열과 각 삼인방의 규칙을 어떻게 비교하나 하는 것이었는데 간단히 **나머지** 함수는 %를 쓰면 됐다...
```JAVA
for(int i=0;i<answers.length;i++){
    int answer = answers[i];
    if(answer==first[i%first.length]) first_score++;
    if(answer==second[i%second.length]) second_score++;
    if(answer==third[i%third.length]) third_score++;
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

<br>
<br>
<br>
