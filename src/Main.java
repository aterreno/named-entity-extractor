import java.io.IOException;
import java.util.List;

public class Main {
    static List<NamedEntity> namedEntities;

    public static void  main(String[] args) throws IOException {
        namedEntities = new NamedEntityExtractor(NamedEntityType.PERSON).findNamedEntities("Ryan went for a trip. to Vegas and meet. Toni");
        for (NamedEntity namedEntity : namedEntities) {
            System.out.println("namedEntity = " + namedEntity);
        }

        namedEntities = new NamedEntityExtractor(NamedEntityType.DATE).findNamedEntities("Ryan went for a trip in December. A trip to Vegas and meet. Toni on Friday. It was the 1st October 2009");
        for (NamedEntity namedEntity : namedEntities) {
            System.out.println("namedEntity = " + namedEntity);
        }

        namedEntities = new NamedEntityExtractor(NamedEntityType.LOCATION).findNamedEntities("distance from Las Vegas to Los Angeles");
        for (NamedEntity namedEntity : namedEntities) {
            System.out.println("namedEntity = " + namedEntity);
        }

        namedEntities = new NamedEntityExtractor(NamedEntityType.ORGANIZATION).findNamedEntities("Ryan works for Forward Internet Group. He used to work with Toni at ThoughtWorks. They never worked at ACME.");
        for (NamedEntity namedEntity : namedEntities) {
            System.out.println("namedEntity = " + namedEntity);
        }

        namedEntities = new NamedEntityExtractor(NamedEntityType.MONEY).findNamedEntities("Ryan won £10 at the Casino. Carl won $1000 1000$ in Vegas. Toni lost €10. Andy won 50p");
        for (NamedEntity namedEntity : namedEntities) {
            System.out.println("namedEntity = " + namedEntity);
        }

        namedEntities = new NamedEntityExtractor(NamedEntityType.PERCENTAGE).findNamedEntities("Ryan is 10% sure that this is gonna work");
        for (NamedEntity namedEntity : namedEntities) {
            System.out.println("namedEntity = " + namedEntity);
        }

        namedEntities = new NamedEntityExtractor(NamedEntityType.TIME).findNamedEntities("Ryan had a cup of tea at 10:00am. Toni will have a coffea at 14PM. Yesterday It's Tuesday 01/03/2011");
        for (NamedEntity namedEntity : namedEntities) {
            System.out.println("namedEntity = " + namedEntity);
        }

    }
}
