import java.util.*;

class Solution {
    
    /**
    * Map을 요리조리 잘 사용하는 문제
    * 
    * 1. Map 두 개 선언
    * - <장르, 노래 재생 시간 합>
    * - <장르, <고유 번호, 노래 재생 시간>
    * 
    * 2. genres, plays 배열 순회하면서 Map 두 개에 <Key, Value> 넣어주기
    * 3. totalTimeMap을 value 기준 내림차순으로 정렬
    * 4. totalTimeMap을 순회하면서 각 장르 별 노래 목록 정렬
    * 5. 노래 목록에서 2개만 추출해서 answer에 저장 (2개 미만이면 개수만큼 저장)
    * 
    */
    public List<Integer> solution(String[] genres, int[] plays) {
        Map<String, Integer> totalTimeMap = new HashMap<>();
        Map<String, Map<Integer, Integer>> songMap = new HashMap<>();
        int N = genres.length;
        
        for (int i = 0; i < N; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            totalTimeMap.put(genre, totalTimeMap.getOrDefault(genre, 0) + play);
            songMap.putIfAbsent(genre, new HashMap<>());
            songMap.get(genre).put(i, play);
        }
        
        List<Integer> answer = new ArrayList<>();

        List<Map.Entry<String, Integer>> list = new ArrayList<>(totalTimeMap.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        
        for (int i = 0; i < list.size(); i++) {
            int num = 0;
            String genre = list.get(i).getKey();
            
            Map<Integer, Integer> map = songMap.get(genre);
            List<Map.Entry<Integer, Integer>> list2 = new ArrayList<>(map.entrySet());
            list2.sort((o1, o2) -> {
                if (o1.getValue() != o2.getValue()) {
                    return o2.getValue() - o1.getValue();
                }
                
                return o1.getKey() - o2.getKey();
            });
            
            for (int j = 0; j < list2.size(); j++) {
                if (num == 2) break;
                answer.add(list2.get(j).getKey());
                num++;
            }
        }
        
        return answer;
    }
}
