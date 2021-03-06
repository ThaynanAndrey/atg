package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphReader {

    private static String fileLine = "";

    public static RawGraph read(final String graphFilePath) {
        File file = new File(graphFilePath);
        BufferedReader br;
        List<String> fileLines = new ArrayList<String>();
		try {
            br = new BufferedReader(new FileReader(file));
            while ((fileLine = br.readLine()) != null) {
            	fileLines.add(fileLine);
            }  
            br.close();
		} catch (IOException e) {
			e.printStackTrace();
        }     
                
        return new RawGraph(fileLines); 
    }
}