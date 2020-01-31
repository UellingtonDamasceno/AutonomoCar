package controllers.backend;

import model.exceptions.FileNameIsEmptyException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.json.JSONObject;

/**
 *
 * @author uellington
 */
public class FileController {

    public JSONObject readJSON(String path) throws FileNotFoundException, IOException {
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

    public void writeJSON(String path, String content) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

        bufferWriter.write(content);

        bufferWriter.flush();

        bufferWriter.close();
        fileWriter.close();
    }

    public void writerObject(String path, Serializable object) throws FileNotFoundException, IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutputStream);

        objectOutput.writeObject(object);
        
        objectOutput.flush();
        objectOutput.close();
        fileOutputStream.close();
    }

    public Object readObject(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);

        Object object = objectInput.readObject();

        objectInput.close();
        fileInputStream.close();

        return object;
    }

    public String[] getAllFilesInDirectory(String directoryPath, String fileExtension) throws FileNameIsEmptyException {
        if (fileExtension == null || fileExtension.isEmpty()) {
            throw new FileNameIsEmptyException();
        } else {
            FilenameFilter fileFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(fileExtension);
                }
            }; 
            File directory = (directoryPath.isEmpty()) ? new File("").getAbsoluteFile() : new File(directoryPath);
            return directory.list(fileFilter);
        }

    }
}
