package back_tracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1987_Alphabet {
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static ArrayList<String> list = new ArrayList<>();
	static int r = 0;
	static int c = 0;
	static int ans = 0;
	
	static void getAnswer(String[][] arr, boolean[][] visited, int x, int y, int cnt) {
		System.out.print(x+", "+y+", cnt= "+cnt+'\n');
		for(int i=0;i<4;i++) {
			int xx = x+dx[i];
			int	yy = y+dy[i];
			if(xx>=0 && xx<r && yy>=0 && yy<c && !visited[xx][yy] && !list.contains(arr[xx][yy])) {
				list.add(arr[xx][yy]);
				visited[xx][yy] = true;
				getAnswer(arr,visited,xx,yy,cnt+1);
				list.remove(arr[xx][yy]);
				visited[xx][yy] = false;
			}
		}
		
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		String[][] arr = new String[r][c];
		boolean[][] visited = new boolean[r][c];
		for(int i=0;i<r;i++) {
			//StringTokenizer st2 = new StringTokenizer(br.readLine());
			String[] str = br.readLine().split("");
			for(int j=0;j<c;j++) {
				arr[i][j] = str[j];
			}
		}
		list.add(arr[0][0]);
		visited[0][0] = true;
		getAnswer(arr,visited,0,0,1);
		//System.out.println(ans);

	}
}
