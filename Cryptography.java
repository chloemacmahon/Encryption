import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.FileChooserUI;
import javax.lang.model.util.ElementScanner6;
import javax.swing.JDialog;
import java.awt.Dialog;
import javax.swing.JOptionPane;
public class Cryptography
{
    public static void main(String[] args)
    {
        boolean brun = true ;
        while (brun)
        {
            Scanner input = new Scanner(System.in);  
            //String sPassword = input.nextLine(); //Receives user's password
            JFrame frame = new JFrame("Cryptography");
            //frame.setVisible(true);
            String sPassword = (String)JOptionPane.showInputDialog(frame,"Please enter your password ",JOptionPane.PLAIN_MESSAGE);
            while (!confirmPassword(sPassword)) //Asks for password until correct password is entered 
            {
                sPassword = (String)JOptionPane.showInputDialog(frame,"Please enter your password ",JOptionPane.PLAIN_MESSAGE);
            }
            JFileChooser opendialog = new JFileChooser();
            opendialog.setCurrentDirectory(new File(System.getProperty("user.home")));
            JDialog jparent = new JDialog();
            int result = opendialog.showOpenDialog(jparent); //Displays open file dialog
            String sfilepath = ""; 
            System.out.println(sPassword);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                sfilepath = opendialog.getSelectedFile().getAbsolutePath(); //Receives filename

                String sfiletype = Fileformat(sfilepath); //Tests filepath
                //Start of user coding 
                System.out.println(sfilepath);
                Object[] options = {"Encryption", "Decryption"};
                JFrame frame1 = new JFrame("Cryptography");
                //frame1.setVisible(true);
                int ichoice = JOptionPane.showOptionDialog(frame1,"Please select either encryption or decyption","Choose",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
                System.out.println(ichoice + "");
                if (ichoice == 0)
                {
                    Encryptprocess(sPassword, sfilepath, sfiletype);
                    JOptionPane.showMessageDialog(frame, "File encoded successfully original file deleted " ,"Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (ichoice == 1)
                {
                    Decryptprocess(sPassword, sfilepath, sfiletype);
                    JOptionPane.showMessageDialog(frame, "File decoded successfully encrypted file deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else 
                {
                    JOptionPane.showMessageDialog(frame, "Decryption or selction was not chosen", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else 
            {
                JOptionPane.showMessageDialog(frame, "File not selected ", "Information", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("File not selected");
            }
            
            JFrame frame2 = new JFrame("Cryptography");
            //frame2.setVisible(true);
            int irepeat = JOptionPane.showConfirmDialog(frame2,"Would you like to decrypt or encrypt another file \n(Keep in mind you'll have to reenter your password for security purposes)","Choose",JOptionPane.YES_NO_OPTION);
            if (irepeat != 0)
            {
                brun = false;
                break;
            }
        }
        
    }

    public static boolean confirmPassword(String sPassword)//Confirms password is strong 
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
        int ilengthoffilename = sfilename.length(); //
        String sfiletype = "";
        boolean bpastcomma = false;
        for (int icounter = ilengthoffilename - 1 ; icounter > 0 ; icounter--) //Finds File type 
        {
            if ((sfilename.charAt(icounter) != '.') && (!bpastcomma)) 
            {
                sfiletype = sfilename.charAt(icounter) + sfiletype ;
            }
            else//Code by C Mac Mahon
            {
                bpastcomma = true;
            }
        }
        return sfiletype; //Returns file type 
    }
    public static void Encryptprocess(String sPassword, String sfilepath, String sfiletype)
    {
        try 
        {
            byte arrdecrypted[] = Filetoformat.filetranslate(sfilepath); //Receives decrypted array
            byte arrencrypted[] = Encrypt.Encode(arrdecrypted, sPassword); //Creates encrypted array
            if (Filetoformat.Deletefile(sfilepath)) //Deletes unencrypted file 
            {
                System.out.println("Original file deleted successfully");
            }
            Filetoformat.bytetranslate(arrencrypted, sfilepath); //Problem maybe
            System.out.println("File encrypted successfully");
            
            
        }
        catch (IOException ex) 
        {
            System.out.println("File not found");
        }
    }

    public static void Decryptprocess(String sPassword, String sfilepath, String sfiletype)
    {
        try 
        {
            byte arrencrypted[] = Filetoformat.filetranslate(sfilepath); //Receives encrypted array
            byte arrdecrypted[] = Decrypt.Decode(arrencrypted, sPassword); //Creates decrypted array
            String snew = new String();
            for (int icounter = 0; icounter < sfilepath.length() ; icounter++)
            {
                snew = snew + sfilepath.charAt(icounter);
            } //Removes the e from filename 
            if (Filetoformat.Deletefile(sfilepath)) //Deletes unencrypted file 
            {
                System.out.println("Encrypted file deleted successfully");
            }
            Filetoformat.bytetranslate(arrdecrypted, snew); //Problem maybe
            System.out.println("File decrypted successfully");
            JFrame frame1 = new JFrame("Cryptography");
            System.out.println(sfiletype);
            //if ((sfiletype == "pdf") || (sfiletype == "txt") || (sfiletype == "jpeg") || (sfiletype == "png") || (sfiletype == "jpg"))
            //{
                int ichoice = JOptionPane.showOptionDialog(frame1, "Would you like to open the file \n(only applicable to files that your computer \nhas the software to open) ", "Open decrypted file", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0); //Asks if user wants to open decrypted file 
                if (ichoice == 0)
                {
                    Filetoformat.displayfile(sfilepath); //Displays decrypted file 
                }
           // }
        }
        catch (IOException ex)
        {
            System.out.println("File not found");
        }
    }
}