# [지형이동](https://programmers.co.kr/learn/courses/30/lessons/62050)
* **참고자료** : https://algorithmstudy-mju.tistory.com/115

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
import java.util.*;
class Solution {
    static int[] parent;

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

	static class Node{
		int x;
		int y;
		int h;
		Node(int x, int y, int h){
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}

	static class Border{
		int num1;
		int num2;
		int diff;
		Border(int num1, int num2, int diff){
			this.num1 = num1;
			this.num2 = num2;
			this.diff = diff;
		}
	}

	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};

	static int solution(int[][] land, int height) {
        int answer = 0;
        int n = land.length;
        int[][] map = new int[n][n];

        ArrayList<Border> list = new ArrayList<Border>();
        // segmentation
        int num = 1;
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		if(map[i][j]==0) {
        			Queue<Node> q = new LinkedList<Node>();
        			q.offer(new Node(i,j,land[i][j]));
        			while(!q.isEmpty()) {
        				Node node = q.poll();
        				map[node.x][node.y] = num;
        				for(int k=0;k<4;k++) {
        					int xx = node.x+dx[k];
        					int yy = node.y+dy[k];
        					if(xx>=0 && xx<n && yy>=0 && yy<n){
        						Node temp_node = new Node(xx,yy,land[xx][yy]);
        						if(map[xx][yy]==0 && Math.abs(node.h-land[xx][yy])<=height) {
        							q.add(temp_node);
        						}
        						if(map[xx][yy]!=0 && Math.abs(node.h-land[xx][yy])>height) {
        							int temp_num = map[xx][yy];
        							int diff = Math.abs(land[node.x][node.y]-land[xx][yy]);
        							if(temp_num!=num) {
        								list.add(new Border(temp_num,num,diff));
        							}
        						}
        					}
        				}
        			}
        			num++;
        		}
        	}
        }

        if(list.size()==0) return 0;

        Collections.sort(list, new Comparator<Border>() {
      			public int compare(Border b1, Border b2) {
      				if(b1.diff<b2.diff) return -1;
      				else if(b1.diff==b2.diff) {
      					if(b1.num1<=b2.num1) return -1;
      					else return 1;
      				}else return 1;
      			}
        });

        parent = new int[num];
        for(int i=1;i<parent.length;i++) {
        	parent[i] = i;
        }

        int cnt = 0;
    		int i = 0;
    		while(cnt<(num-1)-1) {
    			if(!isSame(list.get(i).num1,list.get(i).num2)) {	// MST 미포함 간선이면,
    		      union(list.get(i).num1,list.get(i).num2);	// MST에 포함
    		      cnt++;
    		      answer += list.get(i).diff;
    		    }
    		    i++;
    		}


        return answer;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 4
* BFS, MST(Kruskal)
* 모든 칸을 방문하기 위해 필요한 사다리 설치 비용의 최솟값을 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. BFS를 활용해서 사다리없이 이동할 수 있는 좌표를 그룹핑
#### 2. 그룹핑을 하는 동시에, 경계선에 있는 값들을 List에 집어넣기
#### 3. List에 있는 값들을 정렬 후, MST와 Union-Find를 활용해서 사다리 최소 값 구하기
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. BFS를 활용해서 그룹핑 + 경계선에 있는 값들을 List에 넣기
```java
ArrayList<Border> list = new ArrayList<Border>();

int num = 1;
for(int i=0;i<n;i++) {
  for(int j=0;j<n;j++) {
    if(map[i][j]==0) {
      Queue<Node> q = new LinkedList<Node>();
      q.offer(new Node(i,j,land[i][j]));
      while(!q.isEmpty()) {
        Node node = q.poll();
        map[node.x][node.y] = num;
        for(int k=0;k<4;k++) {
          int xx = node.x+dx[k];
          int yy = node.y+dy[k];
          if(xx>=0 && xx<n && yy>=0 && yy<n){
            Node temp_node = new Node(xx,yy,land[xx][yy]);
            if(map[xx][yy]==0 && Math.abs(node.h-land[xx][yy])<=height) {
              q.add(temp_node);
            }
            // 그룹이 있음에도, 정해진 heigth보다 높은 값들은 List에 넣음
            if(map[xx][yy]!=0 && Math.abs(node.h-land[xx][yy])>height) {
              int temp_num = map[xx][yy];
              int diff = Math.abs(land[node.x][node.y]-land[xx][yy]);
              if(temp_num!=num) {
                // (그룹번호,그룹번호,height차이)
                list.add(new Border(temp_num,num,diff));
              }
            }
          }
        }
      }
      num++;
    }
  }
}
```

#### 2. List 정렬
```java
// diff 오름차순
Collections.sort(list, new Comparator<Border>() {
    public int compare(Border b1, Border b2) {
      if(b1.diff<b2.diff) return -1;
      else if(b1.diff==b2.diff) {
        if(b1.num1<=b2.num1) return -1;
        else return 1;
      }else return 1;
    }
});
```

#### 3. MST(Kruskal)을 활용해서 사다리 비용 최솟값 찾기
```java
parent = new int[num];
for(int i=1;i<parent.length;i++) parent[i] = i;

int cnt = 0;
int i = 0;
while(cnt<(num-1)-1) { // 사다리의 갯수 = 그룹 갯수 - 1
  if(!isSame(list.get(i).num1,list.get(i).num2)) {	// MST 미포함 간선이면,
      union(list.get(i).num1,list.get(i).num2);	// MST에 포함
      cnt++;
      answer += list.get(i).diff;
    }
    i++;
}
```


<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. TC 24~25번 시간초과... 아직 해결못함
#### 2. MST 반복문 조건을 잘못 부여
* 마지막 그룹핑을 하고, num++이 되기 때문에 num-1을 한 이후에 사용했어야 함.
```java
// 실수
while(cnt<num-1) { ... }
// 정답
while(cnt<(num-1)-1) { ... }
```
<br><br>


## &#10095;&#10095;&#10095; 꿀팁
<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
