import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class NamedEntityExtractor {

    SentenceDetectorME englishSentenceDetector;
    List<NameFinderMEWrapper> nameFinders = new ArrayList<NameFinderMEWrapper>();
    NamedEntityType[] entityTypes;
    String modelsRoot = String.format("%s/models/", System.getProperty("user.dir"));

    NamedEntityExtractor(NamedEntityType... entityTypes) throws IOException {
        this.entityTypes = entityTypes;

        englishSentenceDetector = new SentenceDetectorME(new SentenceModel(new FileInputStream(String.format("%senglish.bin.gz", modelsRoot))));

        for (NamedEntityType entityType : entityTypes) {
            String model = String.format("%s%s.bin.gz", modelsRoot, entityType.name());
            NameFinderME nameFinderME = new NameFinderME(new TokenNameFinderModel(new DataInputStream(new FileInputStream(model))));

            nameFinders.add(new NameFinderMEWrapper(nameFinderME, entityType));
        }
    }

    void findNamesInSentence(List<NamedEntity> entities, String[] tokens, NameFinderME finder, NamedEntityType type) {

        Span[] nameSpans = finder.find(tokens);
        if (nameSpans == null || nameSpans.length == 0)
            return;
        for (Span span : nameSpans) {
            StringBuilder buf = new StringBuilder();
            for (int i = span.getStart(); i < span.getEnd(); i++) {
                buf.append(tokens[i]);
                if (i < span.getEnd() - 1) buf.append(" ");
            }

            entities.add(new NamedEntity(type, buf.toString()));
        }
    }

    List<NamedEntity> findNamedEntities(String text) {
        List<NamedEntity> entities = new ArrayList<NamedEntity>();
        String[] sentences = englishSentenceDetector.sentDetect(text);
        opennlp.tools.tokenize.Tokenizer tokenizer = new SimpleTokenizer();

        for (NameFinderMEWrapper nameFinder : nameFinders) {
            for (String sentence : sentences) {
                String[] tokens = tokenizer.tokenize(sentence);


                findNamesInSentence(entities, tokens, nameFinder.nameFinderME, nameFinder.entityType);
            }
        }

        return entities;
    }
}

