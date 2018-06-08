package newTests.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class with auxiliary methods to tests.
 *
 * @author Thaynan Nunes
 */
public class UtilsTest {

    /**
     * Insert file for future tests.
     *
     * @param path File's path.
     * @param fileContent Content to be insert in file.
     * @throws IOException, throws exception if don't write File.
     */
	
	public static final String LINE_SEPARATOR = "\n";
	
    public static void insertFile(String path, String fileContent) throws IOException {
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String[] lines = fileContent.split(", ");
        for(int i=0; i < lines.length; i++) {
            writer.write(lines[i]);
            if(i < lines.length - 1) {
                writer.newLine();
            }
        }
        writer.close();
    }

    /**
     * Deletes the file.
     *
     * @param path, File's path.
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }
}