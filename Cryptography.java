import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
public class Cryptography
{
    public static void main(String[] args)
    {
        System.out.println("Password: ");;
        Scanner input = new Scanner(System.in);
        String sPassword = input.nextLine();
        HashCoding p1 = new HashCoding(sPassword);
        System.out.println("Please enter the filename: ");
        String sfilename = input.nextLine();
        String sfiletype = Fileformat(sfilename);
        System.out.println(p1);
        System.out.println(sfiletype + " " + sfilename);
        try
        {
            byte arrdecrypted[] = Filetoformat.filetranslate("ART.jpg");
           // System.out.println(Arrays.toString(arrdecrypted));
            byte arrencrypted[] = Encrypt.Encode(arrdecrypted, sPassword);
            System.out.println(" ");
          //  System.out.println(Arrays.toString(arrencrypted));
            Filetoformat.bytetranslate(arrencrypted, "output.txt", "txt");
            byte arrdecrypted1[] = Decrypt.Decode(arrencrypted, sPassword);
            Filetoformat.bytetranslate(arrdecrypted1, "ART3.jpg", "image");
        }
        catch (IOException ex)
        {
            System.out.println("File not found");
        }
    }

    public boolean confirmPassword(String sPassword)
    {
        if(sPassword.length() >= 8)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String Fileformat(String sfilename)
    {
        int ilengthoffilename = sfilename.length();
        String sfiletype = "";
        boolean bpastcomma = false;
        for (int icounter = ilengthoffilename - 1 ; icounter > 0 ; icounter--)
        {
            if ((sfilename.charAt(icounter) != '.') && (!bpastcomma))
            {
                sfiletype = sfilename.charAt(icounter) + sfiletype ;
            }
            else
            {
                bpastcomma = true;
            }
        }
        List arrfiletypes = Arrays.asList("jpeg","jpg","png","txt","rar","zip","pdf");
        if (!arrfiletypes.contains(sfiletype))
        {
            sfiletype = "Invalid";
        }
        return sfiletype;
    }
}