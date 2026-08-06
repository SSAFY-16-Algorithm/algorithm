import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MY_S2806 {
	static BufferedReader br;
	static StringBuilder sb;

	static int N; // 행렬 크기, 퀸의 개수
	static int[] queenCol; // 각 row별 퀸의 col 위치를 저장
	static int answer;

	public static void main(String args[]) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		System.setIn(new FileInputStream("sample_input.txt"));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			queenCol = new int[N];
			answer = 0;

			// 첫 행부터 퀸 놓기
			dfs(0);
			sb.append('#').append(test_case).append(" ").append(answer);
		}
	}

	public static void dfs(int row) {
		// 행 별로 퀸은 하나만 배치할 수 있기 때문에 row만큼만 퀸을 놓을 수 있는지 검사하면 됨
		if (row == N){
			answer++;
			return;
		}

		// 이전 행의 퀸 배치에 따라 충돌 검사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < row; j++) {
				
			}
			// 이번 행에서 놓을 위치를 찾으면 함수 호출
			dfs(row++);
		}

	}
}