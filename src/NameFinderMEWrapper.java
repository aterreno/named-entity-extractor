import opennlp.tools.namefind.NameFinderME;

public class NameFinderMEWrapper {

    NameFinderME nameFinderME;
    NamedEntityType entityType;

    public NameFinderMEWrapper(NameFinderME nameFinderME, NamedEntityType entityType) {

        this.nameFinderME = nameFinderME;
        this.entityType = entityType;
    }
}
