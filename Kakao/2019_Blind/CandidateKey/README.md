# [후보키](https://programmers.co.kr/learn/courses/30/lessons/42890)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static Queue<ArrayList<Integer>> q;

	static void combination(int[] arr, boolean[] visited, int depth, int r) {
		if(r==0) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<arr.length;i++) {
				if(visited[i]) list.add(arr[i]);
			}
			q.offer(list);
			return;
		}

		for(int i=depth;i<arr.length;i++) {
			visited[i] = true;
			combination(arr,visited,i+1,r-1);
			visited[i] = false; // backtracking
		}
	}

	public int solution(String[][] relation) {
        ArrayList<ArrayList<Integer>> answer_list = new ArrayList<ArrayList<Integer>>();
        int row_cnt = relation.length;
        int col_cnt = relation[0].length;
        int[] arr = new int[col_cnt];
        for(int i=0;i<col_cnt;i++) arr[i] = i;
        boolean[] visited = new boolean[arr.length];
        q = new LinkedList<ArrayList<Integer>>();
        for(int i=1;i<=col_cnt;i++) {
        	combination(arr,visited,0,i);
        }

        while(!q.isEmpty()) {
        	ArrayList<Integer> list = q.poll();
        	HashSet<String> set = new HashSet<String>();
        	boolean check = true;

        	if(list.size()==1) {
        		int key_idx = list.get(0);
        		for(int i=0;i<relation.length;i++) {
        			String[] row = relation[i];
        			String value = row[key_idx];
        			if(set.contains(value)) {
        				check = false;
        				break;
        			}else {
        				set.add(value);
        			}
        		}
        	}else {
        		boolean isMinimal = true;
        		for(int i=0;i<answer_list.size();i++) {
        			ArrayList<Integer> exist_list = answer_list.get(i);
        			if(list.containsAll(exist_list)) {
        				isMinimal = false;
        				break;
        			}
        		}
        		if(!isMinimal) continue;


        		for(int i=0;i<relation.length;i++) {
        			String[] row = relation[i];
        			StringBuilder sb = new StringBuilder();
        			for(int key_idx=0;key_idx<list.size();key_idx++) {
        				sb.append(row[list.get(key_idx)]);
        			}
        			String value = sb.toString();
        			if(set.contains(value)) {
        				check = false;
        				break;
        			}else {
        				set.add(value);
        			}
        		}
        	}

        	if(check && set.size()==row_cnt) {
        		answer_list.add(list);
        	}
        }

        int answer = answer_list.size();
        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* 후보 키의 최대 개수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 가능한 조합의 수를 구해서 큐에 넣기
#### 2. 큐에서 하나씩 pop하면서 후보키 판단
* 개수가 하나인 조합은 `유일성`만을 판단
* 개수가 2개 이상인 조합부터 `최소성`과 `유일성` 모두 판단
  * `최소성`: answer_list에 있는 조합이 포함되면 continue로 스킵하고 다음 조합으로 넘어감.
  * `유일성`: 해당 조합으로 뽑힌 튜플 값들을 String으로 변환해서 HashSet에 넣은 후 판단.
#### 3. answer_list의 사이즈를 반환
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 가능한 조합의 수를 구해서 큐에 넣기
```java
for(int i=1;i<=col_cnt;i++) {
  combination(arr,visited,0,i);
}
```

#### 2. 큐에서 하나씩 pop하면서 후보키 판단
```java
while(!q.isEmpty()) {
  ArrayList<Integer> list = q.poll();
  HashSet<String> set = new HashSet<String>(); // 유일성 판단을 위한 Set
  boolean check = true; // 유일성 판단

  // 조합 요소가 하나일 때
  if(list.size()==1) {
    int key_idx = list.get(0);
    for(int i=0;i<relation.length;i++) {
      String[] row = relation[i];
      String value = row[key_idx];
      if(set.contains(value)) { // Set에 이미 있다면, 유일성을 만족하지 못하므로
        check = false;  // check를 false로 갱시하고
        break;  // 해당 케이스를 멈춤
      }else {
        set.add(value); // Set에 없다면, 추가
      }
    }
  }

  // 조합 요소가 2개 이상일 때
  else {
    // 최소성 판단
    boolean isMinimal = true;
    for(int i=0;i<answer_list.size();i++) {
      ArrayList<Integer> exist_list = answer_list.get(i);
      // answer_list에 있는 조합을 포함한다면, 최소성을 만족하지 못하므로 정지
      if(list.containsAll(exist_list)) {
        isMinimal = false;
        break;
      }
    }
    if(!isMinimal) continue;  // 최소성을 만족하지 못하면, 스킵하고 다음 조합으로 넘어감

    // 최소성을 만족하면, 유일성 판단
    for(int i=0;i<relation.length;i++) {
      String[] row = relation[i];
      StringBuilder sb = new StringBuilder();
      for(int key_idx=0;key_idx<list.size();key_idx++) {
        sb.append(row[list.get(key_idx)]);
      }
      String value = sb.toString();
      if(set.contains(value)) {
        check = false;
        break;
      }else {
        set.add(value);
      }
    }
  }
  // 유일성을 만족하고, Set의 사이즈가 총 튜플의 갯수와 같다면 answer_list에 추가
  if(check && set.size()==row_cnt) {
    answer_list.add(list);
  }
}
```

#### 3. answer_list의 사이즈를 반환
```java
int answer = answer_list.size();
return answer;
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1.최소성을 판단하는 함수를 잘못 선언.
* `startsWith`를 쓰면, 아래와 같은 케이스에서 오류 발생
```java
String exist_string = "125";
String current_string = "1235";
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
* `containsAll()` : List 요소들의 포함 여부를 판단할 때 사용
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
