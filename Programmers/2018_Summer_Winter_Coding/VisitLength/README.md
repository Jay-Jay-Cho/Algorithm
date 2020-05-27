# [방문길이](https://programmers.co.kr/learn/courses/30/lessons/49994)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};

	public int solution(String dirs) {
        int answer = 0;

        HashSet<String> set = new HashSet<String>();
        int x = 5;
        int y = 5;
        for(int i=0;i<dirs.length();i++) {
        	Character c = dirs.charAt(i);
        	int direction;
        	if(c=='U') direction = 0;
        	else if(c=='D') direction = 1;
        	else if(c=='R') direction = 2;
        	else direction = 3;
        	int xx = x+dx[direction];
        	int yy = y+dy[direction];

        	if(xx<0 || xx>=11 || yy<0 || yy>=11) continue;
        	else {
        		StringBuilder sb = new StringBuilder();
        		sb.append(x).append(y).append(xx).append(yy);
        		String route = sb.toString();
        		StringBuilder sb2 = new StringBuilder();
        		sb2.append(xx).append(yy).append(x).append(y);
        		String route2 = sb2.toString();
        		if(!set.contains(route) && !set.contains(route2)) {
        			set.add(route2);
        			set.add(route);
        			answer++;
        		}
        		x = xx;
            y = yy;
        	}
        }


        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 3
* 시뮬레이션
* 게임 캐릭터가 처음 걸어본 길의 길이를 구하여 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 게임 캐릭터의 초기 위치는 (5,5)
#### 2. 경로를 이동하면서, 이전에 방문하지 않은 경로일 때만 answer++
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 이동 경로만큼 반복문을 돌면서 체크
```java
// 방문한 경로를 넣을 Set
HashSet<String> set = new HashSet<String>();

// 캐릭터 초기 위치
int x = 5;
int y = 5;

for(int i=0;i<dirs.length();i++) {
  Character c = dirs.charAt(i);
  int direction;
  if(c=='U') direction = 0;
  else if(c=='D') direction = 1;
  else if(c=='R') direction = 2;
  else direction = 3;
  int xx = x+dx[direction];
  int yy = y+dy[direction];

  if(xx<0 || xx>=11 || yy<0 || yy>=11) continue;
  else {
    // 현재위치+다음위치를 String으로 변환
    StringBuilder sb = new StringBuilder();
    sb.append(x).append(y).append(xx).append(yy);
    String route = sb.toString();
    // 다음위치+현재위치를 String으로 변환
    StringBuilder sb2 = new StringBuilder();
    sb2.append(xx).append(yy).append(x).append(y);
    String route2 = sb2.toString();
    // 둘 다 포함하지 않을 경우는, 방문하지 않은 경로
    if(!set.contains(route) && !set.contains(route2)) {
      // Set에 추가
      set.add(route2);
      set.add(route);
      answer++;
    }
    x = xx;
    y = yy;
  }
}
```
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 단방향만 생각하고, 양방향을 생각안함
* 예를들어, 현재위치+다음위치만으로 방문 여부를 체크하면, `LR`인 경우 카운트가 2가 됨
* 따라서, 현재위치+다음위치 뿐만 아니라 다음위치+현재위치도 Set에 넣어서 체크해야됨
```java
// 실수
StringBuilder sb = new StringBuilder();
sb.append(x).append(y).append(xx).append(yy);
String route = sb.toString();
if(!set.contains(route)) {
  set.add(route2);
  set.add(route);
  answer++;
}
x = xx;
y = yy;

// 정답
StringBuilder sb = new StringBuilder();
sb.append(x).append(y).append(xx).append(yy);
String route = sb.toString();
StringBuilder sb2 = new StringBuilder();
sb2.append(xx).append(yy).append(x).append(y);
String route2 = sb2.toString();
if(!set.contains(route) && !set.contains(route2)) {
  set.add(route2);
  set.add(route);
  answer++;
}
x = xx;
y = yy;
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
