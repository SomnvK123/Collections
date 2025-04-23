package Entity;

import java.util.*;

public class Word {
    private String meaning;
    private List<String> synonyms;

    public Word(String meaning) {
        this.meaning = meaning;
        this.synonyms = new ArrayList<>();
    }

    public String getMeaning() {
        return meaning;
    }

    public List<String> getSynonyms() {
        return synonyms == null ? new ArrayList<>() : synonyms;
    }

    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }
}
