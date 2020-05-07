# [여행경로](https://programmers.co.kr/learn/courses/30/lessons/60057)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.lang.StringBuilder;
class Solution {
    public int solution(String s) {
        int answer = s.length();

        int max_range = s.length()/2;

        for(int i=1;i<=max_range;i++) {
        	String prev_str = "";
        	int prev_idx = 0;
        	int curr_idx = 0;
        	String[] str_arr = new String[s.length()/i + s.length()%i];
        	int[] cnt_arr = new int[s.length()/i + s.length()%i];

        	for(int j=0;j<s.length();j+=i) {
        		String str;
        		if(j+i<s.length()) {
        			str = s.substring(j,j+i);
        		}else {
        			str = s.substring(j,s.length());
        		}
        		str_arr[curr_idx] = str;
        		if(prev_str.equals(str)) {
        			cnt_arr[prev_idx]++;
        		}else {
        			prev_str = str;
        			prev_idx = curr_idx;
        			cnt_arr[curr_idx]++;
        		}
        		curr_idx++;
        	}

        	StringBuilder sb = new StringBuilder();
        	for(int k=0;k<str_arr.length;k++) {
        		if(cnt_arr[k] == 0) {
        			continue;
        		}else if(cnt_arr[k] == 1) {
        			sb.append(str_arr[k]);
        		}else {
        			sb.append(String.valueOf(cnt_arr[k]));
        			sb.append(str_arr[k]);
        		}
        	}
        	int temp_ans = sb.toString().length();
        	answer = Math.min(answer, temp_ans);
        }


        return answer;
    }
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 2
* 단순 구현
* 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
##### 1) 일단 자를 수 있는 단위의 최댓값은 문자열 `s.length / 2`임을 인지
* 왜냐하면, `s.length / 2`보다 커봤자, 주어진 `s`의 길이보다 짧아질 수 없음

##### 2) 1부터 `s.length / 2`까지 반복문을 돌며 해당 range만큼 잘라서 비교 후, 가장 짧은 길이를 반환

##### 3) 자른 문자열 자체를 저장하는 `str_arr`배열과 해당 문자열이 얼마나 반복되는지를 저장하는 `cnt_arr`배열 활용.
<br><br>


## &#10095;&#10095;&#10095; 풀이
##### 1. 자를 수 있는 최대 단위(range)는 `s.length / 2`
```java
int max_range = s.length()/2;
```

##### 2. 최소 단위(`1`)부터 최대 단위(`s.length / 2`)까지 반복
```java
for(int i=1;i<=max_range;i++) {
  ...
}
```

##### 3. 변수 및 배열 선언
```java
String prev_str = ""; // 이전 문자열 저장
int prev_idx = 0; // 이전 문자열의 index (str_arr 배열)
int curr_idx = 0; // 현재 문자열의 index : prev_idx와 비교를 위한
// range만큼 잘린 문자열들을 저장
String[] str_arr = new String[s.length()/i + s.length()%i];
// 각 문자열의 반복 횟수 저장
int[] cnt_arr = new int[s.length()/i + s.length()%i];
```

##### 4. 문자열의 처음부터 끝까지 반복하면서 잘린 문자열의 반복횟수 저장
```java
// index 간의 간격은 range 만큼 : j+=i
for(int j=0;j<s.length();j+=i) {
  String str;

  // 현재 index 부터 range만큼 문자열을 자름
  if(j+i<s.length()) {
    str = s.substring(j,j+i);
  }
  // 다음 index가 총 문자열의 길이를 초과하면, 그냥 마지막 문자까지만 포함
  else {
    str = s.substring(j,s.length());
  }

  str_arr[curr_idx] = str;  // 잘린 문자열을 배열에 저장

  if(prev_str.equals(str)) {  // 만약 이전 문자열과 현재 문자열이 같다면, (반복 O)
    cnt_arr[prev_idx]++;  // 이전 문자열의 반복횟수 추가
  }

  else {  // 만약 이전 문자열과 현재 문자열이 다르다면, (반복 X)
    prev_str = str; // 다음 문자열과 비교를 위해, 문자열 갱신
    prev_idx = curr_idx;  // 반복횟수 갱신을 위해, index 변경
    cnt_arr[curr_idx]++;  // 현재 index가 반복횟수 추가 (처음에는 0에서 1로 변경)
  }

  curr_idx++; // 다음 index로 포인터 이동
}
```

##### 5. `cnt_arr`와 `str_arr`의 index를 하나씩 반복하면서 최종 문자열 생성
```java
StringBuilder sb = new StringBuilder();
for(int k=0;k<str_arr.length;k++) {

  // 만약 해당 문자열이 반복적으로 나타난 문자열이면
  if(cnt_arr[k] == 0) {
    continue; // 어차피 처음 등장하는 문자열의 반복횟수에 포함되므로, 스킵
  }

  // 한번만 나타나는 문자열이면
  else if(cnt_arr[k] == 1) {
    sb.append(str_arr[k]);  // 반복횟수는 스킵하고 문자열만 반환
  }

  // 두 번 이상 반복되는 문자열이면
  else {
    sb.append(String.valueOf(cnt_arr[k]));  // 반복횟수를 먼저 반환하고,
    sb.append(str_arr[k]);  // 반복되는 문자열을 이어서 반환
  }
}
int temp_ans = sb.toString().length();
answer = Math.min(answer, temp_ans);  // 가장 짧은 길이의 문자열 길이로 갱신
```



<br><br>



## &#10095;&#10095;&#10095; 꿀팁
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 반복문 내에서 index를 초과할 때의 예외처리를 안해줬다...
```java
// 실수
for(int j=0;j<s.length();j+=i) {
  String str;
  str = s.substring(j,j+i);

  ...

}

// 정답
for(int j=0;j<s.length();j+=i) {
  String str;
  if(j+i<s.length()) {
    str = s.substring(j,j+i);
  }else {
    str = s.substring(j,s.length());
  }

  ...

}
```


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점




<br>
<br>
<br>
