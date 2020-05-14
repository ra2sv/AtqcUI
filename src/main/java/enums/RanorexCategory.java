package enums;

public enum RanorexCategory {
    MUSIC("Music"),
    MOVIE("Movie"),
    OTHER("Other");

    private final String value;

    private RanorexCategory(String category) {
        this.value = category;
    }

    public String getValue() {
        return value;
    }

}