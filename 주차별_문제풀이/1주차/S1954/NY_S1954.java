package D2;

import java.util.Scanner;
import java.io.*;

class Solution {
	public static void main(String[] args) throws IOException {
		// 빠른 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T, N; // T : 테스트 케이스의 개수
		
		T = Integer.parseInt(br.readLine());
		
		for (int i=1; i<T+1; i++) {
			N = Integer.parseInt(br.readLine());
			bw.write("#" + i + "\n");
			//bw.write(N + "\n");
			
			int[][] snail = new int[N][N];
			
			int[] cx = {0, 1, 0, -1};
			int[] cy = {1, 0, -1, 0};
			
			int tmp = 1;
			int row = 0, col = 0;
			int idx = 0;
			snail[row][col] = tmp++;
			
			while (tmp <= N*N) {
				int next_row = row + cx[idx], next_col = col + cy[idx];

				if (next_row >= 0 && next_row < N && next_col >= 0 && next_col < N && snail[next_row][next_col] == 0) {
					row = next_row;
					col = next_col;
					snail[row][col] = tmp++;
				} else {
					idx = (idx+1) % 4;
				}
			}
			
			for (int x=0; x<N; x++) {
				for (int y=0; y<N; y++) {
					bw.write(snail[x][y] + " ");
				}
				bw.write("\n");
			}
			
		}
		
		bw.flush();
	}
}