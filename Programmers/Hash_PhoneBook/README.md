

### &#128526;
[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42577)
<br>
<br>
<br>

## 설명
* Level: 2
* Hash
* 배열에서 접두사(prefix)로 될 수 있는 번호가 있는지 체크. 있으면 false. 없으면 true.


<br>
<br>
<br>

## 접근법
1) 마찬가지로 이중for문을 생각했는데, 효율성(time complexity)부분에서 걸려서 out...
2) 처음에 Arrays.sort를 이용해서 문자열 길이 순으로 정렬 후 처리하려고 했는데, Arrays.sort는 사전 순서대로 정렬할 뿐이고 길이 기준으로 정렬을 해주지는 않음. 문자열 길이를 기준으로 정렬을 하려면 Arrays.sort()와 Comparator를 이용해야한다고 함....
3) 일단 이중for문이기는 하나, 효율성에서 걸리지는 않았음,,,,
4) 원래 subString으로 비교를 하나씩 했으나, 알고보니 접두사/접미사 비교를 위한 함수가 따로 있었음. 혹은 indexOf를 써도 되고.
<br>
<br>
<br>

## 유용한 함수
* 배열 정렬(길이 순서대로): 
```java
Arrays.sort(phone_book3, new Comparator<String>(){
  public int compare(String s1, String s2){
    return Integer.compare(s1.length(), s2.length());//문자열 길이순 정렬
  }
});
```
* 접두사/접미사 비교
```java
phone_book[j].startsWith(temp) //접두사
phone_book[j].endsWith(temp)  //접미사

```

<br>
<br>
<br>

## 숙지해야할 점
1) 처음에는 i번째 문자열이 j번째 문자열의 prefix인지만 확인했는데, 오히려 반례가 생길 수 있음. 예를들어, case 1은 괜찮지만 case 2는 오류가 생길 수 있음. 따라서 두번째 반복문도 j=i+1이 아니라, j=0부터 체크해야함.
```java
phone_book = {"119","1192456"} //case 1
phone_book = {"1192456","119"} //case 2
```



<br>
<br>
<br>
