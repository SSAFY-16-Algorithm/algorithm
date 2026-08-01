import java.io.*;
import java.util.*;

class Solution {
	
    static int N;
    static int[][] board;
	static int[] dr = new int[] { 1, 0, -1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			int num = 1;
			
			for (int i = 0; i < N; i++) board[0][i] = num++;
			int d = -1;
			int nr = 0;
			int nc = N - 1;
			
			for (int i = N - 1; i >= 1; i--) {
				for (int j = 0; j < 2; j++) {
					d = ++d % 4;
					for (int k = i; k >= 1; k--) {
						nr += dr[d];
						nc += dc[d];
						board[nr][nc] = num++;
					}
				}
			}
			
			sb.append('#').append(t).append('\n');
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(board[i][j]).append(' ');
				}
				sb.append('\n');
			}
		}
		
		System.out.println(sb);
	}
}
