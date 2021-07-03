package file;

import java.io.FileWriter;
import java.io.IOException;

public class FileManagement {

    public void fileWrite(String text) {
        try (FileWriter writer = new FileWriter("parser-logs.txt", false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
