class NamedEntity {
    public NamedEntityType type;
    public String value;

    public NamedEntity(NamedEntityType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString(){
        return String.format("Type: %s Value: %s", type, value);
    }
}