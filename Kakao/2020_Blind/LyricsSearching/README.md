# [가사 검색](https://programmers.co.kr/learn/courses/30/lessons/60060)
* **참고자료** : https://velog.io/@ptm0304/2020%EC%B9%B4%EC%B9%B4%EC%98%A4%EA%B3%B5%EC%B1%84-%EA%B0%80%EC%82%AC-%EA%B2%80%EC%83%89
* **참고자료** : https://mjson.tistory.com/39 (트라이)

<br>

## &#10095;&#10095;&#10095; 전체 코드

* [Trie 구현](./Technique/Trie)

```java
import java.lang.StringBuilder;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        // all wild-card
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();   

        // Trie
        Trie prefix_trie = new Trie();
        Trie suffix_trie = new Trie();

        // insert
        for(int i=0;i<words.length;i++) {
        	String word = words[i];
        	prefix_trie.insert(word);
        	String reverse_word = new StringBuilder(word).reverse().toString();
        	suffix_trie.insert(reverse_word);
        	int len = word.length();
        	if(map.containsKey(len)) {
        		map.replace(len, map.get(len)+1);
        	}else {
        		map.put(len, 1);
        	}
        }

        // Searching
    		for(int i=0;i<queries.length;i++) {
            	String query = queries[i];

              // query length != any word length
              if(!map.containsKey(query.length())) {
          		answer[i] = 0;
          		continue;
            	}

            	char start = query.charAt(0);
            	char end = query.charAt(query.length()-1);

              // all wild-card : ?????
            	if(start=='?' && end=='?') {
            		answer[i] = map.get(query.length());
            	}else {
                // suffix_trie : ???oo
            		if(query.startsWith("?")) {
            			StringBuilder sb = new StringBuilder();
            			String reverse_query = sb.append(query).reverse().toString();
            			StringBuilder sb2 = new StringBuilder();
            			for(String temp:reverse_query.split("")) {
            				if(temp.equals("?")) {
            					break;
            				}else {
            					sb2.append(temp);
            				}
            			}
            			String suffix = sb2.toString();
            			int ans;
            			if(suffix_trie.contains(suffix)) {
            				ans = suffix_trie.getChildrenLen(suffix, query.length());
            			}else {
            				ans = 0;
            			}
            			answer[i]  = ans;
            		}
                // prefix_trie : ooo??
                else {
            			StringBuilder sb = new StringBuilder();
            			for(String temp:query.split("")) {
            				if(temp.equals("?")) {
            					break;
            				}else {
            					sb.append(temp);
            				}
            			}
            			String prefix = sb.toString();
            			int ans;
            			if(prefix_trie.contains(prefix)) {
            				ans = prefix_trie.getChildrenLen(prefix, query.length());
            			}else {
            				ans = 0;
            			}
            			answer[i]  = ans;
            		}
            	}
            }

        return answer;
    }
}
```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 4
* 구현 : Trie 활용
* 가사에 사용된 모든 단어들이 담긴 배열 `words`와 찾고자 하는 키워드가 담긴 배열 `queries`가 주어질 때, 각 키워드 별로 매치된 단어가 몇 개인지 순서대로 배열에 담아 return
<br><br>


## &#10095;&#10095;&#10095; 접근법  

### - Solution
##### 1. 경우의 수에 따라 로직을 처리할 객체 선언
* `oo???` : prefix_trie
* `???oo` : suffix_trie
* `?????` : map (각 word의 길이 저장)
##### 2. word들을 순차적으로 분기에 따라 객체에 저장
##### 3. query의 종류에 따라 올바른 값 반환
* 예외처리 : query의 길이와 동일한 word가 없을 시에는 반복문 continue

### - Trie
##### 1. `void insert(String str)` : word를 Trie에 삽입
##### 2. `boolean contains(String str)` : word가 Trie에 존재하는지 확인
##### 3. `int getChildrenLen(String str, str_len)`
* 주어진 str을 포함하고, str_len만큼의 길이를 가진 word가 몇 개 있는지 반환
##### 4. `TireNode searchNode(String str)` : str이 포함된 마지막 TrieNode를 반환

