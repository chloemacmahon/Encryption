import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.OutputStream;
import java.awt.Desktop;
import java.awt.desktop.*;

public class Filetoformat 
{
    public static byte[] filetranslate(String sfilename) throws IOException
    {
        File filed = new File(sfilename); //Reads file to bytes array 
        byte[] data = Files.readAllBytes(filed.toPath());
        int icounter = data.length;
        System.out.println(" " + icounter);
        return data;
    }
    public static boolean Deletefile(String sfilepath) //Deletes encrypted or original file
    {
        File fdelete = new File(sfilepath); //Code by C Mac Mahon
        return fdelete.delete();
    }
    public static void bytetranslate(byte[] enfile, String sfilepath) throws IOException 
    {
        OutputStream out = new FileOutputStream(sfilepath); //Writes new encrypted or decrypted file 
        out.write(enfile);
        out.close();
        System.out.println("File saved successfully " + sfilepath);
    }
    public static void displayfile(String filepath) throws IOException
    {
        File decryptedfile = new File(filepath);
        if (Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(decryptedfile);
        }
    }
}