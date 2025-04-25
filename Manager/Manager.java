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

    // public void suggestWords(String word) {
    // String prefix = word.toLowerCase();
    // SortedSet<String> tail = suggest.tailSet(prefix); // get words starting with
    // prefix
    // List<String> suggestions = new ArrayList<>();

    // for (String words : tail) {
    // if (!words.startsWith(prefix))
    // break;
    // suggestions.add(word);
    // }

    // if (suggestions.isEmpty()) {
    // System.out.println("No suggestions found for '" + word + "'.");
    // } else {
    // System.out.println("Suggestions for '" + word + "': " + suggestions);
    // }
    // }

    public void suggestWords(String prefix) {
        String lowerPrefix = prefix.toLowerCase();
        SortedSet<String> tail = suggest.tailSet(lowerPrefix);
        List<String> result = new ArrayList<>();

        for (String word : tail) {
            if (!word.startsWith(lowerPrefix))
                break;
            result.add(word);
            if (result.size() >= 5)
                break; // Giới hạn số gợi ý
        }

        if (result.isEmpty()) {
            System.out.println("No suggestions for prefix '" + prefix + "'.");
        } else {
            System.out.println("Suggestions for '" + prefix + "': " + result);
        }
    }

}
