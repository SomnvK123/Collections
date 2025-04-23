package Manager;

import Entity.Word;
import java.util.*;

public class Manager {
    private final Map<String, Word> dictionary = new HashMap<>();
    private final Deque<String> history = new ArrayDeque<>();
    private final int MAX_HISTORY = 5;

    public void addWord(String word, String meaning) {
        dictionary.put(word.toLowerCase(), new Word(meaning.toLowerCase()));
        System.out.println("Added word '" + word + "' with meaning '" + meaning + "'.");
    }

    public void addSynonym(String word, String synonym) {
        if (dictionary.containsKey(word.toLowerCase())) { // check word exists
            Word existingWord = dictionary.get(word.toLowerCase()); // get the word object
            existingWord.addSynonym(synonym.toLowerCase()); // add synonym to the word object
            System.out.println("Added synonym '" + synonym + "' to word '" + word + "'.");
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

}
