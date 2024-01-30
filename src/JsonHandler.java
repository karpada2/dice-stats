import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;

public class JsonHandler {
    public static void initializeFile(File f) throws FileNotFoundException, Exception {
        if (!f.exists()) {
            throw new Exception("File does not exist!");
        } else if (!f.getName().endsWith(".json")) {
            throw new FileNotFoundException("File path invalid / incorrect file extension!");
        }
        FileWriter fileShitter = new FileWriter(f);
        fileShitter.write("{\n" +
                "   \"tracker\": [\n" +
                "       ]\n" +
                "}");
        fileShitter.flush();
    }
}
