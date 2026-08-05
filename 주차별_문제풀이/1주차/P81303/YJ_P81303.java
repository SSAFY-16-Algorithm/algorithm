import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        
        next[n - 1] = -1;
        
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] deleted = new boolean[n];
        
        int cur = k;
        
        for (String str : cmd) {
            StringTokenizer st = new StringTokenizer(str);
            String op = st.nextToken();
            
            if (op.equals("U")) {
                int x = Integer.parseInt(st.nextToken());
                while (x-- > 0) cur = prev[cur];
            } else if (op.equals("D")) {
                int x = Integer.parseInt(st.nextToken());
                while (x-- > 0) cur = next[cur];
            } else if (op.equals("C")) {
                dq.push(cur);
                deleted[cur] = true;
                
                int pr = prev[cur];
                int nr = next[cur];
                
                if (pr != -1) next[pr] = nr;
                if (nr != -1) prev[nr] = pr;
                
                cur = (nr != -1) ? nr : pr;
            } else if (op.equals("Z")) {
                int now = dq.pop();
                deleted[now] = false;
                
                int pr = prev[now];
                int nr = next[now];
                
                if (pr != -1) next[pr] = now;
                if (nr != -1) prev[nr] = now;
            }
        }

	    for (int i = 0; i < n; i++) {
	    	sb.append(deleted[i] ? "X" : "O");
	    }
        
        return sb.toString();
    }
}
