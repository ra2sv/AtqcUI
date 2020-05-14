package enums;

public enum RanorexGender {
    MALE("Male"),
    FEMALE("Female");

    private final String value;

    private RanorexGender(String gender) {
        this.value = gender;
    }

    public String getValue() {
        return value;
    }
}
