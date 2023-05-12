package items;

public enum Core {
    PHOENIX_FEATHER("Phoenix feather"),
    DRAGON_HEARTSTRING("Dragon heartstring"),
    UNICORN_HAIR("Unicorn hair");
        private String name;

        Core(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
