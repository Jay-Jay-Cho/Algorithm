# 문자열 처리
<br>

## &#10095;&#10095;&#10095; 입력 받아오기
```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

// StringTokenizer
StringTokenizer st = new StringTokenizer(br.readLine());
while(st.hasNextToken()){
  String s = st.nextToken();
}

// String array
String[] str = br.readLine().split(" ");
```
<br><br>


## &#10095;&#10095;&#10095; 영어 대/소문자, 숫자 구분
```java
Character c;

Character.isUpperCase(c); // 대문자
Character.isLowerCase(c); // 소문자
Character.isDigit(c); // 숫자
```
<br><br>



## &#10095;&#10095;&#10095; Iterator
```java
HashMap<Integer,String> map;

Iterator iter = map.keySet().Iterator();
while(iter.hasNext()){
  int key = iter.next();
  int value = map.get(key);
}
```
<br><br>
