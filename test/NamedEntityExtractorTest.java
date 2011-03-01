import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NamedEntityExtractorTest {

    @Test
    public void shouldFindName() throws IOException {
        List<NamedEntity> namedEntities = new NamedEntityExtractor(NamedEntityType.PERSON).findNamedEntities("Ryan went for a trip. to Vegas and meet. Toni");

        assertThat(namedEntities.get(0).value, is("Ryan"));
        assertThat(namedEntities.get(0).type, is(NamedEntityType.PERSON));

    }

    @Test
    public void shouldFindNameInKeyword() throws IOException {
        List<NamedEntity> namedEntities = new NamedEntityExtractor(NamedEntityType.PERSON).findNamedEntities("Ryan");

        assertThat(namedEntities.get(0).value, is("Ryan"));
        assertThat(namedEntities.get(0).type, is(NamedEntityType.PERSON));

    }

    @Test
    public void shouldFindLocationInKeyword() throws Exception {
        List<NamedEntity> namedEntities = new NamedEntityExtractor(NamedEntityType.LOCATION).findNamedEntities("Restaurant London");
        assertThat(namedEntities.get(0).value, is("London"));
    }

    @Test
    public void shouldFindLocationInKeyword2() throws Exception {
        List<NamedEntity> namedEntities = new NamedEntityExtractor(NamedEntityType.LOCATION).findNamedEntities("restaurant Camden Town");
        assertThat(namedEntities.get(0).value, is("Camden Town"));
    }

    @Test
    public void shouldFindLowerCaseNameInKeyword() throws Exception {
        List<NamedEntity> namedEntities = new NamedEntityExtractor(NamedEntityType.PERSON).findNamedEntities("John Lennon autograph");
        assertThat(namedEntities.get(0).value, is("John Lennon"));
        assertThat(namedEntities.get(0).type, is(NamedEntityType.PERSON));
    }


    @Test
    @Ignore
    public void shouldUnderstandLocationAndCommercialPlaces() throws Exception {
        List<NamedEntity> namedEntities = new NamedEntityExtractor(NamedEntityType.LOCATION, NamedEntityType.COMMERCIAL_PLACE).findNamedEntities("restaurant london");
        assertThat(namedEntities.get(0).value, is("Camden"));

    }


}