<br/><br/>

## &#10095;&#10095;&#10095; 풀이
##### 1. 경우의 수에 따라 word를 저장할 객체 선언
```java
HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();  // ?????
Trie prefix_trie = new Trie();  // ooo??
Trie suffix_trie = new Trie();  // ???oo
```

##### 2. 경우에 수에 따라 word 저장
```java
for(int i=0;i<words.length;i++) {
    // ooo??
    String word = words[i];
    prefix_trie.insert(word);

    // ???oo
    String reverse_word = new StringBuilder(word).reverse().toString();
    suffix_trie.insert(reverse_word);

    // ?????
    int len = word.length();
    if(map.containsKey(len)) {
      map.replace(len, map.get(len)+1);
    }else {
      map.put(len, 1);
    }
}
```

##### 3. query 종류에 따라, 조건에 맞는 word 갯수 반환
```java
for(int i=0;i<queries.length;i++) {
    String query = queries[i];

    // 만약 query 길이와 동일한 word가 없다면, 그대로 continue
    if(!map.containsKey(query.length())) {
    answer[i] = 0;
    continue;
    }

    char start = query.charAt(0);
    char end = query.charAt(query.length()-1);

    // ?????
    if(start=='?' && end=='?') {
      answer[i] = map.get(query.length());
    }else {
      // ???oo = suffix
      if(query.startsWith("?")) {
        StringBuilder sb = new StringBuilder();
        // ??cab ---> bac??
        String reverse_query = sb.append(query).reverse().toString();
        StringBuilder sb2 = new StringBuilder();
        for(String temp:reverse_query.split("")) {
          if(temp.equals("?")) {
            break;
          }else {
            sb2.append(temp);
          }
        }
        // ?를 제외한 suffix 부분만 추출 (reverse)
        String suffix = sb2.toString();
        int ans;
        if(suffix_trie.contains(suffix)) {
          // suffix를 포함하고, query의 길이와 동일한 word 갯수
          ans = suffix_trie.getChildrenLen(suffix, query.length());
        }else {
          ans = 0;
        }
        answer[i]  = ans;
      }
      // ooo?? = prefix
      else {
        StringBuilder sb = new StringBuilder();
        for(String temp:query.split("")) {
          if(temp.equals("?")) {
            break;
          }else {
            sb.append(temp);
          }
        }
        // ?를 제외한 prefix 부분만 추출
        String prefix = sb.toString();
        int ans;
        if(prefix_trie.contains(prefix)) {
          // prefix를 포함하고, query의 길이와 동일한 word 갯수
          ans = prefix_trie.getChildrenLen(prefix, query.length());
        }else {
          ans = 0;
        }
        answer[i]  = ans;
      }
    }
  }
```
<br><br>

## &#10095;&#10095;&#10095; 꿀팁
##### 1. [Trie ](./Technique/Trie)
##### 2. 문자열 거꾸로 출력하기
```java
String str;
String reverse_str = new StringBuilder(str).reverse().toString();
```
##### 3. HashMap value 업데이트 : `replace()`
```java
HashMap<String,Integer> map;
String key;
Integer value;
map.replace(key,map.get(key)+1);
```
##### 4. 접두어/접미어 확인
```java
String str = "abcde";
boolean prefix = str.startsWith("a");
boolean suffix = str.endsWith("e");
```
<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 예외 케이스를 고려 안함
* query의 길이와 같은 word가 존재하지 않을 때는 0을 출력
```java
if(!map.containsKey(query.length())) {
  answer[i] = 0;
  continue;
}
```

##### 2. 일반적으로 문자열의 존재 여부만이 아니라, 해당 문자열의 길이도 비교를 해야 하므로 `getChildrenLen` API를 Trie에 구현했어야 함...
* query : `fro??`
* words : [`frodo`, `front`, `froll`, `frozen`]
* 이 경우, prefix가 `fro`고, 총 길이가 5인 단어의 갯수만 선택해야 하므로


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점




<br>
<br>
<br>
