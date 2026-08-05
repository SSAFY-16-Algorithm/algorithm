import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotalPlay = new HashMap<>();
        Map<String, List<Integer>> genreMusics = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genreTotalPlay.put(genres[i], genreTotalPlay.getOrDefault(genres[i], 0) + plays[i]);
            genreMusics.putIfAbsent(genres[i], new ArrayList<>());
            genreMusics.get(genres[i]).add(i);
        }
        
        List<String> sortedGenres = new ArrayList<>(genreTotalPlay.keySet());
        sortedGenres.sort((g1, g2) -> genreTotalPlay.get(g2).compareTo(genreTotalPlay.get(g1)));
        
        List<Integer> bestAlbum = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<Integer> musics = genreMusics.get(genre);
            musics.sort((m1, m2) -> {
                int playCompare = plays[m2] - plays[m1];
                if (playCompare == 0)
                    return m1 - m2;
                return playCompare;
            });
            
            bestAlbum.add(musics.get(0));
            
            if (musics.size() > 1)
                bestAlbum.add(musics.get(1));
        }
        
        return bestAlbum.stream().mapToInt(i -> i).toArray();
    }
}
