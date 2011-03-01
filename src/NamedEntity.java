class NamedEntity {
    NamedEntityType type;
    String value;

    @Override
    public String toString(){
        return String.format("Type: %s Value: %s", type, value);
    }
}