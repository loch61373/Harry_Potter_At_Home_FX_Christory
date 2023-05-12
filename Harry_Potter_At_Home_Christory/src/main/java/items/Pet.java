package items;

public enum Pet {
    OWL("Owl"), RAT("Rat"), CAT("Cat"), TOAD("Toad");

    private String name;

    Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
