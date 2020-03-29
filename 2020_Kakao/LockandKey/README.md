# [자물쇠와 열쇠](https://programmers.co.kr/learn/courses/30/lessons/60059)
* **참고자료** : https://web2eye.tistory.com/185

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
		boolean answer = false;

    	int N = lock.length;
    	int M = key.length;
    	int NM = 2*M+N-2;
    	int[][] new_arr = new int[NM][NM];

    	int a = 0;
    	int b = 0;
    	for(int i=M-1;i<M-1+N;i++) {
    		for(int j=M-1;j<M-1+N;j++) {
    			new_arr[i][j] = lock[a][b];
    			b++;
    		}
    		a++;
    		b = 0;	// mistake
    	}

    	int[][] copy = copy(new_arr); // mistake

    	for(int rotate=0;rotate<4;rotate++) { // mistake

    		int to = NM - M;

    		for(int i=0;i<=to;i++) {
  				for(int j=0;j<=to;j++) {

  					int x = 0;
            int y;
            for (int k = i; k &lt; key.length + i; k++) {
                y = 0;
                for (int l = j; l &lt; key.length + j; l++) {
                    copy[k][l] += key[x][y++];
                }
                x++;
            }

  					if(isFitKeyToLock(copy,N,M)) {
  						return true;
  					}
  					copy = copy(new_arr); // mistake
  				}
  			}
			key = rotateKey(key); // mistake
		}
    	return answer;
    }

    static int[][] copy(int[][] arr){
    	int len = arr.length;
    	int[][] copy_arr = new int[len][len];
    	for(int i=0;i<len;i++) {
    		System.arraycopy(arr[i], 0, copy_arr[i], 0, len);
    	}
    	return copy_arr;
    }

    static boolean isFitKeyToLock(int[][] arr, int N, int M) {
    	boolean check = true;
    	for(int i=M-1;i<M-1+N;i++) {
    		for(int j=M-1;j<M-1+N;j++) {
    			if(arr[i][j] != 1) {
    				check = false;
    				return check;
    			}
    		}
    	}
    	return check;
    }

    static int[][] rotateKey(int[][] key) {
        int keySize = key.length;
        int[][] copyKey = new int[keySize][keySize];

        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                copyKey[i][j] = key[keySize - 1 - j][i];
            }
        }

        for (int i = 0; i < copyKey.length; i++) {
            key[i] = copyKey[i].clone();
        }
        return key;
    }

}

