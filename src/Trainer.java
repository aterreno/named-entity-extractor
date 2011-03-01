import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

public class Trainer {

    static String root = String.format("%s/train/", System.getProperty("user.dir"));

    public static void main(String[] args) throws IOException {
        ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(root + "locations.train"), "UTF-8");
        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

        System.out.println("lineStream = " + lineStream);

        TokenNameFinderModel model = NameFinderME.train("en", "location", sampleStream, Collections.<String, Object>emptyMap(), 1, 0);

        BufferedOutputStream modelOut = null;
        try {
            modelOut = new BufferedOutputStream(new FileOutputStream(root + "lovercase-locations.bin"));
            model.serialize(modelOut);
        } finally {
            if (modelOut != null)
                modelOut.close();
        }
    }
}


