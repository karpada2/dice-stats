import java.io.*;

public class JsonHandler {

    private File f;
    public JsonHandler(File f) throws FileNotFoundException {
        this.f = f;
    }

    public File getFile() {
        return this.f;
    }

    public boolean createEmptyFile() throws IOException {
        File temp = new File("database\\");
        temp.mkdirs();
        return f.createNewFile();
    }

    public boolean initializeFile() throws IOException {
        FileWriter fileShitter = new FileWriter(this.f);

    }
}
