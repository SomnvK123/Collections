package Manager;

import Entity.Word;
import java.util.*;

public class Manager {
    private final Map<String, Word> dictionary = new HashMap<>();
    private final Deque<String> history = new ArrayDeque<>();
    private final TreeSet<String> suggest = new TreeSet<>();
    private final int MAX_HISTORY = 5;

    public void addWord(String word, String meaning) {
        String wordd = word.toLowerCase();
        dictionary.put(wordd, new Word(meaning.toLowerCase()));
        suggest.add(wordd); // add word to suggest set
        System.out.println("Added word '" + word + "' with meaning '" + meaning + "'.");
    }

    public void addSynonym(String word, String synonym) {
        if (dictionary.containsKey(word.toLowerCase())) { // check word exists
            Word existingWord = dictionary.get(word.toLowerCase()); // get word object
            System.out.println("Word '" + word + "' found in the dictionary.");
            if (!dictionary.containsKey(synonym.toLowerCase())) { // synonym not in dictionary
                dictionary.put(synonym.toLowerCase(), new Word(existingWord.getMeaning())); // add synonym to dictionary
                System.out.println("Added synonym '" + synonym + "' with meaning '" + existingWord.getMeaning() + "'.");
            } else { // synonym already in dictionary
                dictionary.get(word.toLowerCase()).addSynonym(synonym.toLowerCase()); // add synonym to the word object
                System.out.println("Added synonym '" + synonym + "' to word '" + word + "'.");
            }
        } else {
            System.out.println("Word '" + word + "' not found in the dictionary. Please add it first.");
        }
    }

    public void listWords() {
        if (dictionary.isEmpty()) {
            System.out.println("No words in the dictionary.");
            return;
        }
        System.out.println("Words in the dictionary:");
        for (String word : dictionary.keySet()) {
            System.out.print("Word: " + word + " -> Translation: ");
            System.out.println(dictionary.get(word).getMeaning());
        }
    }

    public void searchWord(String word) {
        if (dictionary.containsKey(word.toLowerCase())) {
            Word existingWord = dictionary.get(word.toLowerCase());
            System.out.println("Word: " + word + " -> Translation: " + existingWord.getMeaning());
            System.out.println("Synonyms: " + existingWord.getSynonyms());
        } else {
            System.out.println("Word '" + word + "' not found in the dictionary.");
            suggestWords(word.toLowerCase()); // suggest words based on the input
        }
    }

    public void addToHistory(String word) {
        if (history.size() == MAX_HISTORY) {
            history.removeFirst();
        }
        history.addLast(word);
    }

    public void listHistory() {
        if (history.isEmpty()) {
            System.out.println("No history available.");
            return;
        }
        System.out.println("History of searched words:");
        for (String word : history) {
            System.out.println(word);
        }
    }

    public void searchWithPrefix(String prefix) {
        List<String> matchingWords = new ArrayList<>();
        for (String word : dictionary.keySet()) {
            if (word.startsWith(prefix.toLowerCase())) {
                matchingWords.add(word);
            }
        }
        if (matchingWords.isEmpty()) {
            System.out.println("No words found with prefix '" + prefix + "'.");
        } else {
            System.out.println("Words with prefix '" + prefix + "': " + matchingWords);
        }
    }

    private int compareWord(String a, String b) {
        int len = Math.min(a.length(), b.length()); // So sánh độ dài của 2 từ
        for (int i = 0; i < len; i++) { // duyet với vòng lặp
            if (a.charAt(i) != b.charAt(i)) { // So sánh từng ký tự của 2 từ với nhau // Nếu khác nhau thì trả về vị trí
                                              // khác nhau
                System.out.println(
                        "The words differ at position " + i + ": '" + a.charAt(i) + "' vs '" + b.charAt(i) + "'.");
                return i;
            }
            System.out.println("The words are the same up to position " + len + ".");
        }
        return len;
    }

    public void suggestWords(String prefix) {
        String prefixx = prefix.toLowerCase(); // Chuẩn hóa prefix thành chữ thường
        int compare = 0; // Biến để lưu vị trí khác nhau giữa prefix và từ trong danh sách gợi ý
        List<String> suggestions = new ArrayList<>();

        for (String word : suggest) {
            int compareResult = compareWord(prefixx, word); // So sánh prefix với từ trong danh sách gợi ý
            if (compareResult > compare) { // Nếu vị trí khác nhau > vị trí hien tại
                suggestions.clear(); // Xóa tất cả các từ trong danh sách gợi ý
                suggestions.add(word); // Thêm vào danh sách gợi ý
                compare = compareResult; // Câp nhật vị trí khác nhau
            } else if (compareResult == compare && compareResult > 0) { // Nếu vị trí khác nhau = vị trí hiện tại && vị
                                                                        // trí khác nhau > 0
                suggestions.add(word); // Thêm vào danh sách gợi ý
            }
        }

        // In kết quả gợi ý
        if (suggestions.isEmpty()) {
            System.out.println("No suggestions for '" + prefix + "'.");
        } else {
            System.out.println("Did you mean: " + suggestions);
        }
    }

}
