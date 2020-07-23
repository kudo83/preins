/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.helper;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javaxt.io.Image;
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
        try (OutputStream stream = new FileOutputStream(filepath)) {
            stream.write(data);
        }
    }

    public static void saveInputStreamPhoto(InputStream is, String filepath, String extension)
            throws IOException {

        BufferedImage bufferedImage = ImageIO.read(is);
        
        bufferedImage = ImageHelper.resize(bufferedImage, 342,400);
//        Image image = new Image(bufferedImage);
//        System.out.println("width =" + image.getWidth() + "         " + "height=" + image.getHeight());
        
        
//        if (image.getWidth() > image.getHeight()) {
//            image.rotate(0);
//            image.rotateCounterClockwise();
//        } else {
//            image.resize(342, 400);
//        }
//
//        image.resize(image.getWidth()/2, image.getHeight()/2);
//        //ça ne veux rien dire mais c'est comme ça que ça marche!!
////        image.rotate(0);
////        image.rotateCounterClockwise();
////        bufferedImage = =  image.getBufferedImage();
////         image = rotateResizeImage(image);
//        byte[] data  = image.getByteArray();
//        try (OutputStream stream = new FileOutputStream(filepath)) {
//            stream.write(data);
//        }
   //     BufferedImage destinationImage = new BufferedImage(image.getHeight(), image.getWidth(), bufferedImage.getType());
        ImageIO.write(bufferedImage, "png", new File(filepath));
    }

    public static void saveCompressedPhoto(InputStream is, String filepath, String extension) throws FileNotFoundException, IOException {

        File compressedImageFile = new File(filepath);

        OutputStream os = new FileOutputStream(compressedImageFile);

        float quality = 0.5f;

        // create a BufferedImage as the result of decoding the supplied InputStream
        BufferedImage image = ImageIO.read(is);
//        image = resize(image, 400, 342);
//        image = rotateImageByDegrees(image, 270);
        image = rotateResizeImage(image);

        // get all image writers for JPG format
        Iterator<ImageWriter> writers = null;
        if (".jpg".equals(extension) || ".jpeg".equals(extension)) {
            writers = ImageIO.getImageWritersByFormatName("jpg");
        } else if (".png".equals(extension)) {
            writers = ImageIO.getImageWritersByFormatName("png");
        }
        if (writers != null && !writers.hasNext()) {
            throw new IllegalStateException("No writers found");
        }

        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        // compress to a given quality
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);

        // appends a complete image stream containing a single image and
        //associated stream and image metadata and thumbnails to the output
        writer.write(null, new IIOImage(image, null, null), param);

        // close all streams
        is.close();
        os.close();
        ios.close();
        writer.dispose();

//        byte[] data = Base64.decodeBase64(crntImage);
//        //   data = resizeImage(data, 322, 322, extension);
//        try (OutputStream stream = new FileOutputStream(filepath)) {
//            stream.write(data);
//        }
    }

    public static String getFileExtension(String fileName) {
        String extension = "";
        try {

            extension = fileName.substring(fileName.lastIndexOf("."));

        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

//    public static byte[] resizeImage(byte[] fileData, int width, int height, String extension) throws IOException {
//
//        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
//        BufferedImage sourceImage = ImageIO.read(in);
//        Image thumbnail = sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
//                thumbnail.getHeight(null),
//                BufferedImage.TYPE_INT_RGB);
////        bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedThumbnail, extension, baos);
//        return baos.toByteArray();
//    }
//    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
//        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
//        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
//
//        Graphics2D g2d = dimg.createGraphics();
//        g2d.drawImage(tmp, 0, 0, null);
//        g2d.dispose();
//
//        return dimg;
//    }
//    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
//
//        double rads = Math.toRadians(angle);
//        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
//        int w = img.getWidth();
//        int h = img.getHeight();
//        int newWidth = (int) Math.floor(w * cos + h * sin);
//        int newHeight = (int) Math.floor(h * cos + w * sin);
//
//        BufferedImage rotated = new BufferedImage(newWidth, newHeight, img.getType());
//        Graphics2D g2d = rotated.createGraphics();
//        AffineTransform at = new AffineTransform();
//        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
//
//        int x = w / 2;
//        int y = h / 2;
//
//        at.rotate(rads, x, y);
//        g2d.setTransform(at);
//        g2d.drawImage(img, 0, 0,null);
//        //g2d.setColor(Color.RED);
//        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
//        g2d.dispose();
//
//        return rotated;
//    }
    public static BufferedImage rotateResizeImage(BufferedImage img) {

        Image image = new Image(img);
        image.rotate(90);
        image.rotateCounterClockwise();
        image.resize(342, 400);
        return image.getBufferedImage();
    }

}
