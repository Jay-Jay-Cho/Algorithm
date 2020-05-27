# [스킬트리](https://programmers.co.kr/learn/courses/30/lessons/49993)
* **참고자료** : 엄슴.

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i=0;i<skill_trees.length;i++) {
        	String skill_tree = skill_trees[i];
        	StringBuilder sb = new StringBuilder();
        	for(int j=0;j<skill_tree.length();j++) {
        		Character c = skill_tree.charAt(j);
        		if(skill.contains(String.valueOf(c))) {
        			sb.append(c);
        		}
        	}
        	String target = sb.toString();
        	String org = skill.substring(0,target.length());
        	if(org.equals(target)) answer++;
        }

        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* 가능한 스킬트리 개수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 반복문을 돌면서, skill에 포함된 character들만 뽑아서 String 만들기
#### 2. 만들어진 String과 skill과 비교해서, 똑같으면 answer++
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 반복문을 돌면서, skill에 포함된 character들만 뽑아서 String 만들기 & 비교
```java
for(int i=0;i<skill_trees.length;i++) {
  String skill_tree = skill_trees[i];
  StringBuilder sb = new StringBuilder();
  for(int j=0;j<skill_tree.length();j++) {
    Character c = skill_tree.charAt(j);
    // skill에 포함된 character만 StringBuilder에 추가
    if(skill.contains(String.valueOf(c))) {
      sb.append(c);
    }
  }
  String target = sb.toString();
  String org = skill.substring(0,target.length());
  // skill과 추출된 string이 같을 때만 answer++
  if(org.equals(target)) answer++;
}
```
<br><br>

## &#10095;&#10095;&#10095; 실수
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
