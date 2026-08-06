import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			
			int[][] snail = new int[N][N];
			
			int dir = 0;
			
			int currentX = 0;
			int currentY = 0;
			
			for (int i = 1; i <= N * N; i++) {
				snail[currentY][currentX] = i;
				
				if (i == N * N)
					break;
				
				int nextX = currentX + dx[dir];
				int nextY = currentY + dy[dir];

        // 벽을 만나면 회전
				if (nextX >= N || nextY >= N || nextX < 0 || nextY < 0 || snail[nextY][nextX] != 0)
					dir = (dir + 1) % 4;

        // 직진
				currentX += dx[dir];
				currentY += dy[dir];
			}
			
			StringBuilder sb = new StringBuilder("#").append(test_case).append("\n");
			
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					sb.append(snail[y][x]).append(" ");
				}
				sb.append("\n");
			}
			
			bw.write(sb.toString());
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
