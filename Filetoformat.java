import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Filetoformat 
{
    public static byte[] filetranslate(String sfilename) throws IOException
    {
        File filed = new File(sfilename);
        byte[] data = Files.readAllBytes(filed.toPath());
        int icounter = data.length;
        System.out.println(" " + icounter);
        return data;
    }

    public static void bytetranslate(byte[] enfile, String filepath, String filetype) throws IOException
    {
        if (filetype == "image")
        {
            File nimage = new File(filepath);
            ByteArrayInputStream binput = new ByteArrayInputStream(enfile);
            BufferedImage eimage = ImageIO.read(binput);
            ImageIO.write(eimage, "jpg", nimage);
            System.out.println("Image created ");
        }
        else if (filetype == "txt")
        {
            File nencryptedfile = new File(filepath);
            FileOutputStream fos = null;
            fos = new FileOutputStream(nencryptedfile);
            fos.write(enfile);
            fos.close();
            System.out.println("File encrypted successfully");
        }

    }
}