```

<br><br>

## &#10095;&#10095;&#10095; 설명
* Level : 3
* 구현
* 열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법  
##### 1. lock(`N * N`)과 key(`M * M`)를 한번에 비교할 수 있는 확장된 배열을 선언
* 확장된 배열의 한 변의 길이는 `N + 2*(M-1)`
##### 2. 배열 중앙에 lock 배열의 값을 대입
##### 3. 배열의 전체 크기만큼 key 배열을 순회하며, lock 배열의 홈이 다 맞춰져있는지 비교
* key 배열을 더해서, lock 배열이 위치한 중앙부분의 값이 모두 1이면 홈이 다 맞춰졌으므로 true
* 배열의 값이 1이 아니라면, 돌기끼리 만났거나(2), 홈이 채워지지않은(0) 것이므로 false
<br><br>


## &#10095;&#10095;&#10095; 풀이
##### 1. 확장 배열 선언
```java
int N = lock.length;
int M = key.length;
int NM = 2*M+N-2;
int[][] new_arr = new int[NM][NM];
```

##### 2. 확장 배열 중앙에 기존 lock 배열 삽입
* 중앙 lock 배열의 시작 좌표는 key 배열과 겹쳐지는 첫 부분이므로 (`M-1, M-1`)
* 중앙 lock 배열의 종료 좌표는 N 만큼 더해주면 된다 : (`M-1+N, M-1+N`)
* x 좌표가 하나씩 늘어날 때마다, y 좌표를 초기화 시켜준다.
```java
int a = 0;
int b = 0;
for(int i=M-1;i<M-1+N;i++) {
  for(int j=M-1;j<M-1+N;j++) {
    new_arr[i][j] = lock[a][b];
    b++;
  }
  a++;
  b = 0;	// mistake
}
```

##### 3. 반복문마다 확장 배열에 key 배열을 더해줘야 하기 때문에, 반복문 당 초기화를 위해 확장 배열 복사
```java
int[][] copy = copy(new_arr);
```

##### 4. 총 4번의 rotation을 돌면서 lock 배열의 홈이 다 맞춰지는지 확인
```java
for(int rotate=0;rotate<4;rotate++) {

  int to = NM - M;

  // 반복문의 범위는, 확장 배열의 길이 - key 배열의 길이까지
  for(int i=0;i<=to;i++) {
    for(int j=0;j<=to;j++) {

      int x = 0;
      int y;

      for (int k = i; k < key.length + i; k++) {
          y = 0;
          for (int l = j; l < key.length + j; l++) {
              copy[k][l] += key[x][y++];
          }
          x++;
      }

      // lock 배열이 모두 1로만 채워졌는지 확인
      if(isFitKeyToLock(copy,N,M)) {
        return true;
      }

      // 반복문 하나가 끝났으므로, 다음 반복문을 위해 확장 배열 초기화
      copy = copy(new_arr); // mistake
    }
  }

// 키 배열을 시계방향으로 회전  
key = rotateKey(key); // mistake
}
```

<br><br>



## &#10095;&#10095;&#10095; 꿀팁
##### 1. 2차원 배열 복사하기
```java
public int[][] copy(int[][] origin_arr){
    int len = origin_arr.length;
    int[][] copy_arr = new int[len][len];
    for(int i=0;i&lt;len;i++){
      copy_arr[i] = origin_arr[i].clone();
    }
    return copy_arr;
}

```

##### 2. 2차원 배열 회전시키기
```java
public int[][] rotate(int[][] arr){
		int col = arr.length;
		int row = arr[0].length;
		int[][] rotate_arr = new int[row][col];

  	for(int i=0;i<row;i++) {
  		for(int j=0;j<col;j++) {
  			rotate_arr[i][j] = arr[col-j-1][i];
  		}
  	}

    	return rotate_arr;
    }
```


<br><br>


## &#10095;&#10095;&#10095; 실수
##### 1. 확장 배열 중앙에 lock 배열 삽입 시, x 좌표가 늘어날 때마다 y 좌표를 초기화시켜줬어야 한다...
```java
// 실수
for(int i=M-1;i<M-1+N;i++) {
  for(int j=M-1;j<M-1+N;j++) {
    new_arr[i][j] = lock[a][b];
    b++;
  }
  a++;
}

// 정답
for(int i=M-1;i<M-1+N;i++) {
  for(int j=M-1;j<M-1+N;j++) {
    new_arr[i][j] = lock[a][b];
    b++;
  }
  a++;
  b = 0;
}
```

##### 2. [배열 복사 오류](https://vivi-world.tistory.com/27) : 2차원 배열을 복사할 때는, `==` 연산자말고 `clone()` 혹은 `arraycopy()` 메소드를 활용해야 한다.
* `clone()` 혹은 `arraycopy()` 역시, 그냥 사용하면 안되고 각 행마다 수행해야...
```java
// 실수
int[][] copy = new_arr;

// 정답
int[][] copy = copy(new_arr); // mistake
```
```java
static int[][] copy(int[][] arr){
  int len = arr.length;
  int[][] copy_arr = new int[len][len];
  for(int i=0;i<len;i++) {
    System.arraycopy(arr[i], 0, copy_arr[i], 0, len);
    // 혹은 copy_arr[i] = arr[i].clone();
  }
  return copy_arr;
}
```

##### 3. rotaion 반복문을 4번 돌았어야 했는데, 3번만 돌았다...
* 처음, 90도, 180도, 270도

```java
// 실수
for(int rotate=0;rotate<3;rotate++) {
  ...
}

// 실수
for(int rotate=0;rotate<4;rotate++) {
  ...
}
```




<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점




<br>
<br>
<br>
