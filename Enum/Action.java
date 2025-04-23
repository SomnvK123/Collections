package Enum;

public enum Action {
    ADD_WORD(1, "ADD_WORD"),
    ADD_SYNONYM(2, "ADD_SYNONYM"),
    SEARCH_WORD(3, "SEARCH_WORD"),
    LIST_WORDS(4, "LIST_WORDS"),
    LIST_HISTORY(5, "LIST_HISTORY"),
    SEARCH_WITH_PREFIX(6, "SEARCH_WITH_PREFIX"),
    EXIT(0, "EXIT");

    private final int id;
    private final String name;

    Action(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Action getAction(int id) {
        for (Action action : Action.values()) {
            if (action.getId() == id) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid action ID: " + id);
    }
}