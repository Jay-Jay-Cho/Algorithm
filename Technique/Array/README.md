# 배열
<br>

## &#10095;&#10095;&#10095; 1차원 배열 시계방향/반시계방향 회전
```java
int[] arr;
int n = arr.length;
int k // 몇번 돌릴건지
int[] copy_arr = arr.clone();

for(int idx=0;idx<size;idx++){
  int new_idx = (i+(size-k)) % size; //시계방향
  int new_idx = (i+k) % size; //반시계방향
  arr[idx] = copy_arr[new_idx];
}
```
<br><br>
