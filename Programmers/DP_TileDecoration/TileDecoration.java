package dp;

public class TileDecoration {
	
	static long getAnswer(int N){
        long[] arr = new long[N];
        arr[0] = 1;
        arr[1] = 1;
        for(int i=2;i<N;i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return 2*(2*arr[N-1]+arr[N-2]);
    }

	public static void main(String[] args) {
		int N = 5;
		System.out.println(getAnswer(N));

	}

}
