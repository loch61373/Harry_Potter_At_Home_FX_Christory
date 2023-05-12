package items;

public enum House {
    GRYFFINDOR("Gryffindor"),
    SLYTHERIN("Slytherin"),
    HUFFLEPUFF("Hufflepuff"),
    RAVENCLAW("Ravenclaw");

    private String name;

    House(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
