# [Trie](https://mjson.tistory.com/39)
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Trie {
  class TrieNode{
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    HashMap<Integer, Integer> childrenWithLen = new HashMap<Integer,Integer>();
    boolean isLeaf;   

    public TrieNode() {}   
    public TrieNode(char c){
      this.c = c;
    }

  }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String str) {
        HashMap<Character, TrieNode> children = root.children;
        HashMap<Integer, Integer> childrenWithLen = root.childrenWithLen;

        TrieNode t;
        int str_len = str.length();
        for(int i=0;i<str.length();i++) {
          char c = str.charAt(i);
          if(children.containsKey(c)) {
            t = children.get(c);
          }else {
            t = new TrieNode(c);
            children.put(c, t);
          }

          if(childrenWithLen.containsKey(str_len)) {
            childrenWithLen.replace(str_len, childrenWithLen.get(str_len)+1);
          }else {
            childrenWithLen.put(str_len, 1);
          }

          children = t.children;
          childrenWithLen = t.childrenWithLen;

          if(i==str.length()-1) {
            t.isLeaf = true;
          }
        }
    }

    public boolean contains(String str){
        boolean check = true;

        HashMap<Character, TrieNode> children = root.children;

        TrieNode t;
        for(int i=0;i<str.length();i++) {
          char c = str.charAt(i);
          if(children.containsKey(c)) {
            t = children.get(c);
            children = t.children;
          }else {
            check = false;
            break;
          }
        }
        return check;
    }

    public int getChildrenLen(String str, int str_len) {
        int len = 0;

        HashMap<Character, TrieNode> children = root.children;

        TrieNode t = null;
        for(int i=0;i<str.length();i++) {
          char c = str.charAt(i);
          t = children.get(c);
          children = t.children;
        }

        HashMap<Integer, Integer> childrenWithLen = t.childrenWithLen;
        if(childrenWithLen.containsKey(str_len)) {
          len = childrenWithLen.get(str_len);
        }else {
          len = 0;
        }

        return len;
    }

    public TrieNode searchNode(String str){
        HashMap<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }

        return t;
    }


}
```
<br><br>

## &#10095;&#10095;&#10095; Trie
### 생성자
```java
public Trie() {
    root = new TrieNode();
}
```

### void insert(String str)
```java
public void insert(String str) {
    // children은 일단 root의 children으로 초기화
    HashMap<Character, TrieNode> children = root.children;
    HashMap<Integer, Integer> childrenWithLen = root.childrenWithLen;

    TrieNode t;
    int str_len = str.length();
    for(int i=0;i<str.length();i++) {
      char c = str.charAt(i);
      // 부모노드의 children map에 이미 character c 가 있다면,
      if(children.containsKey(c)) {
        // 해당 character c 의 TireNode로 포인터 이동
        t = children.get(c);
      }
      // children Map에 이미 character c 가 없다면,
      else {
        // c를 character로 하는 새로운 TireNode 생성하고, 포인터 이동
        t = new TrieNode(c);
        children.put(c, t); // 부모노드의 children map에 c 와 TrieNode 추가
      }
      // 다음 반복문을 위해, children map을 부모노드에서 현재노드로 이동
      children = t.children;

      // 만약 character c가 마지막이면, c를 포함하는 노드를 leaf Node로 표시
      if(i==str.length()-1) {
        t.isLeaf = true;
      }

      if(childrenWithLen.containsKey(str_len)) {
        childrenWithLen.replace(str_len, childrenWithLen.get(str_len)+1);
      }else {
        childrenWithLen.put(str_len, 1);
      }
      childrenWithLen = t.childrenWithLen;
    }
}
```

### boolean contains(String str)
```java
public boolean contains(String str){
    boolean check = true;

    HashMap<Character, TrieNode> children = root.children;

    TrieNode t;
    for(int i=0;i<str.length();i++) {
      char c = str.charAt(i);
      if(children.containsKey(c)) {
        t = children.get(c);
        children = t.children;
      }else {
        check = false;
        break;
      }
    }
    return check;
}
```

### TireNode searchNode(String str)
```java
public TrieNode searchNode(String str){
    HashMap<Character, TrieNode> children = root.children;
    TrieNode t = null;
    for(int i=0; i<str.length(); i++){
        char c = str.charAt(i);
        if(children.containsKey(c)){
            t = children.get(c);
            children = t.children;
        }else{
            return null;
        }
    }

    return t;
}
```

<br/><br/>
