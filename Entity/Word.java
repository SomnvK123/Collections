package Entity;

import java.util.*;

public class Word {
    private String meaning;
    private Set<String> synonyms;

    public Word(String meaning) {
        this.meaning = meaning;
        this.synonyms = new LinkedHashSet<>();
    }

    public String getMeaning() {
        return meaning;
    }

    public Set<String> getSynonyms() {
        return synonyms == null ? new LinkedHashSet<>() : synonyms;
    }

    public void addSynonym(String synonym) {
        synonyms.add(synonym);
    }
}
