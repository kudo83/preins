/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author kudo
 */
public class FilesHelper {

    public static void copyFile(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        //overwrite the destination file if it exists, and copy
        // the file attributes, including the rwx permissions
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(FROM, TO, options);
    }

    public static void savePhoto(String crntImage, String filepath) throws FileNotFoundException, IOException {

    byte[] data = Base64.decodeBase64(crntImage);
    try (OutputStream stream = new FileOutputStream(filepath)

    
        ) {
    stream.write(data);
    }
}

//    public static void copyFile(String fileName, InputStream in, String destination) {
//        try {
//
//            // write the inputStream to a FileOutputStream
//            OutputStream out = new FileOutputStream(new File(destination + fileName),false);
//
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = in.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//
//            in.close();
//            out.flush();
//            out.close();
//
//            System.out.println("New file created!");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//  
public static String getFileExtension(String fileName) {
        String extension = "";
        try {

            extension = fileName.substring(fileName.lastIndexOf("."));

        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

}
