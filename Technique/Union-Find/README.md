# 유니온-바인드 (Union-Find)
* 참고자료 : https://ssungkang.tistory.com/entry/Algorithm-%EC%9C%A0%EB%8B%88%EC%98%A8-%ED%8C%8C%EC%9D%B8%EB%93%9CUnion-Find
<br>

## &#10095;&#10095;&#10095; 설명
#### void union(int x, int y)
* x와 y를 합집합으로 만들기(부모가 같도록)
#### int find(int x)
* x의 부모노드가 누구인지 반환
#### boolean isSame(int x,int y)
* x의 부모노드와 y의 부모노드가 같은지 확인
* 즉, 같은 집합에 속해있는지
<br><br>

## &#10095;&#10095;&#10095; 전체 코드
```java
public class UnionFind {
		static int[] parent;

		// 부모 노드 찾기
		static int find(int x) {
			if(x == parent[x]) return x;
			else {
				int y = find(parent[x]);
				parent[x] = y;
				return y;
			}
		}

		// 합집합 = 부모 노드를 하나로 통일
		static void union(int x, int y) {
			x = find(x);
			y = find(y);
			if(x!=y) {
				if(x<y) {
					parent[y] = x;
				}else {
					parent[x] = y;
				}
			}
		}

		// 같은 집합인지 확인
		static boolean isSame(int x, int y) {
			return find(x) == find(y);
		}
}
```
<br><br>



<br/><br/>
