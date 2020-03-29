# [여행경로](https://programmers.co.kr/learn/courses/30/lessons/60058)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.lang.StringBuilder;
class Solution {
    public String solution(String p) {
        String answer = recursive(p);
        return answer;
    }

    static String recursive(String str) {

    		if(str.length() == 0) return "";
    		else {
    			StringBuilder sb = new StringBuilder();
    			int cnt = 0;
    			int idx = -1;
    			boolean isRight = true;
    			for(int i=0;i<str.length();i++) {
    				char c = str.charAt(i);

    				if(c == '(') {
    					cnt++;
    					isRight = false;
    				}
    				else {
    					cnt--;
    					isRight = true;
    				}

    				sb.append(c);

    				if(cnt == 0) {
    					idx = i;
    					break;
    				}
    			}

    			String u = sb.toString();
    			String v = str.substring(idx+1,str.length());
    			StringBuilder sb2 = new StringBuilder();
    			if(isRight) {
    				return sb2.append(u).append(recursive(v)).toString();
    			}else {
    				sb2.append('(');
    				sb2.append(recursive(v));
    				sb2.append(')');
    				String[] temp = u.substring(1,u.length()-1).split("");
    				for(String str_temp:temp) {
    					if(str_temp.equals("(")) {
    						str_temp = ")";
    					}else if(str_temp.equals(")")) {
    						str_temp = "(";
    					}
    					sb2.append(str_temp);
    				}
    				return sb2.toString();
    			}
    		}

	}
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 2
* 단순 구현
* 균형잡힌 괄호 문자열 p가 매개변수로 주어질 때, 주어진 알고리즘을 수행해 올바른 괄호 문자열로 변환한 결과를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
### 지문 자체에서 로직을 제공했기 때문에, 딱히 구현에 머리 아픈 일은 없었다...
##### 1. Main 함수에서 recursive 함수 호출
##### 2. Recursive 함수
* 빈 문자열이면, 그대로 반환
* 인자로 받은 배열을 일단 u,v로 분할 (index 0부터 탐색하면서....)
  * u : 균형잡힌 문자열 (괄호 갯수가 맞는)
  * v : 나머지 문자열
* u가 올바른 문자열(`짝이 맞는`)이면, v를 인자값으로 해서 다시 recursive 호출
* u가 올바른 문자열이 아니면,
  * 빈 문자열에 `(`를 붙이고
  * v를 인자값으로 한 recursive 결과를 담고,
  * `)`를 붙이고
  * u의 가장 앞/뒤 index 를 자르고, 나머지 괄호들을 뒤집은 값을 이어붙여줌.  
<br><br>


## &#10095;&#10095;&#10095; 풀이
##### 1. 입력이 빈 문자열일 경우, 그대로 반환
```java
if(str.length() == 0) return "";
```

##### 2. 문자열을 u,v로 분할
* u 문자열
  * 문자열 가장 앞 쪽부터 탐색하면서,
  * `(`를 만나면 cnt++, `)`를 만나면 cnt-- 하면서
  * 균형잡힌 문자열이 완성되면, 즉 cnt가 0이 되면 break
  * 가장 마지막 문자가 `(`면 올바른 문자열이 아니고, `)`면 올바른 문자열이므로 문자 하나씩 탐색할 때마다 올바른 문자열인지 체크(`isRight`)
* v 문자열
  * u 문자열이 끝난 다음 인덱스부터 마지막 인덱스까지
```java
StringBuilder sb = new StringBuilder(); // u 문자열 만들기
int cnt = 0;  // 균형잡힌 문자열 체크
int idx = -1; // u 문자열이 끝나는 지점을 저장
boolean isRight = true; // 올바른 문자열 체크
for(int i=0;i<str.length();i++) {
  char c = str.charAt(i);

  if(c == '(') {
    cnt++;
    isRight = false;
  }
  else {
    cnt--;
    isRight = true;
  }

  sb.append(c);

  if(cnt == 0) {  // 균형잡힌 문자열이 완성되면 break
    idx = i;
    break;
  }
}

String u = sb.toString();
String v = str.substring(idx+1,str.length());
```

##### 3. u 문자열의 올바름 여부를 판단하여 분기
* u 문자열이 올바른 문자열이면,
  * u 문자열을 저장하고, v 문자열에 대해 recursive 수행
* u 문자열이 올바르지 않은 문자열이면,
  * 해당 처리 수행

```java
StringBuilder sb2 = new StringBuilder();

// 3. u 문자열이 올바른 문자열이면,
if(isRight) {
  return sb2.append(u).append(recursive(v)).toString();
}

// 4. u 문자열이 올바르지 않은 문자열이면,
else {
  sb2.append('(');  // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙인다.
  sb2.append(recursive(v)); // 4-2. v 문자열에 대한 재귀결과를 이어붙인다.
  sb2.append(')');  // 4-3. 빈 문자열에 첫 번째 문자로 '('를 붙인다.
  // 4-4. u 문자열의 가장 앞/뒤 문자를 자르고, 나머지 문자열의 방향을 뒤집는다.
  String[] temp = u.substring(1,u.length()-1).split("");
  for(String str_temp:temp) {
    if(str_temp.equals("(")) {
      str_temp = ")";
    }else if(str_temp.equals(")")) {
      str_temp = "(";
    }
    sb2.append(str_temp);
  }
  return sb2.toString();  // 4-5. 생성된 문자열을 반환
}
```
<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. char 값 비교시에는, equals() 메소드말고 `==`연산자로 비교 가능
```java
if(c == '(') {
  ...
}
```
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 문자열의 괄호 방향을 뒤집을 때, 빈 문자열의 경우를 고려안함.... 빈 문자열을 고려안하면, 강제적으로 `(`가 반환됨.
* 오류 예시
  * `p` : **) (**
  * `반환` : **( ) (**
  * `정답` : **( )**
```java
// 실수
String[] temp = u.substring(1,u.length()-1).split("");
for(String str_temp:temp) {
  if(str_temp.equals("(")) str_temp = ")";
  else str_temp = "(";

  sb2.append(str_temp);
}

// 정답
String[] temp = u.substring(1,u.length()-1).split("");
for(String str_temp:temp) {
  if(str_temp.equals("(")) {
    str_temp = ")";
  }else if(str_temp.equals(")")) {
    str_temp = "(";
  }
  sb2.append(str_temp);
}
```

##### 2. String 배열 반환을 그냥 toString()으로 함...
```java
// 실수
sb2.append(temp.toString());

// 정답
for(String str_temp:temp) {
  ...
  sb2.append(str_temp);
}

```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점




<br>
<br>
<br>
