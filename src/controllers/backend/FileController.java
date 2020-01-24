package controllers.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class FileController {

    public JSONObject readJSON(String path) throws FileNotFoundException, IOException{
        FileReader fileReader = new FileReader(new File(path));
        BufferedReader bufferReader = new BufferedReader(fileReader);
        
        String line;
        do {            
            line = bufferReader.readLine();
            System.out.println(line);
        } while (line != null);
        
        bufferReader.close();
        fileReader.close();
        
        return new JSONObject(line);
    }
    
    public void writeJSON(String path, String content) throws IOException{
        FileWriter fileWriter = new FileWriter(new File(path));
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
        
        bufferWriter.write(content);
        
        bufferWriter.flush();
        
        bufferWriter.close();
        fileWriter.close();
    }
    
}
