package newTests.utils;

import java.io.*;

/**
 * Class with auxiliary methods to tests.
 *
 * @author Thaynan Nunes
 */
public class UtilsTest {

    public static final String LINE_SEPARATOR = "\n";
    public static final String RESOURCES_PATH = "src/newTests/resources/";
    public static final String GRAPHS_PATH = RESOURCES_PATH + "graphs/";
    public static final String LIST_REPRESENTATION_PATH = RESOURCES_PATH + "listRepresentation/";
    public static final String MATRIX_REPRESENTATION_PATH = RESOURCES_PATH + "matrixRepresentation/";

    /**
     * Insert file for future tests.
     *
     * @param path File's path.
     * @param fileContent Content to be insert in file.
     * @throws IOException, throws exception if don't write File.
     */
	
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

    /**
     * Returns the content of file of specified path.
     *
     * @param path Path to the file.
     * @return Content of file.
     */
    public static String getFileContent(String path) {
        try {
            FileReader file = new FileReader(path);
            BufferedReader bfFile = new BufferedReader(file);
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = bfFile.readLine()) != null) {
                fileContent.append(line).append(LINE_SEPARATOR);
            }
            file.close();
            return fileContent.toString().trim();
        } catch (IOException e) {
            System.err.println("There was an error opening the file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}