import java.util.*;

class Solution {
    public int[] solution(String[] operations) {        
        List<Integer> list = new ArrayList<>();
        
        StringTokenizer st;
        for (String o : operations) {
            st = new StringTokenizer(o);
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if (op.equals("I")) {
                list.add(num);
            } else if (op.equals("D")) {
                if (list.size() == 0) continue;
                if (num == 1) {
                    list.remove(list.size() - 1);
                } else if (num == -1) {
                    list.remove(0);
                }
            }
            
            Collections.sort(list);
        }
        
        int[] answer;
        if (list.size() == 0) {
            answer = new int[] { 0, 0 };
        } else {
            answer = new int[] { list.get(list.size() - 1), list.get(0) }; 
        }
        
        return answer;
    }
}
