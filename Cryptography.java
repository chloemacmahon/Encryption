import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.FileChooserUI;
import javax.swing.JDialog;
import java.awt.Dialog;
import javax.swing.JOptionPane;
public class Cryptography
{
    public static void main(String[] args)
    {
        System.out.println("Password: "); //Promps user for user password 
        Scanner input = new Scanner(System.in);  
        //String sPassword = input.nextLine(); //Receives user's password
        JFrame frame = new JFrame("Cryptography");
        frame.setVisible(true);
        String sPassword = (String)JOptionPane.showInputDialog(frame,"Please enter your password ",JOptionPane.PLAIN_MESSAGE);
        JFileChooser opendialog = new JFileChooser();
        opendialog.setCurrentDirectory(new File(System.getProperty("user.home")));
        JDialog jparent = new JDialog();
        jparent.setModal(true);
        jparent.setVisible(true);
        int result = opendialog.showOpenDialog(jparent); //Displays open file dialog
        String sfilepath = ""; 
        if (result == JFileChooser.APPROVE_OPTION)
        {
            sfilepath = opendialog.getName(opendialog.getSelectedFile()); //Receives filename
        }
        else 
        {
            System.out.println("File not selected");
        }
        String sfiletype = Fileformat(sfilepath); //Tests filepath
        //Start of user coding 
        System.out.println(sfilepath);
        Object[] options = {"Encryption", "Decryption"};
        JFrame frame1 = new JFrame("Cryptography");
        frame1.setVisible(true);
        int ichoice = JOptionPane.showOptionDialog(frame1,"Please select either encryption or decyption","Choose",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
        System.out.println(ichoice + "");
        if (ichoice == 0)
        {
            Encryptprocess(sPassword, sfilepath, sfiletype);
        }
        else
        {
            Decryptprocess(sPassword, sfilepath, sfiletype);
        }
        
    }

    public boolean confirmPassword(String sPassword)//Confirms password is strong 
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
            Filetoformat.bytetranslate(arrencrypted, "e" + sfilepath); //Problem maybe
            System.out.println("File encrypted successfully");
            if (Filetoformat.Deletefile(sfilepath)) //Deletes unencrypted file 
            {
                System.out.println("Original file deleted successfully");
            }
            
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
            for (int icounter = 1; icounter < sfilepath.length() ; icounter++)
            {
                snew = snew + sfilepath.charAt(icounter);
            } //Removes the e from filename 
            Filetoformat.bytetranslate(arrdecrypted, snew); //Problem maybe
            System.out.println("File decrypted successfully");
            if (Filetoformat.Deletefile(sfilepath)) //Deletes unencrypted file 
            {
                System.out.println("Encrypted file deleted successfully");
            }
        }
        catch (IOException ex)
        {
            System.out.println("File not found");
        }
    }
}